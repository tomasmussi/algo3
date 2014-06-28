package test.modelo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import algo3.modelo.excepcion.CiudadNoEncontradaException;
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
	public void testCrearObjetoRobadoComun()  throws CiudadNoEncontradaException{
		Robable objetoRobado = new ObjetoComun(new CaracteristicaObjeto("Anillo del Papa", "Roma"));
		ladron.robar(objetoRobado);
		assertTrue(true);
	}

}
