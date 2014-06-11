package algo3.modelo.policia.grado;

public class GradoInvestigador extends Grado {

	private static final String GRADO = "Investigador";

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

}
