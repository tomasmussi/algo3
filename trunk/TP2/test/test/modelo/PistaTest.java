package test.modelo;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import algo3.modelo.caso.Caso;
import algo3.modelo.edificio.Edificio;
import algo3.modelo.edificio.EdificioFactory;
import algo3.modelo.excepcion.CiudadNoEncontradaException;
import algo3.modelo.ladron.CaracteristicaLadron;
import algo3.modelo.ladron.Ladron;
import algo3.modelo.mapa.InformacionCiudad;
import algo3.modelo.mapa.InformacionMapa;
import algo3.modelo.mapa.NombresCiudades;
import algo3.modelo.objeto.CaracteristicaObjeto;
import algo3.modelo.policia.Policia;
import algo3.modelo.tiempo.Reloj;

public class PistaTest {

	private CaracteristicaLadron caracteristicaNickBrunch;
	private List<CaracteristicaLadron> ladrones;
	private List<CaracteristicaObjeto> objetos;
	private Ladron nickBrunch;

	@Before
	public void initialize(){
		caracteristicaNickBrunch = new CaracteristicaLadron("Nick Brunch", "Masculino", "Mountain Climbing", "Negro", "Anillo", "Motocicleta");
		nickBrunch = new Ladron(caracteristicaNickBrunch);
		ladrones = new ArrayList<CaracteristicaLadron>();
		objetos = new ArrayList<CaracteristicaObjeto>();

		ladrones.add(caracteristicaNickBrunch);
		objetos.add(new CaracteristicaObjeto("Anillo del Papa", "Roma"));
	}

	private InformacionCiudad getInformacionPara(String ciudad) {
		return InformacionMapa.getInstance().getCiudadDeNombre(ciudad);
	}

	private Policia crearPoliciaSargento() {
		return crearPolicia(20);
	}

	private Policia crearPoliciaDetective() {
		return crearPolicia(5);
	}

	private Policia crearPoliciaInvestigador() {
		return crearPolicia(10);
	}

	private Policia crearPoliciaNovato() {
		return crearPolicia(0);
	}

	private Policia crearPolicia(int arrestos) {

		Policia policia = new Policia();
		Reloj reloj = new Reloj();
		policia.setReloj(reloj);
		try {
			policia.asignarCaso(new Caso(ladrones, objetos, policia.getGrado()));
		} catch (CiudadNoEncontradaException e) {
			System.out.println("Error generando caso: " + e.getMessage());
		}
		policia.emitirOrdenDeArresto(caracteristicaNickBrunch);
		for (int i = 1; i <= arrestos; i++) {
			policia.arrestar(nickBrunch);
		}
		return policia;
	}

	/* ************************************************* */
	/* ******** TEST PARA NIVEL FACIL - NOVATO ********* */
	/* ************************************************* */

	@Test
	public void testPoliciaNovatoIngresaEdificioDeViajeObtienePista() {
		Policia policia = crearPoliciaNovato();
		Edificio edificio = EdificioFactory.crearEdificioDeViajeConEntidad(getInformacionPara(NombresCiudades.ATENAS));
		// Edificio de viaje deberia tener informacion acerca de la bandera del siguiente pais. En este caso "Azul y blanco"
		String pista = policia.visitarEdificioYObtenerPista(edificio);
		assertTrue(pista.startsWith("Me dicen mis fuentes que se fue en un avion con Azul y blanco en sus alas."));
	}

	@Test
	public void testPoliciaNovatoIngresaEdificioFinancieroObtienePista() {
		Policia policia = crearPoliciaNovato();
		Edificio edificio = EdificioFactory.crearEdificioFinancieroConEntidad(getInformacionPara(NombresCiudades.BUENOS_AIRES));
		// Edificio financiero deberia devolverme informacion acerca de la moneda del pais. En este caso "Australes".
		String pista = policia.visitarEdificioYObtenerPista(edificio);
		assertTrue(pista.startsWith("Solo se que cambio todo su dinero a Australes."));
	}

