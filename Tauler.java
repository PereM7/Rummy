package Principi.Reptes.Rummy;

import Principi.Reptes.Rummy.Validacions.ValidarGrups;

import java.util.ArrayList;

public class Tauler<T> {

    private ArrayList<Ma<T>> grupsCartes = new ArrayList<>();
    public ValidarGrups<T> validador;

    public Tauler (ValidarGrups<T> validador) {
        this.validador = validador;
    }

    public boolean afegirGrup (Ma<T> ma) {
        if (validador.esGrupValid(ma)) {
            this.grupsCartes.add(ma);
            return true;
        }
        return false;
    }

    public boolean afegirCarta (T t, int numGrup){
        Ma<T> novaMa = grupsCartes.get(numGrup);
        novaMa.afegirCarta(t);

        if(validador.esGrupValid(novaMa)){
            this.grupsCartes.remove(numGrup);
            this.grupsCartes.add(novaMa);
            return true;
        }
        else {
            novaMa.eliminarCarta(novaMa.getNombreCartes() - 1);
            return false;
        }
    }

    public int getNombreGrups () {
        return this.grupsCartes.size();
    }

    public String toString() {
        String cadena = "";
        for(Ma<T> m: grupsCartes) {
            cadena = cadena + m.toString() + ",\n";
        }
        return cadena;
    }
}
