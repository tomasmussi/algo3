package test.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import algo3.modelo.edificio.Edificio;
import algo3.modelo.mapa.mundi.Ciudad;
import algo3.modelo.mapa.mundi.CiudadFactory;
import algo3.modelo.mapa.mundi.InformacionCiudad;
import algo3.modelo.mapa.mundi.InformacionCiudadProvider;
import algo3.modelo.mapa.mundi.NombresCiudades;
import algo3.modelo.policia.Policia;
import algo3.modelo.tiempo.Reloj;

public class CiudadTest {

	private InformacionCiudad infoCiudadOrigen;
	private InformacionCiudad infoCiudadDestino;

	@Before
	public void crearInformacionDefault() {
		infoCiudadOrigen = InformacionCiudadProvider.getInstance().getInformacionPara(NombresCiudades.NEW_YORK);
		infoCiudadDestino = InformacionCiudadProvider.getInstance().getInformacionPara(NombresCiudades.RIO_DE_JANEIRO);
	}

	@Test
	public void testCrearCiudadDesdeInformacionCiudad() {
		Ciudad ciudad = new Ciudad(infoCiudadOrigen);
		assertEquals(infoCiudadOrigen.getNombreCiudad(), ciudad.getNombre());
	}

	@Test
	public void testCrearDosCiudadesRelacionaUnaConOtra() {
		Policia policia = new Policia(new Reloj());
		Ciudad ciudadOrigen = new Ciudad(infoCiudadOrigen);
		ciudadOrigen.agregarInformacionProximaCiudad(infoCiudadDestino);
		String infoBancaria = policia.visitarEdificioYObtenerPista(ciudadOrigen.getTodosLosEdificios()[0]);
		assertEquals(infoBancaria, "Solo se que cambio todo su dinero a Cruzeiros.");
		String infoBandera = policia.visitarEdificioYObtenerPista(ciudadOrigen.getTodosLosEdificios()[2]);
		assertEquals(infoBandera, "Me dicen mis fuentes que se fue en un avion con Verde, Azul y Amarillo en sus alas.");
	}

	@Test
	public void testCrearCiudadesRelacionadasConArchivoProperties() {
		Policia policia = new Policia(new Reloj());
		// Creo una ciudad (Bangkok) que me de informacion sobre la siguiente ciudad (Buenos Aires)
		Ciudad ciudad = CiudadFactory.crearCiudadConEdificiosSiguienteCiudad(NombresCiudades.BANGKOK, NombresCiudades.BUENOS_AIRES);
		List<String> pistasPosibles = new ArrayList<String>();
		pistasPosibles.add("Un sospechoso estuvo aqui averiguando sobre el tipo de gobierno de un Presidente.");
		pistasPosibles.add("Me dicen mis fuentes que se fue en un avion con Bandera de sol en sus alas.");
		pistasPosibles.add("Solo se que cambio todo su dinero a Australes.");
		Edificio[] edificios = ciudad.getTodosLosEdificios();
		for (Edificio edificio : edificios) {
			assertTrue(pistasPosibles.contains(policia.visitarEdificioYObtenerPista(edificio)));
		}
	}

}
