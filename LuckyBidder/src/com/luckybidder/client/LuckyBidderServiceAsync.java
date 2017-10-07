package com.luckybidder.client;

import com.luckybidder.shared.*;
import org.mapdb.DB;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LuckyBidderServiceAsync {

	void registraUtente(Utente utente, AsyncCallback<Boolean> callback);
	
	void aggiungiCategoria(Categoria categoria, AsyncCallback<Boolean> callback);
	
	
}
