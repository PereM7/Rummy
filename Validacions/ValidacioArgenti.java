package Principi.Reptes.Rummy.Validacions;

import Principi.Reptes.Rummy.Carta;
import Principi.Reptes.Rummy.Ma;
import Principi.Reptes.Rummy.Pal;

import java.util.ArrayList;

public class ValidacioArgenti implements ValidarGrups{


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


    public boolean sonEscala (Ma ma) {
        ArrayList<Carta> grup = ma.getMa();
        int numComodins = 0;
        Ma cartesReals = new Ma();
        Pal palRef = null;
        int nombreAnterior = -1;
        int numBuits = 0;

        for (Carta c: grup) {
            if (c.getPal() != Pal.Comodi && c.getNombre() != 2) {
                cartesReals.afegirCarta(c);
            } else { numComodins++; }
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
        return true;
    }

    public boolean esGrupValid (Ma ma) {
        if (ma.getNombreCartes() >= 3 && ma.getNombreCartes() <= 13) {
            Ma maOrdenada = ordenarCartesMa(ma);

            return sonNombreIguals(maOrdenada) || sonEscala(maOrdenada);
        }
        return false;
    }
}
