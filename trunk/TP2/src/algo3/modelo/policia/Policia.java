package algo3.modelo.policia;

import java.util.ArrayList;
import java.util.List;

import algo3.modelo.caso.Caso;
import algo3.modelo.edificio.Edificio;
import algo3.modelo.ladron.CaracteristicaLadron;
import algo3.modelo.ladron.Ladron;
import algo3.modelo.mapa.mundi.Ciudad;
import algo3.modelo.policia.grado.Grado;
import algo3.modelo.policia.grado.GradoNovato;
import algo3.modelo.tiempo.Reloj;

public class Policia {

	public static final int HORAS_INICIALES = 154;
	private Reloj reloj;
	private int cantidadArrestos;
	private int cantidadDeVisitas;
	private List<Edificio> edificiosVisitados = new ArrayList<Edificio>();
	private Ciudad ciudadActual;
	private Grado grado;
	private Caso caso;

	public Policia() {

		cantidadArrestos = 0;
		cantidadDeVisitas = 0;
		grado = new GradoNovato();
		ciudadActual = null;

	}

	//TODO: VER SI ESTO SE USA EN ALGUN LADO
	public Policia(Ciudad ciudadInicial) {
		cantidadArrestos = 0;
		cantidadDeVisitas = 0;
		grado = new GradoNovato();
		this.ciudadActual = ciudadInicial;
	}

	public void setReloj(Reloj reloj){
		this.reloj = reloj;

	}

	public void asignarCaso(Caso esteCaso) {
		this.caso = esteCaso;
	}

	private void restarHoras(int horas) {
		reloj.transcurrir(horas);
	}

	public void acuchillado() {
		this.restarHoras(2);
	}

	//si es investigador o sargento;
	public void disparado() {
		this.restarHoras(4);
	}

	/**
	 * Viajar recibe la cantidad de kilometros a viajar por parte del policia
	 * y devuelve la cantidad de horas que le consume el viaje de acuerdo a la cantidad de kilometros
	 * que puede viajar
	 * 
	 * */
	public void viajar(int kilometros) {
		// Calculo de cuantas horas tengo que restar
		int horas = kilometros / grado.getKilometrosPorHora();
		horas += (kilometros % grado.getKilometrosPorHora()) != 0 ? 1 : 0;
		reloj.transcurrir(horas);
	}
	private void aumentarArrestos() {
		cantidadArrestos++;
		grado.evaluarGrado(this);
	}

	//METODO SOLO PARA PRUEBAS
	public boolean puedeArrestar() {
		return reloj.hayTiempoRestante();
	}

	public Grado getGrado() {
		return grado;
	}


	public int getCantidadArrestos() {
		return cantidadArrestos;
	}

	public Caso getCaso() {
		return caso;
	}

	public void setGrado(Grado gradoSiguiente) {
		this.grado = gradoSiguiente;
	}

	/**
	 * Aumenta el numero de visitas a edificios.
	 * Agrega el edificio a lista de edificios visitados.
	 * 
	 * @return Horas a restar.
	 */
	private int aumentarVisitas(Edificio edificio) {
		int horasARestar = 0;
		if (!edificiosVisitados.contains(edificio)) {
			edificiosVisitados.add(edificio);
			cantidadDeVisitas++;
			horasARestar = cantidadDeVisitas;
		}
		return horasARestar;
	}

	//Se llama con un evento
	public String visitarEdificioYObtenerPista(Edificio edificio) {
		int horasARestar = aumentarVisitas(edificio);
		reloj.transcurrir(horasARestar);
		// TODO: esto solo deberia pasar cuando es la ultima ciudad, no siempre.
		if(!edificio.estaLadron() ){
			//TODO: Esto se delega en los Grados. Por ejepmlo: grado.recibirAtaque();
			if(getCantidadArrestos()<10){
				acuchillado();
			}else{
				disparado();
			}
		} else {
			// TODO: lo arreste o no, ahora no se enteraria nunca, solo devolvería la pista.
			if(arrestar(getCaso().getLadron())){
				return "GANASTE";
			}
		}

		return grado.getPista(edificio);
	}



	//TODO: solo lo usan las pruebas
	public Ciudad getCiudadActual() {
		return ciudadActual;
	}

	//TODO: Se llama con un evento
	public void viajarA(Ciudad ciudad) {
		if (ciudadActual != null) {
			this.viajar(ciudadActual.getDistanciaCon(ciudad));
			caso.getRecorrido().actualizarNexoEntre(ciudadActual, ciudad);
		}
		this.ciudadActual = ciudad;
	}

	/**
	 * Accion que el policia realiza cuando quiere arrestar un ladron
	 * Devuelve true, si y solo si, el policia tiene una orden de arresto para el ladron
	 * y misma coincide con el ladron que esta siendo perseguido.
	 * Devuelve false en caso contrario
	 * 
	 * @param ladron
	 * @return true si atrapo al ladron, false de lo contrario
	 */
	public boolean arrestar(Ladron ladron) {
		if ((caso.getOrdenDeArresto() == null) || !ladron.coincideCon(caso.getOrdenDeArresto().getCaracteristicaLadron())) {
			return false;
		}
		aumentarArrestos();
		return true;
	}

	public boolean emitirOrdenDeArresto(CaracteristicaLadron caracteristica) {
		reloj.transcurrir(3);
		if ((caracteristica != null) && reloj.hayTiempoRestante()) {
			// TODO:algo de si la pudo emitir o no
			caso.generarOrdenDeArresto(caracteristica);
			return true;

		}
		return false;
	}


}
