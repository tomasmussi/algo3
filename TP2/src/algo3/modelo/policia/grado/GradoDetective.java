package algo3.modelo.policia.grado;

import algo3.modelo.edificio.Edificio;
import algo3.modelo.objeto.CaracteristicaObjeto;
import algo3.modelo.objeto.ObjetoValioso;
import algo3.modelo.objeto.Robable;
import algo3.modelo.pista.DificultadPista;
import algo3.modelo.pista.PistaMedia;

public class GradoDetective extends Grado {

	private static final String GRADO = "Detective";
	private static final int HORAS_A_RESTAR = 2;
	private DificultadPista dificultadDePista;

	public GradoDetective() {
		super();
		dificultadDePista = new PistaMedia();
	}

	@Override
	public String getPlaca() {
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

	@Override
	public String getPista(Edificio edificio) {
		return dificultadDePista.darPista(edificio);
	}

	@Override
	public Robable getObjetoRobado(CaracteristicaObjeto unaCaracteristica) {
		return new ObjetoValioso(unaCaracteristica);
	}

	@Override
	public int horasRestarPorAtaque() {
		return HORAS_A_RESTAR;
	}

	@Override
	protected String getMensajeAtaque() {
		return "Acuchillado";
	}


}
