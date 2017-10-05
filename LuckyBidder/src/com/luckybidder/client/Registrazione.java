package com.luckybidder.client;

import com.luckybidder.client.LuckyBidderService;
import com.luckybidder.client.LuckyBidderServiceAsync;
import com.luckybidder.shared.Utente;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

public class Registrazione extends HorizontalPanel{
	
	private TextBox tUsername;
	private TextBox tNome;
	private TextBox tCognome;
	private TextBox tTelefono;
	private PasswordTextBox tPassword;
	private TextBox tEmail;
	private TextBox tCodiceFiscale;
	private TextBox tIndirizzo;
	private RadioButton radButSessoM;
	private RadioButton radButSessoF;
	private DateBox tDataNascita;
	private TextBox tLuogoNascita;
	
	String SESSION;
	
	public Registrazione() {
		
		final VerticalPanel verticalpanel = new VerticalPanel();
		verticalpanel.setSize("430px", "485px");
		
		final LuckyBidderServiceAsync luckyBidderServiceUtente = GWT.create(LuckyBidderService.class);

		HTMLPanel htmlpanel = new HTMLPanel("");
		verticalpanel.add(htmlpanel);
		
		Grid grid = new Grid(11,3);
		verticalpanel.setCellHorizontalAlignment(grid, HasHorizontalAlignment.ALIGN_CENTER);
		verticalpanel.setTitle("Registrazione");
		grid.setSize("20%", "40%");		
		
		//USERNAME
		Label labelUsername = new Label("Username ");
		tUsername = new TextBox();
		tUsername.setWidth("150px");
		Label requiredUsername = new Label("(*)");
		grid.setWidget(0, 0, labelUsername);
		grid.setWidget(0, 1, tUsername);
		grid.setWidget(0, 2, requiredUsername);
		
		//NOME
		Label labelNome = new Label("Nome ");
		tNome = new TextBox();
		tNome.setWidth("150px");
		Label requiredNome = new Label("(*)");
		grid.setWidget(2, 0, labelNome);
		grid.setWidget(2, 1, tNome);
		grid.setWidget(2, 2, requiredNome);
		
		//COGNOME
		Label labelCognome = new Label("Cognome ");
		tCognome = new TextBox();
		tCognome.setWidth("150px");
		Label requiredCognome = new Label("(*)");
		grid.setWidget(3, 0, labelCognome);
		grid.setWidget(3, 1, tCognome);
		grid.setWidget(3, 2, requiredCognome);
		
		//TELEFONO
		Label labelTelefono = new Label("Telefono ");
		tTelefono = new TextBox();
		tTelefono.setWidth("150px");
		Label requiredTelefono = new Label("(*)");
		grid.setWidget(4, 0, labelTelefono);
		grid.setWidget(4, 1, tTelefono);
		grid.setWidget(4, 2, requiredTelefono);
		
		//PASSWORD
		Label labelPassword = new Label("Password ");
		tPassword = new PasswordTextBox();
		tPassword.setWidth("150px");
		Label requiredPassword = new Label("(*)");
		grid.setWidget(1, 0, labelPassword);
		grid.setWidget(1, 1, tPassword);
		grid.setWidget(1, 2, requiredPassword);
		
		//EMAIL
		Label labelEmail = new Label("Email ");
		tEmail = new TextBox();
		tEmail.setWidth("150px");
		Label requiredEmail = new Label("(*)");
		grid.setWidget(5, 0, labelEmail);
		grid.setWidget(5, 1, tEmail);
		grid.setWidget(5, 2, requiredEmail);
		
		//CODICE FISCALE
		Label labelCodiceFiscale = new Label("Codice Fiscale ");
		tCodiceFiscale = new TextBox();
		tCodiceFiscale.setWidth("150px");
		Label requiredCodiceFiscale = new Label("(*)");
		grid.setWidget(6, 0, labelCodiceFiscale);
		grid.setWidget(6, 1, tCodiceFiscale);
		grid.setWidget(6, 2, requiredCodiceFiscale);
		
		//INDIRIZZO
		Label labelIndirizzo = new Label("Indirizzo ");
		tIndirizzo = new TextBox();
		tIndirizzo.setWidth("150px");
		Label requiredIndirizzo = new Label("(*)");
		grid.setWidget(7, 0, labelIndirizzo);
		grid.setWidget(7, 1, tIndirizzo);
		grid.setWidget(7, 2, requiredIndirizzo);
		
		//SESSO
		Label labelSesso = new Label("Sesso ");
		radButSessoM = new RadioButton("sesso", "M");
		radButSessoF = new RadioButton("sesso", "F");
		Grid radiosGrid = new Grid(1,2);
		radiosGrid.setWidget(0, 0, radButSessoM);
		radiosGrid.setWidget(0, 1, radButSessoF);
		grid.setWidget(8, 0, labelSesso);
		grid.setWidget(8, 1, radiosGrid);
				
		//DATA NASCITA
		Label labelDataNascita = new Label("Data di Nascita ");
		tDataNascita = new DateBox();
		tDataNascita.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("dd/MM/yyyy")));
		tDataNascita.setWidth("150px");
		grid.setWidget(9, 0, labelDataNascita);
		grid.setWidget(9, 1, tDataNascita);
		
		//LUOGO NASCITA
		Label labelLuogoNascita = new Label("Luogo di Nascita ");
		tLuogoNascita = new TextBox();
		tLuogoNascita.setWidth("150px");
		grid.setWidget(10, 0, labelLuogoNascita);
		grid.setWidget(10, 1, tLuogoNascita);
		
		verticalpanel.add(grid);
		
		//BUTTON Registrati e Torna a Login
		Grid gridButton = new Grid(1,2);
		Button bRegistrati = new Button("Registrati");
		Button bReturnLogin = new Button("← Torna a Login");
		gridButton.setWidget(0, 0, bReturnLogin);
		gridButton.setWidget(0, 1, bRegistrati);
		gridButton.setWidth("260px");
		verticalpanel.add(gridButton);
		
		bRegistrati.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				final String username = tUsername.getValue();
				String nome = tNome.getValue();
				String cognome = tCognome.getValue();
				String telefono = tTelefono.getValue();
				String password = tPassword.getValue();
				String email = tEmail.getValue();
				String codiceFiscale = tCodiceFiscale.getValue();
				String indirizzo = tIndirizzo.getValue();
				char sesso = '\u0000';//null char
				Date dataNascita = null;
				String luogoNascita = null;
				
				if(!username.isEmpty() || !nome.isEmpty() || !cognome.isEmpty() || !telefono.isEmpty() ||
						!password.isEmpty() || !email.isEmpty() || !codiceFiscale.isEmpty() || 
						!indirizzo.isEmpty()) {
					
					//Setta sesso se esiste
					if(radButSessoM.getValue()) {
						sesso = 'M';
					} else if(radButSessoF.getValue()) {
						sesso = 'F';
					}
					
					//Setta dataNascita se esiste
					if(tDataNascita.getValue()!=null) {
						dataNascita = tDataNascita.getValue();
					} else {
						
					}
					
					//Setta luogoNascita
					if(tLuogoNascita.getValue() != null) {
						luogoNascita = tLuogoNascita.getValue();
					} else {
						
					}
					
					Utente newUtente = new Utente();
					newUtente.setUsername(username); 
					newUtente.setNome(nome);
					newUtente.setCognome(cognome);
					newUtente.setPassword(password);
					newUtente.setEmail(email);
					newUtente.setCodiceFiscale(codiceFiscale); 
					newUtente.setTelefono(telefono); 
					newUtente.setIndirizzo(indirizzo);
					newUtente.setSesso(sesso);
					newUtente.setDataNascita(dataNascita);
					newUtente.setLuogoNascita(luogoNascita);
					
					luckyBidderServiceUtente.registraUtente(newUtente, new AsyncCallback<Boolean>() {

						@Override
						public void onFailure(Throwable caught) {
							PopupPanel popup = new PopupPanel(true);
							popup.setWidget(new HTML("<font color='red'>Impossibile inserire una nuova registrazione: Errore nella connessione con il server.<br>"+caught+"</font>"));
							popup.center();
							
						}

						@Override
						public void onSuccess(Boolean result) {
							if(result==true) {
								SESSION = username;
								PopupPanel popup = new PopupPanel(true);
								popup.setWidget(new HTML("<font color='Black'>Registrato</font>"));
								popup.center();
							} else if (result==false) {
								PopupPanel popup = new PopupPanel(true);
								popup.setWidget(new HTML("Registrazione fallita, username "+ username +" già esistente!"));
								popup.center();
							}
							
						}
						
					});
					
				} else {
					PopupPanel popup = new PopupPanel(true);
					popup.setWidget(new HTML("I campi contrassegnati con (*) sono obbligatori!"));
					popup.center();
				}
				
			}
		});		
		
		this.add(verticalpanel);
	}
}
