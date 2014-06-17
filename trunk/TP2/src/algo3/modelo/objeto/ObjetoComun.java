package algo3.modelo.objeto;

public class ObjetoComun extends Objeto {

	private static final Integer CIUDADES = 4;
	
	public ObjetoComun(CaracteristicaObjeto caracteristica) {
		super(caracteristica);
	}
	
	@Override
	public Integer getCantidadDeCiudades() {
		return CIUDADES;
	}

}
