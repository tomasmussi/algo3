package test.modelo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import algo3.modelo.excepcion.CiudadNoEncontradaException;
import algo3.modelo.mapa.Ciudad;
import algo3.modelo.mapa.CiudadFactory;
import algo3.modelo.mapa.InformacionCiudad;
import algo3.modelo.mapa.InformacionCiudadProvider;
import algo3.modelo.mapa.InformacionMapa;
import algo3.modelo.mapa.NombresCiudades;
import algo3.modelo.viaje.Mapa;

public class MapaTest {

	private List<InformacionCiudad> listaInformacionCiudadesMundo;
	private List<InformacionCiudad> listaRecorrido;

	private InformacionCiudad getInformacionPara(String ciudad){
		return InformacionCiudadProvider.getInstance().getInformacionPara(ciudad);
	}


	@Before
	public void crearListaDeInformacion() {
		// Recorrido de escape de ladron
		listaRecorrido = new ArrayList<InformacionCiudad>();
		listaRecorrido.add(getInformacionPara(NombresCiudades.RIO_DE_JANEIRO));
		listaRecorrido.add(getInformacionPara(NombresCiudades.NEW_YORK));
		listaRecorrido.add(getInformacionPara(NombresCiudades.OSLO));
		listaRecorrido.add(getInformacionPara(NombresCiudades.BUENOS_AIRES));

		listaInformacionCiudadesMundo = new ArrayList<InformacionCiudad>();
		listaInformacionCiudadesMundo.add(getInformacionPara(NombresCiudades.RIO_DE_JANEIRO));
		listaInformacionCiudadesMundo.add(getInformacionPara(NombresCiudades.NEW_YORK));
		listaInformacionCiudadesMundo.add(getInformacionPara(NombresCiudades.OSLO));
		listaInformacionCiudadesMundo.add(getInformacionPara(NombresCiudades.BUENOS_AIRES));
		listaInformacionCiudadesMundo.add(getInformacionPara(NombresCiudades.TOKIO));
		listaInformacionCiudadesMundo.add(getInformacionPara(NombresCiudades.PARIS));
		listaInformacionCiudadesMundo.add(getInformacionPara(NombresCiudades.NEW_DELHI));
		listaInformacionCiudadesMundo.add(getInformacionPara(NombresCiudades.LIMA));
	}

	@Test
	public void testCiudadesDelRecorridoTienenComoOpcionCiudadSiguiente(){
		// Cargo y genero un recorrido a partir de esta lista
		Mapa mapa = new Mapa(CiudadFactory.crearRecorridoDeCiudades(NombresCiudades.RIO_DE_JANEIRO, 4));
		List<Ciudad> ciudadesPosibles = mapa.getCiudadesPosibles(NombresCiudades.RIO_DE_JANEIRO);
		assertEquals(4, ciudadesPosibles.size());
		Iterator<Ciudad> iter = ciudadesPosibles.iterator();
		while (iter.hasNext()){
			Ciudad next = iter.next();
		}
	}

	@Test(expected=CiudadNoEncontradaException.class)
	public void testMapaElevaExcepcionAlPedirCiudadInexistente() throws CiudadNoEncontradaException {
		Mapa mapa = new Mapa();
		mapa.getCiudadesPosibles("Ciudad Inexistente");
	}
}


