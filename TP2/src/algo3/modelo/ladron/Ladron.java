package algo3.modelo.ladron;


public class Ladron {

	//TODO: Lo que haria yo es construir el ladron con parametros desde el constructor, porque en realidad no
	//necesitamos consultarle todo el tiempo sus propiedades, hacemos un metodo que diga:
	//sosVos("masculino", "alpinismo", "negro", "anillo", "descapotable") y devuelva true o false si es o no es
	// Asi encapsulamos la comparacion en ladron

	//Sexo: Femenino-Masculino
	private String nombre;
	private String sexo;

	//Hobby: Tenis-Música-Alpinismo-Paracaidismo-Natación-Croquet
	private String hobby;

	//Cabello: Castaño - Rubio - Rojo - Negro
	private String colorCabello;

	//Senia: Anillo - Tatuaje - Cicatriz - Joyas
	private String caracteristica; // senia? queda horrible

	// Vehículo: Descapotable - Limusina - Deportivo - Moto
	private String vehiculo;

	//Comunes: 4 paises.
	//Valiosos: 5 paises.
	//Muy Valiosos: 7 paises.
	// Yo creo que este si tiene sentido hacerlo por jerarquía de clases porque tienen distintos comportamientos,
	// a diferencia de el vehiculo, o "senia" que solo deberían ser una información.  Igualmente TBD.
	private ObjetoRobado objetoRobado;

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getColorCabello() {
		return colorCabello;
	}

	public void setColorCabello(String colorCabello) {
		this.colorCabello = colorCabello;
	}

	public String getCaracteristica() {
		return caracteristica;
	}

	public void setCaracteristica(String caracteristica) {
		this.caracteristica = caracteristica;
	}

	public String getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(String vehiculo) {
		this.vehiculo = vehiculo;
	}

	public ObjetoRobado getObjetoRobado() {
		return objetoRobado;
	}

	public void setObjetoRobado(ObjetoRobado objetoRobado) {
		this.objetoRobado = objetoRobado;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(nombre + '\n');
		sb.append(sexo + '\n');
		sb.append(hobby + '\n');
		sb.append(colorCabello + '\n');
		sb.append(caracteristica + '\n');
		sb.append(vehiculo + '\n');
		return sb.toString();
	}


}
