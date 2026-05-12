package Principi.Reptes.Rummy.Jocs;

import Principi.Reptes.Rummy.ES.Llegir;
import Principi.Reptes.Rummy.Jugador;
import Principi.Reptes.Rummy.Ma;

public abstract class JocBase {

    protected final int NUM_JUGADORS;
    protected Jugador[] players;
    protected int torn = 0;

    protected JocBase () {
        this.NUM_JUGADORS = Llegir.demanarNombreJugadors();
        players = new Jugador[NUM_JUGADORS];
    }

    protected void iniciarJugadors () {
        for (int i = 0; i < NUM_JUGADORS; i++) {
            players[i] = new Jugador(Llegir.demanarNom());
        }
    }

    protected abstract Ma extreureMa ();
    protected void repartirMaInicialJugadors () {
        for (int i = 0; i < NUM_JUGADORS; i++) {
            players[i].setMa(extreureMa());
        }
    }

    protected abstract void iniciar();
    protected abstract void tocaTorn ();
    protected abstract boolean haGuanyat();
    public abstract void jugarPartida ();

}
