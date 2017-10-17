package com.luckybidder.shared;

import java.io.Serializable;
import java.util.Date;

public class Offerta implements Serializable {

	private String username;
	private Date dataOfferta;
	private int idProdotto;
	private double prezzo;
	private int id ;
	
	public Offerta() {}
	
	/*public Offerta(int id, int idProdotto, String username, double prezzo, Date dataOfferta) {
		this.id = id;
		this.idProdotto = idProdotto;
		this.username = username;
		this.prezzo = prezzo;	
		this.dataOfferta = dataOfferta;
	
	}*/
	
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
}
