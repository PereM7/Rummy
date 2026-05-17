package Principi.Reptes.Rummy.ES;

import java.util.ArrayList;
import java.util.Scanner;

public class Llegir {
    private static final Scanner sn = new Scanner(System.in);

    public Llegir () {}

    public static int demanarNombreJugadors() {
        int nombre = 0;
        do {
            System.out.println("Quants jugadors hi haurà? (2-4)");
            nombre = sn.nextInt();
            sn.nextLine();

            if (nombre < 2 || nombre > 4) {
                System.out.println("Error, mínim han de ser 2 jugadors i màxim 4.");
            }
        } while (nombre < 2 || nombre > 4);

        return nombre;
    }

    public static String demanarNom () {
        String nom = "";
        do {
            System.out.println("Introdueix el teu nom: ");
            nom = sn.nextLine();

            if (nom.isEmpty()) {
                System.out.println("Error, el nom es buit.");
            }
        }while(nom.isEmpty());

        return nom;
    }

    public static boolean agafarDescarteJugador () {
        String valor = "";
        do {
            System.out.println("Vols agafar la carta descartada per l'anterior jugador?(S/N)");
            valor = sn.nextLine();
            if (!valor.equals("s") && !valor.equals("n")) {
                System.out.println("Error, has d'introduir S o N.");
            }
        }while (!valor.equals("s") && !valor.equals("n"));

        return valor.equals("s");
    }

    public static int demanarCartaDescartar (int tamany) {
        int index = 0;
        do {
            System.out.println("Insereix l'index de la carta a descartar (0-"+tamany+"):" );
            index = sn.nextInt();
            sn.nextLine();

            if (index < 0 || index > tamany) {
                System.out.println("Error, el nmbre ha de ser entre 0 a "+tamany+" incluits.");
            }
        }while (index < 0 || index > tamany);

        return index;
    }

    public static boolean volCombinar () {
        String valor = "";
        do {
            System.out.println("Vols combinar cartes/fitxes o afegir una carta al tauler?(S/N)");
            valor = sn.nextLine().toLowerCase();

            if (!valor.equals("s") && !valor.equals("n")) {
                System.out.println("Error, has d'introduir S o N.");
            }
        }while (!valor.equals("s") && !valor.equals("n"));
        return valor.equals("s");
    }

    public static int demanarCartaCombinar (int tamany) {
        int index = 0;
        do {
            System.out.println("Insereix l'index de la carta/fitxa a combinar (0-"+tamany+"), insereix -1 si no vols combinar més:" );
            index = sn.nextInt();
            sn.nextLine();

            if (index != -1 && index < 0 || index > tamany) {
                System.out.println("Error, el nombre ha de ser entre 0 a "+tamany+" incluits o -1.");
            }
        }while ( index != -1 && index < 0 || index > tamany );

        return index;
    }

    public static int demanarIndexGrupInserir (int tamany) {
        int index = 0;
        do {
            System.out.println("Insereix l'index del grup on vols afegir la carta/fitxa (0-"+tamany+"):" );
            index = sn.nextInt();
            sn.nextLine();

            if (index < 0 || index > tamany) {
                System.out.println("Error, el nombre ha de ser entre 0 a "+tamany+" incluits.");
            }
        } while(index < 0 || index > tamany);
        return index;
    }

    public static int demanarIndexCartaInserir(int tamany) {
        int index = 0;
        do {
            System.out.println("Insereix l'index de la carta/fitxa que vols inserir(0-"+tamany+"):" );
            index = sn.nextInt();
            sn.nextLine();

            if (index < 0 || index > tamany) {
                System.out.println("Error, el nombre ha de ser entre 0 a "+tamany+" incluits.");
            }
        } while(index < 0 || index > tamany);
        return index;
    }

    public static boolean demanarSeguirInserint () {
        String valor = "";
        do {
            System.out.println("Vols inserir un altre grup?(S/N)");
            valor = sn.nextLine().toLowerCase();

            if (!valor.equals("s") && !valor.equals("n")) {
                System.out.println("Error, has d'introduir S o N.");
            }
        }while (!valor.equals("s") && !valor.equals("n"));
        return valor.equals("s");
    }

    public static boolean demanarInserirCartaGrup () {
        String valor = "";
        do {
            System.out.println("Vols una carta a un grup?(S/N)");
            valor = sn.nextLine().toLowerCase();

            if (!valor.equals("s") && !valor.equals("n")) {
                System.out.println("Error, has d'introduir S o N.");
            }
        }while (!valor.equals("s") && !valor.equals("n"));
        return valor.equals("s");
    }

    public static boolean demanarSiVolGinKnock () {
        String valor = "";
        do {
            System.out.println("Vols fer Gin o Knock?(S/N)");
            valor = sn.nextLine().toLowerCase();

            if (!valor.equals("s") && !valor.equals("n")) {
                System.out.println("Error, has d'introduir S o N.");
            }
        }while (!valor.equals("s") && !valor.equals("n"));
        return valor.equals("s");
    }

    public static int demanarGinQueFer () {
        String valor = "";
        do {
            System.out.println("Que vols fer, Gin o Knock?(G/K)");
            valor = sn.nextLine().toLowerCase();

            if (!valor.equals("g") && !valor.equals("k")) {
                System.out.println("Error, has d'introduir G o K.");
            }
        } while (!valor.equals("g") && !valor.equals("k"));
        return valor.equals("g") ? 1 : 2;
    }

    public static int demanarQueFerRummikub() {
        int valor = 0;
        do {
            System.out.println("Què vols fer?");
            System.out.println("1 - Afegir fitxa de la mà a un grup del tauler");
            System.out.println("2 - Crear un grup nou amb fitxes de la mà");
            System.out.println("3 - Moure una fitxa entre grups del tauler");
            System.out.println("0 - Acabar torn");
            valor = sn.nextInt();
            sn.nextLine();
        } while (valor < 0 || valor > 3);
        return valor;
    }

    public static int demanarOpcioInicial () {
        int valor = 0;
        do {
            System.out.println("Què vols fer?");
            System.out.println("1 - Nova Partida");
            System.out.println("2 - Carregar Partida");
            valor = sn.nextInt();
            sn.nextLine();
        }while (valor < 1 || valor > 2);
        return valor;
    }

    public static String demanarNomPartida (ArrayList<String> partides) {
        String valor = "";
        boolean bandera = false;
        do {
            for (String s: partides) {
                System.out.println("- " + s);
            }
            System.out.println("Quina partida vols carregar?");
            valor = sn.nextLine();

            for (String s: partides) {
                if (valor.equals(s)) {
                    bandera = true;
                    break;
                }
            }
        }while (!bandera);
        return valor;
    }

    public static int demanarTipusJoc () {
        int valor = 0;
        do {
            System.out.println("Quin joc vols jugar?");
            System.out.println("1 - Rummy Bàsic");
            System.out.println("2 - Rummy Argentí");
            System.out.println("3 - Gin Rummy");
            System.out.println("4 - Rummikub");
            valor = sn.nextInt();
            sn.nextLine();
        } while (valor < 0 || valor > 4);
        return valor;
    }
}
