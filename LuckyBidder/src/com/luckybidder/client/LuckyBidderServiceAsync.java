package com.luckybidder.client;

import com.luckybidder.shared.*;

import org.eclipse.jetty.server.Authentication.User;
import org.mapdb.DB;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LuckyBidderServiceAsync {

	void registraUtente(Utente utente, AsyncCallback<Boolean> callback);
	
	void loginUtente(String username, String password, AsyncCallback<Utente> callback);
}
