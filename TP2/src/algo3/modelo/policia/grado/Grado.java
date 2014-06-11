package algo3.modelo.policia.grado;

import algo3.modelo.policia.Policia;


public abstract class Grado {

	public abstract String getGrado();

	public abstract int getKilometrosPorHora();

	protected abstract int getTopeArrestos();

	protected abstract Grado getGradoSiguiente();

	public void evaluarGrado(Policia policia){
		// TODO: No se si poner los atributos como package o private y como accederlos desde aca
		if (policia.getCantidadArrestos() == getTopeArrestos()){
			policia.setGrado(getGradoSiguiente());
		}
	}


}
