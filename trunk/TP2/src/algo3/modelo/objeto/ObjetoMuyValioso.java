package algo3.modelo.objeto;


public class ObjetoMuyValioso extends Objeto {

	private static final Integer CIUDADES = 7;
	
	public ObjetoMuyValioso(String nombre, String ciudadOrigen) {
		super(nombre, ciudadOrigen);
	}
	
	@Override
	public Integer getCantidadDeCiudades() {
		return CIUDADES;
	}

}
