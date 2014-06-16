package algo3.modelo.objeto;

public class ObjetoValioso extends Objeto {

	private static final Integer CIUDADES = 5;
	
	public ObjetoValioso(String nombre, String paisOrigen) {
		super(nombre, paisOrigen);
	}
	
	@Override
	public Integer getCantidadDeCiudades() {
		return CIUDADES;
	}

}
