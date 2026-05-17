package Principi.Reptes.Rummy;

import java.io.Serializable;

public enum Pal implements Serializable {
    Diamant("D"),
    Pica("P"),
    Cor("C"),
    Trevol("T"),
    Comodi("Comodi");

    private final String DESCRIPCIO;

    private Pal(String descripcio){
        this.DESCRIPCIO = descripcio;
    }

    public String getDescripcio(){
        return this.DESCRIPCIO;
    }
}
