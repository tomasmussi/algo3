package test.modelo.integracion;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import algo3.modelo.caso.Caso;
import algo3.modelo.ladron.CaracteristicaLadron;
import algo3.modelo.ladron.Ladron;
import algo3.modelo.mapa.Ciudad;
import algo3.modelo.mapa.InformacionCiudad;
import algo3.modelo.mapa.Mapa;
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
	public void cargarMapa(){
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
		Mapa.getInstance().cargarListadoCiudades(listaCiudadesRecorrido);

		listaLadrones = new ArrayList<CaracteristicaLadron>();
		listaLadrones.add(new CaracteristicaLadron("Nick Brunch", "Masculino", "Mountain Climbing", "Negro", "Anillo", "Motocicleta"));

		listaObjetos = new ArrayList<CaracteristicaObjeto>();
		listaObjetos.add(new CaracteristicaObjeto("Algo1", "Oslo"));
		listaObjetos.add(new CaracteristicaObjeto("Algo2", "Tokyo"));
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
		Ciudad ciudadActualLadron2 = esteLadron.getCiudadActual();
		// El policia viaja a la 3er ciudad:
		policia.viajarA(ciudadActualLadron2);
		assertFalse(policia.getCiudadActual().equals(esteLadron.getCiudadActual()));
		Ciudad ciudadActualLadron3 = esteLadron.getCiudadActual();
		// El policia viaja a la 4ta ciudad:
		policia.viajarA(ciudadActualLadron3);
		assertTrue(policia.getCiudadActual().equals(esteLadron.getCiudadActual()));
		assertTrue(policia.emitirOrdenDeArresto(new CaracteristicaLadron("Nick Brunch", "Masculino", "Mountain Climbing", "Negro", "Anillo", "Motocicleta")));
		assertTrue(policia.getCaso().getOrdenDeArresto() != null);
		assertTrue(policia.arrestar(esteLadron));
	}

	@Test
	public void testPoliciaNoAtrapaLadronConOrdenDeArrestoIncorrecta() {
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
		assertTrue(policia.getCiudadActual().equals(esteLadron.getCiudadActual()));
		// Crea Orden de arresto con las caracteristicas del ladron (En este caso el que cree al inicio)
		assertTrue(policia.emitirOrdenDeArresto(new CaracteristicaLadron("Merey Laroc", "Femenino", "Mountain Climbing", "Castaño", "Joyeria", "Limousina")));
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
		assertTrue(policia.getCiudadActual().equals(esteLadron.getCiudadActual()));
		assertTrue(policia.getCaso().getOrdenDeArresto() == null);
		assertFalse(policia.arrestar(esteLadron));
	}

}
