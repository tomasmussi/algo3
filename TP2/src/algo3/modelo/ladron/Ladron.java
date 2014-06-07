package algo3.modelo.ladron;


public class Ladron {

	private CaracteristicaLadron caracteristicas;
	private Objeto objetoRobado;

	public Ladron(CaracteristicaLadron caracteristicas, Objeto objetoRobado){
		this.caracteristicas = caracteristicas;
		this.objetoRobado = objetoRobado;
	}

	/**
	 * Este metodo compara que un conjunto de caracteristicas lo describen a el/ella
	 * @param unaCaracteristica
	 * @return Si sus caracteristicas coinciden, univocamente es el ladron, entonces devuelve true, devuelve false en caso contrario
	 */
	public boolean coincideCon(CaracteristicaLadron unaCaracteristica) {
		//TODO En realidad esto deberia chequear los expedientes actuales.
		// Si hay mas de un expediente, devuelve false (hay que ser mas especifico)
		// Si no hay, tambien devolver false
		// Si hay un solo expediente, comparar por ciertas caracteristicas, es decir si falto especificar color de pelo pero todo lo demas matchea,
		// hay que devolver true
		return caracteristicas.equals(unaCaracteristica);
	}

	public boolean seRobo(Objeto objeto){
		return this.objetoRobado.compareTo(objeto) == 0;
	}

}
