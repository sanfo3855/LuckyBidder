package com.luckybidder.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.luckybidder.shared.Categoria;

public class GestioneCategorie extends VerticalPanel {
	private Button aggiungiCategoria;
	private String stampaCategoria;
	
	private String scorriStampaCategoria(Categoria categoria, String stampa) {
		stampaCategoria = stampa + categoria.getNomeCategoria();
		if(categoria.getPadre()!=null) {
			scorriStampaCategoria(categoria.getPadre(), stampaCategoria + "  >> ");
		}
		return stampaCategoria;
	}
	
	
	public GestioneCategorie() {
		
		Button buttonModificaCategoria = new Button("MODIFICA CATEGORIA");
		buttonModificaCategoria.setEnabled(false);
		
		final Tree alberoCategorie = new Tree();
		alberoCategorie.addSelectionHandler( new SelectionHandler<TreeItem>() {

			@Override
			public void onSelection(SelectionEvent<TreeItem> event) {
				
				
			}
			
		});
		Categoria categoria = new Categoria(null, 1, "informatica");
		Categoria categoriaF1 = new Categoria(categoria,2,"pc");
		Categoria categoriaF2 = new Categoria(categoriaF1,3,"hard Disk");
		
		String stampacat = scorriStampaCategoria(categoriaF2, "");
		GWT.log(stampacat);
	}
}
