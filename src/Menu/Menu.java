/*******************************************
 * MATRICOLA       NOME e COGNOME                
 * 754687          Giulia Magni
 * 752959          Francesco Zappa
 * 
 * SEDE: Como
 ********************************************/

// The code snippet `package src.Menu;` is declaring the package name for the current Java file. In
// this case, the file is located in the `src` directory and the package name is `Menu`.
package src.Menu;

import java.io.*;
import java.util.*;

import src.Operatore.continuo_operatore;

public class Menu {
    /**
     * The main function presents a menu to the user and performs different actions based on their
     * choice.
     */
    public static void main(String[] args) {
        /*
         * The code `Scanner input = new Scanner(System.in);` 
         * creates a new Scanner object named `input` that reads input from the standard input stream (System.in). 
         * This allows the program to read user input from the console.
         */
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

        /*
         * The code `scelta = input.nextInt();` reads an integer input from the user and assigns it to the variable `scelta`.
         */
        scelta = input.nextInt();
        /* Consume the newline character */
        input.nextLine(); 

        switch (scelta) {
            /**
             * The code block `case 1:` is a part of a switch statement in Java. 
             * It is executed when the user chooses option 1 in the menu.
             */
            case 1:
                System.out.println("Hai scelto di proseguire come cittadino.");
                System.out.println("Cercare l'area di interesse: ");
                break;

            /**
             * The code block `case 2:` is a part of a switch statement in Java. 
             * It is executed whenthe user chooses option 2 in the menu.
             */
            case 2:
                continuo_operatore reg = new continuo_operatore();
                reg.registrazione();
                break;

            /**
            * The code block `case 3:` is a part of a switch statement in Java. 
            * It is executed when the user chooses option 3 in the menu.
            */
            case 3:
                System.out.println("Hai scelto di uscire. \nArrivederci e grazie.");
                esci = true;
                break;

            /** 
             * The code block `default:` is a part of the switch statement in Java.
             * It is executed when none of the cases match the value of the variable `scelta`.
             * In this case, if the user enters an option that is not 1, 2, or 3, the program will print "Opzione non valida, riprova."
             * (which means "Invalid option, please try again.") and then break out of the switch statement.
             */ 
            default:
                System.out.println("Opzione non valida, riprova.");
                break;
        }

        input.close();
    }
}
