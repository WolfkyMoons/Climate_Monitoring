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

    public static void main(String[] args) { 
        try (Scanner input = new Scanner(System.in)) {
            String risposta = input.nextLine();
            
            System.out.println("Hai scelto di proseguire come operatore.");                     // Stampa un messaggio
            System.out.println("Hai gia' eseguito la registrazione? (s/n)");                    // Chiede all'utente se deve registrarsi
            if (risposta.equalsIgnoreCase("s")) {                                   // Esegui il codice se l’utente ha scelto si
                // Crea una mappa per memorizzare i dati degli operatori 
                HashMap<String, String> operatori = new HashMap<>();
                // Crea uno scanner per leggere i dati dal file
                Scanner inputo = null;
                try {
                    // Apre il file OperatoriRegistrati.dati.txt
                    inputo = new Scanner(new File("OperatoriRegistrati.dati.txt"));
                    // Legge le righe del file e le inserisce nella mappa
                    while (input.hasNextLine()) {
                        // Legge una riga del file
                        String riga = input.nextLine();
                        // Divide la riga in tre parti: username e password
                        String[] parti = riga.split(", ");
                        // Usa l'username come userID e la password come chaive
                        String userID = parti[4];
                        String chiave = parti[5];
                        // Inserisce la coppia userID-chiave nella mappa
                        operatori.put(userID, chiave);
                    }
                } catch (FileNotFoundException e) {
                    // Gestisce l'eccezione se il file non viene trovato
                    e.printStackTrace();
                } finally {
                    // Chiude lo scanner
                    if (input != null) {
                        inputo.close();
                    }
                }
                // Crea un oggetto console per leggere i dati dall'utente
                Console console = System.console();
                if (console != null) {
                    // Chiede all'utente di inserire il nome e il cognome
                    String userName = console.readLine("Inserisci username: ");
                    // Chiede all'utente di inserire la matricola
                    String password = console.readLine("Inserisci password: ");
                    // Verifica se esiste una corrispondenza nella mappa
                    if (operatori.containsKey(userName) && operatori.get(userName).equals(password)) {
                        // Stampa un messaggio di successo
                        System.out.println("Login effettuato con successo.");
                    } else {
                        // Stampa un messaggio di errore
                        System.out.println("Login fallito. Dati non validi.");
                    }
                } else {
                    // Stampa un messaggio di avviso se la console non è disponibile
                    System.out.println("Console non disponibile.");
                }

            } else if (risposta.equalsIgnoreCase("n")) {                               // Esegui il codice se l’utente ha scelto no    
                Scanner inputi = new Scanner(System.in);
                File file = new File("OperatoriRegistrati.dati.txt");                       // Crea un oggetto File con il nome del file
                PrintWriter output = null;                                                           // Crea un oggetto PrintWriter per scrivere nel file in modalità append

                try {
                // Apre il file in modalità append, cioè aggiunge i dati alla fine del file
                output = new PrintWriter(new FileOutputStream(file, true));
                // Chiede all'utente quanti operatori vuole aggiungere
                System.out.print("Quanti operatori vuoi registrare? ");
                int n = input.nextInt();
                // Legge i dati degli operatori dall'utente e li scrive nel file
                for (int i = 0; i < n; i++) {
                    System.out.println("Inserisci i dati dell'operatore " + (i + 1) + ": ");
                    System.out.print("Nome: ");
                    String nome = input.next();
                    System.out.print("Cognome: ");
                    String cognome = input.next();
                    System.out.print("Codice Fiscale: ");
                    String codicefi = input.next();
                    System.out.print("E-mail: ");
                    String email = input.next();
                    System.out.print("UserID: ");
                    String username = input.next();
                    System.out.print("Password: ");
                    int password = input.nextInt();
                    System.out.print("Centro di Monitoraggio: ");
                    String centromon = input.next();
                    output.println(nome + " " + cognome + ", " + codicefi + ", " + email + ", " + username + ", " + password + ", " + centromon + " ");
                }

                // Chiude il file
                output.close();
                // Stampa un messaggio di conferma
                System.out.println("Gli operatori sono stati aggiunti");
                } catch (Exception e) {
                // Gestisce le eventuali eccezioni
                e.printStackTrace();
                } finally {
                // Chiude lo scanner
                inputi.close();
                }

            } else { // Esegui il codice se l’utente ha inserito una risposta non valida 
                System.out.println("Risposta non riconosciuta"); 
            }
        }
                               

    }
}

