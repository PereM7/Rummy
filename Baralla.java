package Principi.Reptes.Rummy;

import java.util.ArrayList;

public class Baralla {

    private final int NOMBRE_CARTES_BARALLA = 104;
    private ArrayList<Carta> baralla = new ArrayList<Carta>();

    public Baralla() {
        iniciarBaralla();
    }

    private void iniciarBaralla () {
        iniciarPal(Pal.Diamant);
        iniciarPal(Pal.Cor);
        iniciarPal(Pal.Trevol);
        iniciarPal(Pal.Pica);
        iniciarPal(Pal.Comodi);
    }

    private void iniciarPal (Pal pal) {
        int nombreCartes = 13;
        if (pal == Pal.Comodi) {
            nombreCartes = 2;
        }

        for (int x = 2; x > 0; x--) {
            for (int i = 1; i < nombreCartes; i++) {
                this.baralla.add(new Carta(i, pal));
            }
        }
    }

    public void mesclarBaralla () {
        ArrayList<Carta> barallaAleatori = new ArrayList<Carta>();

        while(!baralla.isEmpty()) {
            int numAleatori = (int) (Math.random() * baralla.size());

            barallaAleatori.add(baralla.get(numAleatori));
            baralla.remove(numAleatori);
        }

        setBaralla(barallaAleatori);
    }

    public Carta extreureCarta () {
        Carta c = baralla.getLast();
        baralla.remove(baralla.getLast());
        return c;
    }

    public ArrayList<Carta> getBarralla () {
        return this.baralla;
    }

    public void setBaralla (ArrayList<Carta> baralla) {
        this.baralla = baralla;
    }
}
