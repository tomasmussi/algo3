package algo3.modelo.objeto;


public class ObjetoMuyValioso extends Objeto {

	private static final Integer CIUDADES = 7;
	
	public ObjetoMuyValioso(String nombre, String paisOrigen) {
		super(nombre, paisOrigen);
	}
	
	@Override
	public Integer getCantidadDeCiudades() {
		return CIUDADES;
	}

}
