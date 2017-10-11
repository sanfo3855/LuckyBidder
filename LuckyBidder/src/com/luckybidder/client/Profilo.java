package com.luckybidder.client;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.luckybidder.shared.Prodotto;
import com.luckybidder.shared.Offerta;
import com.luckybidder.shared.Prodotto.StatoAstaProdotto;

public class Profilo extends HorizontalPanel{
	String SESSION;
	private Grid gridMain;
	
	public Profilo () {
		DecoratorPanel decoratorpanel1 = new DecoratorPanel();
		final VerticalPanel verticalpanel = new VerticalPanel();
		
		final LuckyBidderServiceAsync instanceLuckyBidderService = LuckyBidderService.Util.getInstance();
		
		HTMLPanel htmlpanel = new HTMLPanel("<center><b>PROFILO</b></center>");
		htmlpanel.getElement().setAttribute("style", "padding: 5px");
		verticalpanel.add(htmlpanel);
		
		gridMain = new Grid(9, 2);
		verticalpanel.add(gridMain);
		
		//carico i dati nella griglia
		Label lblNome = new Label("Nome");
		gridMain.setWidget(0, 0, lblNome);
		lblNome.setSize("160px", "18px");
		
		Label nomeUser = new Label(Session.getInstance().getSession().getNome());
		gridMain.setWidget(0, 1, nomeUser);
		nomeUser.setSize("160px", "18px");

		Label lblCognome = new Label("Cognome");
		gridMain.setWidget(1, 0, lblCognome);
		lblCognome.setSize("160px", "18px");

		Label cognomeUser = new Label(Session.getInstance().getSession().getCognome());
		gridMain.setWidget(1, 1, cognomeUser);
		cognomeUser.setSize("160px", "18px");

		Label lblEmail = new Label("Email");
		gridMain.setWidget(2, 0, lblEmail);
		lblEmail.setSize("160px", "18px");

		Label emailUser  = new Label(Session.getInstance().getSession().getEmail());
		gridMain.setWidget(2, 1, emailUser);
		emailUser.setSize("160px", "18px");

		Label lblCodiceFiscale = new Label("Codice Fiscale");
		gridMain.setWidget(3, 0, lblCodiceFiscale);
		lblCodiceFiscale.setSize("160px", "18px");

		Label cfUser = new Label(Session.getInstance().getSession().getCodiceFiscale());
		gridMain.setWidget(3, 1, cfUser);
		cfUser.setSize("160px", "18px");

		Label lblIndirizzo = new Label("Indirizzo");
		gridMain.setWidget(4, 0, lblIndirizzo);
		lblIndirizzo.setSize("160px", "18px");

		Label indirizzoUser = new Label(Session.getInstance().getSession().getIndirizzo());
		gridMain.setWidget(4, 1, indirizzoUser);
		indirizzoUser.setSize("160px", "18px");
		

		Label lblSesso = new Label("Sesso");
		gridMain.setWidget(5, 0, lblSesso);
		lblSesso.setSize("160px", "18px");
		
		String sessoString;
		if( Session.getInstance().getSession().getSesso() != '\u0000' ) {
			char sessoChar = Session.getInstance().getSession().getSesso();
			sessoString = Character.toString(sessoChar);
		} else {
			sessoString = "";
		}
		Label sessoUser = new Label(sessoString);
		gridMain.setWidget(5, 1, sessoUser);
		sessoUser.setSize("160px", "18px");


		Label lblDataDiNascita = new Label("Data di nascita");
		gridMain.setWidget(6, 0, lblDataDiNascita);
		lblDataDiNascita.setSize("160px", "18px");
		
		String st;
		if(Session.getInstance().getSession().getDataNascita() != null) {
			Date dateToConvert = Session.getInstance().getSession().getDataNascita();
			DateTimeFormat fm = DateTimeFormat.getFormat("dd/MM/yyyy");
			st = fm.format(dateToConvert);
		} else {
			st = "";
		}
		Label nascitaUser = new Label(st);
		gridMain.setWidget(6, 1, nascitaUser);
		nascitaUser.setSize("160px", "18px");
		
		
		Label lblLuogoDiNascita = new Label("Luogo di Nascita");
		gridMain.setWidget(7, 0, lblLuogoDiNascita);
		lblLuogoDiNascita.setSize("160px", "18px");
		
		String luogo;
		
		if(Session.getInstance().getSession().getLuogoNascita() != null) {
			luogo = Session.getInstance().getSession().getLuogoNascita();
		} else {
			luogo = "";
		}
		Label luogoNascitaUser = new Label(luogo);
		gridMain.setWidget(7, 1, luogoNascitaUser);
		luogoNascitaUser.setSize("160px", "18px");
		
		Label lblTelefono = new Label("Telefono");
		gridMain.setWidget(8, 0, lblTelefono);
		lblTelefono.setSize("160px", "18px");

		Label telefonoUser = new Label(Session.getInstance().getSession().getTelefono());
		gridMain.setWidget(8, 1, telefonoUser);
		telefonoUser.setSize("160px", "18px");
		
		decoratorpanel1.add(verticalpanel);
		decoratorpanel1.getElement().setAttribute("style", "margin-left: 1vw; margin-top: 1vh;");
		this.add(decoratorpanel1);
		
		DecoratorPanel decoratorpanel2 = new DecoratorPanel();
		final VerticalPanel verticalPanel2 = new VerticalPanel();
		verticalPanel2.setSize("300px", "352px");
		
		
		HTMLPanel htmlpanel2 = new HTMLPanel("<center><b>VENDITE</b></center>");
		htmlpanel2.getElement().setAttribute("style", "padding: 5px");
		verticalPanel2.add(htmlpanel2);
		
		ArrayList<Prodotto> prodotti = new ArrayList<Prodotto>();
		
		CellTable<Prodotto> table = new CellTable<Prodotto>();
		table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.DISABLED);
		table.setWidth("810px");
		table.setColumnWidth(1, "270px");
		table.setColumnWidth(1, "270px");
		table.setColumnWidth(2, "270px");
		
