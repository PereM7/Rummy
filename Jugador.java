package Principi.Reptes.Rummy;

import java.io.Serializable;

public class Jugador implements Serializable {

    private final String NOM;
    private int puntuacio = 0;
    private Ma<Carta> ma = new Ma<>();
    private boolean haJugatPrimeraMa = false;
    private boolean estaEnLlei = false;

    public Jugador (String nom) {
        this.NOM = nom;
    }

    public String getNom () {
        return this.NOM;
    }

    public void sumarPuntuacio (int punts) {
        this.puntuacio += punts;
    }
    public void restarPuntuacio (int punts) {
        this.puntuacio -= punts;
    }

    public int getPuntuacio () {
        return this.puntuacio;
    }

    public void setMa (Ma<Carta> ma) {
        this.ma = ma;
    }
    public Ma<Carta> getMa () {
        return this.ma;
    }

    public void setHaJugatPrimeraMa (boolean haJugat) {
        this.haJugatPrimeraMa = haJugat;
    }
    public boolean getHaJugatPrimeraMa () {
        return this.haJugatPrimeraMa;
    }

    public void setEstaEnLlei (boolean estaEnLlei) {
        this.estaEnLlei = estaEnLlei;
    }
    public boolean getEstaEnLlei () {
        return this.estaEnLlei;
    }
}
