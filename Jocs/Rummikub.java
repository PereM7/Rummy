package Principi.Reptes.Rummy.Jocs;

import Principi.Reptes.Rummy.*;
import Principi.Reptes.Rummy.ES.Llegir;
import Principi.Reptes.Rummy.Validacions.ValidacioRummikub;

public class Rummikub extends JocBase{

    private Bossa bossa = new Bossa();
    private Tauler<Fitxa> tauler;
    private int MAX_FITXES_COMBINAR = 13;
    private Ma<Fitxa>[] mansFitxes;

    public Rummikub () {
        super();
        iniciar();
        mansFitxes = new Ma[NUM_JUGADORS];
    }

    protected void iniciar () {
        tauler = new Tauler<>(new ValidacioRummikub());
    }
    protected Ma<Carta> extreureMa () { return null; }

    private Ma<Fitxa> extreureMaFitxes() {
        Ma<Fitxa> ma = new Ma<>();
        for (int i = 0; i < 14; i++) {
            ma.afegirCarta(bossa.extreureFitxa());
        }
        return ma;
    }

    protected void repartirMaInicialJugadors () {
        for (int i = 0; i < NUM_JUGADORS; i++) {
            mansFitxes[i] = extreureMaFitxes();
        }
    }

    private Ma<Fitxa> fitxesCombinar () {
        Ma<Fitxa> maJugador = mansFitxes[torn % NUM_JUGADORS];
        Ma<Fitxa> maCombinada = new Ma<>();
        int index;
        do {
            index = Llegir.demanarCartaCombinar(maJugador.getNombreCartes() - 1);
            if (index != -1) {
                maCombinada.afegirCarta(maJugador.getCarta(index));
            }

            if (maCombinada.getNombreCartes() == MAX_FITXES_COMBINAR) {
                System.out.println("Maxim fitxes seleccionades.");
                break;
            }

        }while(index != -1);
        return maCombinada;
    }

    private void eliminarFitxesMaJugador(Ma<Fitxa> ma) {
        Ma<Fitxa> maJugador = mansFitxes[torn % NUM_JUGADORS];

        for (int i = ma.getNombreCartes() - 1; i >= 0; i--) {
            for (int j = 0; j < maJugador.getNombreCartes(); j++) {
                if (maJugador.getCarta(j).equals(ma.getCarta(i))) {
                    maJugador.eliminarCarta(j);
                    break;
                }
            }
        }
    }

    private boolean inserirMaTauler () {
        Ma<Fitxa> maCombinada = fitxesCombinar();
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
        eliminarFitxesMaJugador(maCombinada);
        return true;
    }

    protected void tocaTorn () {}

    protected boolean haGuanyat() { return false; }

    public void jugarPartida () {}

}
