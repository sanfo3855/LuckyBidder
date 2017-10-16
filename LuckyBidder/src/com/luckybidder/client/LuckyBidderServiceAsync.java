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


	void getProdotti(AsyncCallback<ArrayList<Prodotto>> callback);
	
	void getCategoria(int id, AsyncCallback<Categoria> callback);

	void getAllCategorie(AsyncCallback<ArrayList<Categoria>> callback);

	void modificaCategoria(String nomeCategoria, String nomeNuovo, AsyncCallback<Boolean> callback);

}
