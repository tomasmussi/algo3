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

	private CaracteristicaLadron caracteristica;
	private Ladron ladron;
	private Robable objeto;
	private Reloj reloj;
	private Policia policia;


	public void crearLadronConObjeto(){
		caracteristica = new CaracteristicaLadron("Nick Brunch", "Masculino", "Mountain Climbing", "Negro", "Anillo", "Motocicleta");
		ladron = new Ladron(caracteristica);
		objeto = new ObjetoComun("Buda dorado", "Bangkok");
		reloj = new Reloj();
		policia = new Policia(reloj);
	}

	/* Comienzan los test*/

	@Test
	public void testPoliciaNoAtrapaLadronSinTiempoRestante(){

		//Se quedo sin tiempo para emitir la orden de arresto.
		reloj.transcurrir(154);
		// Para crear la  orden de arresto lo hace con las caracteristicas del ladron que esta en base de datos.
		assertFalse(policia.emitirOrdenDeArresto(caracteristica));

	}

	@Test
	public void testPoliciaNoAtrapaLadronSinOrdenDeArresto() {
		assertFalse(policia.arrestar(ladron));
	}

	@Test
	public void testPoliciaNoAtrapaLadronConOrdenDeArrestoEquivocada(){

		//Crea Orden de arresto con caracteristicas que no son las del ladron juego pero que esta en la base de datos
		assertTrue(policia.emitirOrdenDeArresto(new CaracteristicaLadron("Merey Laroc", "Femenino", "Croquet", "Marron", "Joyas", "Limusina")));
		//No atrapa ladron porque no coincide con la orden de arresto.
		assertFalse(policia.arrestar(ladron));
	}

	@Test
	public void testPoliciaAtrapaLadronConTiempoYOrdenDeArrestoCorrecta(){
		reloj.transcurrir(148);
		// Para crear la  orden de arresto lo hace con las caracteristicas del ladron (En este caso el que cree al inicio).
		assertTrue(policia.emitirOrdenDeArresto(new CaracteristicaLadron("Nick Brunch", "Masculino", "Mountain Climbing", "Negro", "Anillo", "Motocicleta")));
		//La orden de arresto coincide con el ladron del juego y lo arresta
		assertTrue(policia.arrestar(ladron));
	}



}
