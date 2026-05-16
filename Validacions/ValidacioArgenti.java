package Principi.Reptes.Rummy.Validacions;

import Principi.Reptes.Rummy.Carta;
import Principi.Reptes.Rummy.Ma;
import Principi.Reptes.Rummy.Pal;

import java.util.ArrayList;

public class ValidacioArgenti implements ValidarGrups{

    public boolean sonNombreIguals (Ma ma) {
        ArrayList<Carta> grup = ma.getMa();
        ArrayList<Pal> palsVists = new ArrayList<>();
        int nombreFix = -1;
        for (Carta c: grup) {
            if (c.getPal() != Pal.Comodi && c.getNombre() != 2) {
                nombreFix = c.getNombre();
                break;
            }
        }
        if (nombreFix == -1) { return false; }
        for (int i = 0; i < ma.getNombreCartes(); i++) {
            if (grup.get(i).getPal() != Pal.Comodi && grup.get(i).getNombre() != 2) {
                if (nombreFix != grup.get(i).getNombre()) {
                    return false;
                }
                if (palsVists.contains(grup.get(i).getPal())) {
                    return false;
                }
                palsVists.add(grup.get(i).getPal());
            }
        }
        return true;
    }


    public boolean sonEscala (Ma ma) {
        ArrayList<Carta> grup = ma.getMa();
        int numComodins = 0;
        Ma cartesReals = new Ma();
        Pal palRef = null;
        int nombreAnterior = -1;
        int numBuits = 0;

        for (Carta c: grup) {
            if (c.getPal() == Pal.Comodi || c.getNombre() == 2) {
                numComodins++;
            } else { cartesReals.afegirCarta(c); }
        }
        if (numComodins > 1) { return false; }

        palRef = cartesReals.getCarta(0).getPal();
        for (int i = 1; i < cartesReals.getNombreCartes(); i++) {
            nombreAnterior = cartesReals.getCarta(i - 1).getNombre();
            int nombreActual = cartesReals.getCarta(i).getNombre();
            int diferencia = nombreAnterior - nombreActual;

            if (cartesReals.getCarta(i).getPal() != palRef) { return false; }
            if (diferencia <= 0) { return false; }

            numBuits += (diferencia - 1);
        }
        return numBuits == numComodins;
    }

    public boolean sonEscala12 (Ma ma) {
        if (ma.getNombreCartes() == 12) {
            return sonEscala(ma);
        }else { return false; }
    }

    public boolean esGrupValid (Ma ma) {
        if (ma.getNombreCartes() >= 3 && ma.getNombreCartes() <= 13) {
            Ma maOrdenada = ordenarCartesMa(ma);

            return sonNombreIguals(maOrdenada) || sonEscala(maOrdenada);
        }
        return false;
    }
}
