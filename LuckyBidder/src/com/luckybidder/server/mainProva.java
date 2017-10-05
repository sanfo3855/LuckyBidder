package com.luckybidder.server;

import java.io.File;

import org.mapdb.BTreeMap;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import com.luckybidder.shared.Utente;

public class mainProva {

	private static DB dbUtenti;

	public static void main(String[] args) {
		DB dbUtenti = getDBUtenti();
		BTreeMap<String, Utente> mapUtenti = dbUtenti.getTreeMap("MapUtenti");
		System.out.print(mapUtenti.get("a").toString());
	}

	private static DB getDBUtenti() {
		dbUtenti = DBMaker.newFileDB(new File("MapDBUtenti")).closeOnJvmShutdown().make();		
		return dbUtenti;
	}

}
