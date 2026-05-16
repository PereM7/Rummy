package Principi.Reptes.Rummy.Jocs;

import Principi.Reptes.Rummy.*;
import Principi.Reptes.Rummy.ES.Llegir;
import Principi.Reptes.Rummy.ES.Sortides;
import Principi.Reptes.Rummy.Validacions.ValidacioEstandar;

public class GinRummy extends JocBase{

    private Baralla baralla = new BarallaAnglesa();
    private Carta anteriorDescarte;
    private Tauler taulerKnock;
    private Tauler taulerDescartes;
    private int MAX_CARTES_COMBINAR = 10;

    public GinRummy () {
        super(2);
        iniciar();
    }

    protected void iniciar () {
        taulerKnock = new Tauler(new ValidacioEstandar());
        taulerDescartes = new Tauler(new ValidacioEstandar());
    }

    protected Ma extreureMa () {
        int nombreCartesMaInicial = 10;
        Ma maExtreta = new Ma();

        for (int i = 0; i < nombreCartesMaInicial; i++) {
            maExtreta.afegirCarta(baralla.extreureCarta());
        }
        return maExtreta;
    }

    private void iniciarCartaDescarte () {
        anteriorDescarte = baralla.extreureCarta();
    }

    private boolean descartarCarta () {
        Ma maJugador = players[torn % NUM_JUGADORS].getMa();
        int tamany = maJugador.getNombreCartes() - 1;
        int indexDescarte = Llegir.demanarCartaDescartar(tamany);

        if (maJugador.getCarta(indexDescarte).equals(anteriorDescarte)) {
            return false;
        }
        anteriorDescarte = maJugador.getCarta(indexDescarte);
        maJugador.eliminarCarta(indexDescarte);
        return true;
    }

    protected void tocaTorn () {
        Sortides.imprimirTorn(torn, players);
        Jugador jugActual = players[torn % NUM_JUGADORS];
        Ma maJugador = jugActual.getMa();

        Sortides.imprimirDescarte(anteriorDescarte);
        Sortides.imprimirMa(maJugador);
        robarCarta(anteriorDescarte, baralla, maJugador);

        Sortides.imprimirMa(maJugador);
        while(!descartarCarta()) {
            Sortides.errorMateixCartaDescarte();
        }
    }

    private boolean inserirMaTauler (Tauler tauler) {
        Ma maCombinada = cartesCombinar(MAX_CARTES_COMBINAR);
        if (maCombinada.getNombreCartes() == 1) {
            int indexGrup = Llegir.demanarIndexGrupInserir(tauler.getNombreGrups());
            if (!tauler.afegirCarta(maCombinada.getCarta(0), indexGrup)){
                return false;
            }
        }
        else if (maCombinada.getNombreCartes() != 0) {
            if (!tauler.afegirGrup(maCombinada)) {
                return false;
            }
        }
        eliminarCartesMaJugador(maCombinada);
        return true;
    }

    private boolean desglosarMa (Tauler tauler) {
        boolean bandera = false;
        do {
            if (!inserirMaTauler(tauler)) {
                Sortides.errorAlCombinar();
                return false;
            } else { Sortides.combinacioCompletada(); }

            if (players[torn % NUM_JUGADORS].getMa().getNombreCartes() == 0) {
                break;
            }
            bandera = Llegir.demanarSeguirInserint();
        }while(bandera);
        return true;
    }

    private boolean realitzarKnock () {
        Ma copiMaJugador = players[torn % NUM_JUGADORS].getMa().copiarMa();
        Sortides.introduirMaGin();

        Ma maJugador = players[torn % NUM_JUGADORS].getMa();
        if (!desglosarMa(taulerKnock) || maJugador.getPuntsGin() > 10) {
            players[torn % NUM_JUGADORS].setMa(copiMaJugador);
            taulerKnock = new Tauler(new ValidacioEstandar());
            Sortides.errorAlKnock();
            return false;
        }
        return true;
    }

