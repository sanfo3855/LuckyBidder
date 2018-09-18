package com.luckybidder.client;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.luckybidder.shared.Prodotto;

public class EliminaProdotto extends HorizontalPanel {
	public EliminaProdotto(){
		
		final LuckyBidderServiceAsync instanceLuckyBidderService = LuckyBidderService.Util.getInstance();
		final DecoratorPanel decoratorPanel = new DecoratorPanel();
		final VerticalPanel verticalPanel = new VerticalPanel();
		final HorizontalPanel horizontalPanel = new HorizontalPanel();
		final Button btnEliminaProd = new Button("Elimina Prodotto");
		final Label labelSelectedProdotto;
		btnEliminaProd.setEnabled(false);
		verticalPanel.add(horizontalPanel);
		
		instanceLuckyBidderService.getProdotti(new AsyncCallback<ArrayList<Prodotto>>() {
			@Override
			public void onFailure(Throwable caught) {
				PopupPanel poup = new PopupPanel(true);
				poup.setWidget(new HTML("<font color='red'>Impossibile estrarre le informazioni: Errore nella connessione con il server</font>"));
				poup.center();
			}

			@Override
			public void onSuccess(ArrayList<Prodotto> result) {
				ArrayList<Prodotto> resultClear = new ArrayList<Prodotto>();
				for(Prodotto prodotto : result) {
					if(prodotto.getIdProdotto()!=-1) {
						resultClear.add(prodotto);
					}
				}
				result = resultClear;
				if(result.size()>0) {
					CellTable<Prodotto> table = new CellTable<Prodotto>();
					table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
					TextColumn<Prodotto> txtIdProdotto = new TextColumn<Prodotto>() {
						@Override
						public String getValue(Prodotto product) {
							String id = "";
							if(product.getIdProdotto()!=-2) {
								id = String.valueOf(product.getIdProdotto());
							} else {
								id = "Non";
							}
							return id ;
						}
					};
					table.addColumn(txtIdProdotto, "Id prodotto");
					
					TextColumn<Prodotto> txtColName = new TextColumn<Prodotto>(){
						@Override
						public String getValue(Prodotto product) {
							return product.getNomeProdotto();
						}
					};
					table.addColumn(txtColName, "Prodotti in vendita");
					
					TextColumn<Prodotto> txtColScadenza = new TextColumn<Prodotto>() {
						@Override
						public String getValue(Prodotto product) {
							String stToPrint = "";
							if(product.getDataScadenza() != null) {
								Date dateToConvert = product.getDataScadenza();
								DateTimeFormat fm = DateTimeFormat.getFormat("dd/MM/yyyy");
								stToPrint = fm.format(dateToConvert);
							} else {
								stToPrint = "prodotti";
							}
							return stToPrint;
						}
					};
					table.addColumn(txtColScadenza, "Scadenza");
					table.setRowData(0,result);
					//aggiungo selection model per prendere il prodotto selezionato
					final SingleSelectionModel<Prodotto> selectionModel = new SingleSelectionModel<Prodotto>();
					table.setSelectionModel(selectionModel);
					//seleziono il prodotto
					selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
						public void onSelectionChange(SelectionChangeEvent event) {
							final Prodotto selected = selectionModel.getSelectedObject();
							//GWT.log(selected.toString());
							if(selected != null) {
								btnEliminaProd.setEnabled(true);
								btnEliminaProd.addClickHandler(new ClickHandler() {
									public void onClick(ClickEvent event) {
										instanceLuckyBidderService.eliminaProdotto(selected.getIdProdotto(),new AsyncCallback<Boolean>() {
	
											@Override
											public void onFailure(Throwable caught) {
												PopupPanel poup = new PopupPanel(true);
												poup.setWidget(new HTML("<font color='red'>Impossibile estrarre le informazioni: Errore nella connessione con il server</font>"));
												poup.center();
												
											}
	
											@Override
											public void onSuccess(Boolean result) {
												TopBar topbar = new TopBar();
												GestioneCategorie gestioneCategorie = new GestioneCategorie();
												RootPanel.get().clear();
												RootPanel.get().add(topbar);
												RootPanel.get().add(gestioneCategorie);
												
												PopupPanel poup = new PopupPanel(true);
												poup.setWidget(new HTML("<font color='green'>Prodotto eliminato!</font>"));
												poup.center();
												
											}
											
										});
									}
								});
							}
						}
					});
					horizontalPanel.add(table);
					verticalPanel.add(btnEliminaProd);
					decoratorPanel.add(verticalPanel);
				}else {
					Label vuota = new Label("Non ci sono prodotti da eliminare");
					horizontalPanel.add(vuota);
					decoratorPanel.add(verticalPanel);
					
				}
			}
		});
		this.add(decoratorPanel);
	}
	
}
