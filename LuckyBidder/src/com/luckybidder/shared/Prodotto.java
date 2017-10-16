package com.luckybidder.shared;

import java.io.Serializable;
import java.util.Date;

public class Prodotto implements Serializable,Comparable<Prodotto> {
	
	private int idProdotto;
	private String username;
	private String nomeProdotto;
	private String descrizione;
	private double baseAsta;
	private Date dataScadenza;
	private String stato;
	private String vincitore;
	private String nomePropostaMigliore;
	private String categoria;
	
	
	public enum StatoAstaProdotto { APERTA, CHIUSA	};
	
	public Prodotto() {
		
	}

	public int getIdProdotto() {
		return idProdotto;
	}

	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
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

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
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
	
	public String getNomePropostaMigliore() {
		return nomePropostaMigliore;
	}
	
	public void setNomePropostaMigliore(String nomePropostaMigliore) {
		this.nomePropostaMigliore = nomePropostaMigliore;
	}
	
	public String getCategoria() {
		return categoria;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	@Override
	public String toString() {
		String stringProdotto = "id :" +this.idProdotto + "\n" +
				"Venditore " + this.username + "\n" +
				"Nome: " + this.nomeProdotto + "\n" +
				"Categoria: " + this.categoria +"\n";
							  
		return stringProdotto;
	}
	
	@Override
	public int compareTo(Prodotto prodotto) {
		return this.getDataScadenza().compareTo(prodotto.getDataScadenza());

	}
}
