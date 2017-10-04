package com.luckybidder.client;

import com.luckybidder.shared.*;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("LuckyBidderService")
public interface LuckyBidderService extends RemoteService{

	boolean registraUtente(Utente utente);
}
