package com.luckybidder.client;

import com.luckybidder.shared.Utente;

public class Session {
	
	private static Session instance;
	private Utente sessionUtente;
	
	private Session() {};
	
	public static Session getInstance() {
		if( instance == null ) {
			instance = new Session();
		}
		return instance;
	}
	
	/**
	 * setta utente nella session
	 * 
	 * @param utente
	 */
	public void setSession(Utente utente) {
		if(utente!=null) {
			utente.setPassword(null);
			this.sessionUtente = utente;
		}
	}
	
	/**
	 * 
	 * 
	 * @return Utente in session (se c'è)
	 */
	public Utente getSession() {
		return sessionUtente;
	}
}
