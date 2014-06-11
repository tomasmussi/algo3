package algo3.modelo.estacionPolicia;

import java.util.ArrayList;
import java.util.List;

import algo3.modelo.ladron.CaracteristicaLadron;

public class EstacionDePolicia {
	
	private List<CaracteristicaLadron> expedientes;
	
	public EstacionDePolicia(){
		
		List<CaracteristicaLadron> expedientes = new ArrayList<CaracteristicaLadron>();
		
		CaracteristicaLadron expediente1= new CaracteristicaLadron("Nick Brunch","Masculino","Mountain Climbing","Negro","Anillo","Motocicleta");
		CaracteristicaLadron expediente2= new CaracteristicaLadron("Len Bulk", "Masculino", "Mountain Climbing", "Rojo", "Tatuaje", "Motocicleta");
		CaracteristicaLadron expediente3= new CaracteristicaLadron("Ihor Ihorovitch", "Masculino", "Croquet", "Rubio", "Tatuaje", "Limousina");
	
		expedientes.add (expediente1);
		expedientes.add (expediente2);
		expedientes.add (expediente3);
		this.expedientes = expedientes;	
	}

	public List<CaracteristicaLadron> buscarExpediente(CaracteristicaLadron caracteristicaLadron ){
		
		List<CaracteristicaLadron> coincidenciaLadrones = new ArrayList<CaracteristicaLadron>();
		for(CaracteristicaLadron expediente : expedientes){
			if(expediente.equals(caracteristicaLadron)){
				
				coincidenciaLadrones.add(expediente);
			}
		
		}
		return coincidenciaLadrones;
		
	}
}
