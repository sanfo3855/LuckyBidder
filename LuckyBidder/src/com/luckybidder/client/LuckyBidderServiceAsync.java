package com.luckybidder.client;

import com.luckybidder.shared.*;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LuckyBidderServiceAsync {

	void registraUtente(Utente utente, AsyncCallback<Boolean> callback);
	
	
}
