/*******************************************
 * MATRICOLA       NOME e COGNOME                
 * 754687          Giulia Magni
 * 752959          Francesco Zappa
 *                 Franklin 
 * SEDE: Como
 ********************************************/

package Registrazione;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;                               // Importa la classe Scanner per leggere l'input dell'utente
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class registrazione {

    public void effettua_registrazione() {
        try (Scanner input = new Scanner(System.in)) {

            System.out.println("Hai scelto di proseguire come operatore.");
            System.out.println("Hai gia' eseguito la registrazione? (s/n)");
            String risposta = input.nextLine();

            if (risposta.equalsIgnoreCase("s")) {
                HashMap<String, String> operatori = new HashMap<>();
                Scanner inputOp = null;
                try {
                    // Verify that the file path is correct
                    inputOp = new Scanner(new File("Registrazione\\OperatoriRegistrati.dati.txt"));
                    while (inputOp.hasNextLine()) {
                        String riga = inputOp.nextLine();
                        String[] parti = riga.split(",");
                        String userID = parti[4];
                        String chiave = parti[5];
                        operatori.put(userID, chiave);
                    }
                    inputOp.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    System.out.println("Errore: File non trovato.");
                }

                System.out.print("Inserisci username: ");
                String userName = input.nextLine();
                System.out.print("Inserisci password: ");
                String password = input.nextLine();

                if (operatori.containsKey(userName) && operatori.get(userName).equals(password)) {
                    System.out.println("Login effettuato con successo.");
                } else {
                    System.out.println("Login fallito. Dati non validi.");
                }
            } else if (risposta.equalsIgnoreCase("n")) {
                Scanner inputReg = new Scanner(System.in);
                File file = new File("Registrazione\\OperatoriRegistrati.dati.txt");
                PrintWriter output = null;

                try {
                    output = new PrintWriter(new FileOutputStream(file, true));
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
                        System.out.print("Centro di Monitoraggio: ");
                        String centromon = inputReg.nextLine();
                        output.println(nome + "," + cognome + "," + codicefi + "," + email + "," + username + "," + password + "," + centromon);
                    }

                    inputReg.close();
                    output.close();
                    System.out.println("Gli operatori sono stati aggiunti");
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Errore: Impossibile registrare gli operatori.");
                }
            } else {
                System.out.println("Risposta non riconosciuta");
            }
        }
    }
}