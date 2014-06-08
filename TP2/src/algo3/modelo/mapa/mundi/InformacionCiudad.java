package algo3.modelo.mapa.mundi;

public class InformacionCiudad {

	private String coloresBandera;
	private String moneda;
	private String gobierno;
	// TODO: Agregar el resto de la informacion.

	/** Crea una Informacion completamente vacia */
	public InformacionCiudad(){
		super();
	}
	
	/** Crea una Informacion con los valores que le sean proporcionados */
	public InformacionCiudad(String coloresBandera, String moneda, String gobierno){
		super();
		this.coloresBandera = coloresBandera;
		this.moneda = moneda;
		this.gobierno = gobierno;
	}
	
	public String getColoresBandera(){
		return coloresBandera;
	}
	
	public String getMoneda(){
		return moneda;
	}
	
	public String getGobierno(){
		return gobierno;
	}
	
}
