package Principi.Reptes.Rummy;

import java.io.Serializable;

public class Carta implements Serializable {

    private final int NOMBRE;
    private final Pal PAL;

    public Carta (int nombre, Pal pal) {
        this.NOMBRE = nombre;
        this.PAL = pal;
    }

    public int getNombre() {
        return this.NOMBRE;
    }

    public Pal getPal () {
        return this.PAL;
    }

    public String toString(){
        if(PAL != Pal.Comodi){
            return "["+NOMBRE+PAL.getDescripcio()+"]";
        }else {
            return "["+PAL.getDescripcio()+"]";
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof Carta) {
            Carta c = (Carta) obj;
            if (c.getNombre() == this.NOMBRE && c.getPal() == this.PAL) {
                return true;
            }
        }
        return false;
    }

    public int getValorCarta () {
        if (PAL == Pal.Comodi) { return 50; }
        else if (NOMBRE == 2) { return 20; }
        else if (NOMBRE == 1) { return 15; }
        else if (NOMBRE >= 8) { return 10; }
        else { return 5; }
    }
}

