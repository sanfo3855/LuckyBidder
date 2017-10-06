package com.luckybidder.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.luckybidder.shared.*;

public class LuckyBidder implements EntryPoint {
	
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network " + "connection and try again.";
	public String SESSION;
	public void onModuleLoad() {
		
		final RootPanel rootpanel = RootPanel.get();
		rootpanel.setSize("100%", "100%");
		
		//final LuckyBidderServiceAsync luckyBidderServiceUtente = GWT.create(LuckyBidderService.class);
		TopBar topbar = new TopBar();
		rootpanel.add(topbar);
		Login login = new Login();
		rootpanel.add(login);
	}
}
