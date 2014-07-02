package algo3.modelo.policia.grado;

import algo3.modelo.edificio.Edificio;
import algo3.modelo.objeto.CaracteristicaObjeto;
import algo3.modelo.objeto.Robable;
import algo3.modelo.policia.Policia;
import algo3.modelo.tiempo.Reloj;

public abstract class Grado {

	public abstract String getPlaca();

	public abstract int getKilometrosPorHora();

	protected abstract int getTopeArrestos();

	protected abstract Grado getGradoSiguiente();

	public abstract String getPista(Edificio edificio);

	public abstract Robable getObjetoRobado(CaracteristicaObjeto unaCaracteristica);

	public abstract int horasRestarPorAtaque();

	protected abstract String getMensajeAtaque();

	public void evaluarGrado(Policia policia) {
		if (policia.getCantidadArrestos() == getTopeArrestos()) {
			policia.setGrado(getGradoSiguiente());
		}
	}

	public String restarHorasPorAtaque(Reloj reloj) {
		reloj.transcurrir(horasRestarPorAtaque());
		return getMensajeAtaque();
	}


}
