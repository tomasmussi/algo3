package algo3.modelo.policia;

import java.util.ArrayList;
import java.util.List;

import algo3.modelo.caso.Caso;
import algo3.modelo.edificio.Edificio;
import algo3.modelo.ladron.CaracteristicaLadron;
import algo3.modelo.ladron.Ladron;
import algo3.modelo.mapa.mundi.Ciudad;
import algo3.modelo.policia.grado.Grado;
import algo3.modelo.policia.grado.GradoNovato;
import algo3.modelo.tiempo.Reloj;

public class Policia {

        public static final int HORAS_INICIALES = 154;
        private Reloj reloj;
        private int cantidadArrestos;
        private int cantidadDeVisitas;
        private List<Edificio> edificiosVisitados = new ArrayList<Edificio>();
        private Ciudad ciudadActual;
        private Grado grado;
        private Caso caso;

        public Policia(Reloj reloj) {
                this.reloj = reloj;
                cantidadArrestos = 0;
                cantidadDeVisitas = 0;
                grado = new GradoNovato();
                caso = new Caso();

                // (Elisa) Esto me parece que no va aca: 
                // caso = new Caso();
                // El juego le asigna el caso, o no??. 
                // TODO: Implementar el juego.asignarCasoAJugador(jugador);
        }

        public Policia(Ciudad ciudadInicial) {
                cantidadArrestos = 0;
                cantidadDeVisitas = 0;
                grado = new GradoNovato();
                this.ciudadActual = ciudadInicial;
        }

        public void restarHoras(int horas) {
                reloj.transcurrir(horas);
        }

        public void acuchillado() {
                this.restarHoras(2);
        }

        public void disparado() {
                this.restarHoras(4);
        }

        /**
         * Viajar recibe la cantidad de kilometros a viajar por parte del policia
         * y devuelve la cantidad de horas que le consume el viaje de acuerdo a la cantidad de kilometros
         * que puede viajar
         * 
         * */
        public void viajar(int kilometros) {
                // Calculo de cuantas horas tengo que restar
                int horas = kilometros / grado.getKilometrosPorHora();
                horas += (kilometros % grado.getKilometrosPorHora()) != 0 ? 1 : 0;
                reloj.transcurrir(horas);
        }

        public boolean puedeArrestar() {
                return reloj.hayTiempoRestante();
        }

        public String getGrado() {
                return grado.getGrado();
        }

        public void aumentarArrestos() {
                cantidadArrestos++;
                grado.evaluarGrado(this);
        }

        public int getCantidadArrestos() {
                return cantidadArrestos;
        }

        /**
         * Aumenta el numero de visitas a edificios.
         * Agrega el edificio a lista de edificios visitados.
         * 
         * @return Horas a restar.
         */
        private int aumentarVisitas(Edificio edificio) {
                int horasARestar = 0;
                if (!edificiosVisitados.contains(edificio)) {
                        edificiosVisitados.add(edificio);
                        cantidadDeVisitas++;
                        horasARestar = cantidadDeVisitas;
                }
                return horasARestar;
        }

        public String visitarEdificioYObtenerPista(Edificio edificio) {
                int horasARestar = aumentarVisitas(edificio);
                reloj.transcurrir(horasARestar);
                return grado.getPista(edificio);
        }

        public Ciudad getCiudadActual() {
                return ciudadActual;
        }

        public void viajarA(Ciudad ciudad) {
                if (ciudadActual != null) {
                        this.viajar(ciudadActual.getDistanciaCon(ciudad));
                        // (caso.getRecorrido()).actualizarNexoEntre(ciudadActual, ciudadSiguiente);
                }
                this.ciudadActual = ciudad;
        }

        /**
         * Accion que el policia realiza cuando quiere arrestar un ladron
         * Devuelve true, si y solo si, el policia tiene una orden de arresto para el ladron
         * y misma coincide con el ladron que esta siendo perseguido.
         * Devuelve false en caso contrario
         * 
         * @param ladron
         * @return true si atrapo al ladron, false de lo contrario
         */
        public boolean arrestar(Ladron ladron) {
                if ((caso.getOrdenDeArresto() == null) || !ladron.coincideCon(caso.getOrdenDeArresto().getCaracteristicaLadron())) {
                        return false;
                }
                return true;
        }

        public boolean emitirOrdenDeArresto(CaracteristicaLadron caracteristica) {
                reloj.transcurrir(3);   
                if (caracteristica != null && reloj.hayTiempoRestante()) {
                //TODO:algo de si la pudo emitir o no
                        caso.generarOrdenDeArresto(caracteristica);
                        return true;

                }
                return false;
        }

        public void setGrado(Grado gradoSiguiente) {
                this.grado = gradoSiguiente;
        }

}
