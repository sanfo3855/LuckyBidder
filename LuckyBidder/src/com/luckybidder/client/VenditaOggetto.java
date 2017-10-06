package com.luckybidder.client;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

public class VenditaOggetto extends HorizontalPanel{
	private TextBox tNomeOggetto;
	private TextBox tDescrizione;
	private TextBox tPrezzoVendita;
	private DateBox tScadenzaAsta;
	private ListBox lCategoria;
	
	public VenditaOggetto() {
		
		final DecoratorPanel decoratorpanel = new DecoratorPanel();
		final VerticalPanel verticalpanel = new VerticalPanel();
		
		Grid grid = new Grid(5,3);
		verticalpanel.setCellHorizontalAlignment(grid, HasHorizontalAlignment.ALIGN_CENTER);
		verticalpanel.setTitle("Vendita Oggetti");
			
		//NOME OGGETTO
		Label labelNomeOggetto = new Label("Nome Oggetto");
		labelNomeOggetto.setWidth("150px");
		tNomeOggetto = new TextBox();
		tNomeOggetto.setWidth("150px");
		Label requiredNome = new Label("(*)");
		grid.setWidget(0, 0, labelNomeOggetto);
		grid.setWidget(0, 1, tNomeOggetto);
		grid.setWidget(0, 2, requiredNome);
		
		//DESCRIZIONE OGGETTO
		Label labelDescrizione = new Label("Descrizione oggetto");
		tDescrizione = new TextBox();
		tDescrizione.setWidth("150px");
		Label requiredDescrizione = new Label("(*)");
		grid.setWidget(1, 0, labelDescrizione);
		grid.setWidget(1, 1, tDescrizione);
		grid.setWidget(1, 2, requiredDescrizione);
		
		//PREZZO VENDITA
		Label labelPrezzoV = new Label("Prezzo base di vendita");
		tPrezzoVendita = new TextBox();
		tPrezzoVendita.setWidth("150px");
		Label requiredPrezzoV = new Label("(*)");
		grid.setWidget(2, 0, labelPrezzoV);
		grid.setWidget(2, 1, tPrezzoVendita);
		grid.setWidget(2, 2, requiredPrezzoV);
		
		//SCADENZA ASTA
		Label labelDataScadenza = new Label("Data scadenza asta");
		tScadenzaAsta = new DateBox();
		tScadenzaAsta.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("dd/MM/yyyy")));
		tScadenzaAsta.setWidth("150px");
		Label requiredScadenza = new Label("(*)");
		grid.setWidget(3, 0, labelDataScadenza);
		grid.setWidget(3, 1, tScadenzaAsta);
		grid.setWidget(3, 2, requiredScadenza);
		
		//CATEGORIA
		Label labelCategoria = new Label("Categoria");
		lCategoria = new ListBox();
		lCategoria.setWidth("150px");
		lCategoria.addItem("Abbigliamento");
		lCategoria.addItem("Casa");
		lCategoria.addItem("Elettronica");
		lCategoria.addItem("Giardinaggio");
		lCategoria.addItem("Sport");
		Label requiredCategoria = new Label("(*)");
		grid.setWidget(4, 0, labelCategoria);
		grid.setWidget(4, 1, lCategoria);
		grid.setWidget(4, 2, requiredCategoria);
		
		verticalpanel.add(grid);
		decoratorpanel.add(verticalpanel);
		//decoratorpanel.getElement().setAttribute("style", "margin-left: 39vw; margin-top: 10vh;");
		this.add(decoratorpanel);
		
		//BUTTON METTI IN VENDITA
		Grid gridButton = new Grid(1,2);
		Button bVendita = new Button("Metti in vendita");
		Button bturnHome = new Button("← Torna alla Home");
		gridButton.setWidget(0, 0, bVendita);
		gridButton.setWidget(0, 1, bturnHome);
		gridButton.getElement().setAttribute("style", "margin-left: 15%; argin-rigth: 15%; padding: 5px");
		
		verticalpanel.add(gridButton);
		
			
	}
}