package test.modelo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import algo3.modelo.Ladron;

public class LadronTest {

	@Test
	public void testCrearLadronKirchnerista(){
		Ladron ladron = new Ladron();
		assertTrue(ladron != null);
	}
	
}
