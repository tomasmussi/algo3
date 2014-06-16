package algo3.modelo.entidad;

public class EntidadCultural implements Entidad {

	private String informacion;
	private String nombreEntidad;
	
	public EntidadCultural(String nombre, String informacion) {
		this.nombreEntidad = nombre;
		this.informacion = informacion;
	}
	
	@Override
	public String getNombre() {
		return nombreEntidad;
	}

	@Override
	public String getInformacion() {
		return informacion;
	}

}
