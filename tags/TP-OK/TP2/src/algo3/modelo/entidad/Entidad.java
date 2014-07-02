package algo3.modelo.entidad;

import algo3.modelo.policia.Policia;

public interface Entidad {

	public String getNombre();

	public String getInformacion();

	public void atacarPolicia(Policia policia);

}
