package Principi.Reptes.Rummy.Jocs;

import Principi.Reptes.Rummy.*;
import Principi.Reptes.Rummy.ES.Llegir;
import Principi.Reptes.Rummy.ES.Sortides;
import Principi.Reptes.Rummy.Validacions.ValidacioEstandar;

public class RummyBasic extends JocBase {

    private Baralla baralla = new Baralla();
    private Carta anteriorDescarte;
    private Tauler<Carta> tauler;
    private final int MAX_CARTES_COMBINAR = 4;

    public RummyBasic() {
        super();
        iniciar();
    }

    protected void iniciar() {
        tauler = new Tauler<>(new ValidacioEstandar());
    }

    protected Ma<Carta> extreureMa () {
        int nombreCartesMaInicial = 14;
        Ma<Carta> maExtreta = new Ma<>();

        for (int i = 1; i < nombreCartesMaInicial; i++) {
            maExtreta.afegirCarta(baralla.extreureCarta());
        }
        return maExtreta;
    }

    private void descartarCarta () {
        Ma<Carta> maJugador = players[torn % NUM_JUGADORS].getMa();
        int tamany = maJugador.getNombreCartes();
        int indexDescarte = Llegir.demanarCartaDescartar(tamany);

        anteriorDescarte = maJugador.getCarta(indexDescarte);
        maJugador.eliminarCarta(indexDescarte);
    }

    private boolean inserirMaTauler () {
        Ma<Carta> maCombinada = cartesCombinar(MAX_CARTES_COMBINAR);
        if (maCombinada.getNombreCartes() == 1) {
            int indexGrup = Llegir.demanarIndexGrupInserir(tauler.getNombreGrups());
            if (tauler.afegirCarta(maCombinada.getCarta(0), indexGrup)){
                eliminarCartesMaJugador(maCombinada);
                return true;
            }
        }
        else if (maCombinada.getNombreCartes() != 0) {
            if (tauler.afegirGrup(maCombinada)) {
                eliminarCartesMaJugador(maCombinada);
                return true;
            }
        }
        return false;
    }

    protected void tocaTorn () {
        Sortides.imprimirTorn(torn, players);
        Ma<Carta> maJugador = players[torn % NUM_JUGADORS].getMa();
        Sortides.imprimirEstatPartida(maJugador, tauler, anteriorDescarte);

        robarCarta (anteriorDescarte, baralla, maJugador);
        Sortides.imprimirEstatPartida(maJugador, tauler, anteriorDescarte);

        while(Llegir.volCombinar() && !haGuanyat()) {
            Sortides.imprimirMa(maJugador);
            if(!inserirMaTauler()) {
                Sortides.errorAlCombinar();
            }else { Sortides.combinacioCompletada(); }
        }
        Sortides.imprimirMa(maJugador);
        if(!haGuanyat()){
            descartarCarta();
        }
    }

    protected boolean haGuanyat() {
        Ma<Carta> maJugador = players[torn % NUM_JUGADORS].getMa();
        return maJugador.getNombreCartes() == 0;
    }

    private int recomptePuntsGuanyador() {
        int puntsTotals = 0;
        for (int i = 0; i < NUM_JUGADORS; i++) {
            Ma<Carta> maJugador = players[(torn + i) % NUM_JUGADORS].getMa();
            puntsTotals += calcularPunts(maJugador);
        }
        return puntsTotals;
    }

    private void jugarMa () {
        baralla.mesclarBaralla();
        repartirMaInicialJugadors();

        while (!haGuanyat()) {
            tocaTorn();
            if (haGuanyat()) {
                int punts = recomptePuntsGuanyador();
                players[torn % NUM_JUGADORS].sumarPuntuacio(punts);
                Sortides.imprimirGuanyadorMa(players[torn % NUM_JUGADORS], punts);
                break;
            }
            torn++;
        }
        Sortides.imprimirTaulerPunts(players);
    }

    public void jugarPartida () {
        iniciarJugadors();

        while (!hiHaAlgu101()) {
            jugarMa();
            reiniciarPartidaMa();
        }
        Sortides.imprimirGuanyadorTotal(jugadorMesPunts());
    }

    private Jugador jugadorMesPunts() {
        Jugador jugMesPunts = null;
        int puntuacio = 0;
        for (Jugador j: players) {
            if (j.getPuntuacio() > puntuacio) {
                jugMesPunts = j;
                puntuacio = j.getPuntuacio();
            }
        }
        return jugMesPunts;
    }

    private boolean hiHaAlgu101() {
        for (Jugador j : players) {
            if (j.getPuntuacio() >= 101) {
                return true;
            }
        }
        return false;
    }

    private int calcularPunts(Ma<Carta> ma) {
        int punts = 0;
        for (int i = 0; i < ma.getNombreCartes(); i++) {
            punts += ma.getCarta(i).getNombre();
        }
        return punts;
    }


    private void reiniciarPartidaMa () {
        this.torn = 0;
        this.baralla = new Baralla();
        iniciar();
        this.anteriorDescarte = null;
    }
}
