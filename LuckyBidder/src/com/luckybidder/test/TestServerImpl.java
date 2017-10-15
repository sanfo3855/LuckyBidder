package com.luckybidder.test;

import static org.junit.Assert.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Test;

import com.luckybidder.server.LuckyBidderImpl;
import com.luckybidder.shared.Categoria;

public class TestServerImpl {
	
	private void deleteDbCategorie() {
		File mc = new File("MapDBCategorie");
		File mcp = new File("MapDBCategorie.p");
		File mct = new File("MapDBCategorie.t");
		mc.delete();
		mcp.delete();
		mct.delete();
	}

	@Test
	public void testAggiungiCategorie() {
		deleteDbCategorie();
		LuckyBidderImpl impl = new LuckyBidderImpl();
		assertEquals("Inserisci Categoria", true, impl.aggiungiCategoria(new Categoria("informatica"), null)); 
		assertEquals("Inserisci Categoria", true, impl.aggiungiCategoria(new Categoria("cucina"), null));
		assertEquals("Inserisci Categoria", true, impl.aggiungiCategoria(new Categoria("abbigliamento"), null));
		assertEquals("Inserisci Categoria", true, impl.aggiungiCategoria(new Categoria("pc"), "informatica"));
		assertEquals("Inserisci Categoria", true, impl.aggiungiCategoria(new Categoria("pentole"), "cucina"));
		assertEquals("Inserisci Categoria", true, impl.aggiungiCategoria(new Categoria("coltelli"), "cucina"));
		File mc = new File("MapDBCategorie");
		File mcp = new File("MapDBCategorie.p");
		File mct = new File("MapDBCategorie.t");
		
	}
	
	@Test
	public void testGetCategoria() {
		LuckyBidderImpl impl = new LuckyBidderImpl();
		assertNotEquals("Prendi informatica", null, impl.getCategoria(1));
	}

}
