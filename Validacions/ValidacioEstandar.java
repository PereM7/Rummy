package Principi.Reptes.Rummy.Validacions;

import Principi.Reptes.Rummy.Carta;
import Principi.Reptes.Rummy.Ma;
import Principi.Reptes.Rummy.Pal;

import java.util.ArrayList;

public class ValidacioEstandar implements ValidarGrups<Carta> {

    public boolean esGrupValid (Ma ma) {
        if (ma.getNombreCartes() >= 3 && ma.getNombreCartes() <= 13) {
            Ma maOrdenada = ordenarCartesMa(ma);

            return sonNombreIguals(maOrdenada) || sonEscala(maOrdenada);
        }
        return false;
    }

    public boolean sonEscala (Ma<Carta> ma) {
        ArrayList<Carta> grup = ma.getMa();
        int numComodins = 0;
        int numBuits = 0;
        Ma<Carta> cartesReals = new Ma<>();
        Pal palRef = null;
        int nombreAnterior = -1;

        for (Carta c: grup) {
            if (c.getPal() != Pal.Comodi) {
                cartesReals.afegirCarta(c);
            } else { numComodins++; }
        }
        if (cartesReals.getNombreCartes() == 0) { return false; }
        palRef = cartesReals.getCarta(0).getPal();
        for (int i = 1; i < cartesReals.getNombreCartes(); i++) {
            nombreAnterior = cartesReals.getCarta(i - 1).getNombre();
            int nombreActual = cartesReals.getCarta(i).getNombre();
            int diferencia = nombreAnterior - nombreActual;

            if (cartesReals.getCarta(i).getPal() != palRef) { return false; }
            if (diferencia > 1) {
                numBuits += diferencia - 1;
            }
            else if (diferencia <= 0) { return false; };
        }
        return numBuits <= numComodins;
    }

    public boolean sonNombreIguals(Ma<Carta> ma) {
        ArrayList<Carta> grup = ma.getMa();
        ArrayList<Pal> palsVists = new ArrayList<>();
        int nombreFix = -1;
        for (Carta c: grup) {
            if (c.getPal() != Pal.Comodi) {
                nombreFix = c.getNombre();
                break;
            }
        }

        if (nombreFix == -1) { return false; }
        for (int i = 1; i < ma.getNombreCartes(); i++) {
            if (grup.get(i).getPal() != Pal.Comodi) {
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
}
