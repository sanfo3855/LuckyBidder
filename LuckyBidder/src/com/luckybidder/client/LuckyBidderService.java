package com.luckybidder.client;

import com.luckybidder.shared.*;

import org.mapdb.DB;

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
	
	/*boolean aggiungiCategoria(Categoria categoria);*/

	Utente loginUtente(String username, String password);
	
}
