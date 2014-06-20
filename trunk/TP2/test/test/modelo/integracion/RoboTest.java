package test.modelo.integracion;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import algo3.modelo.ladron.CaracteristicaLadron;
import algo3.modelo.ladron.Ladron;
import algo3.modelo.mapa.mundi.InformacionCiudad;
import algo3.modelo.mapa.mundi.MapaMundi;
import algo3.modelo.objeto.CaracteristicaObjeto;
import algo3.modelo.objeto.ObjetoComun;
import algo3.modelo.objeto.ObjetoMuyValioso;
import algo3.modelo.objeto.ObjetoValioso;
import algo3.modelo.objeto.Robable;


public class RoboTest {
	
	@Before
	public void cargarMapa() {
		List<InformacionCiudad> listaCiudadesRecorrido;
		listaCiudadesRecorrido = new ArrayList<InformacionCiudad>();
		listaCiudadesRecorrido.add(new InformacionCiudad("Rio de Janeiro", "Verde y amarilla", "Real", "Presidente"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Nueva York", "Azul, roja y blanca", "Dolar", "Presidente"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Oslo", "Roja y azul", "Corona", "Rey"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Buenos Aires","Blanca y celeste", "Peso", "Presidente"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Tokyo","Blanca y roja", "Yen", "Presidente"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Paris","Blanca, roja y azul", "Franco", "Presidente"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Nueva Delhi","Roja, blanca y verde", "Rupia", "Primer Ministro"));
		listaCiudadesRecorrido.add(new InformacionCiudad("Lima","Roja y blanca", "Sol", "Presidente"));
		MapaMundi.getInstance().cargarListadoCiudades(listaCiudadesRecorrido);
	}

	@Test
	public void testObjetoComunRobadoSonCuatroPaises(){
		Ladron esteLadron = new Ladron (new CaracteristicaLadron("Carmen Sandiego", "Femenino", "Mountain Climbing", "Rojo", "Tatuaje", "Descapotable"));
		Robable esteObjeto = new ObjetoComun(new CaracteristicaObjeto("Algo", "Buenos Aires"));
		esteLadron.robar(esteObjeto);
		assertTrue(esteLadron.getEscapatoria().size() == 4);
	}

	@Test
	public void testObjetoValiosoRobadoSonCincoPaises(){
		Ladron esteLadron = new Ladron (new CaracteristicaLadron("Carmen Sandiego", "Femenino", "Mountain Climbing", "Rojo", "Tatuaje", "Descapotable"));
		Robable esteObjeto = new ObjetoValioso(new CaracteristicaObjeto("Algo", "Buenos Aires"));
		esteLadron.robar(esteObjeto);
		assertTrue(esteLadron.getEscapatoria().size() == 5);
	}

	@Test
	public void testObjetoMuyValiosoRobadoSonSietePaises(){
		Ladron esteLadron = new Ladron (new CaracteristicaLadron("Carmen Sandiego", "Femenino", "Mountain Climbing", "Rojo", "Tatuaje", "Descapotable"));
		Robable esteObjeto = new ObjetoMuyValioso(new CaracteristicaObjeto("Algo", "Buenos Aires"));
		esteLadron.robar(esteObjeto);
		assertTrue(esteLadron.getEscapatoria().size() == 7);
	}

}
