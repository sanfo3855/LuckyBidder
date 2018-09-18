package com.luckybidder.test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Calendar;

import org.junit.Test;

import com.luckybidder.server.LuckyBidderImpl;
import com.luckybidder.shared.Categoria;
import com.luckybidder.shared.Prodotto;
import com.luckybidder.shared.Utente;

public class TestServerImpl {
	
	private void deleteDbCategorie() {
		File mc = new File("MapDBCategorie");
		File mcp = new File("MapDBCategorie.p");
		File mct = new File("MapDBCategorie.t");
		mc.delete();
		mcp.delete();
		mct.delete();
	}
	
	private void deleteDbUtenti() {
		File mc = new File("MapDBUtenti");
		File mcp = new File("MapDBUtenti.p");
		//File mct = new File("MapDBUtenti.t");
		mc.delete();
		mcp.delete();
		//mct.delete();
	}
	
	private void deleteDBProdotti() {
		File mc = new File("MapDBProdotti");
		File mcp = new File("MapDBProdotti.p");
		//File mct = new File("MapDBProdotti.t");
		mc.delete();
		mcp.delete();
		//mct.delete();
	}
	
	private LuckyBidderImpl LuckyBidderImpl() {
		return new LuckyBidderImpl();
	}
	
	@Test
	public void testAggiungiCategorie() {
		deleteDbCategorie();
		assertEquals("Inserisci Categoria", true, LuckyBidderImpl().aggiungiCategoria(new Categoria("informatica"), null)); 
		assertEquals("Inserisci Categoria", true, LuckyBidderImpl().aggiungiCategoria(new Categoria("cucina"), null));
		assertEquals("Inserisci Categoria", true, LuckyBidderImpl().aggiungiCategoria(new Categoria("abbigliamento"), null));
		assertEquals("Inserisci Categoria", true, LuckyBidderImpl().aggiungiCategoria(new Categoria("pc"), "informatica"));
		assertEquals("Inserisci Categoria", true, LuckyBidderImpl().aggiungiCategoria(new Categoria("pentole"), "cucina"));
		assertEquals("Inserisci Categoria", true, LuckyBidderImpl().aggiungiCategoria(new Categoria("coltelli"), "cucina"));	
		assertEquals("Inserisci Categoria", true, LuckyBidderImpl().aggiungiCategoria(new Categoria("hdd"), "pc"));
		assertEquals("Inserisci Categoria", true, LuckyBidderImpl().aggiungiCategoria(new Categoria("ssd"), "pc"));
		assertEquals("Inserisci Categoria", true, LuckyBidderImpl().aggiungiCategoria(new Categoria("gpu"), "pc"));
		assertEquals("Inserisci Categoria", true, LuckyBidderImpl().aggiungiCategoria(new Categoria("schermi"), "pc"));
		assertEquals("Inserisci Categoria", true, LuckyBidderImpl().aggiungiCategoria(new Categoria("pentoleacciaio"), "pentole"));
		assertEquals("Inserisci Categoria", true, LuckyBidderImpl().aggiungiCategoria(new Categoria("pentoleceramica"), "pentole"));
		assertEquals("Inserisci Categoria", true, LuckyBidderImpl().aggiungiCategoria(new Categoria("coltelliacciaio"), "coltelli"));
		assertEquals("Inserisci Categoria", true, LuckyBidderImpl().aggiungiCategoria(new Categoria("coltelliceramica"), "coltelli"));
		assertEquals("Inserisci Categoria", true, LuckyBidderImpl().aggiungiCategoria(new Categoria("sli"), "gpu"));
		assertEquals("Inserisci Categoria", true, LuckyBidderImpl().aggiungiCategoria(new Categoria("nosli"), "gpu"));
		assertEquals("Inserisci Categoria", true, LuckyBidderImpl().aggiungiCategoria(new Categoria("vga"), "schermi"));
		assertEquals("Inserisci Categoria", true, LuckyBidderImpl().aggiungiCategoria(new Categoria("hdmi"), "schermi"));
		assertEquals("Inserisci Categoria", true, LuckyBidderImpl().aggiungiCategoria(new Categoria("dvi"), "schermi"));
		assertEquals("Inserisci Categoria", true, LuckyBidderImpl().aggiungiCategoria(new Categoria("hdmi10"), "hdmi"));
		assertEquals("Inserisci Categoria", true, LuckyBidderImpl().aggiungiCategoria(new Categoria("hdmi20"), "hdmi"));
	}
	
	@Test
	public void testGetAllCategorie() {
		assertNotEquals("Get all Categorie", null, LuckyBidderImpl().getAllCategorie());
	}
	
	@Test
	public void testRegistraUtente() {
		deleteDbUtenti();
		
		for(int i=0; i<10; i++) {
			Utente utente = new Utente();
			utente.setUsername("username" + i);
			utente.setNome("nome"+i);
			utente.setCognome("cognome" + i);
			utente.setCodiceFiscale("codicefiscale"+i);
			utente.setPassword("password"+i);
			utente.setSesso('M');
			utente.setDataNascita(Calendar.getInstance().getTime());
			utente.setEmail("email"+i);
			utente.setLuogoNascita("luogo"+i);
			utente.setIndirizzo("indirizzo"+i);
			utente.setTelefono("telefono"+i);
			assertEquals("Registro utente"+i, true, LuckyBidderImpl().registraUtente(utente));		
			
		}
	}
	
	@Test
	public void testLoginUtente() {
		for(int i=0; i<10; i++) {
			assertNotEquals("Registro utente"+i, null, LuckyBidderImpl().loginUtente("username"+i, "password"+i));		
		}
	}
	
	@Test
	public void testVendiProdotto() {
		deleteDBProdotti();
		for(int i=0; i<10; i++) {
			Prodotto prodotto = new Prodotto();
			prodotto.setBaseAsta(i+1);
			prodotto.setCategoria("informatica");
			prodotto.setDataScadenza(Calendar.getInstance().getTime());
			prodotto.setDescrizione("aaaaaaa");
			prodotto.setNomeProdotto("AAA"+i);
			prodotto.setUsername("venditore");
			assertEquals("Aggiungo AAA"+i, true, LuckyBidderImpl().vendiProdotto(prodotto));
		}
	}

	//@Test
	public void testGetProdotti() {
		for(int i=0; i<10; i++)
		assertNotEquals("Richiedo i prodotti", null, LuckyBidderImpl().getProdotti() );
	}

}
