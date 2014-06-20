package test.modelo.integracion;

import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import algo3.modelo.caso.Caso;
import algo3.modelo.ladron.CaracteristicaLadron;
import algo3.modelo.mapa.mundi.Ciudad;
import algo3.modelo.mapa.mundi.InformacionCiudad;
import algo3.modelo.mapa.mundi.MapaMundi;
import algo3.modelo.objeto.CaracteristicaObjeto;
import algo3.modelo.policia.Policia;
import algo3.modelo.tiempo.Reloj;

public class ViajeTest {
	private Policia policia;
	private Reloj reloj;
	private List<CaracteristicaLadron> listaLadrones;
	private List<CaracteristicaObjeto> listaObjetos;
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
	public void cargarLadrones() {
		listaLadrones = new ArrayList<CaracteristicaLadron>();
		listaLadrones.add(new CaracteristicaLadron("Carmen Sandiego", "Femenino", "Mountain Climbing", "Rojo", "Tatuaje", "Descapotable"));
		listaLadrones.add(new CaracteristicaLadron("Nick Brunch", "Masculino", "Mountain Climbing", "Negro", "Anillo", "Motocicleta"));
		listaLadrones.add(new CaracteristicaLadron("Merey Laroc", "Femenino", "Croquet", "Marron", "Joyas", "Limusina"));
	}

	@Before
	public void cargarObjetos() {
		listaObjetos = new ArrayList<CaracteristicaObjeto>();
		listaObjetos.add(new CaracteristicaObjeto("Algo1", "Oslo"));
		listaObjetos.add(new CaracteristicaObjeto("Algo2", "Tokyo"));
		listaObjetos.add(new CaracteristicaObjeto("Algo3", "Lima"));
		listaObjetos.add(new CaracteristicaObjeto("Algo4", "Paris"));
	}

	@Before
	public void crearPolicia(){
		this.reloj = new Reloj();
		this.policia = new Policia();
		policia.setReloj(reloj);
	}


	@Test
	public void testPoliciaNovatoViajaCiudadLadronEscapa() {
		Caso esteCaso = new Caso(listaLadrones, listaObjetos, policia.getGrado());
		policia.asignarCaso(esteCaso);
		Ciudad ciudadActualLadron = esteCaso.getLadron().getCiudadActual();
		assertFalse(ciudadActualLadron.equals(policia.getCiudadActual()));
		policia.viajarA(ciudadActualLadron);
		assertFalse(esteCaso.getLadron().getCiudadActual().esMismaCiudadQue(policia.getCiudadActual()));
	}
	/*

	@Test
	public void testPoliciaNovatoViajaCiudadLadronEscapa() {
		Caso esteCaso = new Caso(listaLadrones, listaObjetos, policia.getGrado());
		policia.asignarCaso(esteCaso);
		Ciudad inicial = ladron.getCiudadActual();


		ladron.robar(objeto);
		Recorrido esteRecorrido = new Recorrido(ladron.getEscapatoria());
		//Caso esteCaso = new Caso(objeto, ladron, esteRecorrido);
		//policia.asignarCaso(esteCaso);

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
		Ciudad estaCiudad = MapaMundi.getInstance().getCiudadDeNombre(esteObjeto.getCiudadOrigen());

		this.ladron = new Ladron(new CaracteristicaLadron("Nick Brunch", "Masculino", "Mountain Climbing", "Negro", "Anillo", "Motocicleta"));

		ladron.robar(esteObjeto);
		Recorrido esteRecorrido = new Recorrido(ladron.getEscapatoria());
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
		Ciudad estaCiudad = MapaMundi.getInstance().getCiudadDeNombre(esteObjeto.getCiudadOrigen());

		this.ladron = new Ladron(new CaracteristicaLadron("Nick Brunch", "Masculino", "Mountain Climbing", "Negro", "Anillo", "Motocicleta"));

		ladron.robar(esteObjeto);
		Recorrido esteRecorrido = new Recorrido(ladron.getEscapatoria());
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
		Ciudad estaCiudad = MapaMundi.getInstance().getCiudadDeNombre(esteObjeto.getCiudadOrigen());

		this.ladron = new Ladron(new CaracteristicaLadron("Nick Brunch", "Masculino", "Mountain Climbing", "Negro", "Anillo", "Motocicleta"));

		ladron.robar(esteObjeto);
		Recorrido esteRecorrido = new Recorrido(ladron.getEscapatoria());
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
