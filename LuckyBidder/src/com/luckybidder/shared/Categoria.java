package com.luckybidder.shared;

import java.util.ArrayList;

public class Categoria {
	
	public String nomeCategoria;
	private ArrayList<SottoCategoria> ListaSottoCategoria = new ArrayList<>();
	
	public Categoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void modificaNomeCategoria( String modificaNome ) {
		this.nomeCategoria = modificaNome;
	}
	
	public void aggiungiSottoCategoria( SottoCategoria nomeSottoCategoria ) {
		this.ListaSottoCategoria.add( nomeSottoCategoria );
	}
	
	public ArrayList<SottoCategoria> getListaSottoCategorie(){
		return this.ListaSottoCategoria;
	}

}
