package algo3.modelo.ladron;

public class ObjetoComun extends ObjetoRobado {

	private static final Integer PAISES = 4;
	
	public ObjetoComun(String nombre, String paisOrigen){
		super(nombre, paisOrigen);
	}
	
	@Override
	public Integer getCantidadDePaises() {
		return PAISES;
	}

}
