package algo3.modelo.objeto;


public class ObjetoMuyValioso extends Objeto {

	private static final Integer CIUDADES = 7;
	
	public ObjetoMuyValioso(CaracteristicaObjeto caracteristica) {
		super(caracteristica);
	}
	
	@Override
	public Integer getCantidadDeCiudades() {
		return CIUDADES;
	}

}
