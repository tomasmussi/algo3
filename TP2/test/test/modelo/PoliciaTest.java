package test.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import algo3.modelo.caso.Caso;
import algo3.modelo.excepcion.CiudadNoEncontradaException;
import algo3.modelo.ladron.CaracteristicaLadron;
import algo3.modelo.ladron.Ladron;
import algo3.modelo.objeto.CaracteristicaObjeto;
import algo3.modelo.policia.Policia;
import algo3.modelo.tiempo.Reloj;

public class PoliciaTest {

	private CaracteristicaLadron caracteristicaNickBrunch;
	private Ladron nickBrunch;
	private Policia policia;
	private Reloj reloj;

	private List<CaracteristicaLadron> ladrones;
	private List<CaracteristicaObjeto> objetos;

	@Before
	public void initialize()  throws CiudadNoEncontradaException{
		caracteristicaNickBrunch = new CaracteristicaLadron("Nick Brunch", "Masculino", "Mountain Climbing", "Negro", "Anillo", "Motocicleta");
		nickBrunch = new Ladron(caracteristicaNickBrunch);
		reloj = new Reloj();
		policia = new Policia();
		policia.setReloj(reloj);

		ladrones = new ArrayList<CaracteristicaLadron>();
		ladrones.add(caracteristicaNickBrunch);

		objetos = new ArrayList<CaracteristicaObjeto>();
		objetos.add(new CaracteristicaObjeto("Anillo del Papa", "Roma"));

		policia.asignarCaso(new Caso(ladrones,objetos, policia.getGrado()));
	}

	//	@Test
	//	public void testDispararPoliciaRestaCuatroHoras(){
	//		policia.disparado();
	//		assertEquals("Lunes 11:00 horas", reloj.tiempoActual());
	//	}

	@Test
	public void testPoliciaNovatoRecorrerKilometrosRestaHoras(){
		reloj = new Reloj();
		policia = new Policia();
		policia.setReloj(reloj);
		policia.viajar(900);
		assertEquals("Lunes 08:00 horas", reloj.tiempoActual());

		reloj = new Reloj();
		policia = new Policia();
		policia.setReloj(reloj);
		policia.viajar(700);
		assertEquals("Lunes 08:00 horas", reloj.tiempoActual());

		reloj = new Reloj();
		policia = new Policia();
		policia.setReloj(reloj);
		policia.viajar(1100);
		assertEquals("Lunes 09:00 horas", reloj.tiempoActual());

		reloj = new Reloj();
		policia = new Policia();
		policia.setReloj(reloj);
		policia.viajar(1800);
		assertEquals("Lunes 09:00 horas", reloj.tiempoActual());

		reloj = new Reloj();
		policia = new Policia();
		policia.setReloj(reloj);
		policia.viajar(1900);
		assertEquals("Lunes 10:00 horas", reloj.tiempoActual());
	}

	@Test
	public void testPoliciaCreadoPuedeArrestar(){
		assertTrue(policia.puedeArrestar());
	}

	@Test
	public void testPoliciaConMenosHorasPuedeArrestar(){
		reloj.transcurrir(140);
		assertTrue(policia.puedeArrestar());
	}



	@Test
	public void testPoliciaRestarTodasLasHorasJustasNoPuedeArrestar(){
		reloj.transcurrir(154);
		assertFalse(policia.puedeArrestar());
	}

	@Test
	public void testPoliciaCreadoEsNovato(){
		assertEquals(policia.getGrado().getPlaca(), "Novato");
	}

	@Test
	public void testPoliciaAsciendeADetective(){
		assertEquals(policia.getGrado().getPlaca(), "Novato");
		//emite orden de arresto que coincide con ladron del juego
		policia.emitirOrdenDeArresto(caracteristicaNickBrunch);
		for (int i = 1; i <= 5; i++){
			policia.arrestar(nickBrunch);
		}
		assertEquals(policia.getGrado().getPlaca(), "Detective");
	}

	@Test
	public void testPoliciaNovatoAsciendeHastaSargento(){

		assertEquals(policia.getGrado().getPlaca(), "Novato");
		policia.emitirOrdenDeArresto(caracteristicaNickBrunch);
		for (int i = 1; i <= 5; i++){
			policia.arrestar(nickBrunch);
		}
		assertEquals(policia.getGrado().getPlaca(), "Detective");
		for (int i = 6; i <= 10; i++){
			policia.arrestar(nickBrunch);
		}
		assertEquals(policia.getGrado().getPlaca(), "Investigador");
		for (int i = 11; i <= 20; i++){
			policia.arrestar(nickBrunch);
		}
		assertEquals(policia.getGrado().getPlaca(), "Sargento");
		for (int i = 0; i <= 90; i++){
			policia.arrestar(nickBrunch);
		}
		assertEquals(policia.getGrado().getPlaca(), "Sargento");
	}

	@Test
	public void testEmitirOrdenRestaTresHoras(){
		policia.emitirOrdenDeArresto(new CaracteristicaLadron("Carmen Sandiego", "Femenino", "Croquet", "Rojo", "Anillo", "Descapotable"));
		assertEquals("Lunes 10:00 horas", reloj.tiempoActual());
	}

	@Test
	public void testPoliciaNovatoRecorreKilometrosAsciendeTardaMenosHoras(){

		policia.emitirOrdenDeArresto(caracteristicaNickBrunch);
		policia.viajar(1300);
		assertEquals("Lunes 12:00 horas", reloj.tiempoActual());
		assertEquals(policia.getGrado().getPlaca(), "Novato");
		for (int i = 1; i <= 10; i++){
			policia.arrestar(nickBrunch);
		}
		assertEquals(policia.getGrado().getPlaca(), "Investigador");
		//Resta solo 1 hora
		policia.viajar(1300);
		assertEquals("Lunes 13:00 horas", reloj.tiempoActual());
	}

}
