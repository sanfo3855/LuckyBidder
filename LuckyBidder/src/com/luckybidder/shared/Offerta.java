package com.luckybidder.shared;

import java.util.Date;

public class Offerta {

	private double importo;
	private Date dataOfferta;
	private Prodotto prodotto;
	
	public Offerta() {
		
	}

	public double getImporto() {
		return importo;
	}

	public void setImporto(double importo) {
		this.importo = importo;
	}

	public Date getDataOfferta() {
		return dataOfferta;
	}

	public void setDataOfferta(Date dataOfferta) {
		this.dataOfferta = dataOfferta;
	}

	public Prodotto getProdotto() {
		return prodotto;
	}

	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}
}
