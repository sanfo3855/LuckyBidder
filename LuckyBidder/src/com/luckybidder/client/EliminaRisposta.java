package com.luckybidder.client;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
import com.luckybidder.shared.Offerta;
import com.luckybidder.shared.Risposta;

public class EliminaRisposta  extends HorizontalPanel{
	public EliminaRisposta() {
		
		final LuckyBidderServiceAsync instanceLuckyBidderService = LuckyBidderService.Util.getInstance();
		final DecoratorPanel dp = new DecoratorPanel();
		final VerticalPanel vp = new VerticalPanel();
		final HorizontalPanel hp = new HorizontalPanel();
		final Button btnEliminaRisp = new Button("Elimina risposta");
		btnEliminaRisp.setEnabled(false);
		vp.add(hp);
		dp.add(vp);
		
		instanceLuckyBidderService.getRisposte(new AsyncCallback<ArrayList<Risposta>>() {
			@Override
			public void onFailure(Throwable caught) {
				PopupPanel popup = new PopupPanel(true);
				popup.setWidget(new HTML("<font color='red'>Impossibile estrarre le informazioni: Errore con la conessione del server!</font>"));
				popup.center();
				
			}

			@Override
			public void onSuccess(ArrayList<Risposta> result) {
				ArrayList<Risposta> resultClear = new ArrayList<Risposta>();
				for(Risposta risposta : result) {
					if(risposta.getIdRisposta()!=-1) {
						resultClear.add(risposta);
					}
				}
				result=resultClear;
				if(result.size()>0) {
					CellTable<Risposta> tableRisposta = new CellTable<Risposta>();
					tableRisposta.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
					TextColumn<Risposta> txtId = new TextColumn<Risposta>() {
						@Override
						public String getValue(Risposta object) {
								String id=String.valueOf(object.getIdRisposta());
							return id;
						}
					};
					tableRisposta.addColumn(txtId, "Id risposta");
					
					TextColumn<Risposta> txtIdDom = new TextColumn<Risposta>() {
						@Override
						public String getValue(Risposta object) {
							String id=String.valueOf(object.getIdDomandaRelativa());
							return id;
						}
					};
					tableRisposta.addColumn(txtIdDom, "Id domanda relativa");
					tableRisposta.setRowData(0,result);
					
					final SingleSelectionModel<Risposta> sm = new SingleSelectionModel<Risposta>();
					tableRisposta.setSelectionModel(sm);
					
					sm.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
						@Override
						public void onSelectionChange(SelectionChangeEvent event) {
							final Risposta selected = sm.getSelectedObject();
							if(selected != null) {
								btnEliminaRisp.setEnabled(true);
								btnEliminaRisp.addClickHandler(new ClickHandler() {

									@Override
									public void onClick(ClickEvent event) {
										instanceLuckyBidderService.eliminaRisposta(selected.getIdRisposta(),new AsyncCallback<Boolean>(){

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
					hp.add(tableRisposta);
					vp.add(btnEliminaRisp);
				}else {
					Label vuota = new Label("Non ci sono offerta de eliminare");
					hp.add(vuota);
				}
			}
			
		});
		this.add(dp);
	}
}
