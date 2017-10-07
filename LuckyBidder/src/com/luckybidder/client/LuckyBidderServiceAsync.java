package com.luckybidder.client;

import com.luckybidder.shared.*;
<<<<<<< HEAD
=======

import org.eclipse.jetty.server.Authentication.User;
>>>>>>> branch 'master' of https://bitbucket.org/sanfo3855/luckybidder
import org.mapdb.DB;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LuckyBidderServiceAsync {

	void registraUtente(Utente utente, AsyncCallback<Boolean> callback);
	
<<<<<<< HEAD
	void aggiungiCategoria(Categoria categoria, AsyncCallback<Boolean> callback);
	
	
=======
	void loginUtente(String username, String password, AsyncCallback<Utente> callback);
>>>>>>> branch 'master' of https://bitbucket.org/sanfo3855/luckybidder
}
