package test.modelo.integracion;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import algo3.modelo.caso.Caso;
import algo3.modelo.excepcion.CiudadNoEncontradaException;
import algo3.modelo.ladron.CaracteristicaLadron;
import algo3.modelo.objeto.CaracteristicaObjeto;
import algo3.modelo.policia.Policia;
import algo3.modelo.tiempo.Reloj;

public class JuegoTest {


	private List<CaracteristicaLadron> ladrones;
	private List<CaracteristicaObjeto> objetos;

	private CaracteristicaLadron caracteristicaNickBrunch;
	private CaracteristicaLadron caracteristicaUnivocaNickBrunch;

	private CaracteristicaObjeto caracteristicaObjeto;

	private Policia policia;
	private Reloj reloj;
	private Caso caso;

	@Before
	public void inicializar(){
		policia = new Policia();
		reloj = new Reloj();
		policia.setReloj(reloj);

		caracteristicaNickBrunch = new CaracteristicaLadron("Nick Brunch", "Masculino", "Mountain Climbing", "Negro", "Anillo", "Motocicleta");
		caracteristicaUnivocaNickBrunch = new CaracteristicaLadron(null, null, null, "Negro", null, "Motocicleta");
		caracteristicaObjeto = new CaracteristicaObjeto("Anillo del Papa", "Roma");

		ladrones = new ArrayList<CaracteristicaLadron>();
		objetos = new ArrayList<CaracteristicaObjeto>();

		ladrones.add(caracteristicaNickBrunch);
		objetos.add(caracteristicaObjeto);
	}

	@Test
	public void testJugarNivelEnteroPoliciaAtrapaLadron() throws CiudadNoEncontradaException{
		// Tengo un policia
		caso = new Caso(ladrones, objetos, policia.getGrado());
		policia.asignarCaso(caso);


		policia.viajarA(caso.getLadron().getCiudadActual());

		policia.viajarA(caso.getLadron().getCiudadActual());

		policia.viajarA(caso.getLadron().getCiudadActual());
		policia.emitirOrdenDeArresto(caracteristicaUnivocaNickBrunch);

		assertTrue(policia.arrestar(caso.getLadron()));
	}

}
