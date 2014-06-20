package test.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import algo3.modelo.mapa.mundi.Ciudad;
import algo3.modelo.mapa.mundi.InformacionCiudad;
import algo3.modelo.mapa.mundi.InformacionCiudadProvider;
import algo3.modelo.mapa.mundi.NombresCiudades;
import algo3.modelo.viaje.Recorrido;

public class RecorridoTest {


	private List<Ciudad> listaCiudadesMundo;
	private List<Ciudad> listaRecorrido;
	
	private InformacionCiudad getInformacionPara(String ciudad){
		return InformacionCiudadProvider.getInstance().getInformacionPara(ciudad);
	}


	@Before
	public void crearListaDeInformacion() {
		listaCiudadesMundo = new ArrayList<Ciudad>();
		listaRecorrido = new ArrayList<Ciudad>();
		listaCiudadesMundo.add(new Ciudad(0,0, null, null, null, getInformacionPara(NombresCiudades.RIO_DE_JANEIRO)));
		listaCiudadesMundo.add(new Ciudad(0,0, null, null, null, getInformacionPara(NombresCiudades.NEW_YORK)));
		listaCiudadesMundo.add(new Ciudad(0,0, null, null, null, getInformacionPara(NombresCiudades.OSLO)));
		listaCiudadesMundo.add(new Ciudad(0,0, null, null, null, getInformacionPara(NombresCiudades.BUENOS_AIRES)));
		// Para los tests, uso siempre la creacion de 4 ciudades como el recorrido posta, las siguientes son de relleno
		listaCiudadesMundo.add(new Ciudad(0,0, null, null, null, getInformacionPara(NombresCiudades.TOKIO)));
		listaCiudadesMundo.add(new Ciudad(0,0, null, null, null, getInformacionPara(NombresCiudades.PARIS)));
		listaCiudadesMundo.add(new Ciudad(0,0, null, null, null, getInformacionPara(NombresCiudades.NEW_DELHI)));
		listaCiudadesMundo.add(new Ciudad(0,0, null, null, null, getInformacionPara(NombresCiudades.LIMA)));
		listaRecorrido.add(new Ciudad(0,0, null, null, null, getInformacionPara(NombresCiudades.RIO_DE_JANEIRO)));
		listaRecorrido.add(new Ciudad(0,0, null, null, null, getInformacionPara(NombresCiudades.NEW_YORK)));
		listaRecorrido.add(new Ciudad(0,0, null, null, null, getInformacionPara(NombresCiudades.OSLO)));
		listaRecorrido.add(new Ciudad(0,0, null, null, null, getInformacionPara(NombresCiudades.BUENOS_AIRES)));

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
	public void testCiudadDevuelveCuatroOpcionesPosibles(){
		Recorrido recorrido = new Recorrido(listaRecorrido, listaCiudadesMundo);
		Ciudad origen = listaRecorrido.get(0);
		assertEquals(4, recorrido.getCiudadesPosibles(origen).size());
	}

	@Test
	public void testCiudadesDelRecorridoTienenComoOpcionCiudadAnterior(){
		Recorrido recorrido = new Recorrido(listaRecorrido, listaCiudadesMundo);
		boolean ok = true;

		for (int i = 0; i < (listaRecorrido.size()-1); i++){
			Ciudad ciudadPrevia = listaRecorrido.get(i);
			Ciudad ciudadPosterior = listaRecorrido.get(i+1);
			ok &= (recorrido.getCiudadesPosibles(ciudadPosterior)).contains(ciudadPrevia);
		}
		assertTrue(ok);
	}
}


