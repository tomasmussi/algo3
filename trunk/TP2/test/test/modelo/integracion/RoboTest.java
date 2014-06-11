package test.modelo.integracion;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import algo3.modelo.mapa.mundi.InformacionCiudad;
import algo3.modelo.objeto.ObjetoComun;
import algo3.modelo.objeto.ObjetoMuyValioso;
import algo3.modelo.objeto.ObjetoValioso;
import algo3.modelo.viaje.Recorrido;


public class RoboTest {


	@Test
	public void testObjetoComunRobadoSonCuatroPaises(){
		ObjetoComun esteObjeto = new ObjetoComun("Anillo del Papa", "Roma");
		List<InformacionCiudad> lista = new ArrayList<InformacionCiudad>();
		InformacionCiudad info = new InformacionCiudad("Rio de Janeiro", "Verde y Amarillo", "Reales", "Presidente");
		InformacionCiudad info2 = new InformacionCiudad("New York", "Azul, Roja y Blanca", "Dolar", "Presidente");
		lista.add(info);
		lista.add(info2);
		lista.add(info);
		lista.add(info2);
		Recorrido recorrido = new Recorrido(lista, esteObjeto.getCantidadDeCiudades());

		assertTrue(recorrido.longitudEnPaises() == 4);
	}

	@Test
	public void testObjetoValiosoRobadoSonCincoPaises(){
		ObjetoValioso esteObjeto = new ObjetoValioso("Anillo del Papa", "Roma");
		List<InformacionCiudad> lista = new ArrayList<InformacionCiudad>();
		InformacionCiudad info = new InformacionCiudad("Rio de Janeiro", "Verde y Amarillo", "Reales", "Presidente");
		InformacionCiudad info2 = new InformacionCiudad("New York", "Azul, Roja y Blanca", "Dolar", "Presidente");
		lista.add(info);
		lista.add(info2);
		lista.add(info);
		lista.add(info2);
		lista.add(info);
		Recorrido recorrido = new Recorrido(lista, esteObjeto.getCantidadDeCiudades());
		assertTrue(recorrido.longitudEnPaises() == 5);
	}

	@Test
	public void testObjetoMuyValiosoRobadoSonSietePaises(){
		ObjetoMuyValioso esteObjeto = new ObjetoMuyValioso("Anillo del Papa", "Roma");
		List<InformacionCiudad> lista = new ArrayList<InformacionCiudad>();
		InformacionCiudad info = new InformacionCiudad("Rio de Janeiro", "Verde y Amarillo", "Reales", "Presidente");
		InformacionCiudad info2 = new InformacionCiudad("New York", "Azul, Roja y Blanca", "Dolar", "Presidente");
		lista.add(info);
		lista.add(info2);
		lista.add(info);
		lista.add(info2);
		lista.add(info);
		lista.add(info2);
		lista.add(info);
		Recorrido recorrido = new Recorrido(lista, esteObjeto.getCantidadDeCiudades());
		assertTrue(recorrido.longitudEnPaises() == 7);
	}
}
