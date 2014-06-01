package test.modelo;

import static org.junit.Assert.assertTrue;
import junit.framework.Assert;

import org.junit.Test;

import algo3.modelo.ladron.Ladron;
import algo3.modelo.ladron.ObjetoComun;
import algo3.modelo.ladron.ObjetoMuyValioso;
import algo3.modelo.ladron.ObjetoRobado;
import algo3.modelo.ladron.ObjetoValioso;

public class LadronTest {

	@Test
	public void testCrearLadron(){
		Ladron ladron = new Ladron();
		assertTrue(ladron != null);
	}
	
	@Test
	public void testCrearObjetoRobadoComun(){
		ObjetoRobado objeto = new ObjetoComun("Pope Ring", "Rome");
		boolean isOK = objeto.getNombre().equals("Pope Ring")
						&& objeto.getPaisOrigen().equals("Rome")
						&& objeto.getCantidadDePaises().equals(4);
		assertTrue(isOK);
	}
	
	@Test
	public void testCrearObjetoRobadoValioso(){
		ObjetoRobado objeto = new ObjetoValioso("Pope Ring", "Rome");
		boolean isOK = objeto.getNombre().equals("Pope Ring")
						&& objeto.getPaisOrigen().equals("Rome")
						&& objeto.getCantidadDePaises().equals(5);
		assertTrue(isOK);
	}
	
	@Test
	public void testCrearObjetoRobadoMuyValioso(){
		ObjetoRobado objeto = new ObjetoMuyValioso("Pope Ring", "Rome");
		boolean isOK = objeto.getNombre().equals("Pope Ring")
						&& objeto.getPaisOrigen().equals("Rome")
						&& objeto.getCantidadDePaises().equals(7);
		assertTrue(isOK);
	}
	
	@Test
	public void testCrearLadronConInformacion(){
		Ladron ladron = new Ladron();
		ObjetoRobado objeto = new ObjetoMuyValioso("Pope Ring", "Rome");
		
		ladron.setSexo("Masculino");
		ladron.setColorCabello("Negro");
		ladron.setHobby("Croquet");
		ladron.setCaracteristica("Joyas");
		ladron.setVehiculo("Convertible");
		ladron.setObjetoRobado(objeto);
		
		boolean isOK = ladron.getObjetoRobado().getNombre().equals("Pope Ring")
						&& ladron.getVehiculo().equals("Convertible")
						&& ladron.getCaracteristica().equals("Joyas")
						&& ladron.getHobby().equals("Croquet")
						&& ladron.getColorCabello().equals("Negro")
						&& ladron.getSexo().equals("Masculino");
		assertTrue(isOK);
	}
	
	@Test
	public void test() {
		Ladron ladron = new Ladron();
		ObjetoRobado objeto = new ObjetoMuyValioso("Pope Ring", "Rome");
		
		ladron.setSexo("Masculino");
		ladron.setColorCabello("Negro");
		ladron.setHobby("Croquet");
		ladron.setCaracteristica("Joyas");
		ladron.setVehiculo("Convertible");
		ladron.setObjetoRobado(objeto);
		
		boolean isNotOK = ladron.getObjetoRobado().getNombre().equals("Painting from the Kremlin")
						&& ladron.getVehiculo().equals("Convertible")
						&& ladron.getCaracteristica().equals("Joyas")
						&& ladron.getHobby().equals("Croquet")
						&& ladron.getColorCabello().equals("Negro")
						&& ladron.getSexo().equals("Masculino");
		
		Assert.assertFalse(isNotOK);
	}
	
}
