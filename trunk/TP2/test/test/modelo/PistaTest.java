package test.modelo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import algo3.modelo.edificio.Edificio;
import algo3.modelo.edificio.EdificioFactory;
import algo3.modelo.mapa.mundi.InformacionCiudadProvider;
import algo3.modelo.mapa.mundi.NombresCiudades;
import algo3.modelo.policia.Policia;
import algo3.modelo.tiempo.Reloj;

public class PistaTest {

	private InformacionCiudadProvider getInfoProvider() {
		return InformacionCiudadProvider.getInstance();
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
		Policia policia = new Policia(new Reloj());
		for (int i = 1; i <= arrestos; i++) {
			policia.aumentarArrestos();
		}
		return policia;
	}

	/* ************************************************* */
	/* ******** TEST PARA NIVEL FACIL - NOVATO ********* */
	/* ************************************************* */

	@Test
	public void testPoliciaNovatoIngresaEdificioDeViajeObtienePista() {
		Policia policia = crearPoliciaNovato();
		Edificio edificio = EdificioFactory.crearEdificioDeViajeConEntidad(getInfoProvider().getInformacionPara(NombresCiudades.ATENAS));
		// Edificio de viaje deberia tener informacion acerca de la bandera del siguiente pais. En este caso "Azul y blanco"
		String pista = policia.visitarEdificioYObtenerPista(edificio);
		assertEquals("Me dicen mis fuentes que se fue en un avion con Azul y blanco en sus alas.", pista);
	}

	@Test
	public void testPoliciaNovatoIngresaEdificioFinancieroObtienePista() {
		Policia policia = crearPoliciaNovato();
		Edificio edificio = EdificioFactory.crearEdificioFinancieroConEntidad(getInfoProvider().getInformacionPara(NombresCiudades.BUENOS_AIRES));
		// Edificio financiero deberia devolverme informacion acerca de la moneda del pais. En este caso "Australes".
		String pista = policia.visitarEdificioYObtenerPista(edificio);
		assertEquals("Solo se que cambio todo su dinero a Australes.", pista);
	}

	@Test
	public void testPoliciaNovatoIngresaEdificioCulturalObtienePista() {
		Policia policia = crearPoliciaNovato();
		Edificio edificio = EdificioFactory.crearEdificioCulturalConEntidad(getInfoProvider().getInformacionPara(NombresCiudades.PORT_MORESBY));
		// Edificio Cultural me va a proporcionar informacion acerca del gobierno del pais. En este caso "Primer Ministro".
		String pista = policia.visitarEdificioYObtenerPista(edificio);
		assertEquals("Un sospechoso estuvo aqui averiguando sobre el tipo de gobierno de un Primer Ministro.", pista);
	}

	/* ************************************************* */
	/* ******* TEST PARA NIVEL MEDIO - DETECTIVE ******* */
	/* ************************************************* */

	@Test
	public void testPoliciaDetectiveIngresaEdificioDeViajeObtienePista() {
		Policia policia = crearPoliciaDetective();
		Edificio edificio = EdificioFactory.crearEdificioDeViajeConEntidad(getInfoProvider().getInformacionPara(NombresCiudades.ATENAS));
		// Edificio de viaje deberia tener informacion acerca de la bandera del siguiente pais. En este caso "Azul y blanco"
		String pista = policia.visitarEdificioYObtenerPista(edificio);
		assertEquals("Me dicen mis fuentes que se fue en un vehiculo con Azul y blanco en sus banderines.", pista);
	}

	@Test
	public void testPoliciaDetectiveIngresaEdificioFinancieroObtienePista() {
		Policia policia = crearPoliciaDetective();
		Edificio edificio = EdificioFactory.crearEdificioFinancieroConEntidad(getInfoProvider().getInformacionPara(NombresCiudades.BUENOS_AIRES));
		// Edificio financiero deberia devolverme informacion acerca de la moneda del pais. En este caso "Australes".
		String pista = policia.visitarEdificioYObtenerPista(edificio);
		assertEquals("Segun mis fuentes estuvo averiguando sobre el valor de Australes.", pista);
	}

