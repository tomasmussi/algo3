package test.modelo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import algo3.modelo.mapa.mundi.InformacionCiudad;
import algo3.modelo.mapa.mundi.InformacionCiudadProvider;
import algo3.modelo.mapa.mundi.InformacionCultural;
import algo3.modelo.mapa.mundi.InformacionDeViaje;
import algo3.modelo.mapa.mundi.InformacionFinanciera;
import algo3.modelo.mapa.mundi.NombresCiudades;

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
		assertEquals("Monte Fuji", informacion.getInformacionGeografica());
	}

}