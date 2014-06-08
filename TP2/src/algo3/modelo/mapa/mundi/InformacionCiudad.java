package algo3.modelo.mapa.mundi;

import java.util.List;

public class InformacionCiudad implements InformacionFinanciera, InformacionCultural, InformacionDeViaje {
	
	private String nombreCiudad;
	private String coloresBandera;
	private String moneda;
	private List<String> geografia;
	private List<String> lugaresDeReferencia;
	private List<String> industria;
	private List<String> animales;
	private List<String> gente;
	private List<String> idiomas;
	private List<String> arte;
	private List<String> religion;
	private List<String> lider;
	public String getNombreCiudad() {
		return nombreCiudad;
	}

	public void setNombreCiudad(String nombreCiudad) {
		this.nombreCiudad = nombreCiudad;
	}

	public String getGeografia() {
		return geografia.get(0);
	}

	public void setGeografia(List<String> geografia) {
		this.geografia = geografia;
	}

	public List<String> getLugaresDeReferencia() {
		return lugaresDeReferencia;
	}

	public void setLugaresDeReferencia(List<String> lugaresDeReferencia) {
		this.lugaresDeReferencia = lugaresDeReferencia;
	}

	@Override
	public String getIndustria() {
		//TODO: Hacer un random y que devuelva lo que sea de la lista que tenga, si es que
		// tiene
		return industria.get(0);
	}

	public void setIndustria(List<String> industria) {
		this.industria = industria;
	}

	public List<String> getAnimales() {
		return animales;
	}

	public void setAnimales(List<String> animales) {
		this.animales = animales;
	}

	public List<String> getGente() {
		return gente;
	}

	public void setGente(List<String> gente) {
		this.gente = gente;
	}

	public List<String> getIdiomas() {
		return idiomas;
	}

	public void setIdiomas(List<String> idiomas) {
		this.idiomas = idiomas;
	}

	public List<String> getArte() {
		return arte;
	}

	public void setArte(List<String> arte) {
		this.arte = arte;
	}

	public List<String> getReligion() {
		return religion;
	}

	public void setReligion(List<String> religion) {
		this.religion = religion;
	}

	public List<String> getLider() {
		return lider;
	}

	public void setLider(List<String> lider) {
		this.lider = lider;
	}

	public List<String> getMiscelaneo() {
		return miscelaneo;
	}

	public void setMiscelaneo(List<String> miscelaneo) {
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

	private List<String> miscelaneo;
	private String gobierno;

	/** Crea una Informacion completamente vacia */
	public InformacionCiudad(){
		super();
	}
	
	/** Crea una Informacion con los valores que le sean proporcionados */
	public InformacionCiudad(String nombreCiudad, String coloresBandera, String moneda, String gobierno){
		super();
		this.nombreCiudad = nombreCiudad;
		this.coloresBandera = coloresBandera;
		this.moneda = moneda;
		this.gobierno = gobierno;
	}
	
	@Override
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
