package Principi.Reptes.Rummy;

public class Joc {

    private final int NUM_JUGADORS;
    private Jugador[] players;
    private Baralla baralla = new Baralla();
    private Carta anteriorDescarte;
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

    // Mètode per crear ma per introduir al tauler

    private void tocaTorn (int torn) {
        System.out.println("Torn del jugador " + players[torn % NUM_JUGADORS]);
        Ma maJugador = players[torn % NUM_JUGADORS].getMa();

        if (torn != 1 && Llegir.agafarDescarteJugador()) {
            maJugador.afegirCarta(anteriorDescarte);
        } else {
            maJugador.afegirCarta(baralla.extreureCarta());
        }

        if (Llegir.volCombinar()) {
            // Metode introduir a tauler
        }
    }

}
