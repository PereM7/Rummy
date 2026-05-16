package Principi.Reptes.Rummy;

public class Fitxa {
    private final int NOMBRE;
    private final Color COLOR;

    public Fitxa (int nombre, Color color) {
        this.NOMBRE = nombre;
        this.COLOR = color;
    }

    public int getNombre() {
        return this.NOMBRE;
    }
    public Color getColor () {
        return this.COLOR;
    }
}