	@Test
	public void testPoliciaNovatoIngresaEdificioCulturalObtienePista() {
		Policia policia = crearPoliciaNovato();
		Edificio edificio = EdificioFactory.crearEdificioCulturalConEntidad(getInformacionPara(NombresCiudades.PORT_MORESBY));
		// Edificio Cultural me va a proporcionar informacion acerca del gobierno del pais. En este caso "Primer Ministro".
		String pista = policia.visitarEdificioYObtenerPista(edificio);
		assertTrue(pista.startsWith("Estuvo mostrando sus fotos con Taipans."));
	}

	@Test
	public void testPoliciaNovatoIngresaEdificioCulturalObtienePistaDistintaInformacion() {
		Policia policia = crearPoliciaNovato();
		Edificio edificio = EdificioFactory.crearEdificioCulturalConEntidad(getInformacionPara(NombresCiudades.MEXICO_CITY));
		// Edificio Cultural me va a proporcionar informacion acerca del gobierno del pais. En este caso "Primer Ministro".
		String pista = policia.visitarEdificioYObtenerPista(edificio);
		assertTrue(pista.startsWith("Estaba buscando libros acerca de yucateca."));
	}

	@Test
	public void testPoliciaNovatoIngresaEdificioCulturalObtienePistaDistintaInformacionDos() {
		Policia policia = crearPoliciaNovato();
		Edificio edificio = EdificioFactory.crearEdificioCulturalConEntidad(getInformacionPara(NombresCiudades.COLOMBO));
		// Edificio Cultural me va a proporcionar informacion acerca del gobierno del pais. En este caso "Primer Ministro".
		String pista = policia.visitarEdificioYObtenerPista(edificio);
		assertTrue(pista.startsWith("Segun mis fuentes, estuvo mostrando fotos de Golfo de Mannar."));
	}

	@Test
	public void testPoliciaNovatoIngresaEdificioCulturalObtienePistaDistintaInformacionTres() {
		Policia policia = crearPoliciaNovato();
		Edificio edificio = EdificioFactory.crearEdificioCulturalConEntidad(getInformacionPara(NombresCiudades.CAIRO));
		// Edificio Cultural me va a proporcionar informacion acerca del gobierno del pais. En este caso "Primer Ministro".
		String pista = policia.visitarEdificioYObtenerPista(edificio);
		assertTrue(pista.startsWith("Estuvo mostrando sus fotos con nubios."));
	}

	/* ************************************************* */
	/* ******* TEST PARA NIVEL MEDIO - DETECTIVE ******* */
	/* ************************************************* */

	@Test
	public void testPoliciaDetectiveIngresaEdificioDeViajeObtienePista() {
		Policia policia = crearPoliciaDetective();
		Edificio edificio = EdificioFactory.crearEdificioDeViajeConEntidad(getInformacionPara(NombresCiudades.ATENAS));
		// Edificio de viaje deberia tener informacion acerca de la bandera del siguiente pais. En este caso "Azul y blanco"
		String pista = policia.visitarEdificioYObtenerPista(edificio);
		assertTrue(pista.startsWith("Me dicen mis fuentes que se fue en un vehiculo con Azul y blanco en sus banderines."));
	}

	@Test
	public void testPoliciaDetectiveIngresaEdificioFinancieroObtienePista() {
		Policia policia = crearPoliciaDetective();
		Edificio edificio = EdificioFactory.crearEdificioFinancieroConEntidad(getInformacionPara(NombresCiudades.BUENOS_AIRES));
		// Edificio financiero deberia devolverme informacion acerca de la moneda del pais. En este caso "Australes".
		String pista = policia.visitarEdificioYObtenerPista(edificio);
		assertTrue(pista.startsWith("Segun mis fuentes estuvo averiguando sobre el valor de Australes."));
	}

	@Test
	public void testPoliciaDetectiveIngresaEdificioCulturalObtienePista() {
		Policia policia = crearPoliciaDetective();
		Edificio edificio = EdificioFactory.crearEdificioCulturalConEntidad(getInformacionPara(NombresCiudades.PORT_MORESBY));
		// Edificio Cultural me va a proporcionar informacion acerca del gobierno del pais. En este caso "Primer Ministro".
		String pista = policia.visitarEdificioYObtenerPista(edificio);
		assertTrue(pista.startsWith("Estuvo mostrando sus fotos con Taipans."));
	}

