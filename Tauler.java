package Principi.Reptes.Rummy;

import java.util.ArrayList;

public class Tauler {

    private ArrayList<Ma> grupsCartes = new ArrayList<Ma>();

    public Tauler () {}

    public boolean afegirGrup (Ma ma) {
        if (esUnGrupCorrecte(ma)) {
            this.grupsCartes.add(ma);
            return true;
        }
        return false;
    }

    private boolean esUnGrupCorrecte (Ma ma) {
        return false;
    }

}
