package algo3.modelo.excepcion;

public class CiudadNoEncontradaException extends RuntimeException {


	private static final long serialVersionUID = 1L;


	public CiudadNoEncontradaException(String mensaje) {
		super(mensaje);
	}

}
