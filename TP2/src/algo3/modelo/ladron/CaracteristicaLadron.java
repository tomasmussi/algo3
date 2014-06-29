package algo3.modelo.ladron;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CaracteristicaLadron implements Cloneable {

	public static final String SEXO = "SEXO";
	public static final String HOBBY = "HOBBY";
	public static final String CABELLO = "CABELLO";
	public static final String CARACTERISTICA = "CARACTERISTICA";
	public static final String VEHICULO = "VEHICULO";


	private String nombre;
	private String sexo;

	// Hobby: Tenis-Musica-Alpinismo-Paracaidismo-Natacion-Croquet
	private String hobby;

	// Cabello: Castanio - Rubio - Rojo - Negro
	private String colorCabello;

	// Senia: Anillo - Tatuaje - Cicatriz - Joyas
	private String caracteristica; // senia queda horrible

	// Vehiculo: Descapotable - Limusina - Deportivo - Moto
	private String vehiculo;

	public CaracteristicaLadron(String nombre, String sexo, String hobby, String colorCabello, String caracteristica, String vehiculo) {
		this.nombre = nombre;
		this.sexo = sexo;
		this.hobby = hobby;
		this.colorCabello = colorCabello;
		this.caracteristica = caracteristica;
		this.vehiculo = vehiculo;

	}

	public Map<String, String> getCaracteristicasPorTipo(){
		Map<String, String> caracteristicasPorTipo = new HashMap<String, String>();
		caracteristicasPorTipo.put(SEXO, sexo);
		caracteristicasPorTipo.put(HOBBY, hobby);
		caracteristicasPorTipo.put(CABELLO, colorCabello);
		caracteristicasPorTipo.put(CARACTERISTICA, caracteristica);
		caracteristicasPorTipo.put(VEHICULO, vehiculo);
		return caracteristicasPorTipo;
	}

	public List<String> getCaracteristicas() {
		List<String> caracteristicas = getCaracteristicasFisicas();
		caracteristicas.add(nombre);
		caracteristicas.add(sexo);
		return caracteristicas;
	}

	public List<String> getCaracteristicasFisicas() {
		List<String> caracteristicas = new ArrayList<String>();
		caracteristicas.add(hobby);
		caracteristicas.add(colorCabello);
		caracteristicas.add(caracteristica);
		caracteristicas.add(vehiculo);
		return caracteristicas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((caracteristica == null) ? 0 : caracteristica.hashCode());
		result = (prime * result) + ((colorCabello == null) ? 0 : colorCabello.hashCode());
		result = (prime * result) + ((hobby == null) ? 0 : hobby.hashCode());
		result = (prime * result) + ((nombre == null) ? 0 : nombre.hashCode());
		result = (prime * result) + ((sexo == null) ? 0 : sexo.hashCode());
		result = (prime * result) + ((vehiculo == null) ? 0 : vehiculo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		CaracteristicaLadron other = (CaracteristicaLadron) obj;
		if (caracteristica == null) {
			if (other.caracteristica != null) {
				return false;
			}
		} else if (!caracteristica.equals(other.caracteristica)) {
			return false;
		}
		if (colorCabello == null) {
			if (other.colorCabello != null) {
				return false;
			}
		} else if (!colorCabello.equals(other.colorCabello)) {
			return false;
		}
		if (hobby == null) {
			if (other.hobby != null) {
				return false;
			}
		} else if (!hobby.equals(other.hobby)) {
			return false;
		}
		if (nombre == null) {
			if (other.nombre != null) {
				return false;
			}
		} else if (!nombre.equals(other.nombre)) {
			return false;
		}
		if (sexo == null) {
			if (other.sexo != null) {
				return false;
			}
		} else if (!sexo.equals(other.sexo)) {
			return false;
		}
		if (vehiculo == null) {
			if (other.vehiculo != null) {
				return false;
			}
		} else if (!vehiculo.equals(other.vehiculo)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return nombre;

	}

	@Override
	public CaracteristicaLadron clone() {
		CaracteristicaLadron caracteristicaLadron = new CaracteristicaLadron(nombre, sexo, hobby, colorCabello, caracteristica, vehiculo);
		return caracteristicaLadron;
	}

	public String getSexo() {
		return sexo;
	}

	public String getNombre() {
		return nombre;
	}

}
