package Principi.Reptes.Rummy;

import java.util.ArrayList;

public class Tauler {

    private ArrayList<Ma> grupsCartes = new ArrayList<Ma>();

    public Tauler () {}

    public boolean afegirGrup (Ma ma) {
        if (esUnGrupCorrecte(ma)) {
            this.grupsCartes.add(ma);
            return true;
        }
        return false;
    }

    private Ma ordenarCartesMa (Ma ma) {
        ArrayList<Carta> grup = ma.getMa();
        Ma maOrdenada = new Ma();

        grup.sort((c1, c2) -> c2.getNombre() - c1.getNombre());
        for (Carta c : grup) {
            maOrdenada.afegirCarta(c);
        }
        return maOrdenada;
    }

    private boolean esUnGrupCorrecte (Ma ma) {
        if (ma.getNombreCartes() >= 3 && ma.getNombreCartes() <= 4) {
            Ma maOrdenada = ordenarCartesMa(ma);

            return sonNombreIguals(maOrdenada) || sonEscala(maOrdenada);
        }
        return false;
    }

    private boolean sonNombreIguals (Ma ma) {
        ArrayList<Carta> grup = ma.getMa();
        int nombreAnterior = 0 ;
        for (int i = 1; i < ma.getNombreCartes(); i++) {
            if (grup.get(i).getPal() != Pal.Comodi) {
                nombreAnterior = grup.get(i - 1).getNombre();

                if (nombreAnterior != grup.get(i).getNombre()) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean sonEscala (Ma ma) {
        ArrayList<Carta> grup = ma.getMa();
        int nombreAnterior = 0;
        Pal palAnterior;
        for (int i = 1; i < ma.getNombreCartes(); i++) {
            if (grup.get(i).getPal() != Pal.Comodi) {
                nombreAnterior = grup.get(i - 1).getNombre();
                palAnterior = grup.get(i - 1).getPal();

                if (nombreAnterior <= grup.get(i).getNombre() || palAnterior != grup.get(i).getPal()) {
                    return false;
                }
            }
        }
        return true;
    }

}
