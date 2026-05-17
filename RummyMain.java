package Principi.Reptes.Rummy;

import Principi.Reptes.Rummy.ES.Llegir;
import Principi.Reptes.Rummy.ES.Sortides;
import Principi.Reptes.Rummy.Jocs.*;

import java.util.ArrayList;

public class RummyMain {
    public static void main(String[] args) {
        int opcio = Llegir.demanarOpcioInicial();
        JocBase joc;

        if (opcio == 2) {
            ArrayList<String> partides = GestorPartides.llistarPartides();
            if (partides.isEmpty()) {
                System.out.println("No hi ha partides desades.");
                return;
            }
            String nom = Llegir.demanarNomPartida(partides);
            joc = GestorPartides.carregarPartida(nom);
        } else {
            int tipus = Llegir.demanarTipusJoc();
            joc = switch (tipus) {
                case 1 -> new RummyBasic();
                case 2 -> new GinRummy();
                case 3 -> new RummyArgenti();
                case 4 -> new Rummikub();
                default -> new RummyBasic();
            };
        }
        joc.jugarPartida();
    }
}
