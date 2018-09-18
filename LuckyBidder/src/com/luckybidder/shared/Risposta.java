package com.luckybidder.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Risposta implements Serializable{
	
	private int idDomandaRelativa;
	private int idRisposta;
	private String Risposta;
	
	public int getIdDomandaRelativa() {
		return idDomandaRelativa;
	}
	
	public void setIdDomandaRelativa(int idDomandaRelativa) {
		this.idDomandaRelativa = idDomandaRelativa;
	}

	public int getIdRisposta() {
		return idRisposta;
	}

	public void setIdRisposta(int idRisposta) {
		this.idRisposta = idRisposta;
	}

	public String getRisposta() {
		return Risposta;
	}

	public void setRisposta(String risposta) {
		Risposta = risposta;
	}
	
	@Override
	public String toString() {
		return "id: " + this.idRisposta + "\n id domanda: "+ this.idDomandaRelativa + "\n testo: " + this.Risposta + "\n"; 
	}
	
	
}
