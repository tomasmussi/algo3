package algo3.modelo.tiempo;

import java.text.DecimalFormat;

public class Reloj {

	private static final DecimalFormat formato = new DecimalFormat("00");
	private static final int HORAS_A_DORMIR = 8;
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

	public void transcurrirRecursivo(int horas, boolean todaviaNoDurmio){

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
		if (esDeNoche() && !todaviaNoDurmio){
			transcurrirRecursivo(HORAS_A_DORMIR, true);
		}


	}

	public void transcurrir(int horas) {
		transcurrirRecursivo(horas, false);
	}

	private boolean esDeNoche(){
		return horaDia >= 23 || horaDia <= 7;
	
	}



	public boolean hayTiempoRestante(){
		return ! (numeroDia == 6 && horaDia == 17);
	}

}
