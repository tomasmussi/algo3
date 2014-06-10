package algo3.modelo.mapa.mundi;

public class InformacionCiudad implements InformacionFinanciera, InformacionCultural, InformacionDeViaje {

	private String nombreCiudad;
	private String coloresBandera;
	private String moneda;
	private String[] geografia;
	private String[] lugaresDeReferencia;
	private String[] industria;
	private String[] animales;
	private String[] gente;
	private String[] idiomas;
	private String[] arte;
	private String[] religion;
	private String gobierno;
	private String[] miscelaneo;

	/** Crea una Informacion completamente vacia */
	public InformacionCiudad() {
		super();
	}

	/** Crea una Informacion con los valores que le sean proporcionados */
	public InformacionCiudad(String nombreCiudad, String coloresBandera, String moneda, String gobierno) {
		super();
		this.nombreCiudad = nombreCiudad;
		this.coloresBandera = coloresBandera;
		this.moneda = moneda;
		this.gobierno = gobierno;
	}

	public String getNombreCiudad() {
		return nombreCiudad;
	}

	public void setNombreCiudad(String nombreCiudad) {
		this.nombreCiudad = nombreCiudad;
	}

	@Override
	public String getGeografia() {
		return geografia[0];
	}

	public void setGeografia(String[] geografia) {
		this.geografia = geografia;
	}

	public String[] getLugaresDeReferencia() {
		return lugaresDeReferencia;
	}

	public void setLugaresDeReferencia(String[] lugaresDeReferencia) {
		this.lugaresDeReferencia = lugaresDeReferencia;
	}

	@Override
	public String getIndustria() {
		// TODO: Hacer un random y que devuelva lo que sea de la lista que tenga, si es que
		// tiene
		return industria[0];
	}

	public void setIndustria(String[] industria) {
		this.industria = industria;
	}

	public String[] getAnimales() {
		return animales;
	}

	public void setAnimales(String[] animales) {
		this.animales = animales;
	}

	public String[] getGente() {
		return gente;
	}

	public void setGente(String[] gente) {
		this.gente = gente;
	}

	public String[] getIdiomas() {
		return idiomas;
	}

	public void setIdiomas(String[] idiomas) {
		this.idiomas = idiomas;
	}

	public String[] getArte() {
		return arte;
	}

	public void setArte(String[] arte) {
		this.arte = arte;
	}

	public String[] getReligion() {
		return religion;
	}

	public void setReligion(String[] religion) {
		this.religion = religion;
	}

	public String[] getMiscelaneo() {
		return miscelaneo;
	}

	public void setMiscelaneo(String[] miscelaneo) {
		this.miscelaneo = miscelaneo;
	}

	public void setColoresBandera(String coloresBandera) {
		this.coloresBandera = coloresBandera;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public void setGobierno(String gobierno) {
		this.gobierno = gobierno;
	}

	@Override
	public String getColoresBandera() {
		return coloresBandera;
	}

	@Override
	public String getMoneda() {
		return moneda;
	}

	@Override
	public String getGobierno() {
		return gobierno;
	}

}
