package algo3.modelo.mapa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InformacionCiudad implements InformacionFinanciera, InformacionCultural, InformacionDeViaje {

	private static final String SEPARATOR = "|";
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
	private String latitud;
	private String longitud;

	/** Crea una Informacion con los valores que le sean proporcionados */
	public InformacionCiudad(String nombreCiudad, String coloresBandera, String moneda, String gobierno) {
		super();
		this.nombreCiudad = nombreCiudad;
		this.coloresBandera = coloresBandera;
		this.moneda = moneda;
		this.gobierno = gobierno;
	}


	@Override
	public String getInformacionViaje() {
		return coloresBandera;
	}


	@Override
	public String getInformacionFinanciera() {
		return moneda;
	}

	@Override
	public String getInformacionCultural() {
		List<String> informacion = new ArrayList<String>();
		agregarInformacion(informacion, "Geografia", geografia);
		agregarInformacion(informacion, "LugaresDeReferencia", lugaresDeReferencia);
		agregarInformacion(informacion, "Industria", industria);
		agregarInformacion(informacion, "Animales", animales);
		agregarInformacion(informacion, "Gente", gente);
		agregarInformacion(informacion, "Idioma", idiomas);
		agregarInformacion(informacion, "Arte", arte);
		agregarInformacion(informacion, "Religion", religion);
		agregarInformacion(informacion, "Gobierno", gobierno);
		return seleccionarAleatorioDeLista(informacion);
	}

	private void agregarInformacion(List<String> informacion, String nombreEntidad, String entidad) {
		if(!entidad.isEmpty()){
			informacion.add(nombreEntidad + SEPARATOR + entidad);
		}
	}

	private void agregarInformacion(List<String> informacion, String nombreEntidad, String[] entidad) {
		for (String valor : entidad){
			if (!valor.isEmpty()) {
				informacion.add(nombreEntidad + SEPARATOR + valor);
			}
		}
	}

	private String seleccionarAleatorioDeLista(List<String> lista) {
		// Desordeno un poco la lista para darle aleatoriedad
		for (int i = 0; i < lista.size(); i++) {
			int posicionRandom = ((int) Math.random() * 10) % lista.size();
			String elemento = lista.get(i);
			lista.set(i, lista.get(posicionRandom));
			lista.set(posicionRandom, elemento);
		}
		Iterator<String> it = lista.iterator();
		while (it.hasNext()) {
			String next = it.next();
			if ((next != null) && !next.isEmpty()) {
				return next;
			}
		}
		// No hay informacion disponible
		return " | ";
	}

	public String getNombreCiudad() {
		return nombreCiudad;
	}

	public String getLatitud() {
		return latitud.isEmpty() ? "0" : latitud;
	}

	public String getLongitud() {
		return longitud.isEmpty() ? "0" : longitud;
	}

	public void setNombreCiudad(String nombreCiudad) {
		this.nombreCiudad = nombreCiudad;
	}

	public void setGeografia(String[] geografia) {
		this.geografia = geografia;
	}

	public void setLugaresDeReferencia(String[] lugaresDeReferencia) {
		this.lugaresDeReferencia = lugaresDeReferencia;
	}

	public void setIndustria(String[] industria) {
		this.industria = industria;
	}

	public void setAnimales(String[] animales) {
		this.animales = animales;
	}

	public void setGente(String[] gente) {
		this.gente = gente;
	}

	public void setIdiomas(String[] idiomas) {
		this.idiomas = idiomas;
	}

	public void setArte(String[] arte) {
		this.arte = arte;
	}

	public void setReligion(String[] religion) {
		this.religion = religion;
	}

	public void setColoresBandera(String coloresBandera) {
		this.coloresBandera = coloresBandera;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getGobierno() {
		return gobierno;
	}

	public void setGobierno(String gobierno) {
		this.gobierno = gobierno;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	@Override
	public String toString() {
		return nombreCiudad;
	}

}
