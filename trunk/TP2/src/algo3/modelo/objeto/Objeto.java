package algo3.modelo.objeto;

public abstract class Objeto implements Robable {

	private String nombre;

	private String ciudadOrigen;

	public Objeto(CaracteristicaObjeto caracteristica){
		this.nombre = caracteristica.getNombre();
		this.ciudadOrigen = caracteristica.getCiudadOrigen();
	}

	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public String getCiudadOrigen(){
		return ciudadOrigen;
	}

	@Override
	public int compareTo(Robable o) {
		return nombre.compareTo(o.getNombre());
	}

}
