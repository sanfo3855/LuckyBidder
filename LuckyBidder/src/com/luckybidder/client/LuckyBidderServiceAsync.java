package com.luckybidder.client;

import com.luckybidder.shared.*;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LuckyBidderServiceAsync {

	void registraUtente(Utente utente, AsyncCallback<Boolean> callback);
	
	void vendiProdotto(Prodotto prodotto, AsyncCallback<Boolean> callback);

	void aggiungiCategoria(Categoria categoria, AsyncCallback<Boolean> callback);

	void loginUtente(String username, String password, AsyncCallback<Utente> callback);
	
	void getProdottiVenduti(String username, AsyncCallback<ArrayList<Prodotto>> callback);
	void getOfferteFatte(String username, AsyncCallback<ArrayList<Offerta>> callback );

	void modificaScadenza(Prodotto prodotto,int id, AsyncCallback<Boolean> callback);
	void getMaxOfferta(int id, AsyncCallback<Offerta> callback );
	void getProdotti(AsyncCallback<ArrayList<Prodotto>> callback);
	void getProdottoSingolo(int id, AsyncCallback<Prodotto> callback);
	void offri(Offerta offerta, AsyncCallback<Boolean>callback);
	void getOfferte(int idProdotto, AsyncCallback<ArrayList<Offerta>>callback);	
}
