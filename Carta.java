package Principi.Reptes.Rummy;

public class Carta {

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
}

