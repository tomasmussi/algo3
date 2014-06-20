package test.modelo.integracion;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import algo3.modelo.edificio.Aeropuerto;
import algo3.modelo.edificio.Banco;
import algo3.modelo.edificio.Edificio;
import algo3.modelo.edificio.Embajada;
import algo3.modelo.entidad.Bandera;
import algo3.modelo.entidad.Gobierno;
import algo3.modelo.entidad.Moneda;
import algo3.modelo.ladron.CaracteristicaLadron;
import algo3.modelo.ladron.Ladron;
import algo3.modelo.mapa.mundi.Ciudad;
import algo3.modelo.mapa.mundi.InformacionCiudad;
import algo3.modelo.mapa.mundi.MapaMundi;
import algo3.modelo.objeto.CaracteristicaObjeto;
import algo3.modelo.objeto.ObjetoComun;
import algo3.modelo.objeto.Robable;
import algo3.modelo.policia.Policia;
import algo3.modelo.tiempo.Reloj;
import algo3.modelo.viaje.Recorrido;

public class ViajeTest {
	private Policia policia;
	private Reloj reloj;
	private List<InformacionCiudad> listaCiudadesRecorrido;
	private MapaMundi mapa = MapaMundi.getInstance();
	private Ladron ladron;


	@Before
	public void cargarMapa() {
		List<InformacionCiudad> listaCiudadesRecorrido;
		listaCiudadesRecorrido = new ArrayList<InformacionCiudad>();
		listaCiudadesRecorrido.add(new InformacionCiudad("Rio de Janeiro", "Verde y amarilla", "Real", "Presidente"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Nueva York", "Azul, roja y blanca", "Dolar", "Presidente"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Oslo", "Roja y azul", "Corona", "Rey"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Buenos Aires","Blanca y celeste", "Peso", "Presidente"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Tokyo","Blanca y roja", "Yen", "Presidente"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Paris","Blanca, roja y azul", "Franco", "Presidente"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Nueva Delhi","Roja, blanca y verde", "Rupia", "Primer Ministro"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Lima","Roja y blanca", "Sol", "Presidente"));
		MapaMundi.getInstance().cargarListadoCiudades(listaCiudadesRecorrido);
	}


	@Before
	public void crearListaDeInformacion() {
		this.listaCiudadesRecorrido = new ArrayList<InformacionCiudad>();
		listaCiudadesRecorrido.add(new InformacionCiudad("Rio de Janeiro", "Verde y amarilla", "Real", "Presidente"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Nueva York", "Azul, roja y blanca", "Dolar", "Presidente"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Oslo", "Roja y azul", "Corona", "Rey"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Buenos Aires","Blanca y celeste", "Peso", "Presidente"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Tokyo","Blanca y roja", "Yen", "Presidente"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Paris","Blanca, roja y azul", "Franco", "Presidente"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Nueva Delhi","Roja, blanca y verde", "Rupia", "Primer Ministro"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Lima","Roja y blanca", "Sol", "Presidente"));
		mapa.cargarListadoCiudades(listaCiudadesRecorrido);
	}

	@Before
	public void crearPolicia(){
		this.reloj = new Reloj();
		this.policia = new Policia();
		policia.setReloj(reloj);
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
		Edificio edificio1 = new Aeropuerto(new Bandera(siguienteCiudad.getColoresBandera()));
		Edificio edificio2 = new Banco(new Moneda(siguienteCiudad.getMoneda()));
		Edificio edificio3 = new Embajada(new Gobierno(siguienteCiudad.getGobierno()));
		return new Ciudad(1, 1, edificio1, edificio2, edificio3, new InformacionCiudad());
	}

	@Test
	public void testPoliciaViajaCiudadLadronEscapa() {

		Robable objeto = new ObjetoComun(new CaracteristicaObjeto("Presidente", "Lima"));
		Ciudad estaCiudad = mapa.getCiudadDeNombre(objeto.getCiudadOrigen());

		ladron = new Ladron(new CaracteristicaLadron("Carmen Sandiego", "Femenino", "Mountain Climbing", "Rojo", "Tatuaje", "Descapotable"));

		List<Ciudad> ciudades = mapa.getListadoCiudades();
		ladron.robar(objeto);
		Recorrido esteRecorrido = new Recorrido(ladron.getEscapatoria(), ciudades);
		//Caso esteCaso = new Caso(objeto, ladron, esteRecorrido);
		//policia.asignarCaso(esteCaso);

		Ciudad inicial = ladron.getCiudadActual();
		policia.viajarA(inicial);
		// Policia esta en ciudad inicial
		assertTrue(inicial.equals(policia.getCiudadActual()));
		ladron.moverAlSiguientePais();

		// Ahora no deberian estar en la misma ciudad, porque el ladron se fue.
		assertFalse(policia.getCiudadActual().equals(ladron.getCiudadActual()));
	}

	@Test
	public void testPoliciaAtrapaLadronConOrdenDeArrestoCorrectayTiempo() {

		ObjetoComun esteObjeto = new ObjetoComun(new CaracteristicaObjeto("Presidente", "Lima"));
		Ciudad estaCiudad = mapa.getCiudadDeNombre(esteObjeto.getCiudadOrigen());

		this.ladron = new Ladron(new CaracteristicaLadron("Nick Brunch", "Masculino", "Mountain Climbing", "Negro", "Anillo", "Motocicleta"));

		List<Ciudad> ciudades = mapa.getListadoCiudades();

		ladron.robar(esteObjeto);
		Recorrido esteRecorrido = new Recorrido(ladron.getEscapatoria(), ciudades);
		//Caso esteCaso = new Caso(esteObjeto, ladron, esteRecorrido);
		//policia.asignarCaso(esteCaso);

		Ciudad inicial = this.ladron.getCiudadActual();
		policia.viajarA(inicial);
		// El ladron se escapa1.
		ladron.moverAlSiguientePais();

		// Ahora no deberian estar en la misma ciudad, porque el ladron se fue.
		assertFalse(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));
		// Hago viajar al policia al pais donde puedo encontrar al ladron. Utilizo la ciudad del ladron.
		policia.viajarA(ladron.getCiudadActual());
		// Ambos deberian estar en la misma ciudad.
		assertTrue(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));

		// El ladron se escapa2.
		ladron.moverAlSiguientePais();
		// Ambos deberian estar en la misma ciudad.
		assertFalse(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));
		// Hago viajar al policia al pais donde puedo encontrar al ladron. Utilizo la ciudad del ladron.
		policia.viajarA(ladron.getCiudadActual());
		// Ambos deberian estar en la misma ciudad.
		assertTrue(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));

		// El ladron se escapa3.
		ladron.moverAlSiguientePais();
		assertFalse(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));
		// Hago viajar al policia al pais donde puedo encontrar al ladron. Utilizo la ciudad del ladron.
		policia.viajarA(ladron.getCiudadActual());
		// Ambos deberian estar en la misma ciudad.
		assertTrue(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));
		// El ladron se escapa (No se puede seguir escapando)
		ladron.moverAlSiguientePais();
		assertTrue(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));
		//Se fija que le quede tiempo para poder emitir la orden.
		// Crea Orden de arresto con las caracteristicas del ladron (En este caso el que cree al inicio)
		assertTrue(policia.emitirOrdenDeArresto(new CaracteristicaLadron("Nick Brunch", "Masculino", "Mountain Climbing", "Negro", "Anillo", "Motocicleta")));
		// Arresta ladron.
		assertTrue(policia.arrestar(ladron));
	}

	@Test
	public void testPoliciaNoAtrapaLadronConOrdenDeArrestoIncorrecta() {

		ObjetoComun esteObjeto = new ObjetoComun(new CaracteristicaObjeto("Presidente", "Lima"));
		Ciudad estaCiudad = mapa.getCiudadDeNombre(esteObjeto.getCiudadOrigen());

		this.ladron = new Ladron(new CaracteristicaLadron("Nick Brunch", "Masculino", "Mountain Climbing", "Negro", "Anillo", "Motocicleta"));

		List<Ciudad> ciudades = mapa.getListadoCiudades();
		ladron.robar(esteObjeto);
		Recorrido esteRecorrido = new Recorrido(ladron.getEscapatoria(), ciudades);
		//Caso esteCaso = new Caso(esteObjeto, ladron, esteRecorrido);
		//this.policia.asignarCaso(esteCaso);

		Ciudad inicial = this.ladron.getCiudadActual();
		policia.viajarA(inicial);
		// En esta situacion deberian estar en la misma ciudad.
		assertTrue(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));
		// El ladron se escapa.
		ladron.moverAlSiguientePais();
		// Ahora no deberian estar en la misma ciudad, porque el ladron se fue.
		assertFalse(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));
		// Hago viajar al policia al pais donde puedo encontrar al ladron. Utilizo la ciudad del ladron.
		policia.viajarA(ladron.getCiudadActual());
		// Ambos deberian estar en la misma ciudad.
		assertTrue(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));
		// El ladron se escapa.
		ladron.moverAlSiguientePais();
		// Ambos deberian estar en la misma ciudad.
		assertFalse(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));
		// Hago viajar al policia al pais donde puedo encontrar al ladron. Utilizo la ciudad del ladron.
		policia.viajarA(ladron.getCiudadActual());
		// Ambos deberian estar en la misma ciudad.
		assertTrue(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));
		// El ladron se escapa .
		ladron.moverAlSiguientePais();
		// Ambos deberian estar en la misma ciudad.
		assertFalse(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));
		// Hago viajar al policia al pais donde puedo encontrar al ladron. Utilizo la ciudad del ladron.
		policia.viajarA(ladron.getCiudadActual());
		// Ambos deberian estar en la misma ciudad.
		assertTrue(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));
		// El ladron se escapa (No tiene mas paises, no deberia poder irse).
		ladron.moverAlSiguientePais();
		// Ambos deberian estar en la misma ciudad.
		assertTrue(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));
		//Se fija que le quede tiempo para poder hacer la orden.
		// Crea Orden de arresto con las caracteristicas del ladron (En este caso el que cree al inicio)
		assertTrue(policia.emitirOrdenDeArresto(new CaracteristicaLadron("Merey Laroc", "Femenino", "Croquet", "Marron", "Joyas", "Limusina")));
		// Arresta ladron.
		assertFalse(policia.arrestar(ladron));
	}


	@Test
	public void testPoliciaNoAtrapaLadronSinOrdenDeArresto() {

		ObjetoComun esteObjeto = new ObjetoComun(new CaracteristicaObjeto("Presidente", "Lima"));
		Ciudad estaCiudad = mapa.getCiudadDeNombre(esteObjeto.getCiudadOrigen());

		this.ladron = new Ladron(new CaracteristicaLadron("Nick Brunch", "Masculino", "Mountain Climbing", "Negro", "Anillo", "Motocicleta"));

		List<Ciudad> ciudades = mapa.getListadoCiudades();
		ladron.robar(esteObjeto);
		Recorrido esteRecorrido = new Recorrido(ladron.getEscapatoria(), ciudades);
		//Caso esteCaso = new Caso(esteObjeto, ladron, esteRecorrido);
		//this.policia.asignarCaso(esteCaso);

		Ciudad inicial = this.ladron.getCiudadActual();
		policia.viajarA(inicial);
		// En esta situacion deberian estar en la misma ciudad.
		assertTrue(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));
		// El ladron se escapa.
		ladron.moverAlSiguientePais();
		// Ahora no deberian estar en la misma ciudad, porque el ladron se fue.
		assertFalse(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));
		// Hago viajar al policia al pais donde puedo encontrar al ladron. Utilizo la ciudad del ladron.
		policia.viajarA(ladron.getCiudadActual());
		// Ambos deberian estar en la misma ciudad.
		assertTrue(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));
		// El ladron se escapa (No tiene mas paises, no deberia poder irse).
		ladron.moverAlSiguientePais();
		// Ambos deberian estar en la misma ciudad.
		assertFalse(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));
		// Hago viajar al policia al pais donde puedo encontrar al ladron. Utilizo la ciudad del ladron.
		policia.viajarA(ladron.getCiudadActual());
		// Ambos deberian estar en la misma ciudad.
		assertTrue(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));
		// El ladron se escapa .
		ladron.moverAlSiguientePais();
		// Ambos deberian estar en la misma ciudad.
		assertFalse(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));
		// Hago viajar al policia al pais donde puedo encontrar al ladron. Utilizo la ciudad del ladron.
		policia.viajarA(ladron.getCiudadActual());
		// Ambos deberian estar en la misma ciudad.
		assertTrue(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));
		// El ladron se escapa (No tiene mas paises, no deberia poder irse).
		ladron.moverAlSiguientePais();
		// Ambos deberian estar en la misma ciudad.
		assertTrue(policia.getCiudadActual().esMismaCiudadQue(ladron.getCiudadActual()));
		// Arrestar ladron.
		assertFalse(policia.arrestar(ladron));
	}

	/*
	@Test
	public void testEdificioDaPistaSiguienteCiudad() {
		Ciudad siguienteCiudad = crearCiudadPrueba("Buenos Aires", "Celeste y Blanca", "Australes", "Presidente");
		Ciudad bangkok = crearCiudad("Bangkok", siguienteCiudad);
		Edificio[] edificiosPosibles = bangkok.getTodosLosEdificios();
		String pista = policia.visitarEdificioYObtenerPista(edificiosPosibles[0]); // 0 = aeropuerto.
		assertTrue(pista.equals("Me dicen mis fuentes que se fue en un avion con Celeste y Blanca en sus alas."));
	}

	// Entrar a un edificio (1hr la primera vez , 2 hs 2da vez, 3hs 3ra vez).
	@Test
	public void testEdificioRestaUnaHoraPorPrimerEdifico() {
		Edificio banco = new Banco(new Moneda("Peso"));
		policia.visitarEdificioYObtenerPista(banco);
		assertEquals("Lunes 08:00 horas",reloj.tiempoActual());
	}

	@Test
	public void testEdificioRestaDosHorasPorSegundoEdificio() {
		Edificio banco = new Banco(new Moneda("Peso"));
		Edificio aeropuerto = new Aeropuerto(new Bandera("Verde y azul"));
		policia.visitarEdificioYObtenerPista(banco);
		policia.visitarEdificioYObtenerPista(aeropuerto);
		assertEquals("Lunes 10:00 horas", reloj.tiempoActual());
	}

	@Test
	public void testEdificioRestaSoloUnaHoraPorEdificioSinImportarCantidadDeEntradas() {
		Edificio banco = new Banco(new Moneda("Peso"));
		policia.visitarEdificioYObtenerPista(banco);
		policia.visitarEdificioYObtenerPista(banco);
		policia.visitarEdificioYObtenerPista(banco);
		policia.visitarEdificioYObtenerPista(banco);
		assertEquals("Lunes 08:00 horas", reloj.tiempoActual());
	}*/
}