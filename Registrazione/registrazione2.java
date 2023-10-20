package Registrazione;

import java.io.*;
import java.util.*;

public class registrazione2 {

    public void effettua_registrazione() {
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
                File file = new File("Registrazione\\OperatoriRegistrati.dati.txt");

                try {
                    registraOperatori(input, inputReg, file);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Errore: Impossibile registrare gli operatori.");
                }
            } else {
                System.out.println("Risposta non riconosciuta");
            }
        }
    }

    // Legge gli operatori registrati da un file
    private static HashMap<String, String> leggiOperatoriRegistrati() {
        HashMap<String, String> operatori = new HashMap<>();
        try (Scanner inputOp = new Scanner(new File("Registrazione\\OperatoriRegistrati.dati.txt"))) {
            while (inputOp.hasNextLine()) {
                String riga = inputOp.nextLine();
                String[] parti = riga.split(",");
                String userID = parti[4].trim();
                String chiave = parti[5].trim();
                operatori.put(userID, chiave);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Errore: File non trovato.");
        }
        return operatori;
    }

    // Esegue il login
    private static void eseguiLogin(HashMap<String, String> operatori, String userName, String password) {
        if (operatori.containsKey(userName) && operatori.get(userName).equals(password)) {
            System.out.println("Login effettuato con successo.");
        } else {
            System.out.println("Login fallito. Dati non validi.");
        }
    }

    // Registra nuovi operatori
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
                System.out.print("Centro di Monitoraggio: ");
                String centromon = inputReg.nextLine();
                output.println(nome + "," + cognome + "," + codicefi + "," + email + "," + username + "," + password + "," + centromon);
            }
            System.out.println("Gli operatori sono stati aggiunti");
        }
    }
}
