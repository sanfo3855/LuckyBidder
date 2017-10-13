package com.luckybidder.client;

import com.luckybidder.shared.*;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LuckyBidderServiceAsync {

	void registraUtente(Utente utente, AsyncCallback<Boolean> callback);
	
	void vendiProdotto(Prodotto prodotto, AsyncCallback<Boolean> callback);

	void aggiungiCategoria(Categoria categoria, AsyncCallback<Boolean> callback);

	void loginUtente(String username, String password, AsyncCallback<Utente> callback);

	void getProdotti(AsyncCallback<ArrayList<Prodotto>> callback);

}
