package com.luckybidder.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.luckybidder.shared.*;

public class TopBar extends VerticalPanel {
	
	public String SESSION;
	private Label labelNome;
	
	public TopBar() {
		
		final LuckyBidderServiceAsync instanceLuckyBidderService = LuckyBidderService.Util.getInstance();
		
		DecoratorPanel decoratorTopbar = new DecoratorPanel();
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		
		Button homeButton = new Button("HOME");
		Button logoutButton = new Button("LOGOUT");
		labelNome = new Label("");
		

		logoutButton.getElement().setAttribute("style", "margin-right: 5px; margin-left: 88.5vw");
		homeButton.getElement().setAttribute("style", "margin-left: 5px");
		
		horizontalPanel.add(homeButton);
		if(Session.getInstance().getSession()!=null) {
			DecoratorPanel decNome = new DecoratorPanel();
			HTMLPanel labelNome = new HTMLPanel("<center>Loggato come: <b>"+Session.getInstance().getSession().getUsername()+"</b></center>");
			decNome.add(labelNome);
			decNome.getElement().setAttribute("style", "float: right; margin-right: 10px; margin-bottom; 10px");
			this.add(decNome);
			this.add(new HTMLPanel("<br>"));
			
		}
		horizontalPanel.add(logoutButton);
		decoratorTopbar.add(horizontalPanel);
		
		homeButton.addClickHandler( new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				TopBar topbar = new TopBar();
				//Home home = new Home();
				RootPanel.get().clear();
				RootPanel.get().add(topbar);
				//RootPanel.get().add(home);
				
			}
			
		});
		
		logoutButton.addClickHandler( new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Session.getInstance().setSession(null);
				TopBar topbar = new TopBar();
				Login login = new Login();
				RootPanel.get().clear();
				RootPanel.get().add(topbar);
				RootPanel.get().add(login);
			}
			
		});
		
		
		
		this.add(decoratorTopbar);
	}	
}
