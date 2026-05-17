package Principi.Reptes.Rummy;

import java.io.Serializable;

public class BarallaAnglesa extends Baralla implements Serializable {

    public BarallaAnglesa() {
        super();
    }

    protected void iniciarBaralla () {
        iniciarPal(Pal.Diamant);
        iniciarPal(Pal.Cor);
        iniciarPal(Pal.Trevol);
        iniciarPal(Pal.Pica);
    }

    protected void iniciarPal (Pal pal) {
        int nombreCartes = 13;

            for (int i = 1; i <= nombreCartes; i++) {
                this.baralla.add(new Carta(i, pal));
            }
    }
}
