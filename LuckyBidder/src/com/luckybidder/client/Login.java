package com.luckybidder.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.luckybidder.shared.Utente;

public class Login extends HorizontalPanel {
	private TextBox tUsername;
	private TextBox tPassword;
	private Button btnAccedi;
	private Button btnRegistra;
	private Grid gridMain;
	
	
	String SESSION;
	
	
	public Login()  {
		
		final LuckyBidderServiceAsync instanceLuckyBidderService = LuckyBidderService.Util.getInstance();
		
		DecoratorPanel decoratorpanel = new DecoratorPanel();
		final VerticalPanel verticalpanel = new VerticalPanel();
		
		HTMLPanel htmlpanel = new HTMLPanel("<center><b>LOGIN</b></center>");
		htmlpanel.getElement().setAttribute("style", "padding: 5px");
		verticalpanel.add(htmlpanel);
		
		gridMain = new Grid(3, 3);
		verticalpanel.add(gridMain);
		
		Label labelUser = new Label("Username");
		gridMain.setWidget(0, 0, labelUser);
		tUsername = new TextBox();
		gridMain.setWidget(0, 1, tUsername);
		tUsername.setSize("160px", "17px");
		
	
		Label labelPassword = new Label("Password ");
		tPassword = new PasswordTextBox();
		tPassword.setWidth("150px");
		Label requiredPassword = new Label("(*)");
		gridMain.setWidget(1, 0, labelPassword);
		gridMain.setWidget(1, 1, tPassword);
		tPassword.setSize("160px", "17px");
		
		//BUTTON Registrati e Torna a Login
		Grid gridButton = new Grid(1,2);
		btnAccedi = new Button("Accedi");
		btnRegistra = new Button("Registrati");
		gridButton.setWidget(0, 0, btnAccedi);
		gridButton.setWidget(0, 1, btnRegistra);
		gridButton.getElement().setAttribute("style", "margin-left: 22%; argin-rigth: 15%; padding: 5px");
		
		btnAccedi.addClickHandler( new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				instanceLuckyBidderService.loginUtente(tUsername.getValue(), tPassword.getValue(), new AsyncCallback<Utente>() {

					@Override
					public void onFailure(Throwable caught) {
						PopupPanel popup = new PopupPanel(true);
						popup.setWidget(new HTML("<font color='red'>Impossibile effettuare login: Errore nella connessione con il server.<br>"+caught+"</font>"));
						popup.center();
						
					}

					@Override
					public void onSuccess(Utente result) {
						if(result!=null) {
							Session.getInstance().setSession(result);
							TopBar topbar = new TopBar();
							Profilo profilo = new Profilo();
							RootPanel.get().clear();
							RootPanel.get().add(topbar);
							RootPanel.get().add(profilo);
						} else {
							TopBar topbar = new TopBar();
							Login login = new Login();
							RootPanel.get().clear();
							RootPanel.get().add(topbar);
							RootPanel.get().add(login);
							PopupPanel popup = new PopupPanel(true);
							popup.setWidget(new HTML("<font color='red'>Errore Login<br>Reinserire Username e Password</font>"));
							popup.center();
							
						}
						
					}
					
				});
				
				
			}
			
		});
		
		btnRegistra.addClickHandler( new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				TopBar topbar = new TopBar();
				Registrazione registrazione = new Registrazione();
				RootPanel.get().clear();
				RootPanel.get().add(topbar);
				RootPanel.get().add(registrazione);
			}
			
		});
		
		verticalpanel.add(gridButton);
		decoratorpanel.add(verticalpanel);
		decoratorpanel.getElement().setAttribute("style", "margin-left: 39vw; margin-top: 10vh;");
		this.add(decoratorpanel);
	}
	
	
}
