package test.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import algo3.modelo.tiempo.Reloj;

public class RelojTest {


	@Test
	public void testCrearRelojEsLunesSieteHoras(){
		Reloj reloj = new Reloj();
		assertEquals("Lunes 07:00 horas", reloj.tiempoActual());
	}

	@Test
	public void testAumentarHorasCambiaHora(){
		Reloj reloj = new Reloj();
		reloj.transcurrir(3);
		assertEquals("Lunes 10:00 horas", reloj.tiempoActual());
	}

	@Test
	public void testAumentarHorasCambiaDia(){
		Reloj reloj = new Reloj();
		reloj.transcurrir(15); //Son las 22 horas
		assertEquals("Lunes 22:00 horas", reloj.tiempoActual());
		reloj.transcurrir(1); // Lunes 23 horas, es de noche -> Martes 7 horas
		assertEquals("Martes 07:00 horas", reloj.tiempoActual());
	}

	@Test
	public void testAccionAumentaRelojHastaTresAMPasaDiezAM(){
		Reloj reloj = new Reloj();
		reloj.transcurrir(15); // 22 horas
		reloj.transcurrir(5); // 3 am, dormir => 10 am
		assertEquals("Martes 11:00 horas", reloj.tiempoActual());
	}


	@Test
	public void testAumentarCienHorasEsViernesOnceHoras(){
		Reloj reloj = new Reloj();
		reloj.transcurrir(100);
		assertEquals("Viernes 11:00 horas", reloj.tiempoActual());
	}

	@Test
	public void testAumentarHastaElTiempoLimiteNoAumentaMas(){
		Reloj reloj = new Reloj();
		reloj.transcurrir(154);
		assertEquals("Domingo 17:00 horas", reloj.tiempoActual());
		reloj.transcurrir(3); // cambio de hora
		assertEquals("Domingo 17:00 horas", reloj.tiempoActual());
		reloj.transcurrir(20); // cambio de dia
		assertEquals("Domingo 17:00 horas", reloj.tiempoActual());
	}

	@Test
	public void testAumentarHoraLimiteNoHayTiempoRestante(){
		Reloj reloj = new Reloj();
		reloj.transcurrir(153);
		assertTrue(reloj.hayTiempoRestante());
		reloj.transcurrir(1);
		assertFalse(reloj.hayTiempoRestante());
		reloj.transcurrir(100);
		assertFalse(reloj.hayTiempoRestante());
	}


}
