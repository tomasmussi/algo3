package test.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import algo3.modelo.mapa.mundi.Ciudad;
import algo3.modelo.mapa.mundi.CiudadFactory;
import algo3.modelo.mapa.mundi.Edificio;
import algo3.modelo.mapa.mundi.InformacionCiudad;
import algo3.modelo.mapa.mundi.NombresCiudades;

public class CiudadTest {

	private InformacionCiudad infoCiudadOrigen;
	private InformacionCiudad infoCiudadDestino;

	@Before
	public void crearInformacionDefault() {
		infoCiudadOrigen = new InformacionCiudad("Nueva York", "Rojo, blanco y azul", "Dolar", "Presidente");
		infoCiudadDestino = new InformacionCiudad("Rio de Janeiro", "Verde y amarilla", "Real", "Presidente");
	}

	@Test
	public void testCrearCiudadDesdeInformacionCiudad() {
		Ciudad ciudad = new Ciudad(infoCiudadOrigen);
		assertEquals(infoCiudadOrigen.getNombreCiudad(), ciudad.getNombre());
	}

	@Test
	public void testCrearDosCiudadesRelacionaUnaConOtra() {
		Ciudad ciudadOrigen = new Ciudad(infoCiudadOrigen);
		ciudadOrigen.agregarInformacionProximaCiudad(infoCiudadDestino);
		String infoBancaria = ciudadOrigen.getTodosLosEdificios()[0].darPista();
		assertEquals(infoBancaria, "Segun mis fuentes estuvo averiguando sobre el valor de Real");
		String infoBandera = ciudadOrigen.getTodosLosEdificios()[2].darPista();
		assertEquals(infoBandera, "Me dicen mis fuentes que se fue en un avion con una bandera Verde y amarilla en sus alas.");
	}

	@Test
	public void testCrearCiudadesRelacionadasConArchivoProperties() {
		// Creo una ciudad (Bangkok) que me de informacion sobre la siguiente ciudad (Buenos Aires)
		Ciudad ciudad = CiudadFactory.crearCiudadConEdificiosSiguienteCiudad(NombresCiudades.BANGKOK, NombresCiudades.BUENOS_AIRES);
		List<String> pistasPosibles = new ArrayList<String>();
		pistasPosibles.add("Un sospechoso estuvo aquí averiguando sobre el tipo de gobierno de un President");
		pistasPosibles.add("Me dicen mis fuentes que se fue en un avion con una bandera Sun Flag en sus alas.");
		pistasPosibles.add("Segun mis fuentes estuvo averiguando sobre el valor de Australs");
		Edificio[] edificios = ciudad.getTodosLosEdificios();
		for (Edificio edificio : edificios) {
			assertTrue(pistasPosibles.contains(edificio.darPista()));
		}
	}

}
