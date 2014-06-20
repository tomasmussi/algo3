package algo3.modelo.policia.grado;

import algo3.modelo.edificio.Edificio;
import algo3.modelo.objeto.CaracteristicaObjeto;
import algo3.modelo.objeto.Robable;
import algo3.modelo.policia.Policia;

public abstract class Grado {

	public abstract String getPlaca();

	public abstract int getKilometrosPorHora();

	protected abstract int getTopeArrestos();

	protected abstract Grado getGradoSiguiente();

	public abstract String getPista(Edificio edificio);

	public void evaluarGrado(Policia policia) {
		if (policia.getCantidadArrestos() == getTopeArrestos()) {
			policia.setGrado(getGradoSiguiente());
		}
	}

	public abstract Robable getObjetoRobado(CaracteristicaObjeto unaCaracteristica);

}
