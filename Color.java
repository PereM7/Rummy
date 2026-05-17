package Principi.Reptes.Rummy;

public enum Color {
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
