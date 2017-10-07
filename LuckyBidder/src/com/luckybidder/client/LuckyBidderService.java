package com.luckybidder.client;

import com.luckybidder.shared.*;

import org.mapdb.DB;


import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("luckybidderservice")
public interface LuckyBidderService extends RemoteService{

	boolean registraUtente(Utente utente);
	
	boolean aggiungiCategoria(Categoria categoria);
	
	
}
