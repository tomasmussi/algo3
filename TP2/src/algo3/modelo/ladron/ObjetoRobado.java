package algo3.modelo.ladron;

public abstract class ObjetoRobado {
	
	private String nombre;
	
	private String paisOrigen;

	public ObjetoRobado(String nombre, String paisOrigen) {
		this.nombre = nombre;
		this.paisOrigen = paisOrigen;
	}

	public abstract Integer getCantidadDePaises();
	
	public String getNombre() {
		return nombre;
	}
	
	public String getPaisOrigen(){
		return paisOrigen;
	}
	
}