	/* ************************************************* */
	/* ***** TEST PARA NIVEL MEDIO - INVESTIGADOR ****** */
	/* ************************************************* */

	@Test
	public void testPoliciaInvestigadorIngresaEdificioDeViajeObtienePista() {
		Policia policia = crearPoliciaInvestigador();
		Edificio edificio = EdificioFactory.crearEdificioDeViajeConEntidad(getInformacionPara(NombresCiudades.ATENAS));
		// Edificio de viaje deberia tener informacion acerca de la bandera del siguiente pais. En este caso "Azul y blanco"
		String pista = policia.visitarEdificioYObtenerPista(edificio);
		assertTrue(pista.startsWith("Me dicen mis fuentes que se fue en un vehiculo con Azul y blanco en sus banderines."));
	}

	@Test
	public void testPoliciaInvestigadorIngresaEdificioFinancieroObtienePista() {
		Policia policia = crearPoliciaInvestigador();
		Edificio edificio = EdificioFactory.crearEdificioFinancieroConEntidad(getInformacionPara(NombresCiudades.BUENOS_AIRES));
		// Edificio financiero deberia devolverme informacion acerca de la moneda del pais. En este caso "Australes".
		String pista = policia.visitarEdificioYObtenerPista(edificio);
		assertTrue(pista.startsWith("Segun mis fuentes estuvo averiguando sobre el valor de Australes."));
	}

	@Test
	public void testPoliciaInvestigadorIngresaEdificioCulturalObtienePista() {
		Policia policia = crearPoliciaInvestigador();
		Edificio edificio = EdificioFactory.crearEdificioCulturalConEntidad(getInformacionPara(NombresCiudades.PORT_MORESBY));
		// Edificio Cultural me va a proporcionar informacion acerca del gobierno del pais. En este caso "Primer Ministro".
		String pista = policia.visitarEdificioYObtenerPista(edificio);
		assertTrue(pista.startsWith("Estuvo mostrando sus fotos con Taipans."));
	}

	@Test
	public void testPoliciaInvestigadorIngresaEdificioCulturalObtienePistaEnSydney() {
		Policia policia = crearPoliciaInvestigador();
		Edificio edificio = EdificioFactory.crearEdificioCulturalConEntidad(getInformacionPara(NombresCiudades.SYDNEY));
		// Edificio Cultural me va a proporcionar informacion acerca del gobierno del pais. En este caso "Primer Ministro".
		String pista = policia.visitarEdificioYObtenerPista(edificio);
		List<String> posiblesPistas = new ArrayList<String>();
		posiblesPistas.add("Estuvo mostrando sus fotos con los aborigenes.");
		posiblesPistas.add("Estuvo mostrando sus fotos con el capitan Cook.");
		assertTrue(posiblesPistas.contains(pista));
	}

	@Test
	public void testPoliciaInvestigadorIngresaEdificioCulturalObtienePistaEnSanMarino() {
		Policia policia = crearPoliciaInvestigador();
		Edificio edificio = EdificioFactory.crearEdificioCulturalConEntidad(getInformacionPara(NombresCiudades.SAN_MARINO));
		// Edificio Cultural me va a proporcionar informacion acerca del gobierno del pais. En este caso "Primer Ministro".
		String pista = policia.visitarEdificioYObtenerPista(edificio);
		assertTrue(pista.startsWith("Estuvo buscando diccionarios de Italiano."));
	}

	/* ************************************************* */
	/* ****** TEST PARA NIVEL DIFICL - SARGENTO ******** */
	/* ************************************************* */

	@Test
	public void testPoliciaSargentoIngresaEdificioDeViajeObtienePista() {
		Policia policia = crearPoliciaSargento();
		Edificio edificio = EdificioFactory.crearEdificioDeViajeConEntidad(getInformacionPara(NombresCiudades.ATENAS));
		// Edificio de viaje deberia tener informacion acerca de la bandera del siguiente pais. En este caso "Azul y blanco"
		String pista = policia.visitarEdificioYObtenerPista(edificio);
		assertTrue(pista.startsWith("Segun escuche, estaba mostrando una foto del hotel donde se hospedaria, tenia Azul y blanco en su puerta."));
	}

