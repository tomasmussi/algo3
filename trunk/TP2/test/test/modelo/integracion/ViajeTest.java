package test.modelo.integracion;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import algo3.modelo.ladron.CaracteristicaLadron;
import algo3.modelo.ladron.Ladron;
import algo3.modelo.mapa.mundi.Aeropuerto;
import algo3.modelo.mapa.mundi.Banco;
import algo3.modelo.mapa.mundi.Ciudad;
import algo3.modelo.mapa.mundi.Edificio;
import algo3.modelo.mapa.mundi.Embajada;
import algo3.modelo.mapa.mundi.InformacionCiudad;
import algo3.modelo.objeto.ObjetoComun;
import algo3.modelo.objeto.Robable;
import algo3.modelo.policia.Policia;
import algo3.modelo.viaje.Recorrido;

public class ViajeTest {

	/* ************** Métodos auxiliares ************** */
	private Policia crearPolicia() {
		return new Policia();
	}

	/**
	 * Crea un ladrón con una ciudad de origen dada
	 * y crea otra ciudad para escapar.
	 * */
	private Ladron crearLadronConObjetoComunYRecorrido(InformacionCiudad ciudadInicial) {
		CaracteristicaLadron caracteristica = new CaracteristicaLadron("Nick Brunch","Masculino","Mountain Climbing","Negro","Anillo","Motocicleta");
		Robable objeto = new ObjetoComun("Buda dorado", "Bangkok");

		List<InformacionCiudad> ciudadesARecorrer = new ArrayList<InformacionCiudad>();
		InformacionCiudad segundaCiudad = crearInformacionCiudad("New York", "Azul, Roja y Blanca", "Dolar", "Presidente");
		ciudadesARecorrer.add(ciudadInicial);
		ciudadesARecorrer.add(segundaCiudad);
		ciudadesARecorrer.add(ciudadInicial);
		ciudadesARecorrer.add(segundaCiudad);
		Recorrido recorrido = new Recorrido(ciudadesARecorrer, objeto.getCantidadDeCiudades());

		return new Ladron(caracteristica, objeto, recorrido.getCiudadesRecorrido().iterator());
	}

	private InformacionCiudad crearInformacionCiudad(String nombre, String bandera, String moneda, String gobierno) {
		return new InformacionCiudad(nombre, bandera, moneda, gobierno);
	}

	/**
	 * Devuleve una ciudad sin edificios pero con informacion para pedirle: colores bandera, moneda, etc.
	 */
	private Ciudad crearCiudadPrueba(String nombre, String bandera, String moneda, String gobierno) {
		InformacionCiudad informacion = new InformacionCiudad(nombre, bandera, moneda, gobierno);
		return new Ciudad(0, 0, null, null, null, informacion);
	}

	/**
	 * Devuelve una Ciudad con Edificios Fijos {Aeropuerto, Banco, Embajada} pero sin informacion de si misma.
	 */
	private Ciudad crearCiudad(String nombre, Ciudad siguienteCiudad) {
		Edificio edificio1 = new Aeropuerto(siguienteCiudad.getColoresBandera());
		Edificio edificio2 = new Banco(siguienteCiudad.getMoneda());
		Edificio edificio3 = new Embajada(siguienteCiudad.getGobierno());
		return new Ciudad(1, 1, edificio1, edificio2, edificio3, new InformacionCiudad());
	}

	/* ********************************************************** */

