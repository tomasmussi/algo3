package test.modelo.integracion;

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
import algo3.modelo.mapa.Ciudad;
import algo3.modelo.mapa.InformacionCiudad;
import algo3.modelo.objeto.CaracteristicaObjeto;
import algo3.modelo.policia.Policia;
import algo3.modelo.tiempo.Reloj;

public class ViajeTest {
	private Policia policia;
	private Reloj reloj;
	private Caso esteCaso;
	private List<CaracteristicaLadron> listaLadrones;
	private List<CaracteristicaObjeto> listaObjetos;

	@Before
	public void cargarMapa() throws CiudadNoEncontradaException{
		List<InformacionCiudad> listaCiudadesRecorrido;
		listaCiudadesRecorrido = new ArrayList<InformacionCiudad>();
		listaCiudadesRecorrido.add(new InformacionCiudad("Rio de Janeiro", "Verde y amarilla", "Real", "Presidente"));
		listaCiudadesRecorrido.add(new InformacionCiudad("New York", "Azul, roja y blanca", "Dolar", "Presidente"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Oslo", "Roja y azul", "Corona", "Rey"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Buenos Aires","Blanca y celeste", "Peso", "Presidente"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Tokio","Blanca y roja", "Yen", "Presidente"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Paris","Blanca, roja y azul", "Franco", "Presidente"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Nueva Delhi","Roja, blanca y verde", "Rupia", "Primer Ministro"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Lima","Roja y blanca", "Sol", "Presidente"));

		listaLadrones = new ArrayList<CaracteristicaLadron>();
		listaLadrones.add(new CaracteristicaLadron("Nick Brunch", "Masculino", "Mountain Climbing", "Negro", "Anillo", "Motocicleta"));

		listaObjetos = new ArrayList<CaracteristicaObjeto>();
		listaObjetos.add(new CaracteristicaObjeto("Algo1", "Oslo"));
		listaObjetos.add(new CaracteristicaObjeto("Algo2", "Tokio"));
		listaObjetos.add(new CaracteristicaObjeto("Algo3", "Lima"));
		listaObjetos.add(new CaracteristicaObjeto("Algo4", "Paris"));

		this.reloj = new Reloj();
		this.policia = new Policia();
		policia.setReloj(reloj);
		esteCaso = new Caso(listaLadrones, listaObjetos, policia.getGrado());
		policia.asignarCaso(esteCaso);
	}

	@Test
	public void testPoliciaNovatoViajaCiudadLadronEscapa() {
		Ciudad ciudadActualLadron = esteCaso.getLadron().getCiudadActual();
		assertFalse(ciudadActualLadron.equals(policia.getCiudadActual()));
		policia.viajarA(ciudadActualLadron);
		assertFalse(esteCaso.getLadron().getCiudadActual().equals(policia.getCiudadActual()));
	}

	@Test
	public void testPoliciaAtrapaLadronConOrdenDeArrestoCorrectayTiempo() {
		Ladron esteLadron =  esteCaso.getLadron();
		Ciudad ciudadActualLadron = esteLadron.getCiudadActual();
		// El policia viaja a la 2da ciudad:
		policia.viajarA(ciudadActualLadron);
		assertFalse(policia.getCiudadActual().equals(esteLadron.getCiudadActual()));
		ciudadActualLadron = esteLadron.getCiudadActual();
		// El policia viaja a la 3er ciudad:
		policia.viajarA(ciudadActualLadron);
		assertFalse(policia.getCiudadActual().equals(esteLadron.getCiudadActual()));
		ciudadActualLadron = esteLadron.getCiudadActual();
		// El policia viaja a la 4ta ciudad:
		policia.viajarA(ciudadActualLadron);
		assertFalse(policia.getCiudadActual().equals(esteLadron.getCiudadActual()));
		// El policia viaja a la ultima ciudad
		ciudadActualLadron = esteLadron.getCiudadActual();
		policia.viajarA(ciudadActualLadron);
		assertTrue(policia.getCiudadActual().equals(esteLadron.getCiudadActual()));
		List<CaracteristicaLadron> caracteristicas = policia.emitirOrdenDeArresto(new CaracteristicaLadron("Nick Brunch", "Masculino", "Mountain Climbing", "Negro", "Anillo", "Motocicleta"));
		assertTrue(caracteristicas.get(0).equals(new CaracteristicaLadron("Nick Brunch", "Masculino", "Mountain Climbing", "Negro", "Anillo", "Motocicleta")));
		assertTrue(policia.getCaso().getOrdenDeArresto() != null);
		assertTrue(policia.arrestar(esteLadron));
	}

