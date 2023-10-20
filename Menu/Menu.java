package Menu;
/*******************************************
 * MATRICOLA       NOME e COGNOME                
 * 754687          Giulia Magni
 * 752959          Francesco Zappa
 *                 Franklin 
 * SEDE: Como
 ********************************************/

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

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
        input.nextLine();

        switch (scelta) {
            case 1:
                System.out.println("Hai scelto di proseguire come cittadino.");
                System.out.println("Cercare l'area di interesse: ");
                break;

            case 2:
                System.out.println("Hai scelto di proseguire come operatore.");
                System.out.println("Hai gia' eseguito la registrazione? (s/n)");
                String risposta = input.nextLine();

                if (risposta.equals("s")) {
                    HashMap<String, String> operatori = new HashMap<>();
                    Scanner inputOp = null;
                    try {
                        inputOp = new Scanner(new File("Registrazione\\OperatoriRegistrati.dati.txt"));
                        while (inputOp.hasNextLine()) {
                            String riga = inputOp.nextLine();
                            String[] parti = riga.split(", ");
                            String userID = parti[4];
                            String chiave = parti[5];
                            operatori.put(userID, chiave);
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } finally {
                        if (inputOp != null) {
                            inputOp.close();
                        }
                    }

                    Console console = System.console();
                    if (console != null) {
                        String userName = console.readLine("Inserisci username: ");
                        char[] passwordArray = console.readPassword("Inserisci password: ");
                        String password = new String(passwordArray);

                        if (operatori.containsKey(userName) && operatori.get(userName).equals(password)) {
                            System.out.println("Login effettuato con successo.");
                        } else {
                            System.out.println("Login fallito. Dati non validi.");
                        }
                    } else {
                        System.out.println("Console non disponibile.");
                    }

                } else if (risposta.equals("n")) {
                    Scanner inputReg = new Scanner(System.in);
                    File file = new File("Registrazione\\OperatoriRegistrati.dati.txt");
                    PrintWriter output = null;

                    try {
                        output = new PrintWriter(new FileOutputStream(file, true));
                        System.out.print("Quanti operatori vuoi registrare? ");
                        int n = input.nextInt();
                        input.nextLine();

                        for (int i = 0; i < n; i++) {
                            System.out.println("Inserisci i dati dell'operatore " + (i + 1) + ": ");
                            System.out.print("Nome: ");
                            String nome = inputReg.nextLine();
                            System.out.print("Cognome: ");
                            String cognome = inputReg.nextLine();
                            System.out.print("Codice Fiscale: ");
                            String codicefi = inputReg.nextLine();
                            System.out.print("E-mail: ");
                            String email = inputReg.nextLine();
                            System.out.print("UserID: ");
                            String username = inputReg.nextLine();
                            System.out.print("Password: ");
                            String password = inputReg.nextLine();
                            System.out.print("Centro di Monitoraggio: ");
                            String centromon = inputReg.nextLine();
                            output.println(nome + " " + cognome + ", " + codicefi + ", " + email + ", " + username + ", " + password + ", " + centromon);
                        }

                        inputReg.close();
                        output.close();
                        System.out.println("Gli operatori sono stati aggiunti");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Risposta non riconosciuta");
                }
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