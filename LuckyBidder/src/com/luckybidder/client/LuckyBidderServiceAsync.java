package com.luckybidder.client;

import com.luckybidder.shared.*;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LuckyBidderServiceAsync {

	void registraUtente(Utente utente, AsyncCallback<Boolean> callback);
	
	void vendiProdotto(Prodotto prodotto, AsyncCallback<Boolean> callback);

	void aggiungiCategoria(Categoria categoria, String padre, AsyncCallback<Boolean> callback);

	void loginUtente(String username, String password, AsyncCallback<Utente> callback);
	
	void getProdottiVenduti(String username, AsyncCallback<ArrayList<Prodotto>> callback);
	
	void getOfferteFatte(String username, AsyncCallback<ArrayList<Offerta>> callback );

	void modificaScadenza(Prodotto prodotto,int id, AsyncCallback<Boolean> callback);
	
	void getMaxOfferta(int id, AsyncCallback<Offerta> callback );
	
	void getProdotti(AsyncCallback<ArrayList<Prodotto>> callback);
	
	void getCategoria(int id, AsyncCallback<Categoria> callback);

	void getAllCategorie(AsyncCallback<ArrayList<Categoria>> callback);

	void modificaCategoria(String nomeCategoria, String nomeNuovo, AsyncCallback<Boolean> callback);

	void getProdottoSingolo(int id, AsyncCallback<Prodotto> callback);
	
	void offri(Offerta offerta, AsyncCallback<Boolean>callback);
	
	void getOfferte(int idProdotto, AsyncCallback<ArrayList<Offerta>>callback);

	void getDomanda(String username, int id, AsyncCallback<Domanda> asyncCallback);

	void mandaDomanda(String nomeProdotto, String testoDomanda, String username, int id, String usernameVenditore, AsyncCallback<Boolean> asyncCallback);

	void getRisposta(int idDomanda, AsyncCallback<Risposta> asyncCallback);

	void getDomandeToUsername(String usernameVendProdotto, AsyncCallback<ArrayList<Domanda>> asyncCallback);

	void inviaRisposta(int idDomanda, String text, AsyncCallback<Boolean> asyncCallback);	

}
