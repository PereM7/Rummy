package Principi.Reptes.Rummy;

import Principi.Reptes.Rummy.Validacions.ValidarGrups;

import java.util.ArrayList;

public class Tauler {

    private ArrayList<Ma> grupsCartes = new ArrayList<Ma>();
    public ValidarGrups validador;

    public Tauler (ValidarGrups validador) {
        this.validador = validador;
    }

    public boolean afegirGrup (Ma ma) {
        if (validador.esGrupValid(ma)) {
            this.grupsCartes.add(ma);
            return true;
        }
        return false;
    }

    public boolean afegirCarta (Carta carta, int numGrup){
        Ma novaMa = grupsCartes.get(numGrup);
        novaMa.afegirCarta(carta);

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
        for(Ma m: grupsCartes) {
            cadena = cadena + m.toString() + ",\n";
        }
        return cadena;
    }
}
