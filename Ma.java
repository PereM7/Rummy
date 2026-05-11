package Principi.Reptes.Rummy;

import java.util.ArrayList;

public class Ma {

    private ArrayList<Carta> ma = new ArrayList<Carta>();

    public Ma () {}

    public int getNombreCartes () {
        return this.ma.size();
    }

    public Carta getCarta(int index) {
        return this.ma.get(index);
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

    public String toStirng() {
        String cadena = "";
        for (Carta c: ma) {
            cadena = cadena + c.toString() + ",";
        }
        return cadena;
    }
}
