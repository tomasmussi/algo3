package test.modelo.integracion;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import algo3.modelo.ladron.CaracteristicaLadron;
import algo3.modelo.ladron.Ladron;
import algo3.modelo.mapa.mundi.Ciudad;
import algo3.modelo.mapa.mundi.InformacionCiudad;
import algo3.modelo.mapa.mundi.MapaMundi;
import algo3.modelo.objeto.ObjetoComun;
import algo3.modelo.objeto.ObjetoMuyValioso;
import algo3.modelo.objeto.ObjetoValioso;
import algo3.modelo.objeto.Robable;


public class RoboTest {

	private List<InformacionCiudad> listaCiudadesRecorrido;
	private MapaMundi mapa;

	@Before
	public void crearListaDeInformacion() {
		mapa = new MapaMundi();
		listaCiudadesRecorrido = new ArrayList<InformacionCiudad>();
		listaCiudadesRecorrido.add(new InformacionCiudad("Rio de Janeiro", "Verde y amarilla", "Real", "Presidente"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Nueva York", "Azul, roja y blanca", "Dolar", "Presidente"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Oslo", "Roja y azul", "Corona", "Rey"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Buenos Aires","Blanca y celeste", "Peso", "Presidente"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Tokyo","Blanca y roja", "Yen", "Presidente"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Paris","Blanca, roja y azul", "Franco", "Presidente"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Nueva Delhi","Roja, blanca y verde", "Rupia", "Primer Ministro"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Lima","Roja y blanca", "Sol", "Presidente"));
		mapa.cargarListadoCiudades(listaCiudadesRecorrido);
	}

	@Test
	public void testObjetoComunRobadoSonCuatroPaises(){
		ObjetoComun esteObjeto = new ObjetoComun("Algo", "Buenos Aires");
		Ladron esteLadron = new Ladron (new CaracteristicaLadron("Carmen Sandiego", "Femenino", "Mountain Climbing", "Rojo", "Tatuaje", "Descapotable"), esteObjeto);
		Ciudad estaCiudad = mapa.getCiudadDeNombre(esteObjeto.getCiudadOrigen());
		List<Ciudad> ciudades = mapa.getListadoCiudades();
		esteLadron.elegirEscapatoria(ciudades, estaCiudad);
		assertTrue(esteLadron.getLongitudRecorridoEscapatoria() == 4);
	}

	@Test
	public void testObjetoValiosoRobadoSonCincoPaises(){
		ObjetoValioso esteObjeto = new ObjetoValioso("Anillo del Papa", "Roma");
		Ladron esteLadron = new Ladron (new CaracteristicaLadron("Carmen Sandiego", "Femenino", "Mountain Climbing", "Rojo", "Tatuaje", "Descapotable"), esteObjeto);
		Ciudad estaCiudad = mapa.getCiudadDeNombre(esteObjeto.getCiudadOrigen());
		List<Ciudad> ciudades = mapa.getListadoCiudades();
		esteLadron.elegirEscapatoria(ciudades, estaCiudad);
		assertTrue(esteLadron.getLongitudRecorridoEscapatoria() == 5);
	}

	@Test
	public void testObjetoMuyValiosoRobadoSonSietePaises(){
		Robable esteObjeto = new ObjetoMuyValioso("Anillo del Papa", "Roma");
		Ladron esteLadron = new Ladron (new CaracteristicaLadron("Carmen Sandiego", "Femenino", "Mountain Climbing", "Rojo", "Tatuaje", "Descapotable"), esteObjeto);
		Ciudad estaCiudad = mapa.getCiudadDeNombre(esteObjeto.getCiudadOrigen());
		List<Ciudad> ciudades = mapa.getListadoCiudades();
		esteLadron.elegirEscapatoria(ciudades, estaCiudad);
		assertTrue(esteLadron.getLongitudRecorridoEscapatoria() == 7);
	}

	/*	@Test
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
		Robable esteObjeto = new ObjetoMuyValioso("Anillo del Papa", "Roma");
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
	}*/
}
