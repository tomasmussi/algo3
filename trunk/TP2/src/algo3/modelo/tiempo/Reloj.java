package algo3.modelo.tiempo;

import java.text.DecimalFormat;

public class Reloj {

	private static final DecimalFormat formato = new DecimalFormat("00");
	private String[] dias = {"Lunes", "Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"};
	private int numeroDia;
	private int horaDia;

	public Reloj() {
		numeroDia = 0;
		horaDia = 7;
	}

	public String tiempoActual() {
		return dias[numeroDia] + " " + formato.format(horaDia) + ":00 horas";
	}

	public void transcurrir(int horas) {
		int horaTotal = horaDia + horas;
		int cantidadDiasPasados = horaTotal / 24;
		int horaActual = horaTotal % 24;
		if (numeroDia + cantidadDiasPasados < 7){
			// Si todavia hay tiempo disponible, sumo
			numeroDia += cantidadDiasPasados;
			if (numeroDia == 6 && horaActual > 17){
				horaDia = 17;
			} else {
				horaDia = horaActual;
			}
		} else {
			// Si me pase, dejo todo en Domingo 17:00 horas
			numeroDia = 6;
			horaDia = 17;
		}
	}

	public boolean hayTiempoRestante(){
		return ! (numeroDia == 6 && horaDia == 17);
	}

}
