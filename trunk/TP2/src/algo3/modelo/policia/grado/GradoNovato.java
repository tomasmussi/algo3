package algo3.modelo.policia.grado;

import algo3.modelo.edificio.Edificio;
import algo3.modelo.objeto.CaracteristicaObjeto;
import algo3.modelo.objeto.ObjetoComun;
import algo3.modelo.objeto.Robable;
import algo3.modelo.pista.DificultadPista;
import algo3.modelo.pista.PistaFacil;

public class GradoNovato extends Grado {

	private static final String GRADO = "Novato";
	private DificultadPista dificultadDePista;

	public GradoNovato() {
		this.dificultadDePista = new PistaFacil();
	}

	@Override
	public String getGrado() {
		return GRADO;
	}

	@Override
	public int getKilometrosPorHora() {
		return 900;
	}

	@Override
	protected int getTopeArrestos() {
		return 5;
	}

	@Override
	protected Grado getGradoSiguiente() {
		return new GradoDetective();
	}

	@Override
	public String getPista(Edificio edificio) {
		return dificultadDePista.darPista(edificio.getElemento());
	}

	@Override
	public Robable getObjetoRobado(CaracteristicaObjeto unaCaracteristica) {
		return new ObjetoComun(unaCaracteristica);
	}

}
