package com.luckybidder.shared;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
public class Categoria implements Serializable, IsSerializable {
	
	private String nomeCategoria;
	private Categoria padre;
	private int id;
	
	
	public Categoria(Categoria father, int id, String nomeCat) {
		this.setPadre(father);
		this.setId(id);
		this.setNomeCategoria(nomeCat);
	}
	
	public Categoria() {}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public Categoria getPadre() {
		return padre;
	}

	public void setPadre(Categoria padre) {
		this.padre = padre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


}
