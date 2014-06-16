package test.modelo.integracion;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import algo3.modelo.ladron.CaracteristicaLadron;
import algo3.modelo.ladron.Ladron;
import algo3.modelo.objeto.ObjetoComun;
import algo3.modelo.objeto.Robable;
import algo3.modelo.policia.Policia;
import algo3.modelo.tiempo.Reloj;


public class CapturaTest {

	/* ************** Métodos auxiliares ************** */

	private Ladron crearLadronConObjetoComun() {
		CaracteristicaLadron caracteristica = new CaracteristicaLadron("Nick Brunch", "Masculino", "Mountain Climbing", "Negro", "Anillo", "Motocicleta");
		Robable objeto = new ObjetoComun("Buda dorado", "Bangkok");
		return new Ladron(caracteristica);
	}


	private Policia crearPolicia() {
		Reloj reloj = new Reloj();
		return new Policia(reloj);
	}


	/* Comienzan los test*/

	@Test
	public void testPoliciaNoAtrapaLadronSinTiempoRestante(){

		Policia policia = crearPolicia();
		policia.restarHoras(152);
		//Se quedo sin tiempo para emitir la orden de arresto.
		// Para crear la  orden de arresto lo hace con las caracteristicas del ladron que esta en base de datos.
		assertFalse(policia.emitirOrdenDeArresto(new CaracteristicaLadron("Nick Brunch", "Masculino", "Mountain Climbing", "Negro", "Anillo", "Motocicleta")));

	}

	@Test
	public void testPoliciaNoAtrapaLadronSinOrdenDeArresto() {

		Ladron ladron = crearLadronConObjetoComun();
		Policia policia = crearPolicia();
		assertFalse( policia.arrestar(ladron));

	}

	@Test
	public void testPoliciaNoAtrapaLadronConOrdenDeArrestoEquivocada(){

		Ladron ladron = crearLadronConObjetoComun();
		Policia policia = crearPolicia();
		//Crea Orden de arresto con caracteristicas que no son las del ladron juego pero que esta en la base de datos
		assertTrue(policia.emitirOrdenDeArresto(new CaracteristicaLadron("Merey Laroc", "Femenino", "Croquet", "Marron", "Joyas", "Limusina")));
		//No atrapa ladron porque no coincide con la orden de arresto.
		assertFalse(policia.arrestar(ladron));
	}

	@Test
	public void testPoliciaAtrapaLadronConTiempoYOrdenDeArrestoCorrecta(){

		Ladron ladron = crearLadronConObjetoComun();
		Policia policia = crearPolicia();
		policia.restarHoras(148);
		// Para crear la  orden de arresto lo hace con las caracteristicas del ladron (En este caso el que cree al inicio).
		assertTrue(policia.emitirOrdenDeArresto(new CaracteristicaLadron("Nick Brunch", "Masculino", "Mountain Climbing", "Negro", "Anillo", "Motocicleta")));
		//La orden de arresto coincide con el ladron del juego y lo arresta
		assertTrue(policia.arrestar(ladron));
	}

}
