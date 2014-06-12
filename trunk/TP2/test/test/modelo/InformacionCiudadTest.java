package test.modelo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import algo3.modelo.mapa.mundi.InformacionCiudad;
import algo3.modelo.mapa.mundi.InformacionCiudadProvider;
import algo3.modelo.mapa.mundi.InformacionCultural;
import algo3.modelo.mapa.mundi.InformacionDeViaje;
import algo3.modelo.mapa.mundi.InformacionFinanciera;

public class InformacionCiudadTest {

	@Test
	public void testCrearInformacionDeViajeSeleccionaPropiedadNoNula(){
		InformacionCiudadProvider.getInstance().cargarInformacion();
		// Cargada desde el properties, SanMarino no tiene moneda
		InformacionDeViaje informacion = InformacionCiudadProvider.getInstance().getInformacionPara("SanMarino");
		assertEquals("Blue and White",informacion.getInformacionViaje());
		InformacionCiudad info = (InformacionCiudad)informacion;
		info.setColoresBandera("");
		info.setMoneda("Lira");
		assertEquals("Lira",informacion.getInformacionViaje());
	}

	@Test
	public void testCrearInformacionFinancieraSeleccionaPropiedadNoNula(){
		InformacionCiudadProvider.getInstance().cargarInformacion();
		// Cargada desde el properties, SanMarino no tiene moneda
		InformacionFinanciera informacion = InformacionCiudadProvider.getInstance().getInformacionPara("NewYork");
		assertEquals("Dollars",informacion.getInformacionFinanciera());
	}

	@Test
	public void testCrearInformacionGeograficaSeleccionaPropiedadNoNula(){
		InformacionCiudadProvider.getInstance().cargarInformacion();
		// Cargada desde el properties, SanMarino no tiene moneda
		InformacionCultural informacion = InformacionCiudadProvider.getInstance().getInformacionPara("Tokyo");
		assertEquals("Mount Huascaran",informacion.getInformacionGeografica());
	}

}