	@Test
	public void testPoliciaDetectiveIngresaEdificioCulturalObtienePista() {
		Policia policia = crearPoliciaDetective();
		Edificio edificio = EdificioFactory.crearEdificioCulturalConEntidad(getInfoProvider().getInformacionPara(NombresCiudades.PORT_MORESBY));
		// Edificio Cultural me va a proporcionar informacion acerca del gobierno del pais. En este caso "Primer Ministro".
		String pista = policia.visitarEdificioYObtenerPista(edificio);
		assertEquals("Dijo algo acerca de tomar el te con el Primer Ministro.", pista);
	}

	/* ************************************************* */
	/* ***** TEST PARA NIVEL MEDIO - INVESTIGADOR ****** */
	/* ************************************************* */

	@Test
	public void testPoliciaInvestigadorIngresaEdificioDeViajeObtienePista() {
		Policia policia = crearPoliciaInvestigador();
		Edificio edificio = EdificioFactory.crearEdificioDeViajeConEntidad(getInfoProvider().getInformacionPara(NombresCiudades.ATENAS));
		// Edificio de viaje deberia tener informacion acerca de la bandera del siguiente pais. En este caso "Azul y blanco"
		String pista = policia.visitarEdificioYObtenerPista(edificio);
		assertEquals("Me dicen mis fuentes que se fue en un vehiculo con Azul y blanco en sus banderines.", pista);
	}

	@Test
	public void testPoliciaInvestigadorIngresaEdificioFinancieroObtienePista() {
		Policia policia = crearPoliciaInvestigador();
		Edificio edificio = EdificioFactory.crearEdificioFinancieroConEntidad(getInfoProvider().getInformacionPara(NombresCiudades.BUENOS_AIRES));
		// Edificio financiero deberia devolverme informacion acerca de la moneda del pais. En este caso "Australes".
		String pista = policia.visitarEdificioYObtenerPista(edificio);
		assertEquals("Segun mis fuentes estuvo averiguando sobre el valor de Australes.", pista);
	}

	@Test
	public void testPoliciaInvestigadorIngresaEdificioCulturalObtienePista() {
		Policia policia = crearPoliciaInvestigador();
		Edificio edificio = EdificioFactory.crearEdificioCulturalConEntidad(getInfoProvider().getInformacionPara(NombresCiudades.PORT_MORESBY));
		// Edificio Cultural me va a proporcionar informacion acerca del gobierno del pais. En este caso "Primer Ministro".
		String pista = policia.visitarEdificioYObtenerPista(edificio);
		assertEquals("Dijo algo acerca de tomar el te con el Primer Ministro.", pista);
	}

	/* ************************************************* */
	/* ****** TEST PARA NIVEL DIFICL - SARGENTO ******** */
	/* ************************************************* */

	@Test
	public void testPoliciaSargentoIngresaEdificioDeViajeObtienePista() {
		Policia policia = crearPoliciaSargento();
		Edificio edificio = EdificioFactory.crearEdificioDeViajeConEntidad(getInfoProvider().getInformacionPara(NombresCiudades.ATENAS));
		// Edificio de viaje deberia tener informacion acerca de la bandera del siguiente pais. En este caso "Azul y blanco"
		String pista = policia.visitarEdificioYObtenerPista(edificio);
		assertEquals("Segun escuche, estaba mostrando una foto del hotel donde se hospedaria, tenia Azul y blanco en su puerta.", pista);
	}

	@Test
	public void testPoliciaSargentoIngresaEdificioFinancieroObtienePista() {
		Policia policia = crearPoliciaSargento();
		Edificio edificio = EdificioFactory.crearEdificioFinancieroConEntidad(getInfoProvider().getInformacionPara(NombresCiudades.BUENOS_AIRES));
		// Edificio financiero deberia devolverme informacion acerca de la moneda del pais. En este caso "Australes".
		String pista = policia.visitarEdificioYObtenerPista(edificio);
		assertEquals("Estuvo consultando sobre el indice devaluatorio de Australes.", pista);
	}

	@Test
	public void testPoliciaSargentoIngresaEdificioCulturalObtienePista() {
		Policia policia = crearPoliciaSargento();
		Edificio edificio = EdificioFactory.crearEdificioCulturalConEntidad(getInfoProvider().getInformacionPara(NombresCiudades.PORT_MORESBY));
		// Edificio Cultural me va a proporcionar informacion acerca del gobierno del pais. En este caso "Primer Ministro".
		String pista = policia.visitarEdificioYObtenerPista(edificio);
		assertEquals("Mostro interes en aprender sobre el gobierno de un Primer Ministro.", pista);
	}

}
