package test.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import algo3.modelo.mapa.InformacionCiudad;
import algo3.modelo.mapa.InformacionCiudadProvider;
import algo3.modelo.mapa.InformacionCultural;
import algo3.modelo.mapa.InformacionDeViaje;
import algo3.modelo.mapa.InformacionFinanciera;
import algo3.modelo.mapa.NombresCiudades;

public class InformacionCiudadTest {

	@Test
	public void testCrearInformacionDeViajeSeleccionaPropiedadNoNula() {
		// Cargada desde el properties, SanMarino no tiene moneda
		InformacionDeViaje informacion = InformacionCiudadProvider.getInstance().getInformacionPara(NombresCiudades.SAN_MARINO);
		assertEquals("Azul y blanco", informacion.getInformacionViaje());
		assertEquals("", ((InformacionCiudad) informacion).getInformacionFinanciera());
	}

	@Test
	public void testCrearInformacionFinancieraSeleccionaPropiedadNoNula() {
		// Cargada desde el properties, NewYork tiene Dolares
		InformacionFinanciera informacion = InformacionCiudadProvider.getInstance().getInformacionPara(NombresCiudades.NEW_YORK);
		assertEquals("Dolares", informacion.getInformacionFinanciera());
	}

	@Test
	public void testCrearInformacionGeograficaSeleccionaPropiedadNoNula() {
		// Cargada desde el properties, Tokio tiene Monte Fuji
		InformacionCultural informacion = InformacionCiudadProvider.getInstance().getInformacionPara(NombresCiudades.TOKIO);
		List<String> posibleInformacion = new ArrayList<String>();
		posibleInformacion.add("Religion|Shinto");
		posibleInformacion.add("Industria|Automoviles");
		posibleInformacion.add("LugaresDeReferencia|Monte Fuji");
		posibleInformacion.add("Gente|Shoguns");
		assertTrue(posibleInformacion.contains(informacion.getInformacionCultural()));
	}

}
