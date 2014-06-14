package algo3.modelo.policia.grado;

import algo3.modelo.edificio.Edificio;
import algo3.modelo.pista.DificultadPista;
import algo3.modelo.pista.PistaDificil;

public class GradoSargento extends Grado {

	private static final String GRADO = "Sargento";
	private DificultadPista dificultadDePista;

	public GradoSargento() {
		super();
		dificultadDePista = new PistaDificil();
	}

	@Override
	public String getGrado() {
		return GRADO;
	}

	@Override
	public int getKilometrosPorHora() {
		return 1500;
	}

	@Override
	protected int getTopeArrestos() {
		// No hay tope de arrestos
		return 0;
	}

	@Override
	protected Grado getGradoSiguiente() {
		// No hay mas grados posibles
		return this;
	}

	@Override
	public String getPista(Edificio edificio) {
		return dificultadDePista.darPista(edificio.getElemento());
	}
}