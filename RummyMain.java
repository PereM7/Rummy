package Principi.Reptes.Rummy;

import Principi.Reptes.Rummy.Jocs.RummyBasic;

public class RummyMain {
    public static void main(String[] args) {
        RummyBasic basic = new RummyBasic();

        basic.jugarPartida();

        //Rummy:
        // --L'AS no pot fer escala després del rei.
        // --No es tocaria poder elegir la mateixa carta a l'hora de combinar.

    }
}
