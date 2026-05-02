package Principi.Reptes.Rummy;

public class Joc {

    private final int NUM_JUGADORS;
    private Jugador[] players;
    private Baralla baralla = new Baralla();
    private int torn = 0;

    public Joc (int numJugadors) {
        this.NUM_JUGADORS = numJugadors;
        players = new Jugador[NUM_JUGADORS];
        baralla.mesclarBaralla();
    }

    private void iniciarJugadors () {
        for (int i = 0; i < NUM_JUGADORS; i++) {
            //players[i] = new Jugador(*DEMANAR NOM*);
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

    private void tocaTorn () {

        //demanar que fer
    }

}
