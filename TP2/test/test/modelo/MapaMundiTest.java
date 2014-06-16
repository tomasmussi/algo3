package test.modelo;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import algo3.modelo.mapa.mundi.Ciudad;
import algo3.modelo.mapa.mundi.InformacionCiudad;
import algo3.modelo.mapa.mundi.MapaMundi;

public class MapaMundiTest {

	private List<InformacionCiudad> listaDeInfo;

	@Before
	public void crearListaDeInformacion() {
		listaDeInfo = new ArrayList<InformacionCiudad>();

		listaDeInfo.add(new InformacionCiudad("Rio de Janeiro", "Verde y amarilla", "Real", "Presidente"));
		listaDeInfo.add(new InformacionCiudad("Nueva York", "Azul, roja y blanca", "Dolar", "Presidente"));
		listaDeInfo.add(new InformacionCiudad("Oslo", "Roja y azul", "Corona", "Rey"));
		listaDeInfo.add(new InformacionCiudad("Buenos Aires","Blanca y celeste", "Peso", "Presidente"));
		// Para los tests, uso siempre la creacion de 4 ciudades como el recorrido posta, las siguientes son de relleno
		//listaDeInfo.add(new InformacionCiudad("Tokyo","Blanca y roja", "Yen", "Presidente"));
		listaDeInfo.add(new InformacionCiudad("Paris","Blanca, roja y azul", "Franco", "Presidente"));
		listaDeInfo.add(new InformacionCiudad("Nueva Delhi","Roja, blanca y verde", "Rupia", "Primer Ministro"));
		listaDeInfo.add(new InformacionCiudad("Lima","Roja y blanca", "Sol", "Presidente"));
	}

	@Test
	public void testEncuentraCiudadConStringCorrecta() {
		MapaMundi mapa = new MapaMundi();
		mapa.cargarListadoCiudades(listaDeInfo);
		assertTrue((mapa.getCiudadDeNombre("Lima")).getNombre() == "Lima");
	}
	@Test
	public void testEncuentraCiudadConString() {
		MapaMundi mapa = new MapaMundi();
		mapa.cargarListadoCiudades(listaDeInfo);
		assertTrue((mapa.getCiudadDeNombre("Lima")) != null);
	}
	@Test
	public void testEncuentraCiudadCorrecta() {
		MapaMundi mapa = new MapaMundi();
		mapa.cargarListadoCiudades(listaDeInfo);
		Ciudad estaCiudad = new Ciudad(new InformacionCiudad("Tokyo","Blanca y roja", "Yen", "Presidente"));
		mapa.agregarCiudad(estaCiudad);
		assertTrue(mapa.getCiudadDeNombre(estaCiudad.getNombre()) == estaCiudad);
	}

}