		//COLONNA PRODOTTO
		TextColumn<Prodotto> txtColName = new TextColumn<Prodotto>(){
			@Override
			public String getValue(Prodotto object) {
				//Si caricano i nomi
				return object.getNomeProdotto();
			}
		};
		table.addColumn(txtColName, "Oggetto");
		
		//COLONNA STATO
		TextColumn<Prodotto> txtColStato = new TextColumn<Prodotto>(){
			@Override
			public String getValue(Prodotto object) {
				return object.getStato();
			}
		};
		table.addColumn(txtColStato, "Stato");
		
		//COLONNA VINCITORE
		TextColumn<Prodotto> txtColVincitore = new TextColumn<Prodotto>(){
			@Override
			public String getValue(Prodotto object) {
				return object.getVincitore();
			}
		};
		table.addColumn(txtColVincitore, "Vincitore");
		
		
		table.setRowCount(prodotti.size(), true);
		table.setRowData(0, prodotti);
		
		verticalPanel2.add(table);
		
		HTMLPanel htmlpanel3 = new HTMLPanel("<center><b>OFFERTE</b></center>");
		htmlpanel3.getElement().setAttribute("style", "padding: 5px");
		verticalPanel2.add(htmlpanel3);

		ArrayList<Offerta> offerte = new ArrayList<Offerta>();
		
		//TABELLA OFFERTA
		CellTable<Offerta> tableOfferte = new CellTable<Offerta>();
		tableOfferte.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		tableOfferte.setWidth("810px");
		tableOfferte.setColumnWidth(0, "270px");
		tableOfferte.setColumnWidth(1, "270px");
		tableOfferte.setColumnWidth(2, "270px");

		//COLONNA ID PRODOTTO
		TextColumn<Offerta> txtColId = new TextColumn<Offerta>() {
			@Override
			public String getValue(Offerta object) {
				//Si carica l'id
				return Integer.toString(object.getIdProdotto());
			}
		};
		tableOfferte.addColumn(txtColId, "Id oggetto");

		//COLONNA PREZZO
		TextColumn<Offerta> txtColPrezzo = new TextColumn<Offerta>() {
			@Override
			public String getValue(Offerta object) {
				//Si caricano gli importi
				return Double.toString(object.getPrezzo());
			}
		};
		tableOfferte.addColumn(txtColPrezzo, "Prezzo");

		//COLONNA DATA
		TextColumn<Offerta> txtColData = new TextColumn<Offerta>() {
			@Override
			public String getValue(Offerta object) {
				return object.getData();
			}
		};
		tableOfferte.addColumn(txtColData, "Data");
		
		tableOfferte.setRowCount(offerte.size(), true);
		tableOfferte.setRowData(0, offerte);
		
		verticalPanel2.add(tableOfferte);
		
		decoratorpanel2.getElement().setAttribute("style", "margin-left: 1vw; margin-top: 1vh;");
		decoratorpanel2.add(verticalPanel2);
		this.add(decoratorpanel2);
		
		
		
		
		
		
		
		
	}
}
