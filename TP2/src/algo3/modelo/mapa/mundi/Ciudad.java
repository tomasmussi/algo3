package algo3.modelo.mapa.mundi;


public class Ciudad {

	private int coordenadaX;
	private int coordenadaY;
	private Edificio edificio1;
	private Edificio edificio2;
	private Edificio edificio3;
	private String nombre;
	
	
	public Ciudad(int coordenadaX, int coordenadaY, String nombre, 
			Edificio edificio1, Edificio edificio2, Edificio edificio3){
		this.nombre = nombre;
		this.coordenadaX = coordenadaX;
		this.coordenadaY = coordenadaY;
		this.edificio1 = edificio1;
		this.edificio2 = edificio2;
		this.edificio3 = edificio3;
	}

		
	public int calcularDistanciaCon(Ciudad estaCiudad){
		return (int) (Math.pow((Math.pow(this.coordenadaX - estaCiudad.coordenadaX, 2)
				+Math.pow(this.coordenadaY - estaCiudad.coordenadaY, 2)),0.5));
	}

}
