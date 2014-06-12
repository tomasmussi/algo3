package algo3.modelo.mapa.mundi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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

	/**
	 * Crea una Informacion completamente vacia
	 * Hay que pasarle por parametro todas las propiedades
	 * 
	 */
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


	/** Devuelve informacion relevante para utilizar desde un
	 * Aeropuerto, Puerto o algun otro edificio que utilice informacion
	 * sobre bandera o moneda del pais
	 * 
	 */
	@Override
	public String getInformacionViaje() {
		List<String> informacion = new ArrayList<String>(2);
		informacion.add(moneda);
		informacion.add(coloresBandera);
		return seleccionarAleatorioDeLista(informacion);
	}

	@Override
	public String getInformacionFinanciera() {
		List<String> informacion = new ArrayList<String>(1);
		informacion.add(moneda);
		return seleccionarAleatorioDeLista(informacion);
	}

	@Override
	public String getInformacionGeografica() {
		List<String> informacion = new ArrayList<String>();
		informacion.add(moneda);
		informacion.addAll(transformarEnColeccion(geografia));
		informacion.addAll(transformarEnColeccion(lugaresDeReferencia));
		return seleccionarAleatorioDeLista(informacion);
	}

	@Override
	public String getInformacionHistorica() {
		List<String> informacion = new ArrayList<String>();
		informacion.add(moneda);
		informacion.addAll(transformarEnColeccion(industria));
		informacion.addAll(transformarEnColeccion(animales));
		informacion.addAll(transformarEnColeccion(gente));
		informacion.addAll(transformarEnColeccion(idiomas));
		informacion.addAll(transformarEnColeccion(arte));
		informacion.addAll(transformarEnColeccion(religion));
		return seleccionarAleatorioDeLista(informacion);
	}

	private Collection<String> transformarEnColeccion(String[] array){
		Set<String> lista = new HashSet<String>();
		for (int i = 0; i < array.length; i++){
			lista.add(array[i]);
		}
		return lista;
	}

	private String seleccionarAleatorioDeLista(List<String> lista){
		// Desordeno un poco la lista para darle aleatoriedad
		for (int i = 0; i < lista.size(); i++){
			int posicionRandom = ((int) Math.random() * 10) % lista.size();
			String elemento = lista.get(i);
			lista.set(i, lista.get(posicionRandom));
			lista.set(posicionRandom, elemento);
		}
		Iterator<String> it = lista.iterator();
		while (it.hasNext()){
			String next = it.next();
			if (next != null && !next.isEmpty()){
				return next;
			}
		}
		return "";
	}

	public String getNombreCiudad() {
		return nombreCiudad;
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

}
