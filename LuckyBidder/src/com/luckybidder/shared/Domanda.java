package com.luckybidder.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Domanda implements Serializable {

	private int idProdotto;
	private String nomeProdotto;
	private int idDomanda;
	private String testoDomanda;
	private String daCheutente;
	private String nomeUtenteVenditore;
	
	public int getIdProdotto() {
		return idProdotto;
	}
	
	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}
	
	public int getIdDomanda() {
		return idDomanda;
	}
	
	public void setIdDomanda(int idDomanda) {
		this.idDomanda = idDomanda;
	}

	public String getTestoDomanda() {
		return testoDomanda;
	}

	public void setTestoDomanda(String testoDomanda) {
		this.testoDomanda = testoDomanda;
	}

	public String getDaCheutente() {
		return daCheutente;
	}

	public void setDaCheutente(String daCheutente) {
		this.daCheutente = daCheutente;
	}

	public String getNomeUtenteVenditore() {
		return nomeUtenteVenditore;
	}

	public void setNomeUtenteVenditore(String nomeUtenteVenditore) {
		this.nomeUtenteVenditore = nomeUtenteVenditore;
	}

	public String getNomeProdotto() {
		return nomeProdotto;
	}

	public void setNomeProdotto(String nomeProdotto) {
		this.nomeProdotto = nomeProdotto;
	}

}
