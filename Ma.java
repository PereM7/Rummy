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

    public int getPunts () {
        int punts = 0;
        for (Carta c: ma) {
            punts+= c.getNombre();
        }
        return punts;
    }

    public int recomptePunts () {
        int sumaTotal = 0;
        for (Carta c: ma) {
            sumaTotal += c.getValorCarta();
        }
        return sumaTotal;
    }

    public String toString() {
        String cadena = "";
        int contador = 0;
        for (Carta c: ma) {
            cadena = cadena + contador + ":" + c.toString();
            if (!c.equals(ma.getLast())) {
                cadena = cadena + ",";
            }
            contador++;
        }
        return cadena;
    }
}
