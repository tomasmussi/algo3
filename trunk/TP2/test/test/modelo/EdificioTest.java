package test.modelo;

import static org.junit.Assert.assertEquals;

import java.text.DecimalFormat;
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
import algo3.modelo.excepcion.CiudadNoEncontradaException;
import algo3.modelo.ladron.CaracteristicaLadron;
import algo3.modelo.mapa.Ciudad;
import algo3.modelo.mapa.InformacionCiudad;
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
	public void cargarMapa()  throws CiudadNoEncontradaException {
		List<InformacionCiudad> listaCiudadesRecorrido;
		listaCiudadesRecorrido = new ArrayList<InformacionCiudad>();
		listaCiudadesRecorrido.add(new InformacionCiudad("Rio de Janeiro", "Verde y amarilla", "Real", "Presidente"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Nueva York", "Azul, roja y blanca", "Dolar", "Presidente"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Oslo", "Roja y azul", "Corona", "Rey"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Buenos Aires","Blanca y celeste", "Peso", "Presidente"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Tokio","Blanca y roja", "Yen", "Presidente"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Paris","Blanca, roja y azul", "Franco", "Presidente"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Nueva Delhi","Roja, blanca y verde", "Rupia", "Primer Ministro"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Lima","Roja y blanca", "Sol", "Presidente"));

		listaLadrones = new ArrayList<CaracteristicaLadron>();
		listaLadrones.add(new CaracteristicaLadron("Nick Brunch", "Masculino", "Mountain Climbing", "Negro", "Anillo", "Motocicleta"));

		listaObjetos = new ArrayList<CaracteristicaObjeto>();
		listaObjetos.add(new CaracteristicaObjeto("Huevo de 1000 anios", "Pekin"));

		this.reloj = new Reloj();
		this.policia = new Policia();
		policia.setReloj(reloj);
		esteCaso = new Caso(listaLadrones, listaObjetos, policia.getGrado());
		policia.asignarCaso(esteCaso);
	}
	//Entrar a un edificio (1hr la primera vez , 2 hs 2da vez, 3hs 3ra vez).
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

	@Test
	public void testEdificioDaPistaSiguienteCiudad() {
		String colores = esteCaso.getLadron().getCiudadActual().getColoresBandera();
		String pista = policia.visitarEdificioYObtenerPista(policia.getCiudadActual().getTodosLosEdificios()[2]);
		assertEquals("Me dicen mis fuentes que se fue en un avion con " + colores + " en sus alas.", pista);
	}

	@Test
	public void testEntrarEdificiosCiudadesDistintasRestaUnaHora(){
		policia.visitarEdificioYObtenerPista(policia.getCiudadActual().getTodosLosEdificios()[1]);
		assertEquals("Lunes 08:00 horas", reloj.tiempoActual());
		Ciudad siguiente = esteCaso.getMapa().getCiudadesPosibles(policia.getCiudadActual()).get(2);
		//Calculo la distancia de horas que transcurren
		int kilometros = policia.getCiudadActual().getDistanciaCon(siguiente);
		int horas = policia.calcularKilometrosPorHora(kilometros) + 8;
		policia.viajarA(siguiente);
		String horasString = new DecimalFormat("00").format(horas);
		assertEquals("Lunes " + horasString + ":00 horas", reloj.tiempoActual());

		policia.visitarEdificioYObtenerPista(policia.getCiudadActual().getTodosLosEdificios()[2]);
		horas++; //Sumo una hora por visitar un edificio
		horasString = new DecimalFormat("00").format(horas);
		assertEquals("Lunes " + horasString + ":00 horas", reloj.tiempoActual());
	}


}
