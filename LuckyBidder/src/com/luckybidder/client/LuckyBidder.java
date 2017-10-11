package com.luckybidder.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class LuckyBidder implements EntryPoint {
	
	public void onModuleLoad() {
		
		
		final RootPanel rootpanel = RootPanel.get();
		rootpanel.setSize("100%", "100%");
		
		final LuckyBidderServiceAsync instanceLuckyBidderService = LuckyBidderService.Util.getInstance();
		
		TopBar topbar = new TopBar();
		rootpanel.add(topbar);
<<<<<<< HEAD
=======
	
>>>>>>> e96c89081eca7db2b74332291fde6b00fc6b90d8

		Login login = new Login();
		rootpanel.add(login);

	}
}
