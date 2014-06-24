package test.modelo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

import algo3.modelo.mapa.InformacionCiudad;

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

	//	@Test
	//	public void testEncuentraCiudadConStringCorrecta() {
	//		Mapa mapa = Mapa.getInstance();
	//		assertTrue((mapa.getCiudadDeNombre("Lima")).getNombre() == "Lima");
	//	}
	//	@Test
	//	public void testEncuentraCiudadConString() {
	//		Mapa mapa = Mapa.getInstance();
	//		assertTrue((mapa.getCiudadDeNombre("Lima")) != null);
	//	}
	//	@Test
	//	public void testEncuentraCiudadCorrecta() {
	//		Mapa mapa = Mapa.getInstance();
	//		Ciudad estaCiudad = new Ciudad(0,0,null,null,null, new InformacionCiudad("Tokyo","Blanca y roja", "Yen", "Presidente"));
	//	}

}


