package test.modelo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import algo3.modelo.caso.Caso;
import algo3.modelo.edificio.Aeropuerto;
import algo3.modelo.edificio.Banco;
import algo3.modelo.edificio.Edificio;
import algo3.modelo.entidad.Bandera;
import algo3.modelo.entidad.Moneda;
import algo3.modelo.ladron.CaracteristicaLadron;
import algo3.modelo.mapa.InformacionCiudad;
import algo3.modelo.mapa.Mapa;
import algo3.modelo.objeto.CaracteristicaObjeto;
import algo3.modelo.policia.Policia;
import algo3.modelo.tiempo.Reloj;

public class EdificioTest {
	private Policia policia;
	private Reloj reloj;
	private Caso esteCaso;
	private List<CaracteristicaLadron> listaLadrones;
	private List<CaracteristicaObjeto> listaObjetos;

	@Before
	public void cargarMapa() {
		List<InformacionCiudad> listaCiudadesRecorrido;
		listaCiudadesRecorrido = new ArrayList<InformacionCiudad>();
		listaCiudadesRecorrido.add(new InformacionCiudad("Rio de Janeiro", "Verde y amarilla", "Real", "Presidente"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Nueva York", "Azul, roja y blanca", "Dolar", "Presidente"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Oslo", "Roja y azul", "Corona", "Rey"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Buenos Aires", "Blanca y celeste", "Peso", "Presidente"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Tokyo", "Blanca y roja", "Yen", "Presidente"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Paris", "Blanca, roja y azul", "Franco", "Presidente"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Nueva Delhi", "Roja, blanca y verde", "Rupia", "Primer Ministro"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Lima", "Roja y blanca", "Sol", "Presidente"));
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

	// Entrar a un edificio (1hr la primera vez , 2 hs 2da vez, 3hs 3ra vez).
	@Test
	public void testEdificioRestaUnaHoraPorPrimerEdifico() {
		Edificio banco = new Banco(null, new Moneda("Peso"));
		policia.visitarEdificioYObtenerPista(banco);
		assertEquals("Lunes 08:00 horas", reloj.tiempoActual());
	}

	@Test
	public void testEdificioRestaDosHorasPorSegundoEdificio() {
		Edificio banco = new Banco(null, new Moneda("Peso"));
		Edificio aeropuerto = new Aeropuerto(null, new Bandera("Verde y azul"));
		policia.visitarEdificioYObtenerPista(banco);
		policia.visitarEdificioYObtenerPista(aeropuerto);
		assertEquals("Lunes 10:00 horas", reloj.tiempoActual());
	}

	@Test
	public void testEdificioRestaSoloUnaHoraPorEdificioSinImportarCantidadDeEntradas() {
		Edificio banco = new Banco(null, new Moneda("Peso"));
		policia.visitarEdificioYObtenerPista(banco);
		policia.visitarEdificioYObtenerPista(banco);
		policia.visitarEdificioYObtenerPista(banco);
		policia.visitarEdificioYObtenerPista(banco);
		assertEquals("Lunes 08:00 horas", reloj.tiempoActual());
	}

	// @Test
	// public void testEdificioDaPistaSiguienteCiudad() {
	// Ciudad siguienteCiudad = crearCiudadPrueba("Buenos Aires", "Celeste y Blanca", "Australes", "Presidente");
	// Ciudad bangkok = crearCiudad("Bangkok", siguienteCiudad);
	// Edificio[] edificiosPosibles = bangkok.getTodosLosEdificios();
	// String pista = policia.visitarEdificioYObtenerPista(edificiosPosibles[0]); // 0 = aeropuerto.
	// assertTrue(pista.equals("Me dicen mis fuentes que se fue en un avion con Celeste y Blanca en sus alas."));
	// }

}
