package test.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import algo3.modelo.estacionPolicia.EstacionDePolicia;
import algo3.modelo.ladron.CaracteristicaLadron;

public class EstacionDePoliciaTest {

	private CaracteristicaLadron primeraCaracteristica;
	private CaracteristicaLadron segundaCaracteristica;
	private CaracteristicaLadron terceraCaracteristica;

	private List<CaracteristicaLadron> listaUnSoloExpediente;
	private List<CaracteristicaLadron> listaDosExpedientes;
	private List<CaracteristicaLadron> listaTresExpedientes;

	/*
	 * ****** FLAVIA: SUPER FIXME! *******
	 * NOTA: Por convenio, cuando se utiliza el patrón de diseño Singleton, la CARGA de información se genera junto con la instancia,
	 * y si la carga es dínamica porque va variando, buscar una forma más correcta de hacerlo, no utilizando un cargarExpediente cada 5 lineas.
	 */
	@Before
	public void crearExpedientes() {
		primeraCaracteristica = new CaracteristicaLadron("Nick Brunch", "Masculino", "Mountain Climbing", "Negro", "Anillo", "Motocicleta");
		segundaCaracteristica = new CaracteristicaLadron("Nick Brunch", "Masculino", "Mountain Climbing", "Rojo", "Anillo", "Motocicleta");
		terceraCaracteristica = new CaracteristicaLadron("Carmen Sandiego", "Femenino", "Mountain Climbing", "Rojo", "Tatuaje", "Descapotable");

		listaUnSoloExpediente = new ArrayList<CaracteristicaLadron>();
		listaUnSoloExpediente.add(primeraCaracteristica);

		listaDosExpedientes = new ArrayList<CaracteristicaLadron>();
		listaDosExpedientes.addAll(listaUnSoloExpediente);
		listaDosExpedientes.add(segundaCaracteristica);

		listaTresExpedientes = new ArrayList<CaracteristicaLadron>();
		listaTresExpedientes.addAll(listaDosExpedientes);
		listaTresExpedientes.add(terceraCaracteristica);
	}

	@Before
	public void crearEstacionDePolicia() {
		List<CaracteristicaLadron> caracteristicas = new ArrayList<CaracteristicaLadron>();
		EstacionDePolicia.getInstance().cargarExpedientes(caracteristicas);
	}

	@Test
	public void testEncontroUnExpedienteDeEstacionDePolicia() {
		List<CaracteristicaLadron> expedientes = EstacionDePolicia.getInstance().buscarExpediente(new CaracteristicaLadron("Nick Brunch", "Masculino", "Mountain Climbing", "Negro", "Anillo", "Motocicleta"));
		assertEquals(0, expedientes.size());
	}

	@Test
	public void testNoHayCoincidenciaConExpedientesDeEstacionPolicia() {
		// no hay sexo femenino en la base
		List<CaracteristicaLadron> expedientes = EstacionDePolicia.getInstance().buscarExpediente(new CaracteristicaLadron("Nick Brunch", "Femenino", "Mountain Climbing", "Negro", "Anillo", "Motocicleta"));
		assertEquals(0, expedientes.size());
	}

	@Test
	public void testVariasCoincidenciaConExpedientesDeEstacionPolicia() {
		List<CaracteristicaLadron> expedientes = EstacionDePolicia.getInstance().buscarExpediente(new CaracteristicaLadron(null, "Masculino", "Mountain Climbing", null, null, "Motocicleta"));
		assertEquals(0, expedientes.size());
	}

	@Test
	public void testCargarSinCaracteristicasDevuelveTodosLosLadrones() {
		List<CaracteristicaLadron> expedientes = EstacionDePolicia.getInstance().buscarExpediente(new CaracteristicaLadron(null, null, null, null, null, null));
		assertEquals(0, expedientes.size());
	}

	@Test
	public void testCrearOrdenConUnExpedienteDevuelveElMismoExpediente() {
		List<CaracteristicaLadron> expedientes = EstacionDePolicia.getInstance().buscarExpediente(new CaracteristicaLadron(null, null, null, null, null, null));
		assertEquals(expedientes.size(), 0);
		EstacionDePolicia.getInstance().cargarExpedientes(listaUnSoloExpediente);
		expedientes = EstacionDePolicia.getInstance().buscarExpediente(new CaracteristicaLadron(null, null, null, "Negro", null, null));
		assertEquals(expedientes.size(), 1);
		CaracteristicaLadron caracteristicaDevuelta = expedientes.get(0);
		assertTrue(caracteristicaDevuelta.equals(primeraCaracteristica));
	}

	@Test
	public void testCrearOrdenConUnExpedienteDevuelveMismoExpedientePeroNoConDosConMismasCaracteristicas() {
		EstacionDePolicia.getInstance().cargarExpedientes(listaUnSoloExpediente);
		List<CaracteristicaLadron> expedientes = EstacionDePolicia.getInstance().buscarExpediente(new CaracteristicaLadron(null, null, null, null, "Anillo", null));
		assertEquals(expedientes.size(), 1);
		assertTrue(expedientes.get(0).equals(listaUnSoloExpediente.get(0)));

		// Con dos falla el matcher porque hay con Anillo
		EstacionDePolicia.getInstance().cargarExpedientes(listaDosExpedientes);
		expedientes = EstacionDePolicia.getInstance().buscarExpediente(new CaracteristicaLadron(null, null, null, null, "Anillo", null));
		assertEquals(2, expedientes.size());
		assertTrue(expedientes.contains(primeraCaracteristica));
		assertTrue(expedientes.contains(segundaCaracteristica));
	}

	@Test
	public void testCrearOrdenConTresExpedientesNoCoincideConMasculinosPeroSiConFemenino() {
		EstacionDePolicia.getInstance().cargarExpedientes(listaTresExpedientes);
		List<CaracteristicaLadron> expedientes = EstacionDePolicia.getInstance().buscarExpediente(new CaracteristicaLadron(null, null, null, null, "Anillo", null));
		assertEquals(2, expedientes.size());
		assertTrue(expedientes.contains(primeraCaracteristica));
		assertTrue(expedientes.contains(segundaCaracteristica));
		expedientes = EstacionDePolicia.getInstance().buscarExpediente(new CaracteristicaLadron(null, null, "Mountain Climbing", null, null, null));
		assertEquals(3, expedientes.size());
		assertTrue(expedientes.contains(primeraCaracteristica));
		assertTrue(expedientes.contains(segundaCaracteristica));
		assertTrue(expedientes.contains(terceraCaracteristica));
		expedientes = EstacionDePolicia.getInstance().buscarExpediente(new CaracteristicaLadron(null, null, "Mountain Climbing", "Rojo", null, null));
		assertEquals(2, expedientes.size());
		assertTrue(expedientes.contains(segundaCaracteristica));
		assertTrue(expedientes.contains(terceraCaracteristica));
		expedientes = EstacionDePolicia.getInstance().buscarExpediente(new CaracteristicaLadron(null, null, "Mountain Climbing", "Rojo", null, "Motocicleta"));
		assertEquals(1, expedientes.size());
		assertTrue(expedientes.contains(segundaCaracteristica));
		expedientes = EstacionDePolicia.getInstance().buscarExpediente(new CaracteristicaLadron(null, "Femenino", "Mountain Climbing", "Rojo", null, "Motocicleta"));
		assertEquals(0, expedientes.size());
	}

}
