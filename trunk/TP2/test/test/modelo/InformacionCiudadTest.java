package test.modelo;

import org.junit.Test;

import algo3.modelo.mapa.mundi.Ciudad;
import algo3.modelo.mapa.mundi.InformacionCiudad;

public class InformacionCiudadTest {
	
	@Test
	public void testCrearInformacionPartiendoDeUnTexto(){
		InformacionCiudad informacion = new InformacionCiudad();
		Ciudad buenosAires = new Ciudad(informacion);
	}

}
