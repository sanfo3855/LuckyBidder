package com.luckybidder.shared;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
public class Categoria implements Serializable, IsSerializable {
	
	private String nomeCategoria;
	private Categoria padre;
	private int id;
	
	/**
	 * 
	 * @param father
	 * @param id
	 * @param nomeCat
	 */
	public Categoria(Categoria father, int id, String nomeCat) {
		this.setPadre(father);
		this.setId(id);
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


}
