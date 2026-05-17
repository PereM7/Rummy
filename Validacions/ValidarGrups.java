package Principi.Reptes.Rummy.Validacions;

import Principi.Reptes.Rummy.*;

import java.util.ArrayList;

public interface ValidarGrups<T> {

     boolean sonNombreIguals (Ma<T> ma);

    default  Ma<Carta> ordenarCartesMa (Ma<Carta> ma) {
        ArrayList<Carta> grup = new ArrayList<>(ma.getMa());
        Ma<Carta> maOrdenada = new Ma<>();

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

    boolean sonEscala (Ma<T> ma);
    boolean esGrupValid (Ma<T> ma);
}
