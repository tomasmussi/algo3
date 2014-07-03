package algo3.modelo.policia.grado;

import algo3.modelo.edificio.Edificio;
import algo3.modelo.objeto.CaracteristicaObjeto;
import algo3.modelo.objeto.Robable;
import algo3.modelo.policia.Policia;
import algo3.modelo.tiempo.Reloj;

/**
 * Representa el nivel del jugador y en base al mismo, que clase de pistas, cantidad de kilometros por hora que puede viajar,
 * que tipo de ataque recibe por parte del ladron y cuando avanza al siguiente grado
 * */
public abstract class Grado {

	public abstract String getPlaca();

	public abstract int getKilometrosPorHora();

	protected abstract int getTopeArrestos();

	protected abstract Grado getGradoSiguiente();

	public abstract String getPista(Edificio edificio);

	public abstract Robable getObjetoRobado(CaracteristicaObjeto unaCaracteristica);

	public abstract int horasRestarPorAtaque();

	protected abstract String getMensajeAtaque();

	/**
	 * Evalua el grado del policia en base a los arrestos actuales
	 * Avanza de grado si llego al tope de arrestos de su categoria
	 * */
	public final void evaluarGrado(Policia policia) {
		if (policia.getCantidadArrestos() == getTopeArrestos()) {
			policia.setGrado(getGradoSiguiente());
		}
	}

	public String restarHorasPorAtaque(Reloj reloj) {
		reloj.transcurrir(horasRestarPorAtaque());
		return getMensajeAtaque();
	}


}
