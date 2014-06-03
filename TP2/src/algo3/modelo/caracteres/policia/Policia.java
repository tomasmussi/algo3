package algo3.modelo.caracteres.policia;


public class Policia {

	public static final int HORAS_INICIALES = 154;
	private int horasRestantes;
	private int cantidadArrestos;
	Grado grado;
	
	//TODO: faltaria agregar una orden de arresto con las caracteristicas del ladron a atrapar. 
	public Policia(){
		horasRestantes = HORAS_INICIALES;
		cantidadArrestos = 0;
		grado = new GradoNovato();
	}

	public int getHorasRestantes() {
		return horasRestantes;
	}

	public void restarHoras(int horas) {
		if (horas == 0){
			return;
		}
		this.horasRestantes -= horas;
		if (horasRestantes < 0){
			this.horasRestantes = 0;
		}
	}

	public void dormir() {
		this.restarHoras(8);
	}

	public void acuchillado() {
		this.restarHoras(2);
	}

	public void disparado() {
		this.restarHoras(4);
	}

	public void viajar(int kilometros) {
		// Calculo de cuantas horas tengo que restar
		int horas = kilometros / grado.getKilometrosPorHora();
		horas += kilometros % grado.getKilometrosPorHora() != 0 ? 1 : 0;
		this.restarHoras(horas);
	}

	public boolean puedeArrestar() {
		return horasRestantes != 0;
	}

	public String getGrado() {
		return grado.getGrado();
	}

	public void aumentarArrestos() {
		cantidadArrestos++;
		grado.evaluarGrado(this);
	}

	public int getCantidadArrestos() {
		return cantidadArrestos;
	}



}
