package Menu;
/*******************************************
 * MATRICOLA       NOME e COGNOME                
 * 754687          Giulia Magni
 * 752959          Francesco Zappa
 *                 Franklin 
 * SEDE: Como
 ********************************************/

import java.io.*;
import java.util.*;

import Operatore.continuo_operatore;

public class Menu {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int scelta;
        boolean esci = false;

        System.out.println("*                                                        *");
        System.out.println("  ^^  Benvenuti nell'applicazione Climate Monitoring  ^^");
        System.out.println("*                                                        * \n");

        System.out.println("Scegli tra le seguenti opzioni:");
        System.out.println("1. Cittadino");
        System.out.println("2. Operatore");
        System.out.println("3. Esci \n");

        scelta = input.nextInt();
        input.nextLine(); // Consume the newline character

        switch (scelta) {
            case 1:
                System.out.println("Hai scelto di proseguire come cittadino.");
                System.out.println("Cercare l'area di interesse: ");
                break;

            case 2:
                continuo_operatore reg = new continuo_operatore();
                reg.registrazione();
                break;

            case 3:
                System.out.println("Hai scelto di uscire. \nArrivederci e grazie.");
                esci = true;
                break;

            default:
                System.out.println("Opzione non valida, riprova.");
                break;
        }

        input.close();
    }
}
