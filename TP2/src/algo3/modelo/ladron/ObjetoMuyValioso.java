package algo3.modelo.ladron;


public class ObjetoMuyValioso extends ObjetoRobado {

	private static final Integer PAISES = 7;
	
	public ObjetoMuyValioso(String nombre, String paisOrigen) {
		super(nombre, paisOrigen);
	}
	
	@Override
	public Integer getCantidadDePaises() {
		return PAISES;
	}

}
