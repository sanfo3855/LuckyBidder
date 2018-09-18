package com.luckybidder.client;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
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
import com.luckybidder.shared.Offerta;

public class EliminaOfferta extends HorizontalPanel {
	public EliminaOfferta() {
		
		final LuckyBidderServiceAsync instanceLuckyBidderService = LuckyBidderService.Util.getInstance();
		final DecoratorPanel dp = new DecoratorPanel();
		final VerticalPanel vp = new VerticalPanel();
		final HorizontalPanel hp = new HorizontalPanel();
		final Button btnEliminaOff = new Button("Elimina Offerta");
		btnEliminaOff.setEnabled(false);
		vp.add(hp);
		dp.add(vp);
		
		instanceLuckyBidderService.getAllOfferte(new AsyncCallback<ArrayList<Offerta>>() {
			@Override
			public void onFailure(Throwable caught) {
				PopupPanel popup = new PopupPanel(true);
				popup.setWidget(new HTML("<font color='red'>Impossibile estrarre le informazioni: Errore con la conessione del server!</font>"));
				popup.center();
			}

			@Override
			public void onSuccess(ArrayList<Offerta> result) {
				GWT.log(result.toString());
				GWT.log(""+result.size());
				ArrayList<Offerta> resultClear = new ArrayList<Offerta>();
				for(Offerta offerta : result) {
					if(offerta.getId()!=-1) {
						resultClear.add(offerta);
					}
				}
				result=resultClear;
				if(result.size()>0) {
					CellTable<Offerta> tableOfferta = new CellTable<Offerta>();
					tableOfferta.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
					TextColumn<Offerta> txtId = new TextColumn<Offerta>() {
						@Override
						public String getValue(Offerta object) {
								String id=String.valueOf(object.getId());
							return id;
						}
					};
					tableOfferta.addColumn(txtId, "Id offerta");
					
					TextColumn<Offerta> txtNomeProdotto = new TextColumn<Offerta>() {
						@Override
						public String getValue(Offerta object) {
							return object.getNomeProdotto();
						}
					};
					tableOfferta.addColumn(txtNomeProdotto, "Nome prodotto");
					
					TextColumn<Offerta> txtOfferente = new TextColumn<Offerta>() {
						@Override
						public String getValue(Offerta object) {
							return object.getUsername();
						}
					};
					tableOfferta.addColumn(txtOfferente, "Offerente");
					
					TextColumn<Offerta> txtData = new TextColumn<Offerta>() {
						@Override
						public String getValue(Offerta object) {
							String stToPrint = "";
							if(object.getDataOfferta() != null) {
								Date dateToConvert = object.getDataOfferta();
								DateTimeFormat fm = DateTimeFormat.getFormat("dd/MM/yyyy");
								stToPrint = fm.format(dateToConvert);
							}
							return stToPrint;
						}
					};
					tableOfferta.addColumn(txtData, "Data offerta");
					tableOfferta.setRowData(0,result);
					
					final SingleSelectionModel<Offerta> sm = new SingleSelectionModel<Offerta>();
					tableOfferta.setSelectionModel(sm);
					sm.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
						@Override
						public void onSelectionChange(SelectionChangeEvent event) {
							final Offerta selected = sm.getSelectedObject();
							if(selected != null) {
								btnEliminaOff.setEnabled(true);
								btnEliminaOff.addClickHandler(new ClickHandler() {
									@Override
									public void onClick(ClickEvent event) {
										instanceLuckyBidderService.eliminaOfferta(selected.getId(),new AsyncCallback<Boolean>(){

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
												poup.setWidget(new HTML("<font color='green'>Offerta eliminato!</font>"));
												poup.center();
												
											}
											
										});
										
									}
									
								});
							}
							
						}
					});
					hp.add(tableOfferta);
					vp.add(btnEliminaOff);
				}else {
					Label vuota = new Label("Non ci sono offerta de eliminare");
					hp.add(vuota);
				}
			}
			
		});
		this.add(dp);
	}

}