	@Test
	public void testPoliciaViajaCiudadLadronEscapa() {
		InformacionCiudad ciudadInicial = crearInformacionCiudad("Rio de Janeiro", "Verde y Amarillo", "Reales", "Presidente");
		// Creo ladrón en una ciudad inicial.
		Ladron ladron = crearLadronConObjetoComunYRecorrido(ciudadInicial);
		Ciudad inicial = ladron.getCiudadActual();
		// Creo policia sin ciudad inicial.
		Policia policia = crearPolicia();
		// Hago viajar al policia al pais donde puedo encontrar al ladron. En este caso a la ciudad inicial.
		policia.viajarA(inicial);
		// En esta situación deberían estar en la misma ciudad.
		assertTrue(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));
		// El ladron se escapa.
		ladron.moverAlSiguientePais();
		// Ahora no deberían estar en la misma ciudad, porque el ladrón se fue.
		assertFalse(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));
	}

	@Test
	public void testPoliciaAtrapaLadronConOrdenDeArrestoCorrecta() {
		// Caso con 2 ciudades. Ejemplo facil.
		InformacionCiudad ciudadInicial = crearInformacionCiudad("Rio de Janeiro", "Verde y Amarillo", "Reales", "Presidente");
		// Creo ladrón en una ciudad inicial.
		Ladron ladron = crearLadronConObjetoComunYRecorrido(ciudadInicial);
		Ciudad inicial = ladron.getCiudadActual();
		// Creo policia sin ciudad inicial.
		Policia policia = crearPolicia();
		// Hago viajar al policia al pais donde puedo encontrar al ladron. En este caso a la ciudad inicial.
		policia.viajarA(inicial);
		// En esta situación deberían estar en la misma ciudad.
		assertTrue(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));
		// El ladron se escapa.
		ladron.moverAlSiguientePais();
		// Ahora no deberían estar en la misma ciudad, porque el ladrón se fue.
		assertFalse(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));
		// Hago viajar al policia al pais donde puedo encontrar al ladron. Utilizo la ciudad del ladron.
		policia.viajarA(ladron.getCiudadActual());
		// Ambos deberían estar en la misma ciudad.
		assertTrue(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));
		// El ladron se escapa
		ladron.moverAlSiguientePais();
		// Ambos deberían estar en la misma ciudad.
		assertFalse(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));
		// Hago viajar al policia al pais donde puedo encontrar al ladron. Utilizo la ciudad del ladron.
		policia.viajarA(ladron.getCiudadActual());
		// Ambos deberían estar en la misma ciudad.
		assertTrue(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));
		// El ladron se escapa (Ultima vez que se puede cambiar de pais)
		ladron.moverAlSiguientePais();
		assertFalse(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));
		// Hago viajar al policia al pais donde puedo encontrar al ladron. Utilizo la ciudad del ladron.
		policia.viajarA(ladron.getCiudadActual());
		// Ambos deberían estar en la misma ciudad.
		assertTrue(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));
		// El ladron se escapa (No se puede seguir escapando)
		ladron.moverAlSiguientePais();
		assertTrue(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));
		// Crear Orden de arresto con las caracteristicas del ladron (En este caso el que cree al inicio)
		policia.emitirOrdenDeArresto(new CaracteristicaLadron("Nick Brunch","Masculino","Mountain Climbing","Negro","Anillo","Motocicleta"));
		// Arrestar ladron.
		assertTrue(policia.arrestar(ladron));
	}

	@Test
	public void testPoliciaNoAtrapaLadronConOrdenDeArrestoIncorrecta() {
		// Caso con 2 ciudades. Ejemplo facil.
		InformacionCiudad ciudadInicial = crearInformacionCiudad("Rio de Janeiro", "Verde y Amarillo", "Reales", "Presidente");
		// Creo ladrón en una ciudad inicial.
		Ladron ladron = crearLadronConObjetoComunYRecorrido(ciudadInicial);
		// Creo policia sin ciudad inicial.
		Policia policia = crearPolicia();
		// Hago viajar al policia al pais donde puedo encontrar al ladron. En este caso a la ciudad inicial.
		policia.viajarA(ladron.getCiudadActual());
		// En esta situación deberían estar en la misma ciudad.
		assertTrue(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));
		// El ladron se escapa.
		ladron.moverAlSiguientePais();
		// Ahora no deberían estar en la misma ciudad, porque el ladrón se fue.
		assertFalse(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));
		// Hago viajar al policia al pais donde puedo encontrar al ladron. Utilizo la ciudad del ladron.
		policia.viajarA(ladron.getCiudadActual());
		// Ambos deberían estar en la misma ciudad.
		assertTrue(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));
		// El ladron se escapa.
		ladron.moverAlSiguientePais();
		// Ambos deberían estar en la misma ciudad.
		assertFalse(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));
		// Hago viajar al policia al pais donde puedo encontrar al ladron. Utilizo la ciudad del ladron.
		policia.viajarA(ladron.getCiudadActual());
		// Ambos deberían estar en la misma ciudad.
		assertTrue(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));
		// El ladron se escapa .
		ladron.moverAlSiguientePais();
		// Ambos deberían estar en la misma ciudad.
		assertFalse(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));
		// Hago viajar al policia al pais donde puedo encontrar al ladron. Utilizo la ciudad del ladron.
		policia.viajarA(ladron.getCiudadActual());
		// Ambos deberían estar en la misma ciudad.
		assertTrue(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));
		// El ladron se escapa (No tiene mas paises, no debería poder irse).
		ladron.moverAlSiguientePais();
		// Ambos deberían estar en la misma ciudad.
		assertTrue(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));
		// Crear Orden de arresto con las caracteristicas del ladron (En este caso el que cree al inicio)
		policia.emitirOrdenDeArresto(new CaracteristicaLadron("Merey Laroc", "Femenino", "Croquet", "Marron", "Joyas", "Limusina"));
		// Arrestar ladron.
		assertFalse(policia.arrestar(ladron));
	}

	@Test
	public void testPoliciaNoAtrapaLadronSinOrdenDeArresto() {
		// Caso con 2 ciudades. Ejemplo facil.
		InformacionCiudad ciudadInicial = crearInformacionCiudad("Rio de Janeiro", "Verde y Amarillo", "Reales", "Presidente");
		// Creo ladrón en una ciudad inicial.
		Ladron ladron = crearLadronConObjetoComunYRecorrido(ciudadInicial);
		// Creo policia sin ciudad inicial.
		Policia policia = crearPolicia();
		// Hago viajar al policia al pais donde puedo encontrar al ladron. En este caso a la ciudad inicial.
		policia.viajarA(ladron.getCiudadActual());
		// En esta situación deberían estar en la misma ciudad.
		assertTrue(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));
		// El ladron se escapa.
		ladron.moverAlSiguientePais();
		// Ahora no deberían estar en la misma ciudad, porque el ladrón se fue.
		assertFalse(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));
		// Hago viajar al policia al pais donde puedo encontrar al ladron. Utilizo la ciudad del ladron.
		policia.viajarA(ladron.getCiudadActual());
		// Ambos deberían estar en la misma ciudad.
		assertTrue(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));
		// El ladron se escapa (No tiene mas paises, no debería poder irse).
		ladron.moverAlSiguientePais();
		// Ambos deberían estar en la misma ciudad.
		assertFalse(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));
		// Hago viajar al policia al pais donde puedo encontrar al ladron. Utilizo la ciudad del ladron.
		policia.viajarA(ladron.getCiudadActual());
		// Ambos deberían estar en la misma ciudad.
		assertTrue(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));
		// El ladron se escapa .
		ladron.moverAlSiguientePais();
		// Ambos deberían estar en la misma ciudad.
		assertFalse(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));
		// Hago viajar al policia al pais donde puedo encontrar al ladron. Utilizo la ciudad del ladron.
		policia.viajarA(ladron.getCiudadActual());
		// Ambos deberían estar en la misma ciudad.
		assertTrue(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));
		// El ladron se escapa (No tiene mas paises, no debería poder irse).
		ladron.moverAlSiguientePais();
		// Ambos deberían estar en la misma ciudad.
		assertTrue(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));
		// Arrestar ladron.
		assertFalse(policia.arrestar(ladron));
	}

	// @Test
	// public void testRecorridoCorrectoMeLLevaAlLadron(){
	// throw new UnsupportedOperationException("No implementada");
	// }

	@Test
	public void testEdificioDaPistaSiguienteCiudad() {
		Policia policia = crearPolicia();
		Ciudad siguienteCiudad = crearCiudadPrueba("Buenos Aires", "Celeste y Blanca", "Australes", "Presidente");
		Ciudad bangkok = crearCiudad("Bangkok", siguienteCiudad);
		/*
		 * TODO: Analizar la posibilidad de que una ciudad entregue una lista de sus edificios o que se los pueda
		 * acceder mediante una clave. Ej getEdificio("Aeropuerto"). NOTA: puede o no devolver algo. TBD
		 */
		Edificio[] edificiosPosibles = bangkok.getTodosLosEdificios();
		String pista = policia.visitarEdificioYObtenerPista(edificiosPosibles[0]); // 0 = aeropuerto. Solo para prueba. Corregir!

		assertTrue(pista.equals("Me dicen mis fuentes que se fue en un avion con una bandera Celeste y Blanca en sus alas."));
	}

	// Entrar a un edificio (1hr la primera vez , 2 hs 2da vez, 3hs 3ra vez).
	@Test
	public void testEdificioRestaUnaHoraPorPrimerEdifico() {
		Policia policia = crearPolicia();
		int horasIniciales = policia.getHorasRestantes();
		Edificio banco = new Banco("Pesos");
		policia.visitarEdificioYObtenerPista(banco);
		int horasDespuesDeVisitarEdificioUnaVez = policia.getHorasRestantes();
		assertTrue((horasIniciales - 1) == horasDespuesDeVisitarEdificioUnaVez);
	}

	@Test
	public void testEdificioRestaDosHorasPorSegundoEdificio() {
		Policia policia = crearPolicia();
		int horasIniciales = policia.getHorasRestantes();
		Edificio banco = new Banco("Pesos");
		Edificio aeropuerto = new Aeropuerto("verde y azul");
		policia.visitarEdificioYObtenerPista(banco);
		policia.visitarEdificioYObtenerPista(aeropuerto);
		int horasDespuesDeVisitarEdificioUnaVez = policia.getHorasRestantes();
		assertTrue((horasIniciales - 3) == horasDespuesDeVisitarEdificioUnaVez);
	}

	@Test
	public void testEdificioRestaSoloUnaHoraPorEdificioSinImportarCantidadDeEntradas() {
		Policia policia = crearPolicia();
		int horasIniciales = policia.getHorasRestantes();
		Edificio banco = new Banco("Pesos");
		policia.visitarEdificioYObtenerPista(banco);
		policia.visitarEdificioYObtenerPista(banco);
		policia.visitarEdificioYObtenerPista(banco);
		policia.visitarEdificioYObtenerPista(banco);
		int horasDespuesDeVisitarEdificioUnaVez = policia.getHorasRestantes();
		assertTrue((horasIniciales - 1) == horasDespuesDeVisitarEdificioUnaVez);
	}
}
