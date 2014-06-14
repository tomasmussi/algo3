package test.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import algo3.modelo.caso.Caso;
import algo3.modelo.ladron.CaracteristicaLadron;
import algo3.modelo.policia.OrdenDeArresto;
import algo3.modelo.policia.Policia;
import algo3.modelo.tiempo.Reloj;

public class CasoTest {

	private Policia policia;
	private Reloj reloj;

	@Before
	public void test(){
		reloj = new Reloj();
		policia = new Policia(reloj);
	}

	@Test
	public void NoPudoGenerarOrdenArrestoPorTenerVariasCoincidencias(){

		Caso caso= new Caso();
		OrdenDeArresto orden = caso.generarOrdenDeArresto(new CaracteristicaLadron( null, "Masculino", "Mountain Climbing", null, null, "Motocicleta"));
		assertEquals(null, orden);
	}

	@Test
	public void generarOrdenDeArrestoSiHayUnaSolaCoincidencia(){

		Caso caso= new Caso();
		OrdenDeArresto orden = caso.generarOrdenDeArresto(new CaracteristicaLadron("Nick Brunch","Masculino","Mountain Climbing","Negro","Anillo","Motocicleta"));
		assertTrue(orden != null);
	}


	public void testCrearCasoConPoliciaNovato(){
		Caso caso = new Caso();
		assertTrue(caso.getRecorrido().size() == 4);
	}





}
