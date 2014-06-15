package test.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import algo3.modelo.tiempo.Reloj;

public class RelojTest {

	private Reloj reloj;


	@Before
	public void crearReloj(){
		reloj = new Reloj();
	}


	@Test
	public void testCrearRelojEsLunesNueveHoras(){
		assertEquals("Lunes 09:00 horas", reloj.tiempoActual());
	}

	@Test
	public void testAumentarHorasCambiaHora(){
		reloj.transcurrir(3);
		assertEquals("Lunes 12:00 horas", reloj.tiempoActual());
	}

	@Test
	public void testAumentarHorasCambiaDia(){
		reloj.transcurrir(13); //Son las 22 horas
		assertEquals("Lunes 22:00 horas", reloj.tiempoActual());
		reloj.transcurrir(1); // Lunes 23 horas, es de noche -> Martes 8 horas
		assertEquals("Martes 08:00 horas", reloj.tiempoActual());

	}

	@Test
	public void testAccionAumentaRelojHastaTresAMPasaDoceAM(){
		reloj.transcurrir(13); // 22 horas
		reloj.transcurrir(5); // 3 am, dormir => 12 am
		assertEquals("Martes 12:00 horas", reloj.tiempoActual());
	}


	@Test
	public void testAumentarCienHorasEsViernesTreceHoras(){
		reloj.transcurrir(100);
		assertEquals("Viernes 13:00 horas", reloj.tiempoActual());
	}

	@Test
	public void testAumentarHastaElTiempoLimiteNoAumentaMas(){
		reloj.transcurrir(154);
		assertEquals("Domingo 17:00 horas", reloj.tiempoActual());
		reloj.transcurrir(3); // cambio de hora
		assertEquals("Domingo 17:00 horas", reloj.tiempoActual());
		reloj.transcurrir(20); // cambio de dia
		assertEquals("Domingo 17:00 horas", reloj.tiempoActual());
	}

	@Test
	public void testAumentarHoraLimiteNoHayTiempoRestante(){
		reloj.transcurrir(151);
		assertTrue(reloj.hayTiempoRestante());
		reloj.transcurrir(1);
		assertFalse(reloj.hayTiempoRestante());
		reloj.transcurrir(100);
		assertFalse(reloj.hayTiempoRestante());
	}


}
