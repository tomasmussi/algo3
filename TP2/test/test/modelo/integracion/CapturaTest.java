package test.modelo.integracion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import algo3.modelo.estacionPolicia.EstacionDePolicia;
import algo3.modelo.ladron.CaracteristicaLadron;
import algo3.modelo.ladron.Ladron;
import algo3.modelo.policia.Policia;
import algo3.modelo.tiempo.Reloj;


public class CapturaTest {

	/* ************** Métodos auxiliares ************** */

	private CaracteristicaLadron caracteristicaNickBrunch;
	private CaracteristicaLadron caracteristicaMereyLaroc;
	private CaracteristicaLadron caracteristicaUnivocaNickBrunch;
	private Ladron nickBrunch;
	private Reloj reloj;
	private Policia policia;

	@Before
	public void  initialize() {
		caracteristicaNickBrunch = new CaracteristicaLadron("Nick Brunch", "Masculino", "Mountain Climbing", "Negro", "Anillo", "Motocicleta");
		caracteristicaMereyLaroc = new CaracteristicaLadron("Merey Laroc", "Femenino", "Croquet", "Marron", "Joyas", "Limusina");
		caracteristicaUnivocaNickBrunch = new CaracteristicaLadron(null, null, null, "Negro", null, "Motocicleta");
		nickBrunch = new Ladron(caracteristicaNickBrunch);
		reloj = new Reloj();
		policia = new Policia();
		policia.setReloj(reloj);
	}

	/* Comienzan los test*/

	@Test
	public void testPoliciaNoAtrapaLadronSinTiempoRestante(){
		//Se quedo sin tiempo para emitir la orden de arresto.
		reloj.transcurrir(154);
		// Para crear la  orden de arresto lo hace con las caracteristicas del ladron que esta en base de datos.
		assertFalse(policia.emitirOrdenDeArresto(caracteristicaNickBrunch));
	}

	@Test
	public void testPoliciaNoAtrapaLadronSinOrdenDeArresto() {
		assertFalse(policia.arrestar(nickBrunch));
	}

	@Test
	public void testPoliciaNoAtrapaLadronConOrdenDeArrestoEquivocada(){
		//Crea Orden de arresto con caracteristicas que no son las del ladron juego pero que esta en la base de datos
		assertTrue(policia.emitirOrdenDeArresto(caracteristicaMereyLaroc));
		//No atrapa ladron porque no coincide con la orden de arresto.
		assertFalse(policia.arrestar(nickBrunch));
	}

	@Test
	public void testPoliciaAtrapaLadronConTiempoYOrdenDeArrestoCorrecta(){
		reloj.transcurrir(148);
		// Para crear la  orden de arresto lo hace con las caracteristicas del ladron (En este caso el que cree al inicio).
		assertTrue(policia.emitirOrdenDeArresto(caracteristicaNickBrunch));
		//La orden de arresto coincide con el ladron del juego y lo arresta
		assertTrue(policia.arrestar(nickBrunch));
	}

	@Test
	public void testPoliciaIdentificaLadronConOrdenDeArrestoQueCoincideConLadron(){
		List<CaracteristicaLadron> expedientes = EstacionDePolicia.getInstance().buscarExpediente(caracteristicaUnivocaNickBrunch);
		assertEquals(1, expedientes.size());
		CaracteristicaLadron caracteristicaObtenida = expedientes.get(0);
		assertTrue(caracteristicaNickBrunch.equals(caracteristicaObtenida));
	}


}
