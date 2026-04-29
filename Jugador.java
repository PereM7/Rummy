package Principi.Reptes.Rummy;

public class Jugador {

    private final String NOM;
    private int puntuacio = 0;
    private Ma ma = new Ma();

    public Jugador (String nom) {
        this.NOM = nom;
    }

    public String getNom () {
        return this.NOM;
    }

    public void sumarPuntuacio (int punts) {
        this.puntuacio += punts;
    }
    public int getPuntuacio () {
        return this.puntuacio;
    }

    public Ma getMa () {
        return this.ma;
    }
}
