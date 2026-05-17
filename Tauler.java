package Principi.Reptes.Rummy;

import Principi.Reptes.Rummy.Validacions.ValidarGrups;

import java.io.Serializable;
import java.util.ArrayList;

public class Tauler<T> implements Serializable {

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

    public Ma<T> getGrup (int index) {
        return grupsCartes.get(index);
    }


    public T extreureFitxaGrup (int indexGrup, int indexFitxa) {
        Ma<T> grup = grupsCartes.get(indexGrup);
        T fitxa = grup.getCarta(indexFitxa);
        grup.eliminarCarta(indexFitxa);
        if (grup.getNombreCartes() == 0) {
            grupsCartes.remove(indexGrup);
        }
        return fitxa;
    }

    public Tauler<T> copiarTauler () {
        Tauler<T> copiTauler = new Tauler<>(validador);
        for (Ma<T> t: grupsCartes) {
            copiTauler.afegirGrup(t.copiarMa());
        }
        return copiTauler;
    }

    public boolean restaurarEstat (Tauler<T> tauler) {
        this.grupsCartes.clear();
        for (int i = 0; i < tauler.getNombreGrups(); i++) {

            if(!afegirGrup(tauler.getGrup(i))) { return false; }
        }
        return true;
    }

    public boolean verificarEstat () {
        for (Ma<T> grup : grupsCartes) {
            if (!validador.esGrupValid(grup)) return false;
        }
        return true;
    }

    public String toString() {
        String cadena = "";
        for(Ma<T> m: grupsCartes) {
            cadena = cadena + m.toString() + ",\n";
        }
        return cadena;
    }
}
