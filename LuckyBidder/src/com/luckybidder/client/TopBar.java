package com.luckybidder.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class TopBar extends HorizontalPanel {
	
	public String SESSION;
	
	public TopBar() {
		DecoratorPanel decoratorTopbar = new DecoratorPanel();
		HorizontalPanel horizontalPanel = new HorizontalPanel();

		//decoratorTopbar.setSize("100%", "100%");
		int widthRoot = RootPanel.get().getOffsetWidth();
		
		Button homeButton = new Button("HOME");
		Button logoutButton = new Button("LOGOUT");
		int widthLogout = logoutButton.getOffsetWidth();
		int distanzaButton = widthRoot - widthLogout;
		homeButton.getElement().setAttribute("style", "margin-right: 88.5vw");
		horizontalPanel.add(homeButton);
		horizontalPanel.add(logoutButton);
		
		decoratorTopbar.add(horizontalPanel);
		
		this.add(decoratorTopbar);
	}	
}
