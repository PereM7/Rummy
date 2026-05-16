package Principi.Reptes.Rummy;

import Principi.Reptes.Rummy.Jocs.GinRummy;
import Principi.Reptes.Rummy.Jocs.JocBase;
import Principi.Reptes.Rummy.Jocs.RummyArgenti;
import Principi.Reptes.Rummy.Jocs.RummyBasic;

public class RummyMain {
    public static void main(String[] args) {
        JocBase basic = new GinRummy();

        basic.jugarPartida();

        //Rummy:
        // --L'AS no pot fer escala després del rei.
        // --No es tocaria poder elegir la mateixa carta a l'hora de combinar.
        // --No es pot treure el comodí

        //RummyArgentí:
        // --Quan no queden cartes a la baralla no reposta.
        // --Per superar el estar en llei es necessita fer una combinació de 100 punts
        // --No es pot treure el comodí
    }
}
