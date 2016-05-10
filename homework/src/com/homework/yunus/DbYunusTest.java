package com.homework.yunus;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Test;

public class DbYunusTest {

	@Test
	public void testDbYunus() {
		assertNotNull(new DbYunus("yunus","root",""));
	}

	@Test
	public void testGetConnection() {
		DbYunus db = new DbYunus("yunus","root","");
		assertNotNull(db.getConnection());
	}

	@Test
	public void testSearch() {
		DbYunus db = new DbYunus("yunus","root","");
		Vector<ModelYunus> list = db.search("Turkey");
		assertEquals("Ankara", list.elementAt(0).getCapital());
	}

	@Test
	public void testGetHistory() {
		DbYunus db = new DbYunus("yunus","root","");
		assertNotNull(db.getHistory());
	}

}
