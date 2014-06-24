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
import algo3.modelo.mapa.InformacionCiudad;
import algo3.modelo.objeto.CaracteristicaObjeto;
import algo3.modelo.policia.grado.GradoDetective;
import algo3.modelo.policia.grado.GradoInvestigador;
import algo3.modelo.policia.grado.GradoNovato;
import algo3.modelo.policia.grado.GradoSargento;

public class CasoTest {
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

	@Test
	public void testNoPudoGenerarOrdenArrestoPorTenerVariasCoincidencias() throws CiudadNoEncontradaException{
		Caso caso = new Caso(listaLadrones, listaObjetos, new GradoNovato());
		caso.generarOrdenDeArresto(new CaracteristicaLadron(null, "Masculino", "Croquet", null, null, "Limousina"));
		assertEquals(null, caso.getOrdenDeArresto());
	}

	@Test
	public void testGenerarOrdenDeArrestoSiHayUnaSolaCoincidencia() throws CiudadNoEncontradaException{
		Caso caso = new Caso(listaLadrones, listaObjetos, new GradoNovato());
		caso.generarOrdenDeArresto(new CaracteristicaLadron("Nick Brunch","Masculino","Mountain Climbing","Negro","Anillo","Motocicleta"));
		assertFalse(caso.getOrdenDeArresto() == null);
	}

	@Test
	public void testGenerarOrdenDeArrestoCuandoYaExisteUnaLaPisa() throws CiudadNoEncontradaException{
		Caso caso = new Caso(listaLadrones, listaObjetos, new GradoNovato());
		caso.generarOrdenDeArresto(new CaracteristicaLadron("Nick Brunch","Masculino","Mountain Climbing","Negro","Anillo","Motocicleta"));
		assertTrue(caso.getOrdenDeArresto().getCaracteristicaLadron().equals(new CaracteristicaLadron("Nick Brunch","Masculino","Mountain Climbing","Negro","Anillo","Motocicleta")));
		//genero otra Orden De Arresto entonces pisa la anterior Orden.
		caso.generarOrdenDeArresto(new CaracteristicaLadron("Ihor Ihorovitch", "Masculino", "Croquet", "Rubio", "Tatuaje", "Limousina"));
		assertTrue(caso.getOrdenDeArresto().getCaracteristicaLadron().equals(new CaracteristicaLadron("Ihor Ihorovitch", "Masculino", "Croquet", "Rubio", "Tatuaje", "Limousina")));
	}

	@Test
	public void testEnGradoNovatoCreaCasoConEscapatoriaDe4Ciudades() throws CiudadNoEncontradaException{
		Caso caso = new Caso(listaLadrones, listaObjetos, new GradoNovato());
		assertTrue(4 == caso.getLadron().getEscapatoria().size());
	}

	@Test
	public void testEnGradoInvestigadorCreaCasoConEscapatoriaDe5Ciudades() throws CiudadNoEncontradaException{
		Caso caso = new Caso(listaLadrones, listaObjetos, new GradoInvestigador());
		assertTrue(5 == caso.getLadron().getEscapatoria().size());
	}

	@Test
	public void testEnGradoDetectiveCreaCasoConEscapatoriaDe5Ciudades() throws CiudadNoEncontradaException{
		Caso caso = new Caso(listaLadrones, listaObjetos, new GradoDetective());
		assertTrue(5 == caso.getLadron().getEscapatoria().size());
	}

	@Test
	public void testEnGradoSargentoCreaCasoConEscapatoriaDe7Ciudades() throws CiudadNoEncontradaException{
		Caso caso = new Caso(listaLadrones, listaObjetos, new GradoSargento());
		assertTrue(7 == caso.getLadron().getEscapatoria().size());
	}
}
