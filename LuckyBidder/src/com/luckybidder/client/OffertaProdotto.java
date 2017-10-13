package com.luckybidder.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DoubleBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;

public class OffertaProdotto extends DialogBox {
	
	public OffertaProdotto(final int id) {
		
		final LuckyBidderServiceAsync instanceLuckyBidderService = LuckyBidderService.Util.getInstance();
		
		final DoubleBox prezzoOfferta = new DoubleBox();
		final Button btnOffri = new Button("");


		final HorizontalPanel horizontalPanel = new HorizontalPanel();
		this.add(horizontalPanel);
		horizontalPanel.setSize("100%", "550px");

		final VerticalPanel verticalpanel = new VerticalPanel();
		verticalpanel.setSize("301px", "100%");
		horizontalPanel.add(verticalpanel);

		Label VendiOggetto = new Label("SPECIFICHE DELL'OGGETTO");
		verticalpanel.add(VendiOggetto);
		VendiOggetto.setSize("224px", "25px");
		
		
		
		String nome = "M";
		String descrizione = "M";
		final double prezzo = 5;
		String scadenza = "M";
		int categoria;
		final String username = "MM";
		final String stato = "APERTA";
		final String vincitore = "IO";
		
		HTMLPanel panel = new HTMLPanel("<center>" + stato + "</center>");
		verticalpanel.add(panel);

		//Si crea una griglia contenente le informazioni dell'oggetto
		final Grid grid = new Grid(8, 2);
		grid.setCellPadding(10);
		grid.setCellSpacing(10);
		verticalpanel.add(grid);
		grid.setSize("324px", "206px");

		Label lblOggetto1 = new Label("Oggetto:");
		grid.setWidget(0, 0, lblOggetto1);
		lblOggetto1.setSize("85px", "18px");

		Label lblNomeOggetto = new Label(nome);
		grid.setWidget(0, 1, lblNomeOggetto);

		Label lblDescrizione2 = new Label("Descrizione:");
		grid.setWidget(1, 0, lblDescrizione2);
		lblDescrizione2.setSize("74px", "18px");

		Label lblDescrizione = new Label(descrizione);
		grid.setWidget(1, 1, lblDescrizione);

		Label lblVenditore = new Label("Venditore:");
		grid.setWidget(2, 0, lblVenditore);
		lblOggetto1.setSize("85px", "18px");

		Label lblUserVenditore = new Label(username);
		grid.setWidget(2, 1, lblUserVenditore);

		Label lblPrezzo = new Label("Prezzo iniziale:");
		grid.setWidget(3, 0, lblPrezzo);
		lblOggetto1.setSize("85px", "18px");

		Label lblPrezzoIniziale = new Label(Double.toString(prezzo));
		grid.setWidget(3, 1, lblPrezzoIniziale);

		final Label lblData = new Label("Data Scadenza:");
		final Label lblDate = new Label(scadenza);
		
		grid.setWidget(6, 0, lblData);
		grid.setWidget(6, 1, lblDate);

		
		prezzoOfferta.setAlignment(TextAlignment.RIGHT);
		verticalpanel.add(prezzoOfferta);
		verticalpanel.setCellVerticalAlignment(prezzoOfferta, HasVerticalAlignment.ALIGN_MIDDLE);
		verticalpanel.setCellHorizontalAlignment(prezzoOfferta, HasHorizontalAlignment.ALIGN_CENTER);
		prezzoOfferta.setSize("147px", "28px");

		btnOffri.setText("FAI OFFERTA");
		verticalpanel.add(btnOffri);
		verticalpanel.setCellVerticalAlignment(btnOffri, HasVerticalAlignment.ALIGN_BOTTOM);
		verticalpanel.setCellHorizontalAlignment(btnOffri, HasHorizontalAlignment.ALIGN_CENTER);
		btnOffri.setWidth("132px");

	}
}
