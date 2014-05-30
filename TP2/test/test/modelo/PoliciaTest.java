package test.modelo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import algo3.modelo.Policia;

public class PoliciaTest {

	@Test
	public void testCrearPolicia(){
		Policia policia = new Policia();
		assertTrue(policia != null);
	}

}
