package test.modelo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import algo3.modelo.ladron.CaracteristicaLadron;
import algo3.modelo.ladron.Ladron;
import algo3.modelo.objeto.CaracteristicaObjeto;
import algo3.modelo.objeto.ObjetoComun;
import algo3.modelo.objeto.Robable;

public class LadronTest {

	private Ladron ladron;
	private CaracteristicaLadron caracteristicaCorrecta;
	private CaracteristicaLadron caracteristicaIncorrecta;


	@Before
	public void crearCaracteristicaDefault(){
		caracteristicaCorrecta = new CaracteristicaLadron("Carmen Sandiego", "Femenino", "Mountain Climbing", "Rojo", "Tatuaje", "Descapotable");
		caracteristicaIncorrecta = new CaracteristicaLadron("Carmen Sandiego", "Masculino", "Croquet", "Rojo", "Anillo", "Descapotable");
		//Importante crear el Ladron con la referencia de caracteristicaCorrecta instanciada, sino rompe por NullPointerException (referencia nula)
		ladron = new Ladron(caracteristicaCorrecta);
	}

	@After
	public void eliminarReferencia(){
		ladron = null;
	}


	@Test
	public void testCrearLadronConCaracteristicas(){
		assertTrue(ladron.coincideCon(caracteristicaCorrecta));
	}

	@Test
	public void testCrearLadronConCaracteristicasComparaConCaracteristicasIncorrectas() {
		assertFalse(ladron.coincideCon(caracteristicaIncorrecta));
	}

	@Test
	public void testCrearObjetoRobadoComun(){
		//TODO (TOMAS) Coordinar con eli porque no tengo bien idea de a quien le tengo que pedir ciudades para armar recorrido
		Robable objetoRobado = new ObjetoComun(new CaracteristicaObjeto("Anillo del Papa", "Roma"));
		ladron.robar(objetoRobado);
		assertTrue(true);
	}

	//TODO Esto es test puramente de Objeto, no de Ladron, pasar a ObjetoTest
	//	@Test
	//	public void testCrearObjetoRobadoValioso(){
	//		Objeto objeto = new ObjetoValioso("Pope Ring", "Rome");
	//		boolean isOK = objeto.getNombre().equals("Pope Ring")
	//				&& objeto.getCiudadOrigen().equals("Rome")
	//				&& objeto.getCantidadDeCiudades().equals(5);
	//		assertTrue(isOK);
	//	}
	//
	//	@Test
	//	public void testCrearObjetoRobadoMuyValioso(){
	//		Objeto objeto = new ObjetoMuyValioso("Pope Ring", "Rome");
	//		boolean isOK = objeto.getNombre().equals("Pope Ring")
	//				&& objeto.getCiudadOrigen().equals("Rome")
	//				&& objeto.getCantidadDeCiudades().equals(7);
	//		assertTrue(isOK);
	//	}
}
