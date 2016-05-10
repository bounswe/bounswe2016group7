package com.homework.yunus;

import static org.junit.Assert.*;

import org.junit.Test;


public class SparqlYunusTest {

	@Test
	public void testSparqlYunus() {
		assertNotNull(new SparqlYunus());
	}

	@Test
	public void testLoadXMLFromString() throws Exception {
		assertNotNull(SparqlYunus.loadXMLFromString("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<note>"
				+ "<to>Tove</to>"
				+ "<from>Jani</from>"
				+ "<heading>Reminder</heading>"
				+ "<body>Don't forget me this weekend!</body>"
				+ "</note>"));
	}
	
	@Test
	public void testGetData()
	{
		SparqlYunus yns = new SparqlYunus();
		assertNotNull(yns.getData());
	}

}
