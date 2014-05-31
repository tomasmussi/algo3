package test.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import algo3.modelo.caracteres.policia.Policia;

public class PoliciaTest {

	@Test
	public void testCrearPolicia(){
		Policia policia = new Policia();
		assertEquals(policia.getHorasRestantes(), 154);
	}

	@Test
	public void testDescontarHorasPoliciaCreado(){
		Policia policia = new Policia();
		assertEquals(policia.getHorasRestantes(), Policia.HORAS_INICIALES);
		policia.restarHoras(10);
		assertEquals(policia.getHorasRestantes(), Policia.HORAS_INICIALES - 10);
	}

	@Test
	public void testDormirPoliciaSacaHorasRestantes(){
		Policia policia = new Policia();
		policia.dormir();
		assertEquals(policia.getHorasRestantes(), Policia.HORAS_INICIALES - 8);
	}

	@Test
	public void testAcuchillarPoliciaRestaDosHoras(){
		Policia policia = new Policia();
		policia.auchillado();
		assertEquals(policia.getHorasRestantes(), Policia.HORAS_INICIALES - 2);
	}

	@Test
	public void testDispararPoliciaRestaCuatroHoras(){
		Policia policia = new Policia();
		policia.disparado();
		assertEquals(policia.getHorasRestantes(), Policia.HORAS_INICIALES - 4);
	}

	@Test
	public void testPoliciaNovatoRecorrerKilometrosRestaHoras(){
		Policia policia = new Policia();
		policia.viajar(900);
		assertEquals(policia.getHorasRestantes(), Policia.HORAS_INICIALES - 1);

		policia = new Policia();
		policia.viajar(700);
		assertEquals(policia.getHorasRestantes(), Policia.HORAS_INICIALES - 1);

		policia = new Policia();
		policia.viajar(1100);
		assertEquals(policia.getHorasRestantes(), Policia.HORAS_INICIALES - 2);

		policia = new Policia();
		policia.viajar(1800);
		assertEquals(policia.getHorasRestantes(), Policia.HORAS_INICIALES - 2);

		policia = new Policia();
		policia.viajar(1900);
		assertEquals(policia.getHorasRestantes(), Policia.HORAS_INICIALES - 3);
	}

	@Test
	public void testPoliciaNoTieneHorasNegativas(){
		Policia policia = new Policia();
		assertEquals(policia.getHorasRestantes(), Policia.HORAS_INICIALES);
		policia.restarHoras(123);
		assertEquals(policia.getHorasRestantes(), Policia.HORAS_INICIALES - 123);
		policia.restarHoras(123);
		assertEquals(policia.getHorasRestantes(), 0);
	}

	@Test
	public void testPoliciaCreadoPuedeArrestar(){
		Policia policia = new Policia();
		assertTrue(policia.puedeArrestar());
	}

	@Test
	public void testPoliciaConMenosHorasPuedeArrestar(){
		Policia policia = new Policia();
		policia.restarHoras(140);
		assertTrue(policia.puedeArrestar());
	}

	@Test
	public void testPoliciaRestarTodasLasHorasJustasNoPuedeArrestar(){
		Policia policia = new Policia();
		policia.restarHoras(Policia.HORAS_INICIALES);
		assertFalse(policia.puedeArrestar());
	}

	@Test
	public void testPoliciaRestarHorasDemasNoPuedeArrestar(){
		Policia policia = new Policia();
		policia.restarHoras(Policia.HORAS_INICIALES + Policia.HORAS_INICIALES );
		assertFalse(policia.puedeArrestar());
	}


	@Test
	public void testPoliciaCreadoEsNovato(){
		Policia policia = new Policia();
		assertEquals(policia.getGrado(), "Novato");
	}


	@Test
	public void testPoliciaAsciendeADetective(){
		Policia policia = new Policia();
		assertEquals(policia.getGrado(), "Novato");
		for (int i = 1; i <= 5; i++){
			policia.aumentarArrestos();
		}
		assertEquals(policia.getGrado(), "Detective");
	}

	@Test
	public void testPoliciaNovatoAsciendeHastaSargento(){
		Policia policia = new Policia();
		assertEquals(policia.getGrado(), "Novato");
		for (int i = 1; i <= 5; i++){
			policia.aumentarArrestos();
		}
		assertEquals(policia.getGrado(), "Detective");
		for (int i = 6; i <= 10; i++){
			policia.aumentarArrestos();
		}
		assertEquals(policia.getGrado(), "Investigador");
		for (int i = 11; i <= 20; i++){
			policia.aumentarArrestos();
		}
		assertEquals(policia.getGrado(), "Sargento");
		for (int i = 0; i <= 90; i++){
			policia.aumentarArrestos();
		}
		assertEquals(policia.getGrado(), "Sargento");
	}




}
