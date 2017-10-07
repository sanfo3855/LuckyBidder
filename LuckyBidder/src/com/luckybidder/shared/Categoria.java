package com.luckybidder.shared;

import java.util.ArrayList;

public class Categoria {
	
	public String nomeCategoria;
	private Categoria padre;
	private int id;
	private ArrayList<SottoCategoria> ListaSottoCategoria = new ArrayList<>();
	
	public Categoria(Categoria father, int id, String nomeCat) {
		this.padre = father;
		this.id = id;
		this.nomeCategoria = nomeCat;
	}
	
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
	
	
	public Categoria getPadre() {
		return padre;
	}
	public void setFather(Categoria father) {
		this.padre = father;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getNomeCat() {
		return nomeCategoria;
	}
	public void setNomeCat(String nomeCat) {
		this.nomeCategoria = nomeCat;
	}

}