    private boolean haPerdutDefensaKnock () {
        Ma maDefensa = players[torn % NUM_JUGADORS].getMa();
        Ma maAtac = players[(torn + 1) % NUM_JUGADORS].getMa();
        Sortides.imprimirTorn((torn + 1), players);
        Sortides.descartarMaKnock();
        desglosarMa(taulerDescartes);
        while (Llegir.demanarInserirCartaGrup() && maDefensa.getNombreCartes() > 0) {
            int indexGrup = Llegir.demanarIndexGrupInserir(taulerKnock.getNombreGrups());
            int indexCarta = Llegir.demanarIndexCartaInserir(taulerKnock.getNombreGrups());
            if (taulerKnock.afegirCarta(maDefensa.getCarta(indexCarta), indexGrup)){
                maDefensa.eliminarCarta(indexCarta);
            }
        }
        return maDefensa.getPuntsGin() < maAtac.getPunts();
    }

    private boolean realitzarGin () {
        Ma copiMaJugador = players[torn % NUM_JUGADORS].getMa().copiarMa();
        Sortides.introduirMaGin();

        Ma maJugador = players[torn % NUM_JUGADORS].getMa();
        if (!desglosarMa(taulerKnock) || maJugador.getNombreCartes() > 0) {
            players[torn % NUM_JUGADORS].setMa(copiMaJugador);
            taulerKnock = new Tauler(new ValidacioEstandar());
            return false;
        }
        Sortides.ginCorrecte();
        return true;
    }

    private int bonusFiMaGin (boolean haFetGin) {
        int sumabonus = 20;
        return haFetGin ? sumabonus : 0;
    }

    private int bonusFiMaKnock (boolean haFetContra) {
        int sumabonus = 10;
        return haFetContra ? sumabonus : 0;
    }

    private int recomptePuntsGuanyador() {
        int puntsTotals = 0;
            Ma maJugador = players[torn % NUM_JUGADORS].getMa();
            Ma maContrari = players[(torn + 1) % NUM_JUGADORS].getMa();

            puntsTotals = maContrari.getPuntsGin() - maJugador.getPuntsGin();

        return puntsTotals;
    }

    protected boolean haGuanyat () {
        for (Jugador j : players) {
            if (j.getPuntuacio() >= 100) {
                return true;
            }
        }
        return false;
    }

    private void jugarMa () {
        Jugador jugActual = null;
        baralla.mesclarBaralla();
        repartirMaInicialJugadors();
        iniciarCartaDescarte();

        while(true) {
            jugActual = players[torn % NUM_JUGADORS];
            tocaTorn();

            if (Llegir.demanarSiVolGinKnock()) {
                if (Llegir.demanarGinQueFer() == 1) {
                    if (realitzarGin()) {
                        jugActual.sumarPuntuacio(recomptePuntsGuanyador() + bonusFiMaGin(true));
                        break;
                    }
                }
                else {
                    if (realitzarKnock()) {
                        torn++;
                        if (haPerdutDefensaKnock()) {
                            jugActual.sumarPuntuacio(recomptePuntsGuanyador());
                            break;
                        } else {
                            jugActual.sumarPuntuacio(recomptePuntsGuanyador() + bonusFiMaKnock(true));
                        }
                    }
                }
            }
            torn ++;
        }
        Sortides.imprimirGuanyadorMa(jugActual, jugActual.getPuntuacio());
        Sortides.imprimirCalculantPunts();
        Sortides.imprimirTaulerPunts(players);
    }

    private void reiniciarPartidaMa() {
        this.torn = 0;
        this.baralla = new BarallaAnglesa();
        iniciar();
        this.anteriorDescarte = null;
    }

    public void jugarPartida () {
        iniciarJugadors();
        do {
            jugarMa();
            reiniciarPartidaMa();
        }while(!haGuanyat());
    }


}
