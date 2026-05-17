package Principi.Reptes.Rummy;

import Principi.Reptes.Rummy.Jocs.*;

public class RummyMain {
    public static void main(String[] args) {
        JocBase basic = new Rummikub();

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
