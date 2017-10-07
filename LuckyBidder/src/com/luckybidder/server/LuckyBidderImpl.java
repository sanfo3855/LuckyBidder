package com.luckybidder.server;

import com.luckybidder.client.GreetingService;
import com.luckybidder.client.LuckyBidderService;
import com.luckybidder.shared.FieldVerifier;

import java.io.File;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mapdb.BTreeMap;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import com.luckybidder.server.*;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.luckybidder.shared.*;
/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class LuckyBidderImpl extends RemoteServiceServlet implements LuckyBidderService {
	
	DB dbUtenti;
	DB dbProdotti;
	DB dbDomande;
	DB dbOfferte;
	DB dbRisposte;
	DB dbCategorie;

	
	public boolean registraUtente(Utente utente) {
		String username = utente.getUsername();
		String nome = utente.getNome();
		String cognome = utente.getCognome();
		String telefono = utente.getTelefono();
		String password = utente.getPassword();
		String email = utente.getEmail();
		String codiceFiscale = utente.getCodiceFiscale();
		String indirizzo = utente.getIndirizzo();
		char sesso = utente.getSesso();
		Date dataNascita = utente.getDataNascita();
		String luogoNascita = utente.getLuogoNascita();
		
		DB dbUtenti = getDBUtenti();
		BTreeMap<String, Utente> mapUtenti = dbUtenti.getTreeMap("MapUtenti");
		
		if(!mapUtenti.containsKey(username)) {
			
			Utente newUtente = new Utente();
			newUtente.setUsername(username); 
			newUtente.setNome(nome);
			newUtente.setCognome(cognome);
			newUtente.setPassword(password);
			newUtente.setEmail(email);
			newUtente.setCodiceFiscale(codiceFiscale); 
			newUtente.setTelefono(telefono); 
			newUtente.setIndirizzo(indirizzo);
			newUtente.setSesso(sesso);
			newUtente.setDataNascita(dataNascita);
			newUtente.setLuogoNascita(luogoNascita);
			
			mapUtenti.put(newUtente.getUsername(), newUtente);
			dbUtenti.commit();
			SESSION(username);
			System.out.println("Registrato Utente: " + newUtente.toString());
			dbUtenti.close();
			return true;
		} else {
			dbUtenti.close();
			System.out.print("Username " + username +" gi� esistente");
			return false;
		}
	}
	
	private String SESSION(String username) {
		HttpServletRequest request = this.getThreadLocalRequest();
		HttpSession session = request.getSession(true);
		session.setAttribute("username", username);
		return session.getAttribute("username").toString();
		
	}

	private DB getDBUtenti() {

		dbUtenti = DBMaker.newFileDB(new File("MapDBUtenti")).closeOnJvmShutdown().make();		
		return dbUtenti;	
	}
	

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}
	
	private DB getDBCategorie() {

		dbCategorie = DBMaker.newFileDB(new File("MapDBCategorie")).closeOnJvmShutdown().make();		
		return dbCategorie;	
	}
	
	@Override
	public boolean aggiungiCategoria(Categoria categoria) {
		int id;
		Categoria padre = categoria.getPadre();
		String nomeCategoria = categoria.getNomeCat();

		DB dbCategorie = getDBCategorie();	//Si istanzia il Database delle categorie

		BTreeMap<Integer, Categoria> mapCategorie = dbCategorie.getTreeMap("MapCategorie");
		boolean aggiungi= true;
		if(!mapCategorie.isEmpty()){
			for(Map.Entry<Integer, Categoria> categorie : mapCategorie.entrySet()){	
				//Si controlla se la nuova categoria sia gi� presente nel Db
				if (categorie.getValue().getNomeCat().equals(nomeCategoria)) {
					//La variabile restituir� false
					aggiungi=false;
					break;
				}
			}
		}
		if (aggiungi) {
			//Se la variabile risulta true, possiamo aggiungere la nuova actegoria nel Db
			id = (mapCategorie.size()+1);	//Si assegna l'id
			Categoria nuovaCategoria = new Categoria(padre, id, nomeCategoria);
			mapCategorie.put(id,nuovaCategoria);	//La nuova categoria vine inserita nel Db
			dbCategorie.commit();
			System.out.println("Registrazione - Categoria : " + nuovaCategoria.toString());
			return true; // registrazione inserita	
		}
		else 			//Se la variabile risulta false, la categoria � gi� presente nel Db e non viene aggiunta
			return false;
	}
}
