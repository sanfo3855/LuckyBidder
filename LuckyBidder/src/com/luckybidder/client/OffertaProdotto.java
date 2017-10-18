package com.luckybidder.client;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DoubleBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.luckybidder.shared.Domanda;
import com.luckybidder.shared.Offerta;
import com.luckybidder.shared.Prodotto;
import com.luckybidder.shared.Risposta;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;

public class OffertaProdotto extends DialogBox {
	protected String fromPanel;
	private String filtro;
	
	public OffertaProdotto(final int id, final String session, final String fromPanel, final String filtro) {
		this.fromPanel = fromPanel;
		this.filtro = filtro;
		final LuckyBidderServiceAsync instanceLuckyBidderService = LuckyBidderService.Util.getInstance();
		//HorizontalPanel generale
		final HorizontalPanel hpGeneral = new HorizontalPanel();
		//VerticalPanel relativo all'offerta
		final VerticalPanel vpOfferta = new VerticalPanel();
		//VerticalPanel relativo a domanda e risposta
		final VerticalPanel vpDomandaRisposta = new VerticalPanel();
		
		
		final DoubleBox prezzoOfferta = new DoubleBox();
		final Button btnOffri = new Button("");
		final Button btnProfilo = new Button("");


		final HorizontalPanel horizontalPanel = new HorizontalPanel();
		vpOfferta.add(horizontalPanel);
		hpGeneral.add(vpOfferta);
		this.add(hpGeneral);
		//hpGeneral.add(vpDomandaRisposta);
		horizontalPanel.setSize("100%", "550px");

		final VerticalPanel verticalpanel = new VerticalPanel();
		verticalpanel.setSize("301px", "100%");
		horizontalPanel.add(verticalpanel);

		Label VendiOggetto = new Label("SPECIFICHE DELL'OGGETTO");
		verticalpanel.add(VendiOggetto);
		VendiOggetto.setSize("224px", "25px");
		
		instanceLuckyBidderService.getProdottoSingolo(id ,new AsyncCallback<Prodotto>(){
			@Override
			public void onFailure(Throwable caught) {
				PopupPanel popup = new PopupPanel(true);
				popup.setWidget(new HTML("<font color='red'>Impossibile estrarre le informazioni: Errore nella connessione con il server</font>"));
				popup.center();
			}
		
			@Override
			public void onSuccess(Prodotto result) {
				String nome = result.getNomeProdotto();
				String descrizione = result.getDescrizione();
				final double prezzo = result.getBaseAsta();
				Date scadenza = result.getDataScadenza();
				String categoria = result.getCategoria();
				final String username = result.getUsername();
				final String stato = result.getStato();
				final String vincitore = result.getVincitore();
				final String usernameVenditore = result.getUsername();
				final String nomeProdotto = result.getNomeProdotto();

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
				final Label lblDate = new Label(""+scadenza);
				
				if(stato.equals("APERTA")) {
					instanceLuckyBidderService.getMaxOfferta(id, new AsyncCallback<Offerta>() {
						
						@Override
						public void onFailure(Throwable caught) {
							PopupPanel popup = new PopupPanel(true);
							popup.setWidget(new HTML("<font color='red'>Impossibile estrarre le informazioni: Errore nella connessione con il server</font>"));
							popup.center();
						}
						
						@Override
						public void onSuccess(final Offerta resultOff) {
							Label lblOfferta = new Label("Offerta più alta:");
							grid.setWidget(4, 0, lblOfferta);
							
							if(resultOff.getPrezzo() == 0) {
								Label lblPrezzo = new Label(" - ");
								grid.setWidget(4, 1, lblPrezzo);
							} else {
								Label lblPrezzo = new Label(Double.toString(resultOff.getPrezzo()));
								grid.setWidget(4, 1, lblPrezzo);
							}
							
							String migliorOfferente = resultOff.getUsername();
							Label lblOffertaMax = new Label("Miglior offerente:");
							grid.setWidget(5, 0, lblOffertaMax);
							
							Label lblOffertaMaxUser = new Label(migliorOfferente);
							grid.setWidget(5, 1, lblOffertaMaxUser);
							
							if(migliorOfferente.equals(Session.getInstance().getSession().getUsername())) {
								prezzoOfferta.setVisible(false);
								btnOffri.setVisible(false);
							}
						}
					});
				} else  {
					instanceLuckyBidderService.getMaxOfferta(id, new AsyncCallback<Offerta>() {

						@Override
						public void onFailure(Throwable caught) {
							PopupPanel popup = new PopupPanel(true);
							popup.setWidget(new HTML("<font color='red'>Impossibile estrarre le informazioni: Errore nella connessione con il server</font>"));
							popup.center();	
							
						}

						@Override
						public void onSuccess(Offerta resultOff) {
							Label lblPrezzo = new Label();
							Label lblPrezzoAcq = new Label("Prezzo di acquisto:");
							grid.setWidget(5, 0, lblPrezzoAcq);

							if(resultOff.getPrezzo() == 0) {
								lblPrezzo.setText(" - ");
							} else {
								lblPrezzo.setText(Double.toString(resultOff.getPrezzo()));
							}
							
							grid.setWidget(5, 1, lblPrezzo);
							
							Label lblVincitore = new Label("Vincitore asta:");
							grid.setWidget(4, 0, lblVincitore);
							
							Label lblVinUser = new Label(vincitore);
							grid.setWidget(4, 1, lblVinUser);
						}
						
					});
				}
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
				if(usernameVenditore.contentEquals(Session.getInstance().getSession().getUsername())) {
					btnOffri.setEnabled(false);
					prezzoOfferta.setEnabled(false);
				}
				btnProfilo.setText("Chiudi");
				verticalpanel.add(btnProfilo);
				verticalpanel.setCellVerticalAlignment(btnProfilo, HasVerticalAlignment.ALIGN_BOTTOM);
				verticalpanel.setCellVerticalAlignment(btnProfilo, HasVerticalAlignment.ALIGN_BOTTOM);
				
				btnProfilo.addClickHandler(new ClickHandler(){
					@Override
					public void onClick(ClickEvent event) {
						RootPanel.get().clear();
						TopBar topbar = new TopBar();
						RootPanel.get().add(topbar);
						if(fromPanel.equals("Home")) {
							btnProfilo.setText("Torna alla Home");
							HomeProdotti home = new HomeProdotti(filtro);
							RootPanel.get().add(home);
						} else if(fromPanel.equals("Profilo")) {
							btnProfilo.setText("Torna al Profilo");
							Profilo profilo = new Profilo();
							RootPanel.get().add(profilo);
						}
						
						
						
						
						
					}
				});
				
				if(Session.getInstance().getSession() != null) {
					btnOffri.addClickHandler(new ClickHandler(){
						public void onClick(ClickEvent event) {
							instanceLuckyBidderService.getMaxOfferta(id, new AsyncCallback<Offerta>(){
									
								@Override
								public void onFailure(Throwable caught) {
									PopupPanel popup = new PopupPanel(true);
									popup.setWidget(new HTML("<font color='red'>Impossibile estrarre le informazioni: Errore nella connessione con il server</font>"));
									popup.center();	
									
								}

								@Override
								public void onSuccess(Offerta resultOff) {
									boolean controllaP = true;
									if(resultOff.getPrezzo() != 0) {
										double prezzoMax = resultOff.getPrezzo();
										if(prezzoOfferta.getValue() == null || prezzoOfferta.getValue() < prezzo || prezzoOfferta.getValue() <= prezzoMax ) {
											controllaP = false;
										}
									}
									if(prezzoOfferta.getValue() == null || prezzoOfferta.getValue() < prezzo || !controllaP) {
										PopupPanel popup = new PopupPanel(true);
										popup.setWidget(new HTML("<font color='red'>Offerta non valida!</font>"));
										popup.center();
									}else {
										double offriPrezzo = prezzoOfferta.getValue();
										
										DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
										Date date = new Date();
										Date dataGiusta = null;
										String dataformat = dateFormat.format(date);
										//String oggi = dateFormat.format(date);
										
										Offerta offerta = new Offerta();
								            try {
								                DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
								                dataGiusta = dateTimeFormat.parse(dataformat);
								                offerta.setDataOfferta(dataGiusta);
								            } catch (Exception e)
								            {
								            	System.out.println(e);
								            }
										
										offerta.setIdProdotto(id);
										offerta.setUsername(Session.getInstance().getSession().getUsername());
										offerta.setPrezzo(offriPrezzo);
										
										instanceLuckyBidderService.offri(offerta, new AsyncCallback<Boolean>(){
											
											@Override
											public void onFailure(Throwable caught) {
												PopupPanel popup = new PopupPanel(true);
												popup.setWidget(new HTML("<font color='red'>Impossibile estrarre le informazioni: Errore nella connessione con il server</font>"));
												popup.center();	
												
											}

											@Override
											public void onSuccess(Boolean result) {
											
													OffertaProdotto offertaProdotto = new OffertaProdotto(id,Session.getInstance().getSession().getUsername(), fromPanel, filtro);
													OffertaProdotto.this.hide();
													offertaProdotto.center();
													offertaProdotto.show();
													PopupPanel popup = new PopupPanel(true);
													popup.setWidget(new HTML("<font color='green'size='5'>Offerta inserita correttamente!</font>"));
													popup.center();
													
													TopBar topbar = new TopBar();
													HomeProdotti homeProdotti = new HomeProdotti(filtro);
													RootPanel.get().clear();
													RootPanel.get().add(topbar);
													RootPanel.get().add(homeProdotti);
												
												
											}
											
										});
							
									}
								}
							
							});	
						}
					});
				} else {
					prezzoOfferta.setEnabled(false);
					btnOffri.setEnabled(false);
				}
				
				//Domanda e Risposta
				if(Session.getInstance().getSession() != null) {
					if(!Session.getInstance().getSession().getUsername().equals("admin") && !Session.getInstance().getSession().getUsername().equals(usernameVenditore)) {
						hpGeneral.add(vpDomandaRisposta);
						final Label labelDomanda = new Label("DOMANDA");
						instanceLuckyBidderService.getDomanda(Session.getInstance().getSession().getUsername(),id, new AsyncCallback<Domanda>() {
						
							@Override
							public void onFailure(Throwable caught) {
								PopupPanel popup = new PopupPanel(true);
								popup.setWidget(new HTML("<font color='red'>Impossibile ottenere le domanda: Errore nella connessione con il server</font>"));
								popup.center();	
							}

							@Override
							public void onSuccess(Domanda result) {
								if(result==null) {
									labelDomanda.getElement().setAttribute("style", "margin:5px; font-size:20px");
									final Label faiLaDomanda = new Label("Scrivi qua sotto la tua domanda:");
									faiLaDomanda.getElement().setAttribute("style", "margin:5px");
									final TextArea taDomanda = new TextArea();
									taDomanda.getElement().setAttribute("style", "margin:5px");
									final Button faiDomandaButton = new Button("Manda Domanda");
									faiDomandaButton.getElement().setAttribute("style", "margin:5px");
									taDomanda.setCharacterWidth(30);
									taDomanda.setVisibleLines(6);
								    vpDomandaRisposta.add(labelDomanda);
								    vpDomandaRisposta.add(faiLaDomanda);
									vpDomandaRisposta.add(taDomanda);
									vpDomandaRisposta.add(faiDomandaButton);
									
									faiDomandaButton.addClickHandler( new ClickHandler() {

										@Override
										public void onClick(ClickEvent event) {
											instanceLuckyBidderService.mandaDomanda(nomeProdotto, taDomanda.getValue(), Session.getInstance().getSession().getUsername(),id, usernameVenditore, new AsyncCallback<Boolean>() {

												@Override
												public void onFailure(Throwable caught) {
													
													
												}

												@Override
												public void onSuccess(Boolean result) {
													vpDomandaRisposta.remove(faiLaDomanda);
													vpDomandaRisposta.remove(taDomanda);
													vpDomandaRisposta.remove(faiDomandaButton);
													Label haiChiesto = new Label("Hai chiesto:");
													haiChiesto.getElement().setAttribute("style", "margin:5px");
													vpDomandaRisposta.add( haiChiesto);
													taDomanda.setText( taDomanda.getValue());
													taDomanda.setReadOnly(true);
													vpDomandaRisposta.add( taDomanda );
													
													
													
												}
												
											});
											
										}
										
									});
								} else {
									labelDomanda.	getElement().setAttribute("style", "margin:5px; font-size:20px");
									vpDomandaRisposta.add(labelDomanda);
									Domanda domandaReturn = result;
									Label haiChiesto = new Label("Hai chiesto:");
									haiChiesto.getElement().setAttribute("style", "margin:5px");
									vpDomandaRisposta.add( haiChiesto);
									TextArea taDomanda = new TextArea();
									taDomanda.setText( result.getTestoDomanda() );
									taDomanda.setCharacterWidth(30);
									taDomanda.setVisibleLines(6);
									taDomanda.setReadOnly(true);
									vpDomandaRisposta.add( taDomanda );
									
									instanceLuckyBidderService.getRisposta(result.getIdDomanda(), new AsyncCallback<Risposta>() {

										@Override
										public void onFailure(Throwable caught) {
											// TODO Auto-generated method stub
											
										}

										@Override
										public void onSuccess(Risposta result) {
											
											if(result != null) {
												Label risposta = new Label("RISPOSTA");
												risposta.getElement().setAttribute("style", "margin:5px; font-size:20px; margin-left:15px");
												vpDomandaRisposta.add(risposta);
												Label labelRispondi = new Label("Rispondi:");
												labelRispondi.getElement().setAttribute("style", "margin:5px; margin-left:15px");
												vpDomandaRisposta.add(labelRispondi);
												
												final TextArea taRisposta = new TextArea();
												taRisposta.setCharacterWidth(30);
												taRisposta.setVisibleLines(6);
												taRisposta.setText( result.getRisposta() );
												taRisposta.setReadOnly(true);
												vpDomandaRisposta.add(taRisposta);
												Button rispondiButton = new Button("Rispondi");
												vpDomandaRisposta.add(rispondiButton);
											}
											
										}
										
									});
										
								}
							}
							
						});
					}
				}
				
			}
			
			
		});

	}
}
