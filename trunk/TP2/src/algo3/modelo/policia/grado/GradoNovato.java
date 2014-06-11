package algo3.modelo.policia.grado;


public class GradoNovato extends Grado {

	private static final String GRADO = "Novato";

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

}
