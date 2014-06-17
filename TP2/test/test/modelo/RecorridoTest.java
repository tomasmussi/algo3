package test.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import algo3.modelo.mapa.mundi.Ciudad;
import algo3.modelo.mapa.mundi.InformacionCiudad;
import algo3.modelo.viaje.Recorrido;

public class RecorridoTest {


	private List<Ciudad> listaCiudadesMundo;
	private List<Ciudad> listaRecorrido;


	@Before
	public void crearListaDeInformacion() {
		listaCiudadesMundo = new ArrayList<Ciudad>();
		listaRecorrido = new ArrayList<Ciudad>();
		listaCiudadesMundo.add(new Ciudad(new InformacionCiudad("Rio de Janeiro", "Verde y amarilla", "Real", "Presidente")));
		listaCiudadesMundo.add(new Ciudad(new InformacionCiudad("Nueva York", "Azul, roja y blanca", "Dolar", "Presidente")));
		listaCiudadesMundo.add(new Ciudad(new InformacionCiudad("Oslo", "Roja y azul", "Corona", "Rey")));
		listaCiudadesMundo.add(new Ciudad(new InformacionCiudad("Buenos Aires","Blanca y celeste", "Peso", "Presidente")));
		// Para los tests, uso siempre la creacion de 4 ciudades como el recorrido posta, las siguientes son de relleno
		listaCiudadesMundo.add(new Ciudad(new InformacionCiudad("Tokyo","Blanca y roja", "Yen", "Presidente")));
		listaCiudadesMundo.add(new Ciudad(new InformacionCiudad("Paris","Blanca, roja y azul", "Franco", "Presidente")));
		listaCiudadesMundo.add(new Ciudad(new InformacionCiudad("Nueva Delhi","Roja, blanca y verde", "Rupia", "Primer Ministro")));
		listaCiudadesMundo.add(new Ciudad(new InformacionCiudad("Lima","Roja y blanca", "Sol", "Presidente")));
		listaRecorrido.add(new Ciudad(new InformacionCiudad("Rio de Janeiro", "Verde y amarilla", "Real", "Presidente")));
		listaRecorrido.add(new Ciudad(new InformacionCiudad("Nueva York", "Azul, roja y blanca", "Dolar", "Presidente")));
		listaRecorrido.add(new Ciudad(new InformacionCiudad("Oslo", "Roja y azul", "Corona", "Rey")));
		listaRecorrido.add(new Ciudad(new InformacionCiudad("Buenos Aires","Blanca y celeste", "Peso", "Presidente")));

	}

	@Test
	public void testCantidadDeCiudadesARecorrer(){
		assertTrue(listaRecorrido.size() == 4);
	}


	@Test
	public void testCiudadesDelRecorridoTienenComoOpcionCiudadSiguiente(){
		Recorrido recorrido = new Recorrido(listaRecorrido, listaCiudadesMundo);
		boolean ok = true;
		for (int i = 0; i < listaRecorrido.size()-1; i++){
			Ciudad ciudadPrevia = listaRecorrido.get(i);
			Ciudad ciudadPosterior = listaRecorrido.get(i+1);
			ok &= recorrido.sonConsecutivas(ciudadPrevia, ciudadPosterior);
		}
		assertTrue(ok);
	}

	@Test
	public void testCiudadDevuelveTresOpcionesPosibles(){
		//FIXME: ELI ESTA PRUEBA NO PASA FIJATE PORQUE DEVUELVE UN 4 EN VEZ DE UN 3
		//NOSE SI EL METODO SE TIENE Q LLAMAR 4OPCPOSIBLES Y PONES UN 4 O ESTA MAL LA LOGICA
		Recorrido recorrido = new Recorrido(listaRecorrido, listaCiudadesMundo);
		Ciudad origen = listaRecorrido.get(0);
		assertEquals(3, recorrido.getCiudadesPosibles(origen).size());
	}

	@Test
	public void testCiudadesDelRecorridoTienenComoOpcionCiudadAnterior(){
		Recorrido recorrido = new Recorrido(listaRecorrido, listaCiudadesMundo);
		boolean ok = true;
		for (int i = 0; i < listaRecorrido.size()-1; i++){
			Ciudad ciudadPrevia = listaRecorrido.get(i);
			Ciudad ciudadPosterior = listaRecorrido.get(i+1);
			ok &= (recorrido.getCiudadesPosibles(ciudadPosterior)).contains(ciudadPrevia);
		}
		assertTrue(ok);
	}
}


