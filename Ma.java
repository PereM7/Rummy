package Principi.Reptes.Rummy;

import java.util.ArrayList;

public class Ma {

    private ArrayList<Carta> ma = new ArrayList<Carta>();

    public Ma () {}

    public int getNombreCartes () {
        return this.ma.size();
    }

    public void afegirCarta (Carta c) {
        ma.add(c);
    }
    public void eliminarCarta (int index) {
        ma.remove(index);
    }

    public ArrayList<Carta> getMa () {
        return this.ma;
    }

}
