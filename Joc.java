package Principi.Reptes.Rummy;

public class Joc {

    private final int NUM_JUGADORS;
    private Jugador[] players;
    private Baralla baralla = new Baralla();
    private Carta anteriorDescarte;
    private Tauler tauler = new Tauler();
    private int torn = 0;

    public Joc () {
        this.NUM_JUGADORS = Llegir.demanarNombreJugadors();
        players = new Jugador[NUM_JUGADORS];
    }

    private void iniciarJugadors () {
        for (int i = 0; i < NUM_JUGADORS; i++) {
            players[i] = new Jugador(Llegir.demanarNom());
        }
    }

    private Ma extreureMa () {
        int nombreCartesMaInicial = 14;
        Ma maExtreta = new Ma();

        for (int i = 1; i < nombreCartesMaInicial; i++) {
            maExtreta.afegirCarta(baralla.extreureCarta());
        }
        return maExtreta;
    }

    private void repartirMaInicialJugadors () {
        for (int i = 0; i < NUM_JUGADORS; i++) {
            players[i].setMa(extreureMa());
        }
    }

    private void descartarCarta () {
        Ma maJugador = players[torn % NUM_JUGADORS].getMa();
        int tamany = maJugador.getNombreCartes();
        int indexDescarte = Llegir.demanarCartaDescartar(tamany);

        anteriorDescarte = maJugador.getCarta(indexDescarte);
        maJugador.eliminarCarta(indexDescarte);
    }

    private Ma cartesCombinar () {
        Ma maJugador = players[torn % NUM_JUGADORS].getMa();
        Ma maCombinada = new Ma();
        int index;
        do {
            index = Llegir.demanarCartaCombinar(maJugador.getNombreCartes() - 1);
            if (index != -1) {
                maCombinada.afegirCarta(maJugador.getCarta(index));
            }

            if (maCombinada.getNombreCartes() == 4) {
                System.out.println("Maxim cartes seleccionades.");
                break;
            }
        }while(index != -1);
    return maCombinada;
    }

    private void eliminarCartesMaJugador(Ma ma) {
        Ma maJugador = players[torn % NUM_JUGADORS].getMa();

        for (int i = 0; i < ma.getNombreCartes(); i++) {
            for (int j = 0; j < maJugador.getNombreCartes(); j++) {
                if (maJugador.getCarta(j).equals(ma.getCarta(i))) {
                    maJugador.eliminarCarta(j);
                }
            }
        }
    }

    private boolean inserirMaTauler () {
        Ma maCombinada = cartesCombinar();
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

    private void tocaTorn () {
        Sortides.imprimirTorn(torn, players);
        Ma maJugador = players[torn % NUM_JUGADORS].getMa();
        Sortides.imprimirEstatPartida(maJugador, tauler, anteriorDescarte);

        if (Llegir.agafarDescarteJugador()) {
            if (anteriorDescarte != null) {
                maJugador.afegirCarta(anteriorDescarte);
            } else {
                Sortides.errorCartaDescarteBuida();
                maJugador.afegirCarta(baralla.extreureCarta());
            }
        } else {
            maJugador.afegirCarta(baralla.extreureCarta());
        }
        Sortides.imprimirEstatPartida(maJugador, tauler, anteriorDescarte);

        while(Llegir.volCombinar() && !haGuanyat()) {
            if(!inserirMaTauler()) {
                Sortides.errorAlCombinar();
            }else { Sortides.combinacioCompletada(); }
        }
        Sortides.imprimirMa(maJugador);
        if(!haGuanyat()){
            descartarCarta();
        }
    }

    private boolean haGuanyat() {
        Ma maJugador = players[torn % NUM_JUGADORS].getMa();
        return maJugador.getNombreCartes() == 0;
    }

    private int recomptePuntsGuanyador() {
        int puntsTotals = 0;
        for (int i = 0; i < NUM_JUGADORS; i++) {
            Ma maJugador = players[(torn + i) % NUM_JUGADORS].getMa();
            puntsTotals += maJugador.getPunts();
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
        Sortides.imprimirGuanyadorTotal(players[torn % NUM_JUGADORS]);
    }

    private boolean hiHaAlgu101() {
        for (Jugador j : players) {
            if (j.getPuntuacio() >= 101) {
                return true;
            }
        }
        return false;
    }

    private void reiniciarPartidaMa () {
        this.torn = 0;
        this.baralla = new Baralla();
        baralla.mesclarBaralla();
        this.tauler = new Tauler();
        this.anteriorDescarte = null;
    }
}
