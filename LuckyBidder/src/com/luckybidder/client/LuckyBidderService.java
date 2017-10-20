package com.luckybidder.client;

import com.luckybidder.shared.*;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("luckybidderservice")
public interface LuckyBidderService extends RemoteService{
	
	public static class Util{
		private static LuckyBidderServiceAsync instance;
		
		public static LuckyBidderServiceAsync getInstance() {
			if (instance == null) {
				instance = GWT.create(LuckyBidderService.class);
			}
			return instance;
		}
		
	}
	
	boolean registraUtente(Utente utente);

	Utente loginUtente(String username, String password);
	
	ArrayList<Prodotto> getProdottiVenduti(String username);
	
	ArrayList<Offerta> getOfferteFatte(String username);

	boolean vendiProdotto(Prodotto prodotto);
	
	boolean modificaScadenza(Prodotto prodotto, int id);
	
	Offerta getMaxOfferta(int id);

	ArrayList<Prodotto> getProdotti();

	boolean aggiungiCategoria(Categoria categoria, String padre);
	
	Categoria getCategoria(int idCategoria);
	
	ArrayList<Categoria> getAllCategorie();
	
	boolean modificaCategoria(String nomeCategoria, String nomeNuovo);

	Prodotto getProdottoSingolo(int id);
	
	boolean offri(Offerta offerta);
	
	ArrayList <Offerta> getOfferte(int idProdotto);

	Domanda getDomanda(String username, int id);

	boolean mandaDomanda(String nomeProdotto, String testoDomanda, String username, int id, String usernameVenditore);

	Risposta getRisposta(int idDomanda);

	ArrayList<Domanda> getDomandeToUsername(String usernameVendProdotto);

	boolean inviaRisposta(int idDomanda, String text);
	
	boolean eliminaProdotto(int idProdotto);

	ArrayList<Offerta> getAllOfferte();

	boolean eliminaOfferta(int idOfferta);

	ArrayList<Risposta> getRisposte();

	boolean eliminaRisposta(int idRisposta);

	ArrayList<Domanda> getDomande();

	boolean eliminaDomanda(int idDomanda);

	
}
