package algo3.modelo.policia.grado;

import algo3.modelo.edificio.Edificio;
import algo3.modelo.objeto.CaracteristicaObjeto;
import algo3.modelo.objeto.ObjetoValioso;
import algo3.modelo.objeto.Robable;
import algo3.modelo.pista.DificultadPista;
import algo3.modelo.pista.PistaMedia;

public class GradoInvestigador extends Grado {

	private static final String GRADO = "Investigador";
	private DificultadPista dificultadDePista;

	public GradoInvestigador() {
		super();
		dificultadDePista = new PistaMedia();
	}

	@Override
	public String getGrado() {
		return GRADO;
	}

	@Override
	public int getKilometrosPorHora() {
		return 1300;
	}

	@Override
	protected int getTopeArrestos() {
		return 20;
	}

	@Override
	protected Grado getGradoSiguiente() {
		return new GradoSargento();
	}

	@Override
	public String getPista(Edificio edificio) {
		return dificultadDePista.darPista(edificio.getElemento());
	}

	@Override
	public Robable getObjetoRobado(CaracteristicaObjeto unaCaracteristica) {
		return new ObjetoValioso(unaCaracteristica.getNombre(),unaCaracteristica.getCiudadOrigen());
	}

}
