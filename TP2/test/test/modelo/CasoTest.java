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
	
	public Caso crearCaso(){
		return new Caso();
	}
	
	@Test
	public void NoPudoGenerarOrdenArrestoPorTenerVariasCoincidencias(){
		
		Caso caso = crearCaso();
		caso.generarOrdenDeArresto(new CaracteristicaLadron( null, "Masculino", "Mountain Climbing", null, null, "Motocicleta"));
		assertEquals(null, caso.getOrdenDeArresto());
	}
	
	@Test
	public void generarOrdenDeArrestoSiHayUnaSolaCoincidencia(){
		
		Caso caso = crearCaso();
		caso.generarOrdenDeArresto(new CaracteristicaLadron("Nick Brunch","Masculino","Mountain Climbing","Negro","Anillo","Motocicleta"));
		assertTrue(caso.getOrdenDeArresto() != null);
	}
	
	@Test
	public void generarOrdenDeArrestoCuandoYaExisteUnaLaPisa(){
		
		Caso caso = crearCaso();
		caso.generarOrdenDeArresto(new CaracteristicaLadron("Nick Brunch","Masculino","Mountain Climbing","Negro","Anillo","Motocicleta"));
		assertTrue(caso.getOrdenDeArresto().getCaracteristicaLadron().equals(new CaracteristicaLadron("Nick Brunch","Masculino","Mountain Climbing","Negro","Anillo","Motocicleta")));
		//genero otra Orden De Arresto entonces pisa la anterior Orden.
		caso.generarOrdenDeArresto(new CaracteristicaLadron("Ihor Ihorovitch", "Masculino", "Croquet", "Rubio", "Tatuaje", "Limousina"));
		assertTrue(caso.getOrdenDeArresto().getCaracteristicaLadron().equals(new CaracteristicaLadron("Ihor Ihorovitch", "Masculino", "Croquet", "Rubio", "Tatuaje", "Limousina")));
	}
	public void testCrearCasoConPoliciaNovato(){
		Caso caso = crearCaso();
		assertTrue(caso.getRecorrido().size() == 4);
	}
	
	
	
	

}
