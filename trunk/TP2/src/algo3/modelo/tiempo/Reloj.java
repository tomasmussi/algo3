package algo3.modelo.tiempo;

import java.text.DecimalFormat;
import java.util.Observable;

import algo3.vista.Vista;

public class Reloj extends Observable {

	private static final DecimalFormat formato = new DecimalFormat("00");
	private static final int HORAS_A_DORMIR = 8;
	private String[] dias = { "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo" };
	private int numeroDia;
	private int horaDia;

	/**
	 * Crea el reloj del juego para llevar el tiempo transcurrido desde que comienza el caso hasta que termina
	 * */
	public Reloj() {
		resetear();
	}

	public void setVista(Vista vista) {
		addObserver(vista);
	}

	/**
	 * Imprime de una forma elegante el tiempo actual del caso.
	 * */
	public String tiempoActual() {
		return dias[numeroDia] + " " + formato.format(horaDia) + ":00 horas";
	}

	/**
	 * Transcurre una cantidad de horas que corresponda
	 * Es recursivo dado que al transcurrir tiempo, puedo llegar al limite en el cual el jugador tiene que dormir,
	 * entonces hay que llamar devuelta al transcurrir horas, pero no tiene que llamarse mas de una vez, por eso
	 * @param todaviaNoDurmio para identificar si tiene que dormir o no si esta en el rango horario correspondiente
	 * */
	public void transcurrirRecursivo(int horas, boolean todaviaNoDurmio) {

		int horaTotal = horaDia + horas;
		int cantidadDiasPasados = horaTotal / 24;
		int horaActual = horaTotal % 24;
		if ((numeroDia + cantidadDiasPasados) < 7) {
			// Si todavia hay tiempo disponible, sumo
			numeroDia += cantidadDiasPasados;
			if ((numeroDia == 6) && (horaActual > 17)) {
				horaDia = 17;
			} else {
				horaDia = horaActual;
			}
		} else {
			// Si me pase, dejo todo en Domingo 17:00 horas
			numeroDia = 6;
			horaDia = 17;
		}
		if (esDeNoche() && !todaviaNoDurmio) {
			transcurrirRecursivo(HORAS_A_DORMIR, true);
		}
	}

	public void transcurrir(int horas) {
		transcurrirRecursivo(horas, false);
		notificar();
	}

	private boolean esDeNoche() {
		return (horaDia >= 23) || (horaDia <= 7);
	}

	public boolean hayTiempoRestante() {
		return !((numeroDia == 6) && (horaDia == 17));
	}

	@Override
	public String toString() {
		return tiempoActual();
	}

	/**
	 * Deja al reloj en el estado de recien iniciado el caso
	 * */
	public void resetear() {
		numeroDia = 0;
		horaDia = 7;
	}

	public void notificar() {
		setChanged();
		notifyObservers();
	}

}
