package Principi.Reptes.Rummy.Jocs;

import Principi.Reptes.Rummy.Baralla;
import Principi.Reptes.Rummy.Carta;
import Principi.Reptes.Rummy.ES.Llegir;
import Principi.Reptes.Rummy.ES.Sortides;
import Principi.Reptes.Rummy.Ma;
import Principi.Reptes.Rummy.Tauler;
import Principi.Reptes.Rummy.Validacions.ValidacioArgenti;


public class RummyArgenti extends JocBase{

    private Baralla baralla = new Baralla();
    private Carta anteriorDescarte;
    private Tauler tauler;
    private final int MAX_CARTES_COMBINAR = 11;

    public RummyArgenti () {
        super();
        iniciar();
    }

    protected void iniciar () {
        tauler = new Tauler(new ValidacioArgenti());
    }

    protected Ma extreureMa () {
        int nombreCartesMaInicial = 9;
        Ma maExtreta = new Ma();

        for (int i = 0; i < nombreCartesMaInicial; i++) {
            maExtreta.afegirCarta(baralla.extreureCarta());
        }
        return maExtreta;
    }

    private void iniciarCartaDescarte () {
        anteriorDescarte = baralla.extreureCarta();
    }


    private boolean inserirMaTauler () {
        Ma maCombinada = cartesCombinar(MAX_CARTES_COMBINAR);
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

    private void descartarCarta () {
        Ma maJugador = players[torn % NUM_JUGADORS].getMa();
        int tamany = maJugador.getNombreCartes();
        int indexDescarte = Llegir.demanarCartaDescartar(tamany);

        anteriorDescarte = maJugador.getCarta(indexDescarte);
        maJugador.eliminarCarta(indexDescarte);
    }

    protected void tocaTorn () {
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

    //Canviar
    protected boolean haGuanyat() { return true; }
    public void jugarPartida () {}
}
