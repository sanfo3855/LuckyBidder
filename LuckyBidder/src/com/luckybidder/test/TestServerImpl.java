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
		assertEquals("Inserisci Categoria", true, impl.aggiungiCategoria(new Categoria("hdd"), "pc"));
		assertEquals("Inserisci Categoria", true, impl.aggiungiCategoria(new Categoria("ssd"), "pc"));
		assertEquals("Inserisci Categoria", true, impl.aggiungiCategoria(new Categoria("gpu"), "pc"));
		assertEquals("Inserisci Categoria", true, impl.aggiungiCategoria(new Categoria("schermi"), "pc"));
		assertEquals("Inserisci Categoria", true, impl.aggiungiCategoria(new Categoria("pentoleacciaio"), "pentole"));
		assertEquals("Inserisci Categoria", true, impl.aggiungiCategoria(new Categoria("pentoleceramica"), "pentole"));
		assertEquals("Inserisci Categoria", true, impl.aggiungiCategoria(new Categoria("coltelliacciaio"), "coltelli"));
		assertEquals("Inserisci Categoria", true, impl.aggiungiCategoria(new Categoria("coltelliceramica"), "coltelli"));
		assertEquals("Inserisci Categoria", true, impl.aggiungiCategoria(new Categoria("sli"), "gpu"));
		assertEquals("Inserisci Categoria", true, impl.aggiungiCategoria(new Categoria("nosli"), "gpu"));
		assertEquals("Inserisci Categoria", true, impl.aggiungiCategoria(new Categoria("vga"), "schermi"));
		assertEquals("Inserisci Categoria", true, impl.aggiungiCategoria(new Categoria("hdmi"), "schermi"));
		assertEquals("Inserisci Categoria", true, impl.aggiungiCategoria(new Categoria("dvi"), "schermi"));
		assertEquals("Inserisci Categoria", true, impl.aggiungiCategoria(new Categoria("hdmi10"), "hdmi"));
		assertEquals("Inserisci Categoria", true, impl.aggiungiCategoria(new Categoria("hdmi20"), "hdmi"));
	}
	
	@Test
	public void testGetCategoria() {
		LuckyBidderImpl impl = new LuckyBidderImpl();
		assertNotEquals("Prendi informatica", null, impl.getCategoria(1));
		assertNotEquals("Prendi informatica", null, impl.getCategoria(2));
		assertNotEquals("Prendi informatica", null, impl.getCategoria(3));
		assertNotEquals("Prendi informatica", null, impl.getCategoria(4));
		assertNotEquals("Prendi informatica", null, impl.getCategoria(5));
		assertNotEquals("Prendi informatica", null, impl.getCategoria(6));
		assertNotEquals("Prendi informatica", null, impl.getCategoria(7));
		assertNotEquals("Prendi informatica", null, impl.getCategoria(8));
		assertNotEquals("Prendi informatica", null, impl.getCategoria(9));
		assertNotEquals("Prendi informatica", null, impl.getCategoria(10));
		assertNotEquals("Prendi informatica", null, impl.getCategoria(11));
		assertNotEquals("Prendi informatica", null, impl.getCategoria(12));
		assertNotEquals("Prendi informatica", null, impl.getCategoria(13));
		assertNotEquals("Prendi informatica", null, impl.getCategoria(14));
		assertNotEquals("Prendi informatica", null, impl.getCategoria(15));
		assertNotEquals("Prendi informatica", null, impl.getCategoria(16));
		assertNotEquals("Prendi informatica", null, impl.getCategoria(17));
		assertNotEquals("Prendi informatica", null, impl.getCategoria(18));
		assertNotEquals("Prendi informatica", null, impl.getCategoria(19));
		assertNotEquals("Prendi informatica", null, impl.getCategoria(20));
		assertNotEquals("Prendi informatica", null, impl.getCategoria(21));
	}
	
	@Test
	public void testGetTreeCategorie() {
		LuckyBidderImpl impl = new LuckyBidderImpl();
		assertNotEquals("Tree Categorie", null, impl.getAllCategorie());
	}

}
