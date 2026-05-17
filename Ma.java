package Principi.Reptes.Rummy;

import java.util.ArrayList;

public class Ma<T> {

    private ArrayList<T> ma = new ArrayList<T>();

    public Ma () {}

    public int getNombreCartes () {
        return this.ma.size();
    }

    public T getCarta(int index) {
        return this.ma.get(index);
    }
    public void afegirCarta (T c) {
        ma.add(c);
    }
    public void eliminarCarta (int index) {
        ma.remove(index);
    }

    public ArrayList<T> getMa () {
        return this.ma;
    }

    public Ma<T> copiarMa() {
        Ma<T> copia = new Ma<>();
        for(T c: ma) {
            copia.afegirCarta(c);
        }
        return copia;
    }

    public String toString() {
        String cadena = "";
        int contador = 0;
        for (T c: ma) {
            cadena = cadena + contador + ":" + c.toString();
            if (!c.equals(ma.getLast())) {
                cadena = cadena + ",";
            }
            contador++;
        }
        return cadena;
    }
}
