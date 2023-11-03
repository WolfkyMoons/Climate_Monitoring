/*******************************************
 * MATRICOLA       NOME e COGNOME                
 * 754687          Giulia Magni
 * 752959          Francesco Zappa
 * 
 * SEDE: Como
 ********************************************/

/*
 * The code `package src.Operatore;` is a package declaration. 
 * It specifies that the class `continuo_operatore` belongs to the package `src.Operatore`.
 */
package src.Operatore;

import java.io.*;
import java.util.*;

public class continuo_operatore {

    /**
     * The `registrazione()` function handles the registration process for an operator, allowing them
     * to either log in if they have already registered or register as a new operator.
     */
    public void registrazione() {
        
        // This code block is responsible for handling the registration process for an operator.
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Hai scelto di proseguire come operatore.");
            System.out.println("Hai gia' eseguito la registrazione? (s/n)");
            String risposta = input.nextLine();

            if (risposta.equalsIgnoreCase("s")) {
                HashMap<String, String> operatori = leggiOperatoriRegistrati();

                System.out.print("Inserisci username: ");
                String userName = input.nextLine();
                System.out.print("Inserisci password: ");
                String password = input.nextLine();
                eseguiLogin(operatori, userName, password);

            } else if (risposta.equalsIgnoreCase("n")) {
                Scanner inputReg = new Scanner(System.in);
                File file = new File("data\\OperatoriRegistrati.dati.txt");

                /*
                 * The code block is trying to register new operators by calling the `registraOperatori`method.
                 * If an `IOException` occurs during the registration process,
                 * it will be caughtand the error message "Errore: Impossibile trovare l'operatore." will be printed.
                 */
                try {
                    registraOperatori(input, inputReg, file);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Errore: Impossibile trovare l'operatore.");
                }
            } else {
                System.out.println("Risposta non riconosciuta");
            }
        }
    }

    /* APPUNTI:
     * Legge gli operatori registrati da un file
     * momento in cui si esegue la lettura del file degli operatori
    */

    /**
     * The function "leggiOperatoriRegistrati" reads data from a file and returns a HashMap containing userIDs and keys.
     * 
     * @return The method is returning a HashMap<String, String> object.
     */
    private static HashMap<String, String> leggiOperatoriRegistrati() {
        HashMap<String, String> operatori = new HashMap<>();
        
        // The code block is reading data from a file named "OperatoriRegistrati.dati.txt" and
        // populating a HashMap called `operatori` with the userIDs and keys stored in the file.
        try (Scanner inputOp = new Scanner(new File("data\\OperatoriRegistrati.dati.txt"))) {
            while (inputOp.hasNextLine()) {
                String riga = inputOp.nextLine();
                String[] parti = riga.split(", ");
                String userID = parti[4].trim();
                String chiave = parti[5].trim();
                operatori.put(userID, chiave);
            }

            // The code block is a catch block that handles the exception when the file "OperatoriRegistrati.dati.txt" is not found.
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Errore: File non trovato.");
        }
        return operatori;
    }

    /**
     * The function "eseguiLogin" checks if the given username and password match the values stored in
     * a HashMap and prints a success message if they do, or a failure message if they don't.
     * 
     * @param operatori A HashMap that stores the usernames and passwords of the operators.
     * @param userName The username entered by the user for login.
     * @param password The password parameter is a String that represents the password entered by the
     * user during the login process.
     */
    private static void eseguiLogin(HashMap<String, String> operatori, String userName, String password) {
        if (operatori.containsKey(userName) && operatori.get(userName).equals(password)) {
            System.out.println("Login effettuato con successo.");
        } else {
            System.out.println("Login fallito. Dati non validi.");
        }
    }

    /* APPUNTI:
     * inserire un modo per ritentare di effetturare l'accesso al login dell'operatore,
     * se dopo 5 tentativi non siesegue con successo si esce dalla scela operatore e si torna al menu
     * Registra nuovi operatori:
     * In questa parte di codice si trova la parte in cui si chiede all'operatore di registrarsi come nuovo operatore ....
     */

    // The `registraOperatori` method is responsible for registering new operators. It takes three
    // parameters: `input`, `inputReg`, and `file`.
    private static void registraOperatori(Scanner input, Scanner inputReg, File file) throws IOException {
        try (PrintWriter output = new PrintWriter(new FileOutputStream(file, true))) {
            System.out.print("Quanti operatori vuoi registrare? ");
            int n = input.nextInt();
            input.nextLine(); // Consume the newline character

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

                /* APPUNTI:
                 * in questo punto bisogna inserire la sceta del centro di monitoraggio se è gia presente o no 
                 * se presente no prob si termina la registrazione
                 * se non esiste bisogna mandare un altro codice dome si va a registrare un nuovo centro dimonitoraggio
                 * una volta terminato esce il messaggio "centrodi monitoraggio registrato" 
                 * e termini la registrazione.
                 * 
                 * il file di registrazione del centro di monitoraggiodeve solo prendere il nome del centro di monitoraggio 
                 * e la sigla del luogo e caricarlo nella registrazione operatori  
                */
                System.out.print("Centro di Monitoraggio: ");
                String centromon = inputReg.nextLine();
                output.println(nome + ", " + cognome + ", " + codicefi + ", " + email + ", " + username + ", " + password + ", " + centromon);
            }
            System.out.println("Gli operatori sono stati aggiunti");
        }
    }
}
