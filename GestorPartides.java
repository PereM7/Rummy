package Principi.Reptes.Rummy;

import Principi.Reptes.Rummy.Jocs.JocBase;

import java.io.*;
import java.util.ArrayList;

public class GestorPartides {
    private static final String DIRECTORI = "partides_guardades/";

    public static void guardarPartida(JocBase joc, String nomFitxer) {
        new File(DIRECTORI).mkdirs();
        try (ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream(DIRECTORI + nomFitxer + ".dat" ))) {
            oos.writeObject(joc);
            System.out.println("Partida guardada correctament.");
        } catch (IOException e) {
            System.out.println("Error al guardar partida: " + e.getMessage());
        }
    }

    public static JocBase carregarPartida(String nomFitxer) {
        try (ObjectInputStream ois = new ObjectInputStream( new FileInputStream(DIRECTORI + nomFitxer + ".dat" ))) {
            return (JocBase) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al carregar partida: " + e.getMessage());
            return null;
        }
    }

    public static ArrayList<String> llistarPartides() {
        ArrayList<String> partides = new ArrayList<>();
        File dir = new File(DIRECTORI);
        if (dir.exists()) {
            for (File f : dir.listFiles()) {
                    partides.add(f.getName());
                }
            }
        return partides;
    }
}
