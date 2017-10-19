package com.luckybidder.client;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
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
import com.luckybidder.shared.Domanda;
import com.luckybidder.shared.Offerta;

public class EliminaDomanda extends HorizontalPanel {
public EliminaDomanda() {
		
		final LuckyBidderServiceAsync instanceLuckyBidderService = LuckyBidderService.Util.getInstance();
		final DecoratorPanel dp = new DecoratorPanel();
		final VerticalPanel vp = new VerticalPanel();
		final HorizontalPanel hp = new HorizontalPanel();
		final Button btnEliminaDom = new Button("Elimina Domanda");
		btnEliminaDom.setEnabled(false);
		vp.add(hp);
		dp.add(vp);
		
		instanceLuckyBidderService.getDomande(new AsyncCallback<ArrayList<Domanda>>() {
			@Override
			public void onFailure(Throwable caught) {
				PopupPanel popup = new PopupPanel(true);
				popup.setWidget(new HTML("<font color='red'>Impossibile estrarre le informazioni: Errore con la conessione del server!</font>"));
				popup.center();
			}

			@Override
			public void onSuccess(ArrayList<Domanda> result) {
				GWT.log(result.toString());
				GWT.log(""+result.size());
				ArrayList<Domanda> resultClear = new ArrayList<Domanda>();
				for(Domanda domanda : result) {
					if(domanda.getIdDomanda()!=-1) {
						resultClear.add(domanda);
					}
				}
				result=resultClear;
				if(result.size()>0) {
					CellTable<Domanda> tableDomanda = new CellTable<Domanda>();
					tableDomanda.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
					TextColumn<Domanda> txtId = new TextColumn<Domanda>() {
						@Override
						public String getValue(Domanda object) {
								String id=String.valueOf(object.getIdDomanda());
							return id;
						}
					};
					tableDomanda.addColumn(txtId, "Id Domanda");
					
					TextColumn<Domanda> txtProdottoRelativo = new TextColumn<Domanda>() {
						@Override
						public String getValue(Domanda object) {
							return object.getNomeProdotto();
						}
					};
					tableDomanda.addColumn(txtProdottoRelativo, "Nome prodotto");
					
					TextColumn<Domanda> txtDaCheutente = new TextColumn<Domanda>() {
						@Override
						public String getValue(Domanda object) {
							return object.getDaCheutente();
						}
					};
					tableDomanda.addColumn(txtDaCheutente, "Richiedente");
					
					tableDomanda.setRowData(0,result);
					
					final SingleSelectionModel<Domanda> sm = new SingleSelectionModel<Domanda>();
					tableDomanda.setSelectionModel(sm);
					sm.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
						@Override
						public void onSelectionChange(SelectionChangeEvent event) {
							final Domanda selected = sm.getSelectedObject();
							if(selected != null) {
								btnEliminaDom.setEnabled(true);
								btnEliminaDom.addClickHandler(new ClickHandler() {
									@Override
									public void onClick(ClickEvent event) {
										instanceLuckyBidderService.eliminaDomanda(selected.getIdDomanda(),new AsyncCallback<Boolean>(){

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
					hp.add(tableDomanda);
					vp.add(btnEliminaDom);
				}else {
					Label vuota = new Label("Non ci sono domande de eliminare");
					hp.add(vuota);
				}
			}
			
		});
		this.add(dp);
	}
}
