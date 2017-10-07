package com.luckybidder.shared;

import java.io.Serializable;
import java.util.Date;

public class Utente implements Serializable{

	private String username;
	private String nome;
	private String cognome;
	private String telefono;
	private String password;
	private String email;
	private String codiceFiscale;
	private String indirizzo;
	private char sesso;
	private Date dataNascita;
	private String luogoNascita;
	
	
	/**
	 * 
	 * @param username
	 * @param nome
	 * @param cognome
	 * @param telefono
	 * @param password
	 * @param email
	 * @param codiceFiscale
	 * @param indirizzo
	 * @param sesso
	 * @param dataNascita
	 * @param luogoNascita
	 */
	public Utente() {}
	/*public Utente( String username, String nome, String cognome, String telefono, String password, String email, 
			String codiceFiscale, String indirizzo, char sesso, Date dataNascita, String luogoNascita) {
		
	}*/

	/**
	 * 
	 * @return String username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * 
	 * @param String username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * 
	 * @return String nome
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * 
	 * @param String nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * 
	 * @return String cognome
	 */
	public String getCognome() {
		return cognome;
	}
	
	/**
	 * 
	 * @param String cognome
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	/**
	 * 
	 * @return String telefono
	 */
	public String getTelefono() {
		return telefono;
	}
	
	/**
	 * 
	 * @param String telefono
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	/**
	 * 
	 * @return String password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * 
	 * @param String password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * 
	 * @return String email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * 
	 * @param String email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * 
	 * @return String codiceFiscale
	 */
	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	/**
	 * 
	 * @param String codiceFiscale
	 */
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	
	/**
	 * 
	 * @return String indirizzo
	 */
	public String getIndirizzo() {
		return indirizzo;
	}
	
	/**
	 * 
	 * @param String indirizzo
	 */
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	
	/**
	 * 
	 * @return char Sesso
	 */
	public char getSesso() {
		return sesso;
	}
	
	/**
	 * 
	 * @param char sesso
	 */
	public void setSesso(char sesso) {
		this.sesso = sesso;
	}
	
	/**
	 * 
	 * @return Date dataNascita
	 */
	public Date getDataNascita() {
		return dataNascita;
	}
	
	/**
	 * 
	 * @param Date dataNascita
	 */
	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	/**
	 * 
	 * @return String luogoNascita
	 */
	public String getLuogoNascita() {
		return luogoNascita;
	}
	
	/**
	 * 
	 * @param String luogoNascita
	 */
	public void setLuogoNascita(String luogoNascita) {
		this.luogoNascita = luogoNascita;
	}
	
	@Override
	public String toString() {
		String stringUtente = "Username :" +this.username + "\n" +
				"Nome: " + this.nome + "\n" +
				"Cognome: " + this.cognome +"\n";
							  
		return stringUtente;
	}
	
}
