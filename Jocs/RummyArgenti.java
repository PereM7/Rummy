package Principi.Reptes.Rummy.Jocs;

import Principi.Reptes.Rummy.*;
import Principi.Reptes.Rummy.ES.Llegir;
import Principi.Reptes.Rummy.ES.Sortides;
import Principi.Reptes.Rummy.Validacions.ValidacioArgenti;


public class RummyArgenti extends JocBase{

    private Baralla baralla = new Baralla();
    private Carta anteriorDescarte;
    private Tauler<Carta> tauler;
    private final int MAX_CARTES_COMBINAR = 13;

    public RummyArgenti () {
        super();
        iniciar();
    }

    protected void iniciar () {
        tauler = new Tauler<>(new ValidacioArgenti());
    }

    protected Ma<Carta> extreureMa () {
        int nombreCartesMaInicial = 12;
        Ma<Carta> maExtreta = new Ma<>();

        for (int i = 0; i < nombreCartesMaInicial; i++) {
            maExtreta.afegirCarta(baralla.extreureCarta());
        }
        return maExtreta;
    }

    private void iniciarCartaDescarte () {
        anteriorDescarte = baralla.extreureCarta();
    }


    private boolean inserirMaTauler () {
        Ma<Carta> maCombinada = cartesCombinar(MAX_CARTES_COMBINAR);
        Jugador jugActual = players[torn % NUM_JUGADORS];

        if ( (!jugActual.getHaJugatPrimeraMa() && jugActual.getEstaEnLlei()) && !maSuperior100Punts(maCombinada) ) {
            Sortides.errorEstarEnLlei();
            return false;
        } else {
            if (maCombinada.getNombreCartes() == 1) {
                int indexGrup = Llegir.demanarIndexGrupInserir(tauler.getNombreGrups());
                if (!tauler.afegirCarta(maCombinada.getCarta(0), indexGrup)){
                    return false;
                }
            }
            else if (maCombinada.getNombreCartes() != 0) {
                if (comprovarEscala12(maCombinada)) { castigPuntsEscala(); }
                if (!tauler.afegirGrup(maCombinada)) {
                    return false;
                }
            }
            sumarPuntsJugadorActual(recomptePunts(maCombinada));
            eliminarCartesMaJugador(maCombinada);
            return true;
        }
    }

    private boolean comprovarEscala12 (Ma<Carta> ma) {
        if(tauler.validador instanceof ValidacioArgenti validacio) {
            return (validacio.sonEscala12(ma));
        }
        return false;
    }


    private void descartarCarta () {
        Ma<Carta> maJugador = players[torn % NUM_JUGADORS].getMa();
        int tamany = maJugador.getNombreCartes();
        int indexDescarte = Llegir.demanarCartaDescartar(tamany);

        anteriorDescarte = maJugador.getCarta(indexDescarte);
        maJugador.eliminarCarta(indexDescarte);
    }

    protected void tocaTorn () {
        Sortides.imprimirTorn(torn, players);
        Jugador jugActual = players[torn % NUM_JUGADORS];
        Ma<Carta> maJugador = jugActual.getMa();
        boolean haInserit = false;
        Sortides.imprimirEstatPartida(maJugador, tauler, anteriorDescarte);

        robarCarta (anteriorDescarte, baralla, maJugador);
        Sortides.imprimirEstatPartida(maJugador, tauler, anteriorDescarte);

        while(Llegir.volCombinar() && !haGuanyat()) {
            Sortides.imprimirMa(maJugador);
            if(!inserirMaTauler()) {
                Sortides.errorAlCombinar();
            }
            else {
                haInserit = true;
                Sortides.combinacioCompletada();
            }
        }

        Sortides.imprimirMa(maJugador);
        if(!haGuanyat()){
            descartarCarta();
            if (haGuanyat()) {
                sumarPuntsJugadorActual(bonusFiMa(false));
            }
        } else if (haGuanyat() && !jugActual.getHaJugatPrimeraMa()){
            sumarPuntsJugadorActual(bonusFiMa(true));
        }

        if (haInserit) { jugActual.setHaJugatPrimeraMa(true); }
    }

    protected boolean haGuanyat() {
        Ma<Carta> maJugador = players[torn % NUM_JUGADORS].getMa();
        return maJugador.getNombreCartes() == 0;
    }

    protected boolean haGuanyatPartida() {
        Jugador guanyador = players[torn % NUM_JUGADORS];
        return guanyador.getPuntuacio() >= 1000;
    }

    private void reiniciarPartidaMa () {
        this.torn = 0;
        this.baralla = new Baralla();
        iniciar();
        this.anteriorDescarte = null;
        reiniciarPrimeraMaJugada();
    }

    private void jugarMa () {
        Jugador jugActual = null;
        baralla.mesclarBaralla();
        repartirMaInicialJugadors();
        iniciarCartaDescarte();

        while(!haGuanyat()) {
            jugActual = players[torn % NUM_JUGADORS];
            tocaTorn();
            if (haGuanyat()) {
                Sortides.imprimirGuanyadorMa(jugActual, jugActual.getPuntuacio());
                break;
            }
            torn++;
        }
        Sortides.imprimirCalculantPunts();
        restarPuntsCartesRestants(jugActual);
        Sortides.imprimirTaulerPunts(players);
    }

    public void jugarPartida () {
        iniciarJugadors();
        do {
            jugarMa();
            assignarJugadorsEnLlei();
            reiniciarPartidaMa();
        }while(!haGuanyatPartida());
    }

    //Sistema punts
    private void sumarPuntsJugadorActual (int punts) {
        Jugador jugActual = players[torn % NUM_JUGADORS];
        jugActual.sumarPuntuacio(punts);
    }

    private void castigPuntsEscala () {
        int puntsRestar = 50;
        for (Jugador j: players) {
            if (j != players[torn % NUM_JUGADORS]) {
                j.restarPuntuacio(puntsRestar);
            }
        }
    }

    private int bonusFiMa(boolean haFinalitzatAdalt) {
        int sumaAdalt = 100;
        int sumaAbaix = 50;
        return haFinalitzatAdalt ? sumaAdalt : sumaAbaix;
    }

    private void assignarJugadorsEnLlei () {
        for (Jugador j: players) {
            if (j.getPuntuacio() >= 700) {
                j.setEstaEnLlei(true);
            }
        }
    }

    private void reiniciarPrimeraMaJugada () {
        for (Jugador j: players) {
           j.setHaJugatPrimeraMa(false);
        }
    }

    private boolean maSuperior100Punts (Ma<Carta> ma) {
        return recomptePunts(ma) >= 100;
    }

    public int recomptePunts (Ma<Carta> ma) {
        int sumaTotal = 0;
        for (int i = 0; i < ma.getNombreCartes(); i++) {
            sumaTotal += ma.getCarta(i).getValorCarta();
        }
        return sumaTotal;
    }

    private void restarPuntsCartesRestants (Jugador guanyador) {
        for (Jugador j: players) {
            if (j != guanyador) {
                j.restarPuntuacio(recomptePunts(j.getMa()));
            }
        }
    }


}
