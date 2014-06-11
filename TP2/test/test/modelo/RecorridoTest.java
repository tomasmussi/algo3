package test.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import algo3.modelo.mapa.mundi.Ciudad;
import algo3.modelo.mapa.mundi.InformacionCiudad;
import algo3.modelo.viaje.Recorrido;

public class RecorridoTest {

	private static final int MAX_CIUDADES = 4;

	private List<InformacionCiudad> listaCiudadesRecorrido;

	@Before
	public void crearListaDeInformacion() {
		listaCiudadesRecorrido = new ArrayList<InformacionCiudad>();
		listaCiudadesRecorrido.add(new InformacionCiudad("Rio de Janeiro", "Verde y amarilla", "Real", "Presidente"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Nueva York", "Azul, roja y blanca", "Dolar", "Presidente"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Oslo", "Roja y azul", "Corona", "Rey"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Buenos Aires","Blanca y celeste", "Peso", "Presidente"));
		// Para los tests, uso siempre la creacion de 4 ciudades como el recorrido posta, las siguientes son de relleno
		listaCiudadesRecorrido.add(new InformacionCiudad("Tokyo","Blanca y roja", "Yen", "Presidente"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Paris","Blanca, roja y azul", "Franco", "Presidente"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Nueva Delhi","Roja, blanca y verde", "Rupia", "Primer Ministro"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Lima","Roja y blanca", "Sol", "Presidente"));
	}

	@Test
	public void testCrearRecorridoPartiendoDeObjetoRobado() {
		Recorrido recorrido = new Recorrido(listaCiudadesRecorrido, MAX_CIUDADES);
		List<Ciudad> ciudades = recorrido.getCiudadesRecorrido();
		boolean verificar = true;
		for (int i = 0; i < ciudades.size(); i++){
			Ciudad ciudad = ciudades.get(i);
			String nombreCiudad = listaCiudadesRecorrido.get(i).getNombreCiudad();
			verificar = verificar && (nombreCiudad.equals(ciudad.getNombre()));
		}
		assertTrue(verificar);
	}

	@Test
	public void testCiudadesDelRecorridoTienenSiguienteOpcionCiudadCorrectaReescrito(){
		Recorrido recorrido = new Recorrido(listaCiudadesRecorrido, MAX_CIUDADES);
		boolean ok = true;
		Iterator<Ciudad> previa = recorrido.getCiudadesRecorrido().iterator();
		Iterator<Ciudad> posterior = recorrido.getCiudadesRecorrido().iterator();
		posterior.next();
		while (previa.hasNext()){
			Ciudad ciudadPrevia = previa.next();
			if (posterior.hasNext()){
				Ciudad ciudadPosterior = posterior.next();
				//Comparar
				ok &= recorrido.sonConsecutivas(ciudadPrevia, ciudadPosterior);
			}
		}
		assertTrue(ok);
	}

	@Test
	public void testCiudadDevuelveCuatroOpcionesPosibles(){
		Recorrido recorrido = new Recorrido(listaCiudadesRecorrido, MAX_CIUDADES);
		Ciudad origen = new Ciudad(listaCiudadesRecorrido.get(0));
		assertEquals(4, recorrido.getCiudadesPosibles(origen).size());
	}

	@Test
	public void testCiudadDevuelveComoOpcionSiguienteCiudadsDelRecorrido(){
		Recorrido recorrido = new Recorrido(listaCiudadesRecorrido, MAX_CIUDADES);
		Ciudad origen = new Ciudad(listaCiudadesRecorrido.get(0));
		Ciudad destino = new Ciudad(listaCiudadesRecorrido.get(1));
		Iterator<Ciudad> ciudades = recorrido.getCiudadesPosibles(origen).iterator();
		boolean ok = false;
		while (ciudades.hasNext() && !ok){
			Ciudad posibleCiudad = ciudades.next();
			ok = posibleCiudad.equals(destino);
		}
		assertTrue(ok);
	}

	@Test
	public void testSiguienteCiudadDelRecorridoDevuelveCiudadOrigenComoOpcion(){
		Recorrido recorrido = new Recorrido(listaCiudadesRecorrido, MAX_CIUDADES);
		Ciudad origen = new Ciudad(listaCiudadesRecorrido.get(0));
		Ciudad destino = new Ciudad(listaCiudadesRecorrido.get(1));
		Iterator<Ciudad> ciudades = recorrido.getCiudadesPosibles(destino).iterator();
		boolean ok = false;
		while (ciudades.hasNext() && !ok){
			Ciudad posibleCiudad = ciudades.next();
			ok = posibleCiudad.equals(origen);
		}
		assertTrue(ok);

		// Chequeo con las 2 ultimas ciudades del recorrido
		origen = new Ciudad(listaCiudadesRecorrido.get(2));
		destino = new Ciudad(listaCiudadesRecorrido.get(3));
		ciudades = recorrido.getCiudadesPosibles(destino).iterator();
		ok = false;
		while (ciudades.hasNext() && !ok){
			Ciudad posibleCiudad = ciudades.next();
			ok = posibleCiudad.equals(origen);
		}
		assertTrue(ok);
	}


}
