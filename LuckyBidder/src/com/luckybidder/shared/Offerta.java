package com.luckybidder.shared;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Offerta implements Serializable {

	private String username;
	private Date dataOfferta;
	private int idProdotto;
	private double prezzo;
	private int id ;
	private String nomeProdotto;
	
	public Offerta() {}
	
	public int getId() {
		return id;
	}
	public String getUsername(){
		return username;
	}
	
	
	public double getPrezzo() {
		return prezzo;
	}
	
	public int getIdProdotto() {
		return idProdotto;
	}
	

	public Date getDataOfferta() {
		return dataOfferta;
	}

	public void setDataOfferta(Date dataOfferta) {
		this.dataOfferta = dataOfferta;
	}
	
	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}

	
	public void setUsername(String username) {
		this.username = username;
	}
	public void setIdOfferta(int id) {
		this.id = id;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	
	@Override
	public String toString() {
		String stringOfferta = "id :" +this.id + "\n" +
				"Offerente " + this.username + "\n" +
				"id Prodotto: " + this.idProdotto + "\n" +
				"prezzo: " + this.prezzo +"\n";
							  
		return stringOfferta;
	}

	public String getNomeProdotto() {
		return nomeProdotto;
	}

	public void setNomeProdotto(String nomeProdotto) {
		this.nomeProdotto = nomeProdotto;
	}
}
