package com.luckybidder.shared;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
public class Categoria implements Serializable, IsSerializable {
	
	private String nomeCategoria;
	private Categoria padre;
	private ArrayList<Categoria> categorieFiglie;
	private int id;
	
	/**
	 * 
	 * @param father
	 * @param id
	 * @param nomeCat
	 */
	public Categoria(String nomeCat) {
		categorieFiglie = new ArrayList<Categoria>();
		this.setNomeCategoria(nomeCat);
	}
	
	public Categoria() {}
	
	/**
	 * 
	 * @return nomeCategoria
	 */
	public String getNomeCategoria() {
		return nomeCategoria;
	}
	
	/**
	 * 
	 * @param nomeCategoria
	 */
	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
	
	/**
	 * 
	 * @return padre
	 */
	public Categoria getPadre() {
		return padre;
	}

	/**
	 * 
	 * @param padre
	 */
	public void setPadre(Categoria padre) {
		this.padre = padre;
	}
	
	/**
	 * 
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Categoria> getCategorieFiglie() {
		return categorieFiglie;
	}

	public void setCategoriaFiglia(Categoria categoria) {
		this.categorieFiglie.add(categoria);
	}
	
	public void setCategorieFiglie(ArrayList<Categoria> categorieFiglie) {
		this.categorieFiglie = categorieFiglie;
	}
	
	@Override
	public String toString() {
		return "\nID: " + this.id + "\nNome: " + this.nomeCategoria + "\nPadre: " + this.padre +"\n";
	}
}
