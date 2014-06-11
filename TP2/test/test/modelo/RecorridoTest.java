package test.modelo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import algo3.modelo.mapa.mundi.Ciudad;
import algo3.modelo.mapa.mundi.InformacionCiudad;
import algo3.modelo.objeto.ObjetoComun;
import algo3.modelo.objeto.Robable;
import algo3.modelo.viaje.Recorrido;

public class RecorridoTest {
	
	private List<InformacionCiudad> listaDeCiudades;

	@Before
	public void crearListaDeInformacion() {
		listaDeCiudades = new ArrayList<InformacionCiudad>();
		listaDeCiudades.add(new InformacionCiudad("Rio de Janeiro", "Verde y amarilla", "Real", "Presidente"));
		listaDeCiudades.add(new InformacionCiudad("Nueva York", "Azul, roja y blanca", "Dolar", "Presidente"));
		listaDeCiudades.add(new InformacionCiudad("Oslo", "Roja y azul", "Corona", "Rey"));
		listaDeCiudades.add(new InformacionCiudad("Buenos Aires","Blanca y celeste", "Peso", "Presidente"));		
	}
	
	
	@Test
	public void testCrearRecorridoPartiendoDeObjetoRobado() {
		Robable objeto = new ObjetoComun("Anillo del Papa", "Roma");
		Recorrido recorrido = new Recorrido(listaDeCiudades, objeto);
		List<Ciudad> ciudades = recorrido.obtenerCiudadesRecorrido();
		boolean verificar = true;
		for (int i = 0; i < ciudades.size(); i++){
			Ciudad ciudad = ciudades.get(i);
			String nombreCiudad = listaDeCiudades.get(i).getNombreCiudad();
			verificar = verificar && (nombreCiudad.equals(ciudad.getNombre()));
		}
		assertTrue(verificar);
	}

}
