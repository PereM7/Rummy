package Principi.Reptes.Rummy;

import java.io.Serializable;

public enum Color implements Serializable {
    Vermell("V"),
    Blau("B"),
    Grog("G"),
    Negre("N"),
    Comodi("Comodi");

    private final String DESCRIPCIO;

    Color (String descripcio) {
        this.DESCRIPCIO = descripcio;
    }

    public String getDescripcio() {
        return DESCRIPCIO;
    }
}
