package test.modelo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import algo3.modelo.mapa.mundi.Ciudad;
import algo3.modelo.mapa.mundi.InformacionCiudad;

public class CiudadTest {
	
	private InformacionCiudad infoCiudadOrigen;
	private InformacionCiudad infoCiudadDestino;
	
	@Before
	public void crearInformacionDefault(){
		infoCiudadOrigen = new InformacionCiudad("Nueva York", "Rojo, blanco y azul","Dolar" ,"Presidente");
		infoCiudadDestino = new InformacionCiudad("Rio de Janeiro", "Verde y amarilla","Real" ,"Presidente");		
	}
	
	@Test
	public void testCrearCiudadDesdeInformacionCiudad(){
		Ciudad ciudad = new Ciudad(infoCiudadOrigen);
		assertEquals(infoCiudadOrigen.getNombreCiudad(), ciudad.getNombre());
	}
	
	@Test
	public void testCrearDosCiudadesRelacionaUnaConOtra(){
		Ciudad ciudadOrigen = new Ciudad(infoCiudadOrigen);
		ciudadOrigen.agregarInformacionProximaCiudad(infoCiudadDestino);
		String infoBancaria = ciudadOrigen.getTodosLosEdificios()[0].darPista();
		assertEquals(infoBancaria, "Segun mis fuentes estuvo averiguando sobre el valor de Real");
		String infoBandera = ciudadOrigen.getTodosLosEdificios()[2].darPista();
		assertEquals(infoBandera, "Me dicen mis fuentes que se fue en un avion con una bandera Verde y amarilla en sus alas.");
		
	}

}
