package test.modelo;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import algo3.modelo.mapa.mundi.InformacionCiudad;
import algo3.modelo.mapa.mundi.InformacionCiudadProvider;
import algo3.modelo.mapa.mundi.NombresCiudades;

public class InformacionCiudadProviderTest {

	private String[] nombresCiudades;

	@Before
	public void cargarPosiblesNombresCiudadesEnUnArrayDeString() {
		nombresCiudades = NombresCiudades.getTodosLosNombres();

	}

	@Test
	public void testGenerarInformacionCiudadUtilizandoElProvider() {
		InformacionCiudad informacion;
		for (String nombreCiudad : nombresCiudades) {
			informacion = InformacionCiudadProvider.getInstance().getInformacionPara(nombreCiudad);
			assertTrue(informacion != null);
		}

	}
}
