package algo3.modelo.policia;

import java.util.ArrayList;
import java.util.List;

import algo3.modelo.caso.Caso;
import algo3.modelo.ladron.CaracteristicaLadron;
import algo3.modelo.ladron.Ladron;
import algo3.modelo.mapa.mundi.Ciudad;
import algo3.modelo.mapa.mundi.Edificio;
import algo3.modelo.policia.grado.Grado;
import algo3.modelo.policia.grado.GradoNovato;

public class Policia {

	public static final int HORAS_INICIALES = 154;
	private int horasRestantes;
	private int cantidadArrestos;
	private int cantidadDeVisitas;
	private List<Edificio> edificiosVisitados = new ArrayList<Edificio>();
	private Ciudad ciudadActual;
	private OrdenDeArresto ordenDeArresto;
	private Grado grado;
	private Caso caso;

	public Policia() {
		horasRestantes = HORAS_INICIALES;
		cantidadArrestos = 0;
		cantidadDeVisitas = 0;
		grado = new GradoNovato();
		caso = new Caso();
	}

	public Policia(Ciudad ciudadInicial) {
		horasRestantes = HORAS_INICIALES;
		cantidadArrestos = 0;
		cantidadDeVisitas = 0;
		grado = new GradoNovato();
		this.ciudadActual = ciudadInicial;
	}

	public int getHorasRestantes() {
		return horasRestantes;
	}

	public void restarHoras(int horas) {
		this.horasRestantes -= horas;
		if (horasRestantes < 0) {
			this.horasRestantes = 0; // Si llega a 0 debería pasar algo igual...
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
		horas += (kilometros % grado.getKilometrosPorHora()) != 0 ? 1 : 0;
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

	/**
	 * Aumenta el numero de visitas a edificios.
	 * Agrega el edificio a lista de edificios visitados.
	 * @return Horas a restar.
	 */
	private int aumentarVisitas(Edificio edificio) {
		int horasARestar = 0;
		if (!edificiosVisitados.contains(edificio)){
			edificiosVisitados.add(edificio);
			cantidadDeVisitas++;
			horasARestar = cantidadDeVisitas;
		}
		return horasARestar;
	}

	public String visitarEdificioYObtenerPista(Edificio edificio) {
		int horasARestar = aumentarVisitas(edificio);
		restarHoras(horasARestar);
		return edificio.darPista();
	}

	public Ciudad getCiudadActual() {
		return ciudadActual;
	}

	public void viajarA(Ciudad ciudad) {
		this.viajar(ciudadActual.getDistanciaCon(ciudad));
		this.ciudadActual = ciudad;
	}

	/**
	 * Accion que el policia realiza cuando quiere arrestar un ladron
	 * Devuelve true, si y solo si, el policia tiene una orden de arresto para el ladron
	 * y misma coincide con el ladron que esta siendo perseguido.
	 * Devuelve false en caso contrario
	 * @param ladron
	 * @return true si atrapo al ladron, false de lo contrario
	 */
	public boolean arrestar(Ladron ladron) {
		if (ordenDeArresto == null || !ladron.coincideCon(ordenDeArresto.getCaracteristicaLadron())) {
			return false;
		}
		return true;
	}

	public void emitirOrdenDeArresto(CaracteristicaLadron caracteristica) {
		if (caracteristica != null) {

			boolean seGenero = this.caso.generarOrdenDeArresto(caracteristica);
			if (seGenero) this.restarHoras(3);

		}
	}

	public void setGrado(Grado gradoSiguiente) {
		this.grado = gradoSiguiente;
	}

}
