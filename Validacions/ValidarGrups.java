package Principi.Reptes.Rummy.Validacions;

import Principi.Reptes.Rummy.Carta;
import Principi.Reptes.Rummy.Ma;
import Principi.Reptes.Rummy.Pal;

import java.util.ArrayList;

public interface ValidarGrups {

    default boolean sonNombreIguals (Ma ma) {
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

    boolean sonEscala (Ma ma);
    boolean esGrupValid (Ma ma);
}
