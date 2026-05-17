package Principi.Reptes.Rummy;

import java.util.ArrayList;
import java.util.Collections;

public class Bossa {
    private ArrayList<Fitxa> bossa = new ArrayList<>();

    public Bossa () {
        iniciarBossa();
        mesclarBossa();
    }

    private void iniciarBossa () {
        iniciarColor(Color.Vermell);
        iniciarColor(Color.Blau);
        iniciarColor(Color.Grog);
        iniciarColor(Color.Negre);
        iniciarColor(Color.Comodi);
    }

    protected void iniciarColor (Color color) {
        int nombreCartes = 13;
        if (color == Color.Comodi) {
            nombreCartes = 1;
        }

        for (int x = 2; x > 0; x--) {
            for (int i = 1; i <= nombreCartes; i++) {
                this.bossa.add(new Fitxa(i, color));
            }
        }
    }

    public Fitxa extreureCarta () {
        Fitxa f = bossa.getLast();
        bossa.removeLast();
        return f;
    }

    public void mesclarBossa () {
        Collections.shuffle(bossa);
    }

    public boolean esBuida () {
        return bossa.isEmpty();
    }
}
