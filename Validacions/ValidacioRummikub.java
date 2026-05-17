package Principi.Reptes.Rummy.Validacions;

import Principi.Reptes.Rummy.*;

import java.util.ArrayList;

public class ValidacioRummikub implements ValidarGrups<Fitxa> {

    public Ma<Fitxa> ordenarFitxesMa (Ma<Fitxa> ma) {
        ArrayList<Fitxa> grup = new ArrayList<>(ma.getMa());
        Ma<Fitxa> maOrdenada = new Ma<>();

        grup.sort((c1, c2) -> {
            if (c1.getColor() == Color.Comodi) return 1;
            if (c2.getColor() == Color.Comodi) return -1;

            return Integer.compare(c2.getNombre(), c1.getNombre());
        });
        for (Fitxa c : grup) {
            maOrdenada.afegirCarta(c);
        }
        return maOrdenada;
    }

    public boolean sonEscala (Ma<Fitxa> ma) {
        ArrayList<Fitxa> grup = ma.getMa();
        int numComodins = 0;
        int numBuits = 0;
        Ma<Fitxa> fitxesReals = new Ma<>();
        Color colorRef = null;
        int nombreAnterior = -1;

        for (Fitxa c: grup) {
            if (c.getColor() != Color.Comodi) {
                fitxesReals.afegirCarta(c);
            } else { numComodins++; }
        }
        if (fitxesReals.getNombreCartes() == 0) { return false; }
        colorRef = fitxesReals.getCarta(0).getColor();
        for (int i = 1; i < fitxesReals.getNombreCartes(); i++) {
            nombreAnterior = fitxesReals.getCarta(i - 1).getNombre();
            int nombreActual = fitxesReals.getCarta(i).getNombre();
            int diferencia = nombreAnterior - nombreActual;

            if (fitxesReals.getCarta(i).getColor() != colorRef) { return false; }
            if (diferencia > 1) {
                numBuits += diferencia - 1;
            }
            else if (diferencia <= 0) { return false; };
        }
        return numBuits <= numComodins;
    }

    public boolean sonNombreIguals (Ma<Fitxa> ma) {
        ArrayList<Fitxa> grup = ma.getMa();
        ArrayList<Color> colorsVists = new ArrayList<>();
        int nombreFix = -1;
        for (Fitxa c: grup) {
            if (c.getColor() != Color.Comodi) {
                nombreFix = c.getNombre();
                break;
            }
        }

        if (nombreFix == -1) { return false; }
        for (int i = 1; i < ma.getNombreCartes(); i++) {
            if (grup.get(i).getColor() != Color.Comodi) {
                if (nombreFix != grup.get(i).getNombre()) {
                    return false;
                }
                if (colorsVists.contains(grup.get(i).getColor())) {
                    return false;
                }
                colorsVists.add(grup.get(i).getColor());
            }
        }
        return true;
    }

    public boolean esGrupValid (Ma<Fitxa> ma) {
        if (ma.getNombreCartes() >= 3 && ma.getNombreCartes() <= 13) {
            Ma<Fitxa> maOrdenada = ordenarFitxesMa(ma);

            return sonNombreIguals(maOrdenada) || sonEscala(maOrdenada);
        }
        return false;
    }
}
