package com.luckybidder.client;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.luckybidder.shared.Categoria;

public class GestioneCategorie extends VerticalPanel {
	
	private Button bAggiungiCategoria;
	private TextBox tAggiungiCategoria;

	private Button bAggiungiSottoCategoria;
	private TextBox tAggiungiSottoCategoria;
	private Button bModificaCategoria;
	private TextBox tModificaCategoria;
	private Label labelSelectedCategoria;
	
	protected ArrayList<Categoria> listaStampati = new ArrayList<Categoria>();
	private TreeItem root = new TreeItem();
	
	
	public GestioneCategorie() {
		
		final LuckyBidderServiceAsync instanceLuckyBidderService = LuckyBidderService.Util.getInstance();
		DecoratorPanel decoratorPanel = new DecoratorPanel();
		
		/**********************
		 * AGGIUNGI CATEGORIA *
		 **********************/
		final VerticalPanel vpGeneral = new VerticalPanel();
		bAggiungiCategoria = new Button("+ Categoria Generale");
		tAggiungiCategoria = new TextBox();
		final HorizontalPanel hpAggiungiCategoria = new HorizontalPanel();
		hpAggiungiCategoria.add(tAggiungiCategoria);
		hpAggiungiCategoria.add(bAggiungiCategoria);
		vpGeneral.add(hpAggiungiCategoria);
		vpGeneral.getElement().setAttribute("style", "margin: 10px");
		bAggiungiCategoria.addClickHandler( new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Categoria addCategoria = new Categoria(tAggiungiCategoria.getValue());
				instanceLuckyBidderService.aggiungiCategoria(addCategoria, null, new AsyncCallback<Boolean>() {

					@Override
					public void onFailure(Throwable caught) {
						PopupPanel popup = new PopupPanel(true);
						popup.setWidget( new HTML("<font color='red'>Impossibile inserire una nuova categoria: Errore nella connessione con il server.<br>"+caught+"</font>"));
						popup.center();
					}

					@Override
					public void onSuccess(Boolean result) {
						if(result == true) {
							PopupPanel popup = new PopupPanel(true);
							popup.setWidget( new HTML("<font color='black'>Categoria Inserita</font>"));
							popup.center();
							TopBar topbar = new TopBar();
							GestioneCategorie gestionecategorie = new GestioneCategorie();
							RootPanel.get().clear();
							RootPanel.get().add(topbar);
							RootPanel.get().add(gestionecategorie);
						} else {
							PopupPanel popup = new PopupPanel(true);
							popup.setWidget( new HTML("<font color='black'>Categoria già presente</font>"));
							popup.center();
						}
						
						
					}
					
				});
				
			}
			
		});
		
		/*****************
		 * GET CATEGORIE *
		 *****************/
		final Tree tt = new Tree();
		tt.getElement().setAttribute("style", "margin:10px");
		instanceLuckyBidderService.getAllCategorie( new AsyncCallback<ArrayList<Categoria>>() {

			@Override
			public void onFailure(Throwable caught) {
				PopupPanel popup = new PopupPanel(true);
				popup.setWidget( new HTML("<font color='red'>Impossibile inserire ottenere categorie: Errore nella connessione con il server.<br>"+caught+"</font>"));
				popup.center();
			}

			@Override
			public void onSuccess(ArrayList<Categoria> result) {
				GWT.log(result.toString());
				GWT.log("");
				GWT.log("");
				GWT.log("");
				Categoria nullCat = new Categoria("null");
				listaStampati.add(nullCat);
				processTree(nullCat, result, root);
				root.setState(true);
				tt.addItem(root);
				
				
			}
			
		});
	    

		/**************************************
		 * AGGIUNGI SOTTOCATEGORIA e MODIFICA *
		 **************************************/
		tt.addSelectionHandler( new SelectionHandler<TreeItem>() {

			@Override
			public void onSelection(final SelectionEvent<TreeItem> event) {
				if(!event.getSelectedItem().getText().equals("CATEGORIE")) {
					bAggiungiSottoCategoria = new Button("+ Sottocategoria");
					tAggiungiSottoCategoria = new TextBox();
					labelSelectedCategoria = new Label("Selezionato: " + event.getSelectedItem().getText());
					labelSelectedCategoria.getElement().setAttribute("style", "font-size: 20px; font-weight: bold; padding-top: 25px; padding-bottom: 5px");
					bModificaCategoria = new Button("Modifica Categoria");
					tModificaCategoria = new TextBox();
					tModificaCategoria.setText(event.getSelectedItem().getText());
					
					HorizontalPanel hpAggiungiSottoCategoria = new HorizontalPanel();
					hpAggiungiSottoCategoria.add(tAggiungiSottoCategoria);
					hpAggiungiSottoCategoria.add(bAggiungiSottoCategoria);
					bAggiungiSottoCategoria.addClickHandler( new ClickHandler() {
					
					
						@Override
						public void onClick(ClickEvent eventClick) {
							Categoria addCategoria = new Categoria(tAggiungiSottoCategoria.getValue());
							instanceLuckyBidderService.aggiungiCategoria(addCategoria, event.getSelectedItem().getText(), new AsyncCallback<Boolean>() {

								@Override
								public void onFailure(Throwable caught) {
									PopupPanel popup = new PopupPanel(true);
									popup.setWidget( new HTML("<font color='red'>Impossibile inserire una nuova categoria: Errore nella connessione con il server.<br>"+caught+"</font>"));
									popup.center();
								}

								@Override
								public void onSuccess(Boolean result) {
									if(result == true) {
										PopupPanel popup = new PopupPanel(true);
										popup.setWidget( new HTML("<font color='black'>Categoria Inserita</font>"));
										popup.center();
										TopBar topbar = new TopBar();
										GestioneCategorie gestionecategorie = new GestioneCategorie();
										RootPanel.get().clear();
										RootPanel.get().add(topbar);
										RootPanel.get().add(gestionecategorie);
									} else {
										PopupPanel popup = new PopupPanel(true);
										popup.setWidget( new HTML("<font color='black'>Categoria già presente</font>"));
										popup.center();
									}
									
									
								}
								
							});
							
						}
						
					});
					
					HorizontalPanel hpModificaCategoria = new HorizontalPanel();
					hpModificaCategoria.add(tModificaCategoria);
					hpModificaCategoria.add(bModificaCategoria);
					bModificaCategoria.addClickHandler( new ClickHandler() {

						@Override
						public void onClick(ClickEvent eventClick) {
							instanceLuckyBidderService.modificaCategoria(event.getSelectedItem().getText(), tModificaCategoria.getValue(), new AsyncCallback<Boolean>() {

								@Override
								public void onFailure(Throwable caught) {
									PopupPanel popup = new PopupPanel(true);
									popup.setWidget( new HTML("<font color='red'>Impossibile eliminare categoria: Errore nella connessione con il server.<br>"+caught+"</font>"));
									popup.center();
									
								}

								@Override
								public void onSuccess(Boolean result) {
									if(result) {
										PopupPanel popup = new PopupPanel(true);
										popup.setWidget( new HTML("<font color='black'>Modificato "+ event.getSelectedItem().getText() +" in " + tModificaCategoria.getValue() +"</font>"));
										popup.center();
										TopBar topbar = new TopBar();
										GestioneCategorie gestionecategorie = new GestioneCategorie();
										RootPanel.get().clear();
										RootPanel.get().add(topbar);
										RootPanel.get().add(gestionecategorie);
										
									}
									
								}
								
							});
							
						}
						
					});
					
					vpGeneral.clear();
					vpGeneral.add(hpAggiungiCategoria);
					vpGeneral.add(labelSelectedCategoria);	
					vpGeneral.add(hpAggiungiSottoCategoria);
					vpGeneral.add(hpModificaCategoria);

				}
				
				
			}
			
		});
		
		/****************************
		 * DECORATOR PANEL GENERALE *
		 ****************************/
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(tt);
		hp.add(vpGeneral);
		decoratorPanel.add(hp);
		
		decoratorPanel.getElement().setAttribute("style", "margin: 10px");
		
		this.add(decoratorPanel);
		
	}
	
	private void processTree(Categoria padre, ArrayList<Categoria> listaCategorie, TreeItem itemPadre) {
		if(padre.getNomeCategoria().equals("null")) {
			itemPadre.setText("CATEGORIE");
		}
		for(Categoria cat : listaCategorie) {
			if(!listaStampati.contains(cat) && cat.getPadre().getNomeCategoria().equals(padre.getNomeCategoria())) {
				GWT.log("Inserisco" + cat.toString());
				TreeItem ti = new TreeItem();
				ti.setText(cat.getNomeCategoria());
				listaStampati.add(cat);
				for(Categoria cat1 : listaCategorie) {
					if(cat1.getPadre().getNomeCategoria().equals(cat.getNomeCategoria())) {
						GWT.log("trovato figlio" + cat1.toString());
						processTree(cat, listaCategorie, ti);
					}
				}
				itemPadre.addItem(ti);
			}
		}
	}
}
