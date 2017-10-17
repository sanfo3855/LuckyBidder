package com.luckybidder.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class TopBar extends VerticalPanel {
	
	public TopBar() {
		
		final LuckyBidderServiceAsync instanceLuckyBidderService = LuckyBidderService.Util.getInstance();
		
		DecoratorPanel decoratorTopbar = new DecoratorPanel();
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		
		Button homeButton = new Button("HOME");
		Button logoutButton = new Button("LOGOUT");
		Button profiloButton = new Button("PROFILO");
		Button vendiProdottoButton = new Button("VENDI PRODOTTO");
		Button gestioneCategorieButton = new Button("GESTIONE CATEGORIE");
		Button loginButton = new Button("LOGIN");
		

		logoutButton.getElement().setAttribute("style", "margin-right: 5px; margin-left: 5px;");
		homeButton.getElement().setAttribute("style", "margin-left: 5px; margin-right: 5px;");
		profiloButton.getElement().setAttribute("style", "margin-left: 5px; margin-right: 5px;");
		vendiProdottoButton.getElement().setAttribute("style", "margin-left: 5px; margin-right: 5px;");
		loginButton.getElement().setAttribute("style", "margin-left: 5px; margin-right: 5px;");
		horizontalPanel.add(homeButton);
		
		homeButton.addClickHandler( new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				TopBar topbar = new TopBar();
				HomeProdotti homeprodotti = new HomeProdotti(null);
				RootPanel.get().clear();
				RootPanel.get().add(topbar);
				RootPanel.get().add(homeprodotti);	
			}
			
		});
		
		
		

		if(Session.getInstance().getSession()!=null) {
			if(!Session.getInstance().getSession().getUsername().equals("admin")){
				horizontalPanel.add(profiloButton);
				profiloButton.addClickHandler( new ClickHandler() {
				
					@Override
					public void onClick(ClickEvent event) {
						TopBar topbar = new TopBar();
						Profilo profilo = new Profilo();
						RootPanel.get().clear();
						RootPanel.get().add(topbar);
						RootPanel.get().add(profilo);
					}
					
				});
				horizontalPanel.add(vendiProdottoButton);
				vendiProdottoButton.addClickHandler( new ClickHandler() {
				
					@Override
					public void onClick(ClickEvent event) {
						TopBar topbar = new TopBar();
						VenditaProdotto venditaProdotto = new VenditaProdotto();
						RootPanel.get().clear();
						RootPanel.get().add(topbar);
						RootPanel.get().add(venditaProdotto);
					}
				});
				
			} else {
				horizontalPanel.add(gestioneCategorieButton);
				gestioneCategorieButton.addClickHandler( new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						TopBar topbar = new TopBar();
						GestioneCategorie gestioneCategorie = new GestioneCategorie();
						RootPanel.get().clear();
						RootPanel.get().add(topbar);
						RootPanel.get().add(gestioneCategorie);
					}
					
				});
			}
			horizontalPanel.add(logoutButton);
			Label labelNome = new Label("Loggato come: " +Session.getInstance().getSession().getUsername());
			labelNome.getElement().setAttribute("style", "margin-left: 5px; margin-right: 5px;");
			horizontalPanel.add(labelNome);
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
		} else {
			horizontalPanel.add(homeButton);
			loginButton.addClickHandler( new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					TopBar topbar = new TopBar();
					Login login = new Login();
					RootPanel.get().clear();
					RootPanel.get().add(topbar);
					RootPanel.get().add(login);	
				}
				
			});
			horizontalPanel.add(loginButton);	
		}
		
		decoratorTopbar.add(horizontalPanel);
		this.add(decoratorTopbar);
	}	
}
