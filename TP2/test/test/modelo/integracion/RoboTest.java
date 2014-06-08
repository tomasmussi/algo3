package test.modelo.integracion;

import static org.junit.Assert.*;

import org.junit.Test;

import algo3.modelo.ladron.CaracteristicaLadron;
import algo3.modelo.ladron.Ladron;
import algo3.modelo.objeto.ObjetoComun;
import algo3.modelo.objeto.ObjetoMuyValioso;
import algo3.modelo.objeto.ObjetoValioso;
import algo3.modelo.viaje.Recorrido;


public class RoboTest {


	@Test
	public void testObjetoComunRobadoSonCuatroPaises(){
		ObjetoComun esteObjeto = new ObjetoComun("Anillo del Papa", "Roma");
		Ladron ladron = new Ladron(new CaracteristicaLadron("Carmen Sandiego", "Femenino", "Rojo", "Tenis", "Descapotable"), esteObjeto);
		assertTrue(Recorrido.longitudEnPaises() == 4);
	}
	
	@Test
	public void testObjetoValiosoRobadoSonCincoPaises(){
		ObjetoValioso esteObjeto = new ObjetoValioso("Anillo del Papa", "Roma");
		Ladron ladron = new Ladron(new CaracteristicaLadron("Carmen Sandiego", "Femenino", "Rojo", "Tenis", "Descapotable"), esteObjeto);
		assertTrue(Recorrido.longitudEnPaises() == 5);
	}
	
	@Test
	public void testObjetoMuyValiosoRobadoSonSietePaises(){
		ObjetoMuyValioso esteObjeto = new ObjetoMuyValioso("Anillo del Papa", "Roma");
		Ladron ladron = new Ladron(new CaracteristicaLadron("Carmen Sandiego", "Femenino", "Rojo", "Tenis", "Descapotable"), esteObjeto);
		assertTrue(Recorrido.longitudEnPaises() == 7);
	}
}
