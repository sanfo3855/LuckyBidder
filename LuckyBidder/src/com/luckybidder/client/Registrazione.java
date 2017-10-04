package com.luckybidder.client;

import com.luckybidder.client.LuckyBidderService;
import com.luckybidder.client.LuckyBidderServiceAsync;
import com.google.gwt.core.client.GWT;
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
		
		//final LuckyBidderServiceAsync luckyBidderServiceUtente = GWT.create(LuckyBidderDefault.class);

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
		
		verticalpanel.add(grid);
		this.add(verticalpanel);
	}
}
