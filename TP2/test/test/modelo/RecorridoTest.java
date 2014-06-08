package test.modelo;

import java.util.ArrayList;

import org.junit.Test;

import algo3.modelo.mapa.mundi.Ciudad;
import algo3.modelo.viaje.Recorrido;

public class RecorridoTest {

	@Test
	public void testCrearRecorridoPartiendoDeObjetoRobado() {
		Recorrido recorrido = new Recorrido(new ArrayList<Ciudad>());
	}

}
