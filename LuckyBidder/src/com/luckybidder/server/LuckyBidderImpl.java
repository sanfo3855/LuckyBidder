package com.luckybidder.server;

import com.luckybidder.client.LuckyBidderService;

import java.io.File;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
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
	
	public Utente loginUtente(String username, String password) {
		dbUtenti = getDBUtenti();
		BTreeMap<String, Utente> mapUtenti = dbUtenti.getTreeMap("MapUtenti");
		if(mapUtenti.containsKey(username)) {
			if(mapUtenti.get(username).getPassword().equals(password)) {
				return mapUtenti.get(username);
			}
		} else if( username.equals("admin") && password.equals("admin")) {
			Utente adminUtente = new Utente();
			adminUtente.setUsername("admin");
			return adminUtente;
		}
		return null;
		
	}
	
	public boolean registraUtente(Utente utente) {
		
		dbUtenti = getDBUtenti();
		BTreeMap<String, Utente> mapUtenti = dbUtenti.getTreeMap("MapUtenti");
		
		if(!mapUtenti.containsKey(utente.getUsername()) && !utente.getUsername().equals("admin") && !utente.getUsername().equals("Admin") ) {
			mapUtenti.put(utente.getUsername(), utente);
			dbUtenti.commit();
			dbUtenti.close();
			System.out.println("Registrato Utente: " + utente.toString());
			return true;
		} else {
			dbUtenti.close();
			System.out.print("Username " + utente.getUsername() +" gi� esistente");

			return false;
		}
	}
	
	public boolean vendiProdotto(Prodotto prodotto) {
			
			dbProdotti = getDBProdotti();
			BTreeMap<Integer, Prodotto> mapProdotti = dbProdotti.getTreeMap("MapProdotti");
			int size = mapProdotti.size();
			int id = size + 1;
			prodotto.setIdProdotto(id);
			mapProdotti.put(id, prodotto);
			dbProdotti.commit();
			dbProdotti.close();
			System.out.println("Prodotto messo in vednita: " + prodotto.toString());
			return true;
	}
	
	@Override
	public Prodotto getProdottoSingolo(int id) {
		dbProdotti = getDBProdotti();
		BTreeMap<Integer, Prodotto> mapProdotti = dbProdotti.getTreeMap("MapProdotti");
		Prodotto prodottoEstratto = new Prodotto();
		if(!mapProdotti.isEmpty()) {
			prodottoEstratto = mapProdotti.get(id);
		}
		return prodottoEstratto;
	}
	
	@Override
	public ArrayList<Prodotto> getProdotti() {
	
		dbProdotti = getDBProdotti();
	
		BTreeMap<Integer, Prodotto> mapProdotti = dbProdotti.getTreeMap("MapProdotti");
		ArrayList<Prodotto> listaProdotti = new ArrayList<Prodotto>();
		if(!mapProdotti.isEmpty()){
			for(Map.Entry<Integer, Prodotto> prodotto : mapProdotti.entrySet()){
				
				Prodotto prodottoEstratto = new Prodotto();
				
				Date controllaData = null;
				prodottoEstratto = prodotto.getValue();
				controllaData = prodottoEstratto.getDataScadenza();
				
				Date dataOggi = Calendar.getInstance().getTime();
				if((controllaData.compareTo(dataOggi)<0)&& (prodottoEstratto.getStato().contentEquals("APERTA"))) {
					modificaScadenza(prodottoEstratto, prodottoEstratto.getIdProdotto());
				}
				if(!prodottoEstratto.getStato().equals("CHIUSA")) {
					listaProdotti.add(prodottoEstratto);
				}
			}
		}
		//La lista viene ordinata in ordine di scadenza
		Collections.sort(listaProdotti);
		return listaProdotti;
	}
	
	@Override
	public boolean modificaScadenza(Prodotto prodotto, int id) {
	
		dbProdotti = getDBProdotti();
		BTreeMap<Integer, Prodotto> mapProdotti = dbProdotti.getTreeMap("MapProdotti");
	
		dbOfferte = getDBOfferte(); 
		BTreeMap<Integer, Offerta> mapOfferte = dbOfferte.getTreeMap("MapOfferte");
	
		Prodotto prodottoModificato = new Prodotto();
		if(!mapProdotti.isEmpty()){
			prodottoModificato = prodotto;
			Offerta offertaMax = getMaxOfferta(prodottoModificato.getIdProdotto());	//Si cerca l'offerta massima per l'oggetto
			if (offertaMax.getPrezzo()>0) {
				//Se � presente un offerta, si setta come vincitore l'username
				//dell'utente che ha presentato l'offerta pi� alta
				prodottoModificato.setVincitore(offertaMax.getUsername()); 
			}
			//L'asta viene chiusa
			prodottoModificato.setStato("CHIUSA");	
			//Si aggiorna il valore dell'oggetto
			mapProdotti.replace(id, prodottoModificato);	
			dbProdotti.commit();
			return true;
		}
		else{
			return false;
		}
	}
	
	@Override
	public Offerta getMaxOfferta(int idProdotto) {
		dbOfferte = getDBOfferte();
		BTreeMap<Integer, Offerta> mapOfferte = dbOfferte.getTreeMap("MapOfferte");
		Offerta offertaMax = new Offerta();
		double prezzoMax = 0;
		if(!mapOfferte.isEmpty()) {
			for(Map.Entry<Integer, Offerta> offerta : mapOfferte.entrySet()){
				//Se l'offerta � riferita all'oggetto, non � stata eliminata ed � maggiore dell' ultimo prezzo massimo
				if (offerta.getValue().getIdProdotto() == idProdotto && offerta.getValue().getPrezzo()>prezzoMax && !(offerta.getValue().getIdProdotto()==-1)){
					offertaMax = offerta.getValue();
					//Si prende come offerta massima
					prezzoMax=offertaMax.getPrezzo();
				}
			}	
		}
		return offertaMax;
	}
	
	@Override
	public boolean offri(Offerta offerta) {
		/*int id;
		int idOggetto = offerta.getIdProdotto();
		String username = offerta.getUsername();
		double prezzo = offerta.getPrezzo();	
		Date date = offerta.getDataOfferta();*/
		dbOfferte = getDBOfferte();
		BTreeMap<Integer, Offerta> mapOfferte = dbOfferte.getTreeMap("MapOfferte");
			int id=(mapOfferte.size()+1);
			offerta.setIdOfferta(id);
			//Offerta nuovaOfferta = new Offerta(id,idOggetto,username,prezzo,date);
			mapOfferte.put(id, offerta);
			dbOfferte.commit();
			System.out.println("Inserimento offerta: "+ offerta.toString());
			return true;
	}
	
	@Override
	public ArrayList<Offerta> getOfferte(int idProdotto){
		
		dbOfferte=getDBOfferte();
		BTreeMap<Integer,Offerta> mapOfferte = dbOfferte.getTreeMap("MapOfferte");
		ArrayList<Offerta> listaOfferte = new ArrayList<Offerta>();
		if(!mapOfferte.isEmpty()) {
			for(Map.Entry<Integer, Offerta> offerta : mapOfferte.entrySet()) {
				if(offerta.getValue().getIdProdotto() == idProdotto && !(offerta.getValue().getIdProdotto()==-1)) {
					Offerta offertaEstratta = new Offerta();
					offertaEstratta = offerta.getValue();
					listaOfferte.add(offertaEstratta);
				}
			}
		}
		return listaOfferte;
	}


	private DB getDBUtenti() {

		dbUtenti = DBMaker.newFileDB(new File("MapDBUtenti")).closeOnJvmShutdown().make();		
		return dbUtenti;	
	}
	
	private DB getDBOfferte() {

		dbOfferte = DBMaker.newFileDB(new File("MapDBOfferte")).closeOnJvmShutdown().make();		
		return dbOfferte;	
	}
	
	private DB getDBProdotti() {

		dbProdotti = DBMaker.newFileDB(new File("MapDBProdotti")).closeOnJvmShutdown().make();		
		return dbProdotti;	
	}
	
	//prendo i prodotti messi in vendita per ogni utente
	public ArrayList<Prodotto> getProdottiVenduti(String username) {
		
		dbProdotti = getDBProdotti();
		BTreeMap<Integer, Prodotto> mapProdotti = dbProdotti.getTreeMap("MapProdotti");
		Prodotto prodottoEstratto = new Prodotto();
		ArrayList<Prodotto> listaProdotti= new ArrayList<Prodotto>();
		if(!mapProdotti.isEmpty()) {
			for(Map.Entry<Integer, Prodotto> prodotto : mapProdotti.entrySet()) {
				if(prodotto.getValue().getUsername().equals(username) && !(prodotto.getValue().getIdProdotto()==-1)) {
					prodottoEstratto = prodotto.getValue();
					listaProdotti.add(prodottoEstratto);
				}
			}
			
		}
		return listaProdotti;
	}
	
	//prendo le offerte fatte per ogni utente
		public ArrayList<Offerta> getOfferteFatte(String username) {
			
			dbOfferte = getDBOfferte();
			
			BTreeMap<Integer, Offerta> mapOfferte = dbOfferte.getTreeMap("MapOfferte");
			Offerta offertaFatta = new Offerta();
			ArrayList<Offerta> listaOfferte= new ArrayList<Offerta>();
			if(!mapOfferte.isEmpty()) {
				for(Map.Entry<Integer, Offerta> offerta : mapOfferte.entrySet()){
					if(offerta.getValue().getUsername().equals(username) && !(offerta.getValue().getIdProdotto()==-1)) {
						offertaFatta = offerta.getValue();
						listaOfferte.add(offertaFatta);
					}
				}
				
			}
			return listaOfferte;
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
		String nomeCategoria = categoria.getNomeCategoria();

		DB dbCategorie = getDBCategorie();	

		BTreeMap<Integer, Categoria> mapCategorie = dbCategorie.getTreeMap("MapCategorie");
		boolean aggiungi= true;
		if(!mapCategorie.isEmpty()){
			for(Map.Entry<Integer, Categoria> categorie : mapCategorie.entrySet()){	
				
				if (categorie.getValue().getNomeCategoria().equals(nomeCategoria)) {
					
					aggiungi=false;
					break;
				}
			}
		}
		if (aggiungi) {
			
			id = (mapCategorie.size()+1);	
			Categoria nuovaCategoria = new Categoria(padre, id, nomeCategoria);
			mapCategorie.put(id,nuovaCategoria);	
			dbCategorie.commit();
			System.out.println("Aggiunta Categoria : " + nuovaCategoria.toString());
			return true; 	
		}
		else 			
			return false;
	}
}
