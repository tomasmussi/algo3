package algo3.modelo.objeto;

public class ObjetoComun extends Objeto {

	private static final Integer CIUDADES = 4;
	
	public ObjetoComun(String nombre, String paisOrigen){
		super(nombre, paisOrigen);
	}
	
	@Override
	public Integer getCantidadDeCiudades() {
		return CIUDADES;
	}

}
