package test.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import algo3.modelo.caso.Caso;
import algo3.modelo.ladron.CaracteristicaLadron;

public class CasoTest {


	public Caso crearCaso(){
		return new Caso();
	}

	@Test
	public void NoPudoGenerarOrdenArrestoPorTenerVariasCoincidencias(){

		Caso caso = crearCaso();
		caso.generarOrdenDeArresto(new CaracteristicaLadron( null, "Masculino", "Croquet", null, null, "Limousina"));
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







}
