package Principi.Reptes.Rummy.ES;

import Principi.Reptes.Rummy.*;

public class Sortides {

    public static void imprimirTauler(Tauler t) {
        System.out.println(t.toString());
    }

    public static void imprimirMa(Ma<Carta> m) {
        System.out.println(m.toString());
    }

    public static void imprimirMaFitxes(Ma<Fitxa> m) {
        System.out.println(m.toString());
    }

    public static void imprimirDescarte(Carta c) {
        System.out.println("Carta descarte: "+ (c != null ? c.toString() : "Cap"));
    }

    public static void imprimirTorn(int torn, Jugador[] players) {
        System.out.println("Torn del jugador " + players[torn % players.length].getNom());
    }

    public static void imprimirEstatRummikub (Ma<Fitxa> ma, Tauler tauler) {
        System.out.println("Tauler Actual:");
        if (tauler.getNombreGrups() == 0) {
            System.out.println("[ El tauler està buit ]");
        } else {
            imprimirTauler(tauler);
        }
        System.out.println();
        System.out.print("Ma actual: ");
        imprimirMaFitxes(ma);
        System.out.println();
    }

    public static void imprimirEstatPartida (Ma<Carta> ma, Tauler tauler, Carta descarte) {
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

    public static void errorBarallaBuida () {
        System.out.println("La baralla és buida, no es pot agafar carta.");
    }

    public static void introduirMaGin () {
        System.out.println("Per verificar ara has d'agrupar les cartes de la teva ma:");
    }

    public static void errorAlKnock () {
        System.out.println("Error al fer knock, les cartes restants sumen més de 10 punts.");
    }

    public static void descartarMaKnock () {
        System.out.println("Introdueix els grups formats:");
    }

    public static void ginCorrecte () {
        System.out.println("Gin correcte!!!");
    }

    public static void errorMateixCartaDescarte () {
        System.out.println("Error, és la mateixa carta que has agafat del descarte.");
    }

    public static void errorPrimeraMa () {
        System.out.println("Error, la primera mà que s'insereix ha de superar els 30 punts.");
    }

    public static void noHaverInserit () {
        System.out.println("No has inserit cap grup o fitxa.");
        System.out.println("Agafant fitxa...");
    }
}
