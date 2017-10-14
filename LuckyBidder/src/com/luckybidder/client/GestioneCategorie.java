package com.luckybidder.client;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.luckybidder.shared.Categoria;

public class GestioneCategorie extends VerticalPanel {
	
	private Button bAggiungiCategoria;
	private TextBox tAggiungiCategoria;
	protected ArrayList<Categoria> resultCategorie;
	
	public GestioneCategorie() {
		
		final LuckyBidderServiceAsync instanceLuckyBidderService = LuckyBidderService.Util.getInstance();
		DecoratorPanel decoratorPanel = new DecoratorPanel();
		
		/**********************
		 * AGGIUNGI CATEGORIA *
		 **********************/
		bAggiungiCategoria = new Button("Aggiungi Categoria");
		tAggiungiCategoria = new TextBox();
		HorizontalPanel hpAggiungiCategoria = new HorizontalPanel();
		hpAggiungiCategoria.add(tAggiungiCategoria);
		hpAggiungiCategoria.add(bAggiungiCategoria);
		
		/*bAggiungiCategoria.addClickHandler( new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Categoria addCategoria = new Categoria(tAggiungiCategoria.getValue());
				instanceLuckyBidderService.aggiungiCategoria(addCategoria, new AsyncCallback<Boolean>() {

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
			
		});*/
		hpAggiungiCategoria.getElement().setAttribute("style", "margin-bottom: 10px");
		
		Categoria informatica = new Categoria("informatica");
		Categoria cucina = new Categoria("cucina");
		Categoria abbigliamento = new Categoria("abbigliamento");
		//livello 1
		Categoria pc = new Categoria("pc");
		Categoria pentole = new Categoria("pentole");
		Categoria coltelli = new Categoria("coltelli");
		
		//livello 2
		Categoria hdd = new Categoria("hdd");
		Categoria ssd = new Categoria("ssd");
		Categoria gpu = new Categoria("gpu");
		Categoria schermi = new Categoria("schermi");
		Categoria pentoleacciaio = new Categoria("pentoleacciaio");
		Categoria pentoleceramica = new Categoria("pentoleceramica");
		Categoria coltelliacciaio = new Categoria("coltelliacciaio");
		Categoria coltelliceramica = new Categoria("coltelliceramica");
		
		
		//livello 3
		Categoria sli = new Categoria("sli");
		Categoria nosli = new Categoria("nosli");
		Categoria vga = new Categoria("vga");
		Categoria hdmi = new Categoria("hdmi");
		Categoria dvi = new Categoria("dvi");
		
		//livello 4
		Categoria hdmi10 = new Categoria("hdmi1.0");
		Categoria hdmi20 = new Categoria("hdmi2.0");
		
		
		
	  	instanceLuckyBidderService.aggiungiCategoria(hdmi20, "hdmi", new AsyncCallback<Boolean>() {
				@Override
				public void onFailure(Throwable caught) {
					GWT.log(""+caught);
					
				}

				@Override
				public void onSuccess(Boolean result) {
					GWT.log(""+result);
					
				}
				
		});
		/*****************
		 * GET CATEGORIE *
		 *****************/
		
		instanceLuckyBidderService.getCategorie( new AsyncCallback<Categoria>() {

			@Override
			public void onFailure(Throwable caught) {
				PopupPanel popup = new PopupPanel(true);
				popup.setWidget( new HTML("<font color='red'>Impossibile inserire ottenere categorie: Errore nella connessione con il server.<br>"+caught+"</font>"));
				popup.center();
			}

			@Override
			public void onSuccess(Categoria result) {
				
				GWT.log(result.toString());
				
				//Nodo nodoRoot = processTree(null, resultCategorie);
			}
			
		});
		
		
		
		/****************************
		 * DECORATOR PANEL GENERALE *
		 ****************************/
		decoratorPanel.add(hpAggiungiCategoria);
		decoratorPanel.getElement().setAttribute("style", "margin: 10px");
		
		this.add(decoratorPanel);
	}
}
