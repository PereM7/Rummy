package Principi.Reptes.Rummy;

import java.io.Serializable;

public class Fitxa implements Serializable {
    private final int NOMBRE;
    private final Color COLOR;

    public Fitxa (int nombre, Color color) {
        this.NOMBRE = nombre;
        this.COLOR = color;
    }

    public int getNombre() {
        return this.NOMBRE;
    }
    public Color getColor () {
        return this.COLOR;
    }

    public String toString(){
        if(COLOR != Color.Comodi){
            return "["+NOMBRE+COLOR.getDescripcio()+"]";
        }else {
            return "["+COLOR.getDescripcio()+"]";
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof Fitxa) {
            Fitxa c = (Fitxa) obj;
            if (c.getNombre() == this.NOMBRE && c.getColor() == this.COLOR) {
                return true;
            }
        }
        return false;
    }
}
