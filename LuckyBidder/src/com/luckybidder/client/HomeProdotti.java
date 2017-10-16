package com.luckybidder.client;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.luckybidder.shared.Prodotto;

public class HomeProdotti extends HorizontalPanel{
	
	public HomeProdotti(){
		final LuckyBidderServiceAsync instanceLuckyBidderService = LuckyBidderService.Util.getInstance();
		DecoratorPanel decoratorPanel = new DecoratorPanel();
		final VerticalPanel verticalPanel = new VerticalPanel();
		
		instanceLuckyBidderService.getProdotti(new AsyncCallback<ArrayList<Prodotto>>(){
			@Override
			public void onFailure(Throwable caught) {
				PopupPanel popup = new PopupPanel(true);
				popup.setWidget(new HTML("<font color='red'>Impossibile estrarre le informazioni: Errore nella connessione con il server</font>"));
				popup.center();	

			};
			
			@Override
			public void onSuccess(ArrayList<Prodotto> result) {
				
				ArrayList<Prodotto> prodotti = new ArrayList<Prodotto>(result);
				CellTable<Prodotto> table = new CellTable<Prodotto>();
				table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
				
				TextColumn<Prodotto> txtColName = new TextColumn<Prodotto>() {
					@Override

					public String getValue(Prodotto object) {
						return object.getNomeProdotto();
					}
				};
				table.addColumn(txtColName, "PRODOTTI IN VENDITA");
				
				TextColumn<Prodotto> txtColScadenza = new TextColumn<Prodotto>() {
					@Override
					
					public String getValue(Prodotto object) {
						String stToPrint = "";
						if(object.getDataScadenza() != null) {
							Date dateToConvert = object.getDataScadenza();
							DateTimeFormat fm = DateTimeFormat.getFormat("dd/MM/yyyy");
							stToPrint = fm.format(dateToConvert);
						}
						return stToPrint;
						
					}
				};
				table.addColumn(txtColScadenza, "SCADENZA");
				
				
				final SingleSelectionModel<Prodotto> selectionModel = new SingleSelectionModel<Prodotto>();
				table.setSelectionModel(selectionModel);
				selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						Prodotto selected = selectionModel.getSelectedObject();
						if (selected != null) {
							OffertaProdotto visualizzaInfo = new OffertaProdotto(selected.getIdProdotto(),Session.getInstance().getSession().getUsername(), "Home");
							visualizzaInfo.center();
							visualizzaInfo.show();
						}
					}
				});
				
				table.setRowCount(prodotti.size(), true);
				table.setRowData(0, prodotti);
				verticalPanel.add(table);	
							
			}
					
		});
		
		decoratorPanel.add(verticalPanel);
		decoratorPanel.getElement().setAttribute("style", "margin-left: 14px; margin-top: 6px");
		this.add(decoratorPanel);
		
	}
}
