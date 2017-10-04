package com.luckybidder.shared;

import java.util.Date;

public class Prodotto {
	
	private int idProdotto;
	private String nomeProdotto;
	private String descrizione;
	private double baseAsta;
	private Date dataScadenza;
	private StatoAstaProdotto stato;
	private String vincitore;
	
	
	public enum StatoAstaProdotto { APERTA, CHIUSA	};
	
	public Prodotto() {
		
	}

	public int getIdProdotto() {
		return idProdotto;
	}

	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public double getBaseAsta() {
		return baseAsta;
	}

	public void setBaseAsta(double baseAsta) {
		this.baseAsta = baseAsta;
	}

	public Date getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public StatoAstaProdotto getStato() {
		return stato;
	}

	public void setStato(StatoAstaProdotto stato) {
		this.stato = stato;
	}

	public String getVincitore() {
		return vincitore;
	}

	public void setVincitore(String vincitore) {
		this.vincitore = vincitore;
	}

	public String getNomeProdotto() {
		return nomeProdotto;
	}

	public void setNomeProdotto(String nomeProdotto) {
		this.nomeProdotto = nomeProdotto;
	}
	
	
}
