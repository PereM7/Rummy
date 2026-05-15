package Principi.Reptes.Rummy.ES;

import Principi.Reptes.Rummy.Carta;
import Principi.Reptes.Rummy.Jugador;
import Principi.Reptes.Rummy.Ma;
import Principi.Reptes.Rummy.Tauler;

public class Sortides {

    public static void imprimirTauler(Tauler t) {
        System.out.println(t.toString());
    }

    public static void imprimirMa(Ma m) {
        System.out.println(m.toString());
    }

    public static void imprimirTorn(int torn, Jugador[] players) {
        System.out.println("Torn del jugador " + players[torn % players.length].getNom());
    }

    public static void imprimirEstatPartida (Ma ma, Tauler tauler, Carta descarte) {
        System.out.println("Tauler Actual:");
        if (tauler.getNombreGrups() == 0) {
            System.out.println("[ El tauler està buit ]");
        } else {
            imprimirTauler(tauler);
        }
        System.out.println();
        System.out.println("Carta descarte: " + (descarte != null ? descarte.toString() : "Cap"));
        System.out.print("Ma actual: ");
        imprimirMa(ma);
        System.out.println();
    }

    public static void imprimirTaulerPunts (Jugador[] players) {
        System.out.println("Punts dels jugadors:");
        for (Jugador j: players) {
            System.out.println("- " + j.getNom() + ": " + j.getPuntuacio());
        }
    }

    public static void errorAlCombinar() {
        System.out.println("Error, la combinació no és vàlida (mínim 3 cartes del mateix número o escala del mateix pal).");
    }

    public static void combinacioCompletada() {
        System.out.println("Combinació acceptada i afegida al tauler.");
    }

    public static void errorCartaDescarteBuida () {
        System.out.println("Error, no hi ha carta descartada.");
        System.out.println("Agafant carta de la baralla...");
    }

    public static void imprimirGuanyadorMa (Jugador jug, int puntsTotals) {
        System.out.println("Fi de la ma, el jugador " + jug.getNom() + " ha guanyat!!!");
        System.out.println("Punts guanyats en aquesta ronda: " + puntsTotals);
    }

    public static void imprimirGuanyadorTotal (Jugador jug) {
        System.out.println("Fi de la partida, el jugador " + jug.getNom()+ " ha arribat als 101 punts.");
        System.out.println("Enhorabona !!!!");
    }

    public static void errorEstarEnLlei () {
        System.out.println("Error, estas en llei");
        System.out.println("Per poder inserir necessites almenys una mà de 100 punts.");
    }

    public static void imprimirCalculantPunts () {
        System.out.println("Calculant punts de la partida...");
        System.out.println("Restant punts de les cartes restants...");
    }

}
