package Principi.Reptes.Rummy;

public enum Pal {
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
