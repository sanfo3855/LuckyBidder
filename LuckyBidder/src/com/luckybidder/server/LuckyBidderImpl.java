package com.luckybidder.server;

import com.luckybidder.client.LuckyBidderService;
import com.luckybidder.shared.FieldVerifier;

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
public ArrayList<Prodotto> getProdotti() {

	dbProdotti = getDBProdotti();

	BTreeMap<Integer, Prodotto> mapProdotti = dbProdotti.getTreeMap("MapProdotti");
	ArrayList<Prodotto> listaProdotti = new ArrayList<Prodotto>();
	Prodotto prodottoSelezionato = new Prodotto();
	
	if(!mapProdotti.isEmpty()){
		for(Map.Entry<Integer, Prodotto> prodotto : mapProdotti.entrySet()){
			
			prodottoSelezionato = prodotto.getValue(); 
			listaProdotti.add(prodottoSelezionato);
			
			
			//Date oggi = Calendar.getInstance().getTime();
			//DATA SCADENZA > DATA ODIERNA
			/*
			if ((prodottoSelezionato.getDataScadenza().after(oggi)) && (prodottoSelezionato.getStato().equals("Asta in corso"))){
				modificaScadenza(prodottoSelezionato, prodottoSelezionato.getIdProdotto());
			} */
			//if (!prodottoSelezionato.getStato().equals("Asta chiusa") && !(prodotto.getValue().getIdProdotto()==-1)) {
				
			//}
		}
	}
	//La lista viene ordinata in ordine di scadenza
	//Collections.sort(listaProdotti);
	return listaProdotti;
}
/*
@Override
public boolean modificaScadenza(Prodotto prodotto, int id) {

	DB dbProd = getDBProdotti();
	BTreeMap<Integer, Prodotto> mapProdotti = dbProd.getTreeMap("MapProdotti");

	DB dbOff = getDBOfferte(); 
	BTreeMap<Integer, Offerta> mapOfferte = dbOff.getTreeMap("MapOfferte");

	Prodotto prodottoModificato = new Prodotto();
	if(!mapProdotti.isEmpty()){
		prodottoModificato = prodotto;
		Offerta offertaMax = getMaxOfferta(prodottoModificato.getIdProdotto());	//Si cerca l'offerta massima per l'oggetto
		if (offertaMax.getPrezzo()>0) {
			oggettoModificato.setVincitore(offertaMax.getUsername());	//Se � presente un offerta, si setta come vincitore l'username 
		}																//dell'utente che ha presentato l'offerta pi� alta
		oggettoModificato.setStato("Asta chiusa");	//L'asta viene chiusa
		mapOggetti.replace(id, oggettoModificato);	//Si aggiorna il valore dell'oggetto
		dbOg.commit();
		return true;
	}
	else{
		return false;
	}
}
*/
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

	public boolean aggiungiCategoria(Categoria categoria, String padre) {
		dbCategorie = getDBCategorie();
		BTreeMap<Integer, Categoria> mapCategorie = dbCategorie.getTreeMap("MapDBCategorie");
		boolean aggiungi= true;
		
		if(!mapCategorie.isEmpty()) {
			for(Map.Entry<Integer, Categoria> categorie : mapCategorie.entrySet()){	
				if (categorie.getValue().getNomeCategoria().equals(categoria.getNomeCategoria())) {
					aggiungi=false;
					System.out.print("Categorie gia esistente");
					break;
				}
			}
		}
		
		if (aggiungi) {
			System.out.println("Devo inserire la categoria");
			int id = (mapCategorie.size()+1);
			boolean catPadreCheck = false;
			Categoria catPadre = null;
			categoria.setId(id);
			
			if(padre==null) {
				System.out.println("Padre null");
				catPadreCheck = true;
			} else {
				System.out.println("Cerco padre...");
				if(!mapCategorie.isEmpty()) {
					for(Map.Entry<Integer, Categoria> categorie: mapCategorie.entrySet()) {
						if(categorie.getValue().getNomeCategoria().equals(padre)) {
							catPadreCheck = true;
							System.out.println("Padre trovato");
							catPadre = categorie.getValue();
							System.out.println(catPadre.toString()+"\n");
						}
					}
				}
			}
			if(catPadreCheck) {
				categoria.setPadre(catPadre);
				mapCategorie.put(id,categoria);
				if(catPadre != null) {
					System.out.println("Padre != null");
					for(Map.Entry<Integer, Categoria> categorie: mapCategorie.entrySet()) {
						if(categorie.getValue().getNomeCategoria().equals(padre)) {
							System.out.println("Padre trovato!!");
							Categoria p = categorie.getValue();
							p.setCategoriaFiglia(categoria);
							
							System.out.println(p.toString()+"\n");
							mapCategorie.remove(p.getId());
							mapCategorie.put(p.getId(), p);
							
						}
					}
				}
			} else {
				System.out.println("Check padre esistente false");
				return false;
			}
			dbCategorie.commit();
			System.out.println("Aggiunta Categoria : " + categoria.toString());
			return true; 	
		}
		else {
			System.out.println("Aggiungi false");
			return false;
		}
			
	}
	
	
	private Categoria processTree(ArrayList<Categoria> list){;
		if(list == null ) {
			return null;
		} else {
			for(Categoria pointed : list) {
				if(pointed.getCategorieFiglie()!=null) {
					processTree(pointed.getCategorieFiglie());
				}
			}
		}
		return null;
	}
	
	public Categoria getCategorie(){
		dbCategorie = getDBCategorie();
		BTreeMap<Integer, Categoria> mapCategorie = dbCategorie.getTreeMap("MapDBCategorie");
		
		
		ArrayList<Categoria> list = new ArrayList<Categoria>();
		if(!mapCategorie.isEmpty()) {
			for(Map.Entry<Integer, Categoria> categoria : mapCategorie.entrySet()) {
				list.add(categoria.getValue());
			}
		}
		//processTree(list);
		return null;		
	}
}
