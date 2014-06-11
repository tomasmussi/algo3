package algo3.modelo.policia.grado;

public class GradoSargento extends Grado{

	private static final String GRADO = "Sargento";

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

}
