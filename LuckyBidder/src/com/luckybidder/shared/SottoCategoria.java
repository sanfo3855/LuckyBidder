package com.luckybidder.shared;

import java.util.ArrayList;

public class SottoCategoria {
	
	public String nomeSottoCategoria;
	private ArrayList<SottoCategoria> ListaSottoCategoria = new ArrayList<>();
	
	public SottoCategoria( String nomeSottoCategoria ) {
		this.nomeSottoCategoria = nomeSottoCategoria;
	}
	
	public String getNomeSottoCategoria() {
		return nomeSottoCategoria;
	}
	
	public void aggiungiSottoCategoria( SottoCategoria nomeSottoCategoria ) {
		this.ListaSottoCategoria.add( nomeSottoCategoria );
	}
	
	public ArrayList<SottoCategoria> getListaSottoCategorie(){
		return this.ListaSottoCategoria;
	}
}
