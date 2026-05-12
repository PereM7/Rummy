package Principi.Reptes.Rummy.Validacions;

import Principi.Reptes.Rummy.Carta;
import Principi.Reptes.Rummy.Ma;
import Principi.Reptes.Rummy.Pal;

import java.util.ArrayList;

public class ValidacioEstandar implements ValidarGrups {

    public boolean esGrupValid (Ma ma) {
        if (ma.getNombreCartes() >= 3 && ma.getNombreCartes() <= 13) {
            Ma maOrdenada = ordenarCartesMa(ma);

            return sonNombreIguals(maOrdenada) || sonEscala(maOrdenada);
        }
        return false;
    }

    private Ma ordenarCartesMa (Ma ma) {
        ArrayList<Carta> grup = new ArrayList<>(ma.getMa());
        Ma maOrdenada = new Ma();

        grup.sort((c1, c2) -> {
            if (c1.getPal() == Pal.Comodi) return 1;
            if (c2.getPal() == Pal.Comodi) return -1;

            return Integer.compare(c2.getNombre(), c1.getNombre());
        });
        for (Carta c : grup) {
            maOrdenada.afegirCarta(c);
        }
        return maOrdenada;
    }

    public boolean sonNombreIguals (Ma ma) {
        ArrayList<Carta> grup = ma.getMa();
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
            }
        }
        return true;
    }

    public boolean sonEscala (Ma ma) {
        ArrayList<Carta> grup = ma.getMa();
        int numComodins = 0;
        int numBuits = 0;
        Ma cartesReals = new Ma();
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
}
