package Principi.Reptes.Rummy;

public class Joc {

    private final int NUM_JUGADORS;
    private Jugador[] players;
    private Baralla baralla = new Baralla();
    private Carta anteriorDescarte;
    private Tauler tauler = new Tauler();
    private int torn = 0;

    public Joc (int numJugadors) {
        this.NUM_JUGADORS = numJugadors;
        players = new Jugador[NUM_JUGADORS];
        baralla.mesclarBaralla();
    }

    private void iniciarJugadors () {
        for (int i = 0; i < NUM_JUGADORS; i++) {
            players[i] = new Jugador(Llegir.demanarNom());
        }
    }

    private Ma extreureMa () {
        int nombreCartesMaInicial = 14;
        Ma maExtreta = new Ma();

        for (int i = 0; i < nombreCartesMaInicial; i++) {
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
        int contador = 1;
        do {
            index = Llegir.demanarCartaCombinar(maJugador.getNombreCartes());
            if (index != -1) {
                maCombinada.afegirCarta(maJugador.getCarta(index));
            } else { contador++; }

            if (contador == 4) {
                System.out.println("Maxim cartes seleccionades.");
                break;
            }
        }while(index == -1);
    return maCombinada;
    }

    private boolean inserirMaTauler () {
        Ma maCombinada = cartesCombinar();
        if (maCombinada.getNombreCartes() == 1) {
            int indexGrup = Llegir.demanarIndexGrupInserir(tauler.getNombreGrups());
            tauler.afegirCarta(maCombinada.getCarta(0), indexGrup);
            return true;
        }
        else if (maCombinada.getNombreCartes() != 0) {
            tauler.afegirGrup(maCombinada);
            return true;
        }
        return false;
    }

    private void tocaTorn (int torn) {
        System.out.println("Torn del jugador " + players[torn % NUM_JUGADORS]);
        Ma maJugador = players[torn % NUM_JUGADORS].getMa();

        if (torn != 1 && Llegir.agafarDescarteJugador()) {
            maJugador.afegirCarta(anteriorDescarte);
        } else {
            maJugador.afegirCarta(baralla.extreureCarta());
        }

        while(Llegir.volCombinar()) {
            if(!inserirMaTauler()) {
                System.out.println("Error, no s'ha seleccionat cap carta.");
            }
        }
        descartarCarta();
    }

}
