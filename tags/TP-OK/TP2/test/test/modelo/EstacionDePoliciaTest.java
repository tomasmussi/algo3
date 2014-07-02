package test.modelo;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import algo3.modelo.estacionPolicia.EstacionDePolicia;
import algo3.modelo.ladron.CaracteristicaLadron;

public class EstacionDePoliciaTest {
	

	@Test
	public void testEncontroUnExpedienteDeEstacionDePolicia(){

		List<CaracteristicaLadron> expedientes= EstacionDePolicia.getInstance().buscarExpediente(new CaracteristicaLadron("Nick Brunch","Masculino","Mountain Climbing","Negro","Anillo","Motocicleta"));
		assertEquals(expedientes.size(), 1);
	}

	@Test
	public void testNoHayCoincidenciaConExpedientesDeEstacionPolicia(){
		//no hay sexo femenino en la base
		List<CaracteristicaLadron> expedientes= EstacionDePolicia.getInstance().buscarExpediente(new CaracteristicaLadron("Nick Brunch","Femenino","Mountain Climbing","Negro","Anillo","Motocicleta"));

		assertEquals(expedientes.size(), 0);
	}

	@Test
	public void testVariasCoincidenciaConExpedientesDeEstacionPolicia(){

		List<CaracteristicaLadron> expedientes= EstacionDePolicia.getInstance().buscarExpediente(new CaracteristicaLadron( null, "Masculino", "Mountain Climbing", null, null, null));

		assertEquals(expedientes.size(), 2);
	}	
		
	@Test
	public void testCargarSinCaracteristicasDevuelveTodosLosLadrones(){
		
		List<CaracteristicaLadron> expedientes= EstacionDePolicia.getInstance().buscarExpediente(new CaracteristicaLadron( null, null, null, null, null, null));
		assertEquals(expedientes.size(), 10);

	}

}