	@Test
	public void testPoliciaNoAtrapaLadronConOrdenDeArrestoIncorrecta() {
		Ladron esteLadron =  esteCaso.getLadron();

		// El policia viaja a la 2da ciudad:
		Ciudad ciudadActualLadron = esteLadron.getCiudadActual();
		policia.viajarA(ciudadActualLadron);
		assertFalse(policia.getCiudadActual().equals(esteLadron.getCiudadActual()));
		// El policia viaja a la 3er ciudad:
		ciudadActualLadron = esteLadron.getCiudadActual();
		policia.viajarA(ciudadActualLadron);
		assertFalse(policia.getCiudadActual().equals(esteLadron.getCiudadActual()));

		// El policia viaja a la 4ta ciudad:
		ciudadActualLadron = esteLadron.getCiudadActual();
		policia.viajarA(ciudadActualLadron);
		assertFalse(policia.getCiudadActual().equals(esteLadron.getCiudadActual()));

		// El policia viaja a la ultima ciudad
		ciudadActualLadron = esteLadron.getCiudadActual();
		policia.viajarA(ciudadActualLadron);
		assertTrue(policia.getCiudadActual().equals(ciudadActualLadron));
		// Crea Orden de arresto con las caracteristicas del ladron (En este caso el que cree al inicio)
		List<CaracteristicaLadron> caracteristicas = policia.emitirOrdenDeArresto(new CaracteristicaLadron("Merey Laroc", "Femenino", "Mountain Climbing", "Castanio", "Joyeria", "Limusina"));
		assertTrue(caracteristicas.get(0).equals(new CaracteristicaLadron("Merey Laroc", "Femenino", "Mountain Climbing", "Castanio", "Joyeria", "Limusina")));
		// Arresta ladron.
		assertTrue(policia.getCaso().getOrdenDeArresto() != null);
		assertFalse(policia.arrestar(esteLadron));


	}

	@Test
	public void testPoliciaNoAtrapaLadronSinOrdenDeArresto() {
		Ladron esteLadron =  esteCaso.getLadron();
		Ciudad ciudadActualLadron = esteLadron.getCiudadActual();
		// El policia viaja a la 2da ciudad:
		policia.viajarA(ciudadActualLadron);
		assertFalse(policia.getCiudadActual().equals(esteLadron.getCiudadActual()));
		Ciudad ciudadActualLadron2 = esteLadron.getCiudadActual();
		// El policia viaja a la 3er ciudad:
		policia.viajarA(ciudadActualLadron2);
		assertFalse(policia.getCiudadActual().equals(esteLadron.getCiudadActual()));
		Ciudad ciudadActualLadron3 = esteLadron.getCiudadActual();
		// El policia viaja a la 4ta ciudad:
		policia.viajarA(ciudadActualLadron3);
		assertFalse(policia.getCiudadActual().equals(esteLadron.getCiudadActual()));
		// El policia viaja a ultima ciudad:
		ciudadActualLadron = esteLadron.getCiudadActual();
		policia.viajarA(ciudadActualLadron);
		assertTrue(policia.getCiudadActual().equals(esteLadron.getCiudadActual()));
		assertTrue(policia.getCaso().getOrdenDeArresto() == null);
		assertFalse(policia.arrestar(esteLadron));
	}

}
