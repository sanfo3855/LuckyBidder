package com.luckybidder.client;

import com.luckybidder.shared.*;

<<<<<<< HEAD
import org.mapdb.DB;


=======
import com.google.gwt.core.client.GWT;
>>>>>>> branch 'master' of https://bitbucket.org/sanfo3855/luckybidder
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
<<<<<<< HEAD
	
	boolean aggiungiCategoria(Categoria categoria);
=======

	Utente loginUtente(String username, String password);
>>>>>>> branch 'master' of https://bitbucket.org/sanfo3855/luckybidder
	
	
}
