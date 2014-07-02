package algo3.modelo.objeto;

public class ObjetoValioso extends Objeto {

	public ObjetoValioso(CaracteristicaObjeto caracteristica) {
		super(caracteristica);
	}

	private static final Integer CIUDADES = 5;
	
	@Override
	public Integer getCantidadDeCiudades() {
		return CIUDADES;
	}

}