	@Test
	public void testPoliciaSargentoIngresaEdificioFinancieroObtienePista() {
		Policia policia = crearPoliciaSargento();
		Edificio edificio = EdificioFactory.crearEdificioFinancieroConEntidad(getInformacionPara(NombresCiudades.BUENOS_AIRES));
		// Edificio financiero deberia devolverme informacion acerca de la moneda del pais. En este caso "Australes".
		String pista = policia.visitarEdificioYObtenerPista(edificio);
		assertTrue(pista.startsWith("Estuvo consultando sobre el indice devaluatorio de Australes."));
	}

	@Test
	public void testPoliciaSargentoIngresaEdificioCulturalObtienePista() {
		Policia policia = crearPoliciaSargento();
		Edificio edificio = EdificioFactory.crearEdificioCulturalConEntidad(getInformacionPara(NombresCiudades.PORT_MORESBY));
		// Edificio Cultural me va a proporcionar informacion acerca del gobierno del pais. En este caso "Primer Ministro".
		String pista = policia.visitarEdificioYObtenerPista(edificio);
		assertTrue(pista.startsWith("Estuvo mostrando sus fotos con Taipans."));
	}

	@Test
	public void testPoliciaSargentoIngresaEdificioYObtienePista() {
		Policia policia = crearPoliciaSargento();
		Edificio edificio = EdificioFactory.crearEdificioCulturalConEntidad(getInformacionPara(NombresCiudades.PARIS));
		String pista = policia.visitarEdificioYObtenerPista(edificio);
		assertTrue(pista.startsWith("Una persona estuvo aqui buscando informacion sobre moda."));
	}

	@Test
	public void testPoliciaSargentoIngresaEdificioYObtienePistaGeografica() {
		Policia policia = crearPoliciaSargento();
		Edificio edificio = EdificioFactory.crearEdificioCulturalConEntidad(getInformacionPara(NombresCiudades.NEW_YORK));
		String pista = policia.visitarEdificioYObtenerPista(edificio);
		List<String> posiblesPistas = new ArrayList<String>();
		posiblesPistas.add("Estuvo mostrando sus fotos con Henry Hudson.");
		posiblesPistas.add("Segun mis fuentes, estuvo mostrando fotos de tumba de Grant.");
		posiblesPistas.add("Segun mis fuentes, estuvo mostrando fotos de Rio Hudson.");
		posiblesPistas.add("Segun mis fuentes, estuvo mostrando fotos de Naciones Unidas.");
		posiblesPistas.add("Segun mis fuentes, estuvo mostrando fotos de World Trade Center.");
		assertTrue(posiblesPistas.contains(pista));
	}

	@Test
	public void testPoliciaSargentoIngresaEdificioObtienePistaYPosiblePistaLadron() {
		Policia policia = crearPoliciaSargento();
		Edificio edificio = EdificioFactory.crearEdificioCulturalConEntidad(caracteristicaNickBrunch, getInformacionPara(NombresCiudades.NEW_YORK));
		String pista = policia.visitarEdificioYObtenerPista(edificio);
		List<String> posiblesPistas = new ArrayList<String>();
		posiblesPistas.add("Estuvo mostrando sus fotos con Henry Hudson.");
		posiblesPistas.add("Segun mis fuentes, estuvo mostrando fotos de tumba de Grant.");
		posiblesPistas.add("Segun mis fuentes, estuvo mostrando fotos de Rio Hudson.");
		posiblesPistas.add("Segun mis fuentes, estuvo mostrando fotos de Naciones Unidas.");
		posiblesPistas.add("Segun mis fuentes, estuvo mostrando fotos de World Trade Center.");
		posiblesPistas.add("Comento que le gustaba el Mountain Climbing");
		posiblesPistas.add("Tenia el pelo Negro");
		posiblesPistas.add("Llevaba una gran piedra preciosa en su dedo");
		posiblesPistas.add("Llevaba consigo un casco");
		String[] pistaSplit = pista.split("\\.");
		if (pistaSplit.length == 2) {
			assertTrue(posiblesPistas.contains(pistaSplit[1].trim()));
		} else {
			assertTrue(posiblesPistas.contains(pista));
		}
	}
}