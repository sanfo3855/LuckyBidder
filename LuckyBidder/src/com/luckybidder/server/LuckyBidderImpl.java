package com.luckybidder.server;

import com.luckybidder.client.GreetingService;
import com.luckybidder.client.LuckyBidderService;
import com.luckybidder.shared.FieldVerifier;

import java.io.File;
import java.util.Date;

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
			System.out.print("Username " + utente.getUsername() +" già esistente");
			return false;
		}
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


	
}
