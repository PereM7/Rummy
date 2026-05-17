package Principi.Reptes.Rummy.Jocs;

import Principi.Reptes.Rummy.*;
import Principi.Reptes.Rummy.ES.Llegir;
import Principi.Reptes.Rummy.ES.Sortides;
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

    private boolean inserirPrimeraMa () {
        Tauler<Fitxa> copiTauler = tauler.copiarTauler();
        Ma<Fitxa> copiMa = mansFitxes[torn % NUM_JUGADORS].copiarMa();
        int punts = 0;
        boolean bandera = false;
        do {
            if (!inserirMaTauler()) {
                Sortides.errorAlCombinar();
                tauler.restaurarEstat(copiTauler);
                mansFitxes[torn % NUM_JUGADORS] = copiMa;
                return false;
            } else {
                Ma<Fitxa> maCombinada = tauler.getGrup(tauler.getNombreGrups() - 1);
                punts += calcularPunts(maCombinada);
                Sortides.combinacioCompletada();
            }

            if (mansFitxes[torn % NUM_JUGADORS].getNombreCartes() == 0) {
                break;
            }
            bandera = Llegir.demanarSeguirInserint();
        }while(bandera);
        if (punts < 30) {
            tauler.restaurarEstat(copiTauler);
            mansFitxes[torn % NUM_JUGADORS] = copiMa;
            return false;
        }
        return true;
    }

    protected void tocaTorn () {
        Sortides.imprimirTorn(torn, players);
        Jugador jugActual = players[torn % NUM_JUGADORS];
        Ma<Fitxa> maJugador = mansFitxes[torn % NUM_JUGADORS];
        boolean haInserit = false;

        Sortides.imprimirEstatRummikub(maJugador, tauler);
        if (!jugActual.getHaJugatPrimeraMa()) {
            if (!inserirPrimeraMa()) {
                Sortides.errorPrimeraMa();
                maJugador.afegirCarta(bossa.extreureFitxa());
            } else {
                haInserit = true;
                jugActual.setHaJugatPrimeraMa(true);
                Sortides.combinacioCompletada();
            }
        } else {
            Tauler<Fitxa> copiTauler = tauler.copiarTauler();
            Ma<Fitxa> copiMa = mansFitxes[torn % NUM_JUGADORS].copiarMa();
            int valor;
            do {
                Sortides.imprimirEstatRummikub(maJugador, tauler);
                valor = Llegir.demanarQueFerRummikub();

                switch(valor) {
                    case 1 -> {
                        int indexFitxa = Llegir.demanarCartaCombinar(maJugador.getNombreCartes() - 1);
                        int indexGrup  = Llegir.demanarIndexGrupInserir(tauler.getNombreGrups() - 1);
                        tauler.getGrup(indexGrup).afegirCarta(maJugador.getCarta(indexFitxa));
                        maJugador.eliminarCarta(indexFitxa);
                        haInserit = true;
                    }
                    case 2 -> {
                        if (inserirMaTauler()) {
                            haInserit = true;
                            Sortides.combinacioCompletada();
                        } else {
                            Sortides.errorAlCombinar();
                        }
                    }
                    case 3 -> {
                        Sortides.imprimirOnTreureFitxa();
                        int grupOrigen = Llegir.demanarIndexGrupInserir(tauler.getNombreGrups() - 1);
                        int indexFitxa = Llegir.demanarCartaCombinar(tauler.getGrup(grupOrigen).getNombreCartes() - 1);

                        Sortides.imprimirOnPosarFitxa();
                        int grupDesti  = Llegir.demanarIndexGrupInserir(tauler.getNombreGrups() - 1);
                        Fitxa fitxa = tauler.extreureFitxaGrup(grupOrigen, indexFitxa);
                        tauler.afegirCarta(fitxa, grupDesti);
                    }
                }
            } while (valor != 0 && !haGuanyat());


            if (!haInserit) {
                Sortides.noHaverInserit();
                maJugador.afegirCarta(bossa.extreureFitxa());
            }
            else if (!tauler.verificarEstat()) {
                Sortides.errorTaulerInvalid();
                tauler.restaurarEstat(copiTauler);
                mansFitxes[torn % NUM_JUGADORS] = copiMa;
                maJugador.afegirCarta(bossa.extreureFitxa());
            }
        }

    }

    protected boolean haGuanyat() {
        return mansFitxes[torn % NUM_JUGADORS].getNombreCartes() == 0;
    }

    private boolean haGuanyatPartida() {
        for (Jugador j : players) {
            if (j.getPuntuacio() >= 100) return true;
        }
        return false;
    }

    private void reiniciarPartidaMa () {
        this.torn = 0;
        this.bossa = new Bossa();
        iniciar();
    }

    public void jugarMa() {
        bossa.mesclarBossa();
        repartirMaInicialJugadors();

        while (!haGuanyat()) {
            tocaTorn();
            if (haGuanyat()) {
                int punts = recomptePuntsGuanyador();
                players[torn % NUM_JUGADORS].sumarPuntuacio(punts);
                restarPuntsPerdedors();
                Sortides.imprimirGuanyadorMa(players[torn % NUM_JUGADORS], punts);
                break;
            }
            torn++;
        }
        Sortides.imprimirTaulerPunts(players);
    }

    public void jugarPartida () {
        iniciarJugadors();
        do {
            jugarMa();
            reiniciarPartidaMa();
        }while(!haGuanyatPartida());
        Sortides.imprimirGuanyadorTotal(jugadorMesPunts());
    }


    private int calcularPunts(Ma<Fitxa> ma) {
        int punts = 0;
        for (int i = 0; i < ma.getNombreCartes(); i++) {
            if (ma.getCarta(i).getColor() == Color.Comodi) {
                punts += 50;
            } else {
                punts += ma.getCarta(i).getNombre();
            }
        }
        return punts;
    }

    private int recomptePuntsGuanyador() {
        int puntsTotals = 0;
        for (int i = 0; i < NUM_JUGADORS; i++) {
            Ma<Fitxa> maJugador = mansFitxes[i];
            puntsTotals += calcularPunts(maJugador);
        }
        return puntsTotals;
    }

    private void restarPuntsPerdedors () {
        int indexGuanyador = (torn % NUM_JUGADORS);
        for (int i = 0; i < NUM_JUGADORS; i++) {
            Ma<Fitxa> maJugador = mansFitxes[(torn + i) % NUM_JUGADORS];
            if (i != indexGuanyador) {
                players[i].restarPuntuacio(calcularPunts(maJugador));
            }
        }
    }

    private Jugador jugadorMesPunts() {
        Jugador millor = players[0];
        for (Jugador j : players) {
            if (j.getPuntuacio() > millor.getPuntuacio()) millor = j;
        }
        return millor;
    }
}
