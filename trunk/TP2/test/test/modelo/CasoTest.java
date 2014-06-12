package test.modelo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import algo3.modelo.caso.Caso;
import algo3.modelo.ladron.CaracteristicaLadron;

public class CasoTest {
	
	@Test
	public void NoPudoGenerarOrdenArrestoPorTenerVariasCoincidencias(){
		
		Caso caso= new Caso();
		boolean seGenero=caso.generarOrdenDeArresto(new CaracteristicaLadron( null, "Masculino", "Mountain Climbing", null, null, "Motocicleta"));
		assertFalse(seGenero);
	}
	
	@Test
	public void generarOrdenDeArrestoSiHayUnaSolaCoincidencia(){
		
		Caso caso= new Caso();
		boolean seGenero=caso.generarOrdenDeArresto(new CaracteristicaLadron("Nick Brunch","Masculino","Mountain Climbing","Negro","Anillo","Motocicleta"));
		assertTrue(seGenero);
	}
	
	

}
