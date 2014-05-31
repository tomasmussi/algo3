package algo3.modelo.caracteres.policia;

public class GradoDetective extends Grado {

	private static final String GRADO = "Detective";

	@Override
	public String getGrado() {
		return GRADO;
	}

	@Override
	public int getKilometrosPorHora() {
		return 1100;
	}

	@Override
	protected int getTopeArrestos() {
		return 10;
	}

	@Override
	protected Grado getGradoSiguiente() {
		return new GradoInvestigador();
	}

}
