package algo3.modelo.mapa.mundi;


public class Ciudad {

	private int coordenadaX;
	private int coordenadaY;
	private Edificio edificio1;
	private Edificio edificio2;
	private Edificio edificio3;
	private String nombre;
	private InformacionCiudad informacion;

	public Ciudad(int coordenadaX, int coordenadaY, Edificio edificio1, Edificio edificio2, Edificio edificio3, InformacionCiudad informacion) {
		this.nombre = informacion.getNombreCiudad();
		this.coordenadaX = coordenadaX;
		this.coordenadaY = coordenadaY;
		this.edificio1 = edificio1;
		this.edificio2 = edificio2;
		this.edificio3 = edificio3;
		this.informacion = informacion;
	}

	public Ciudad(InformacionCiudad informacion) {
		this.nombre = informacion.getNombreCiudad();
		this.informacion = informacion;
	}

	public int calcularDistanciaCon(Ciudad estaCiudad) {
		return (int) (Math.pow((Math.pow(this.coordenadaX - estaCiudad.coordenadaX, 2) + Math.pow(this.coordenadaY - estaCiudad.coordenadaY, 2)), 0.5));
	}

	public String visitarEdificio(Edificio edificio) {
		return edificio.darPista();
	}

	public String getNombre() {
		return nombre;
	}

	public String getColoresBandera() {
		return informacion.getColoresBandera();
	}

	public String getMoneda() {
		return informacion.getMoneda();
	}

	public String getGobierno() {
		return informacion.getMoneda();
	}

	public Edificio[] getTodosLosEdificios() {
		Edificio[] edificios = { edificio1, edificio2, edificio3 };
		return edificios;
	}

	public boolean esMismaCiudadQue(Ciudad ciudad) {
		// Solucion temporal, comparo nombres nada mas. TODO: mejorar.
		if (ciudad == null) {
			return false;
		}
		return nombre.equals(ciudad.getNombre());
	}

	@Override
	public String toString() {
		return "Ciudad: " + this.nombre;
	}

	public void agregarInformacionProximaCiudad(InformacionCiudad infoCiudad) {
		this.edificio1 = EdificioFactory.crearEdificioFinanciero(infoCiudad);
		this.edificio2 = EdificioFactory.crearEdificioCultural(infoCiudad);
		this.edificio3 = EdificioFactory.crearEdificioDeViaje(infoCiudad);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ciudad other = (Ciudad) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

}
