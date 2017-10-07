package com.luckybidder.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.luckybidder.shared.*;

public class LuckyBidder implements EntryPoint {
	
	public void onModuleLoad() {
		
		
		final RootPanel rootpanel = RootPanel.get();
		rootpanel.setSize("100%", "100%");
		
		final LuckyBidderServiceAsync instanceLuckyBidderService = LuckyBidderService.Util.getInstance();
		
		TopBar topbar = new TopBar();
		rootpanel.add(topbar);
<<<<<<< HEAD
		//Login login = new Login();
		//rootpanel.add(login);
		
		Profilo profilo = new Profilo ();
		rootpanel.add(profilo);
	
=======
		Login login = new Login();
		rootpanel.add(login);
		
>>>>>>> branch 'master' of https://bitbucket.org/sanfo3855/luckybidder
	}
}
