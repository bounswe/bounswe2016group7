package com.homework.yunus;

import static org.junit.Assert.*;

import org.junit.Test;

public class ModelYunusTest {

	@Test
	public void testGetCountry() {
		ModelYunus actual = new ModelYunus("Turkey","Ankara");
		assertEquals(actual.getCountry(), "Turkey");
	}

	@Test
	public void testGetCapital() {
		ModelYunus actual = new ModelYunus("Turkey","Ankara");
		assertEquals(actual.getCapital(), "Ankara");
	}

}
