package Principi.Reptes.Rummy.Jocs;

import Principi.Reptes.Rummy.Baralla;
import Principi.Reptes.Rummy.Carta;
import Principi.Reptes.Rummy.ES.Llegir;
import Principi.Reptes.Rummy.ES.Sortides;
import Principi.Reptes.Rummy.Jugador;
import Principi.Reptes.Rummy.Ma;

public abstract class JocBase {

    protected int NUM_JUGADORS;
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

    protected Ma cartesCombinar (int cartesMaxim) {
        Ma maJugador = players[torn % NUM_JUGADORS].getMa();
        Ma maCombinada = new Ma();
        int index;
        do {
            index = Llegir.demanarCartaCombinar(maJugador.getNombreCartes() - 1);
            if (index != -1) {
                maCombinada.afegirCarta(maJugador.getCarta(index));
            }

            if (maCombinada.getNombreCartes() == cartesMaxim) {
                System.out.println("Maxim cartes seleccionades.");
                break;
            }

        }while(index != -1);
        return maCombinada;
    }

    public void eliminarCartesMaJugador(Ma ma) {
        Ma maJugador = players[torn % NUM_JUGADORS].getMa();

        for (int i = ma.getNombreCartes() - 1; i >= 0; i--) {
            for (int j = 0; j < maJugador.getNombreCartes(); j++) {
                if (maJugador.getCarta(j).equals(ma.getCarta(i))) {
                    maJugador.eliminarCarta(j);
                    break;
                }
            }
        }
    }

    protected void robarCarta (Carta anteriorDescarte, Baralla baralla, Ma maJugador) {
        if (Llegir.agafarDescarteJugador()) {
            if (anteriorDescarte != null) {
                maJugador.afegirCarta(anteriorDescarte);
            } else {
                Sortides.errorCartaDescarteBuida();
                if (!baralla.esBuida()){
                    maJugador.afegirCarta(baralla.extreureCarta());
                } else { Sortides.errorBarallaBuida(); }
            }
        } else {
            if (!baralla.esBuida()){
                maJugador.afegirCarta(baralla.extreureCarta());
            } else { Sortides.errorBarallaBuida(); }
        }
    }

    protected abstract void iniciar();
    protected abstract void tocaTorn ();
    protected abstract boolean haGuanyat();
    public abstract void jugarPartida ();

}
