package com.luckybidder.client;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.luckybidder.shared.Domanda;
import com.luckybidder.shared.Risposta;

public class DomandaRisposta extends HorizontalPanel{

	public DomandaRisposta( final String usernameVendProdotto) {
		
		final LuckyBidderServiceAsync instanceLuckyBidder = LuckyBidderService.Util.getInstance();
		
		final DecoratorPanel dcPanel = new DecoratorPanel();
		final VerticalPanel vpGeneral = new VerticalPanel();
		final DomandaRisposta thisPanel = this;
		dcPanel.add(vpGeneral);
		dcPanel.getElement().setAttribute("style", "padding-left: 15px; padding-top:5px");
		instanceLuckyBidder.getDomandeToUsername(usernameVendProdotto, new AsyncCallback<ArrayList<Domanda>>() {

			@Override
			public void onFailure(Throwable caughtDomande) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(ArrayList<Domanda> resultDomande) {
				GWT.log("Domande per " + usernameVendProdotto);
				if(resultDomande!=null) {
					if(resultDomande.size()>0) {
						for(final Domanda domanda: resultDomande) {
							instanceLuckyBidder.getRisposta(domanda.getIdDomanda(), new AsyncCallback<Risposta>() {
	
								@Override
								public void onFailure(Throwable caughtRisposta) {
									// TODO Auto-generated method stub
									
								}
	
								@Override
								public void onSuccess(Risposta resultRisposta) {
									
									HorizontalPanel hpGeneral = new HorizontalPanel();
									vpGeneral.add(hpGeneral);
									final VerticalPanel vpDomanda = new VerticalPanel();
									vpDomanda.getElement().setAttribute("style", "margin: 5px");
									final VerticalPanel vpRisposta = new VerticalPanel();
									vpRisposta.getElement().setAttribute("style", "margin: 5px");
									hpGeneral.add(vpDomanda);
									hpGeneral.add(vpRisposta);
									
									//STAMPA DOMANDA
									Label labelDomanda = new Label("DOMANDA \nsu \""+ domanda.getNomeProdotto() + "\"");
									TextArea testoDomanda = new TextArea();
									testoDomanda.setText(domanda.getTestoDomanda());
									testoDomanda.setReadOnly(true);
									testoDomanda.setCharacterWidth(30);
									testoDomanda.setVisibleLines(6);
									testoDomanda.getElement().setAttribute("style", "color: black");
									Label labelFattaDa = new Label("fatta da: " + domanda.getDaCheutente());
									vpDomanda.add(labelDomanda);
									vpDomanda.add(testoDomanda);
									vpDomanda.add(labelFattaDa);
									if(resultRisposta != null) {
										Label labelRisposta = new Label("RISPOSTA");
										TextArea testoRisposta = new TextArea();
										testoRisposta.setText(resultRisposta.getRisposta());
										testoRisposta.setReadOnly(true);
										testoRisposta.setCharacterWidth(30);
										testoRisposta.setVisibleLines(6);
										testoRisposta.getElement().setAttribute("style", "color: black");
										vpRisposta.add(labelRisposta);
										vpRisposta.add(testoRisposta);
										thisPanel.add(dcPanel);
									} else {
										Label labelRisposta = new Label("RISPOSTA a ");
										final TextArea testoRisposta = new TextArea();
										testoRisposta.setCharacterWidth(30);
										testoRisposta.setVisibleLines(6);
										final Button buttonInviaRisposta = new Button("Rispondi");
										vpRisposta.add(labelRisposta);
										vpRisposta.add(testoRisposta);
										vpRisposta.add(buttonInviaRisposta);
										
										buttonInviaRisposta.addClickHandler( new ClickHandler() {

											@Override
											public void onClick(ClickEvent event) {
												instanceLuckyBidder.inviaRisposta(domanda.getIdDomanda(), testoRisposta.getText(), new AsyncCallback<Boolean>() {

													@Override
													public void onFailure(Throwable caught) {
														// TODO Auto-generated method stub
														
													}

													@Override
													public void onSuccess(Boolean result) {
														vpRisposta.remove(testoRisposta);
														vpRisposta.remove(buttonInviaRisposta);
														testoRisposta.setText(testoRisposta.getText());
														testoRisposta.setReadOnly(true);
														testoRisposta.getElement().setAttribute("style", "color: black");
														vpRisposta.add(testoRisposta);
													}
													
												});											
											}
											
										});
										thisPanel.add(dcPanel);
									}
									
								}
								
							});
						}
					}
				
				}
			}
			
		});
	}
}
