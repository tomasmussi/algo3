package algo3.modelo.ladron;

public class ObjetoValioso extends ObjetoRobado {

	private static final Integer PAISES = 5;
	
	public ObjetoValioso(String nombre, String paisOrigen) {
		super(nombre, paisOrigen);
	}
	
	@Override
	public Integer getCantidadDePaises() {
		return PAISES;
	}

}
