package com.luckybidder.client;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.luckybidder.shared.Categoria;
import com.luckybidder.shared.Prodotto;

public class HomeProdotti extends HorizontalPanel{
	
	private String filtroCategoria;
	private ArrayList<Categoria> categorieFiglieFiltro = new ArrayList<Categoria>();
	
	private void categorieFiglie(ArrayList<Categoria> list, Categoria padre) {
		
		if(padre != null) {
			GWT.log(padre.toString());
			if(padre.getNomeCategoria().equals("Stop")) {
				GWT.log("STOP");
				return;
			}
			for(Categoria pointed : list) {
					if(pointed.getPadre().getNomeCategoria().equals(padre.getNomeCategoria())) {
						if(pointed.getCategorieFiglie().size()>0) {
							categorieFiglie(list, pointed);
						} else {
							categorieFiglie(list, new Categoria("Stop"));
						}
						pointed.toString();
						categorieFiglieFiltro.add(pointed);
						
					}
			}
		} else {
			GWT.log("Padre == Null");
			categorieFiglieFiltro = list;
		}
		
		
	}
	public HomeProdotti(final String filtroCategoria){
		
		this.filtroCategoria = filtroCategoria;
		final LuckyBidderServiceAsync instanceLuckyBidderService = LuckyBidderService.Util.getInstance();
		DecoratorPanel decoratorPanel = new DecoratorPanel();
		final VerticalPanel verticalPanel = new VerticalPanel();
		
		final VerticalPanel vpFiltro = new VerticalPanel();
		final Label labelFiltro;
		
		if(this.filtroCategoria.equals(null) ) {
			labelFiltro = new Label("CATEGORIA: Tutte le Categorie");
		} else {
			labelFiltro = new Label("CATEGORIA: "+ this.filtroCategoria);
		}
		labelFiltro.getElement().setAttribute("style", "font-size:20px");
		final HorizontalPanel filtro = new HorizontalPanel();
		filtro.add(labelFiltro);
		vpFiltro.add(filtro);
		
		final HorizontalPanel hpFiltraAncora = new HorizontalPanel();
		final Button filtraAncora = new Button("Filtra Ancora");
		final ListBox listCategorieDisponibili = new ListBox();
		instanceLuckyBidderService.getAllCategorie( new AsyncCallback<ArrayList<Categoria>>() {

			@Override
			public void onFailure(Throwable caught) {
				PopupPanel popup = new PopupPanel(true);
				popup.setWidget(new HTML("<font color='red'>Impossibile otttenere categorie: Errore nella connessione con il server</font>"));
				popup.center();	
			}

			@Override
			public void onSuccess(ArrayList<Categoria> result) {
				Categoria p = null;
				if(filtroCategoria != null) {
					p = new Categoria(filtroCategoria);
				}
				categorieFiglie(result, p);
				int i = 0;
				for(Categoria cat : categorieFiglieFiltro) {
					listCategorieDisponibili.addItem(cat.getNomeCategoria());
				}
				if(categorieFiglieFiltro.size()>0) {
					
					listCategorieDisponibili.getElement().setAttribute("style", "margin-top: 5px; margin-right: 5px");
					hpFiltraAncora.add(listCategorieDisponibili);
					hpFiltraAncora.add(filtraAncora);
					
				}
			}
			
		});
		
		filtraAncora.addClickHandler( new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get().clear();
				RootPanel.get().add( new TopBar());
				RootPanel.get().add( new HomeProdotti( listCategorieDisponibili.getSelectedItemText() ));
			}
			
		});
		
		Button resetFiltro = new Button("Reset Filtri");
		resetFiltro.addClickHandler( new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				HomeProdotti home = new HomeProdotti(null);
				RootPanel.get().clear();
				RootPanel.get().add( new TopBar());
				RootPanel.get().add(home);
			}
			
		});
		
		vpFiltro.add(hpFiltraAncora);
		hpFiltraAncora.getElement().setAttribute("style", "margin:3px; margin-left:0px");
		vpFiltro.add(resetFiltro);
		vpFiltro.getElement().setAttribute("style", "margin: 10px");
		
		instanceLuckyBidderService.getProdotti(new AsyncCallback<ArrayList<Prodotto>>(){
			@Override
			public void onFailure(Throwable caught) {
				PopupPanel popup = new PopupPanel(true);
				popup.setWidget(new HTML("<font color='red'>Impossibile estrarre le informazioni: Errore nella connessione con il server</font>"));
				popup.center();	

			};
			
			@Override
			public void onSuccess(ArrayList<Prodotto> result) {
				
				ArrayList<Prodotto> prodotti = new ArrayList<Prodotto>();
				for(Prodotto prodotto : result) {
					if(prodotto.getCategoria().equals(filtroCategoria)) {
						prodotti.add(prodotto);
					} else {
						for(Categoria catFiglia : categorieFiglieFiltro) {
							if(prodotto.getCategoria().equals(catFiglia.getNomeCategoria())) {
								prodotti.add(prodotto);
							}
						}
					}
				};
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
							if(Session.getInstance().getSession() != null) {
								OffertaProdotto visualizzaInfo = new OffertaProdotto(selected.getIdProdotto(),Session.getInstance().getSession().getUsername(), "Home", filtroCategoria);
								visualizzaInfo.center();
								visualizzaInfo.show();
							} else {
								OffertaProdotto visualizzaInfo = new OffertaProdotto(selected.getIdProdotto(),"", "Home", filtroCategoria);
								visualizzaInfo.center();
								visualizzaInfo.show();
							}
							
						}
					}
				});
				
				table.setRowCount(prodotti.size(), true);
				table.setRowData(0, prodotti);
				verticalPanel.add(table);	
							
			}
					
		});
		HorizontalPanel hpGeneral = new HorizontalPanel();
		hpGeneral.add(vpFiltro);
		hpGeneral.add(verticalPanel);
		decoratorPanel.add(hpGeneral);
		decoratorPanel.getElement().setAttribute("style", "margin-left: 14px; margin-top: 6px");
		this.add(decoratorPanel);
		
	}
}
