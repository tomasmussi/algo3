package algo3.modelo.viaje;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import algo3.modelo.mapa.Ciudad;
import algo3.modelo.mapa.CiudadFactory;
import algo3.modelo.mapa.InformacionCiudad;
import algo3.modelo.mapa.InformacionMapa;

public class Mapa {

	private static final int CANTIDAD_CIUDADES_POSIBLES = 4;

	/**
	 * Hash para tener las ciudades creadas y las posibles ciudades de destino
	 */
	private Map<Ciudad, List<Ciudad>> ciudadesUsadas;

	/**
	 * Hash para tener los nombres de ciudades que me fueron pidiendo y las ciudades creadas
	 */
	private Map<String, Ciudad> nombresDeCiudades;
	private Map<String, InformacionCiudad> ciudadesLibres;

	/**
	 * Crea un objeto de destinos posibles para un caso determinado.
	 * Cuando el ladron elige una ruta de escape, crea ciudades por las cuales se escapara
	 * y crea un objeto de DestinosPosibles, que conocera las conexiones que ya creo el ladron
	 * y ademas se encargara de crear ciudades de relleno en caso de que el jugador se equivoque
	 * y elija mal caminos
	 */
	public Mapa(List<Ciudad> escapatoria) {
		ciudadesUsadas = new HashMap<Ciudad, List<Ciudad>>();
		nombresDeCiudades = new HashMap<String, Ciudad>();
		ciudadesLibres = new HashMap<String, InformacionCiudad>();
		cargarCiudades();
		crearMapaDeRecorrido(escapatoria);
	}

	public Mapa() {
		this(new ArrayList<Ciudad>());
	}

	private void cargarCiudades(){
		Iterator<InformacionCiudad> iter = InformacionMapa.getInstance().getListadoCiudades().iterator();
		while (iter.hasNext()){
			InformacionCiudad informacion = iter.next();
			ciudadesLibres.put(informacion.getNombreCiudad(), informacion);
		}
	}

	/**
	 * Relaciona una ciudad con sus anteriores y posteriores, si tiene
	 * Solo utilizado por ciudades del recorrido
	 * */
	private void crearMapaDeRecorrido(List<Ciudad> escapatoria) {
		for (int i = 0; i < escapatoria.size(); i++){
			Ciudad unaCiudad = escapatoria.get(i);
			List<Ciudad> posibles = new ArrayList<Ciudad>();

			if (i != escapatoria.size() - 1){
				posibles.add(escapatoria.get(i+1));
			}
			if (i != 0){
				posibles.add(escapatoria.get(i-1));
			}
			agregarCiudad(unaCiudad, posibles);
			ciudadesLibres.remove(unaCiudad.getNombre());
		}
	}

	private void agregarCiudad(Ciudad origen, List<Ciudad> posibles){
		ciudadesUsadas.put(origen, posibles);
		nombresDeCiudades.put(origen.getNombre(), origen);
	}


	/**
	 * Devuelve las posibles ciudades, donde siempre estaran de la que viene y a la que tiene que ir + otra(s) elegida(s) Randomly.
	 */
	public List<Ciudad> getCiudadesPosibles(Ciudad ciudadOrigen){

		List<Ciudad> origenPosibles = ciudadesUsadas.get(ciudadOrigen);

		Iterator<Entry<String, InformacionCiudad>> iter = ciudadesLibres.entrySet().iterator();
		while (origenPosibles.size() < CANTIDAD_CIUDADES_POSIBLES && iter.hasNext()) {
			Entry<String, InformacionCiudad> entry = iter.next();

			Ciudad nuevaCiudad = CiudadFactory.crearCiudadComun(entry.getValue());
			List<Ciudad> destinoPosibles = new ArrayList<Ciudad>();
			// Destino -> Origen
			destinoPosibles.add(ciudadOrigen);
			agregarCiudad(nuevaCiudad, destinoPosibles);
			// Origen -> Destino
			origenPosibles.add(nuevaCiudad);
			//Lo saco de las listas posibles para no armar un recorrido que pueda volver
			iter.remove();
		}
		return origenPosibles;
	}


	/**
	 * Llamado por ladron para encapsular su ruta de escape y tener la informacion de las ciudades
	 * que eligio el ladron para escaparse y poder rellenar la informacion
	 * 
	 */
	public void agregarCiudades(List<Ciudad> rutaEscape) {
		crearMapaDeRecorrido(rutaEscape);
	}
}
