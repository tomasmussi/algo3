package algo3.modelo.objeto;

public class CaracteristicaObjeto {

	private String nombre;
	private String ciudadOrigen;

	public CaracteristicaObjeto(String nombre, String ciudadOrigen){
		this.nombre = nombre;
		this.ciudadOrigen = ciudadOrigen;
	}
	
	public String getNombre() {
		return nombre;
	}

	public String getCiudadOrigen(){
		return ciudadOrigen;
	}
}
