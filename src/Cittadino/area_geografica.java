package src.Cittadino;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class area_geografica {

    public void cercaAreaGeografica() {
        List<AreaGeografica> areeGeografiche = leggiDaCSV("CoordinateMonitoraggio.dati.csv");

        String termineRicerca = "Italia"; // Sostituisci con il termine di ricerca desiderato

        // Ricerca per denominazione e stato
        List<AreaGeografica> risultatiDenominazione = cercaPerDenominazione(areeGeografiche, termineRicerca);
        List<AreaGeografica> risultatiStato = cercaPerStato(areeGeografiche, "Italia"); // Sostituisci con lo stato desiderato

        // Ricerca per coordinate geografiche
        double latitudine = 41.9028; // Sostituisci con la latitudine desiderata
        double longitudine = 12.4964; // Sostituisci con la longitudine desiderata
        AreaGeografica risultatoCoordinate = cercaPerCoordinate(areeGeografiche, latitudine, longitudine);

        // Stampare i risultati
        System.out.println("Risultati per denominazione:");
        for (AreaGeografica area : risultatiDenominazione) {
            System.out.println(area);
        }

        System.out.println("\nRisultati per stato:");
        for (AreaGeografica area : risultatiStato) {
            System.out.println(area);
        }

        System.out.println("\nRisultato per coordinate:");
        System.out.println(risultatoCoordinate);
    }

    public static List<AreaGeografica> leggiDaCSV(String nomeFile) {
        List<AreaGeografica> areeGeografiche = new ArrayList<>();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(nomeFile))) {
            br.readLine(); // Ignora l'intestazione del CSV
            while ((line = br.readLine()) != null) {
                String[] campi = line.split(",");
                if (campi.length == 4) {
                    String nome = campi[0];
                    String stato = campi[1];
                    double latitudine = Double.parseDouble(campi[2]);
                    double longitudine = Double.parseDouble(campi[3]);
                    AreaGeografica area = new AreaGeografica(nome, stato, latitudine, longitudine);
                    areeGeografiche.add(area);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return areeGeografiche;
    }

    public static List<AreaGeografica> cercaPerDenominazione(List<AreaGeografica> areeGeografiche, String termine) {
        List<AreaGeografica> risultati = new ArrayList<>();
        for (AreaGeografica area : areeGeografiche) {
            if (area.getNome().contains(termine)) {
                risultati.add(area);
            }
        }
        return risultati;
    }

    public static List<AreaGeografica> cercaPerStato(List<AreaGeografica> areeGeografiche, String stato) {
        List<AreaGeografica> risultati = new ArrayList<>();
        for (AreaGeografica area : areeGeografiche) {
            if (area.getStato().equalsIgnoreCase(stato)) {
                risultati.add(area);
            }
        }
        return risultati;
    }

    public static AreaGeografica cercaPerCoordinate(List<AreaGeografica> areeGeografiche, double latitudine, double longitudine) {
        AreaGeografica risultato = null;
        double distanzaMinima = Double.MAX_VALUE;
        for (AreaGeografica area : areeGeografiche) {
            double distanza = calcolaDistanza(latitudine, longitudine, area.getLatitudine(), area.getLongitudine());
            if (distanza < distanzaMinima) {
                distanzaMinima = distanza;
                risultato = area;
            }
        }
        return risultato;
    }

    public static double calcolaDistanza(double lat1, double lon1, double lat2, double lon2) {
        // Formula per il calcolo della distanza tra due punti dati le coordinate geografiche
        // Puoi utilizzare diverse formule a seconda delle tue esigenze
        // Questa è una semplice approssimazione
        double R = 6371; // Raggio medio della Terra in chilometri
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        double dLat = lat2Rad - lat1Rad;
        double dLon = lon2Rad - lon1Rad;

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(lat1Rad) * Math.cos(lat2Rad)
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
}

class AreaGeografica {
    private String nome;
    private String stato;
    private double latitudine;
    private double longitudine;

    public AreaGeografica(String nome, String stato, double latitudine, double longitudine) {
        this.nome = nome;
        this.stato = stato;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
    }

    public String getNome() {
        return nome;
    }

    public String getStato() {
        return stato;
    }

    public double getLatitudine() {
        return latitudine;
    }

    public double getLongitudine() {
        return longitudine;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Stato: " + stato + ", Latitudine: " + latitudine + ", Longitudine: " + longitudine;
    }
}


/*da controllare se veramente è così il programma
import java.util.ArrayList;
import java.util.List;

class AreaGeografica {
    private String nome;
    private String stato;
    private double latitudine;
    private double longitudine;

    public AreaGeografica(String nome, String stato, double latitudine, double longitudine) {
        this.nome = nome;
        this.stato = stato;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
    }

    public String getNome() {
        return nome;
    }

    public String getStato() {
        return stato;
    }

    public double getLatitudine() {
        return latitudine;
    }

    public double getLongitudine() {
        return longitudine;
    }
}

public class RicercaAreeGeografiche {
    private List<AreaGeografica> areeGeografiche;

    public RicercaAreeGeografiche() {
        areeGeografiche = new ArrayList<>();
        // Aggiungi le tue aree geografiche qui
        areeGeografiche.add(new AreaGeografica("Roma", "Italia", 41.9028, 12.4964));
        areeGeografiche.add(new AreaGeografica("Parigi", "Francia", 48.8566, 2.3522));
        // Aggiungi altre aree geografiche
    }

    public List<AreaGeografica> cercaPerDenominazione(String input) {
        List<AreaGeografica> risultati = new ArrayList<>();
        for (AreaGeografica area : areeGeografiche) {
            if (area.getNome().toLowerCase().contains(input.toLowerCase())) {
                risultati.add(area);
            }
        }
        return risultati;
    }

    public List<AreaGeografica> cercaPerStato(String stato) {
        List<AreaGeografica> risultati = new ArrayList<>();
        for (AreaGeografica area : areeGeografiche) {
            if (area.getStato().toLowerCase().equals(stato.toLowerCase())) {
                risultati.add(area);
            }
        }
        return risultati;
    }

    public String cercaPerCoordinate(double latitudine, double longitudine) {
        AreaGeografica areaPiuVicina = null;
        double distanzaMinima = Double.MAX_VALUE;

        for (AreaGeografica area : areeGeografiche) {
            double distanza = calcolaDistanza(latitudine, longitudine, area.getLatitudine(), area.getLongitudine());
            if (distanza < distanzaMinima) {
                distanzaMinima = distanza;
                areaPiuVicina = area;
            }
        }

        if (areaPiuVicina != null) {
            return areaPiuVicina.getNome();
        } else {
            return "Nessuna area trovata nelle vicinanze.";
        }
    }

    private double calcolaDistanza(double lat1, double lon1, double lat2, double lon2) {
        // Implementa il calcolo della distanza tra due coordinate geografiche.
        // Puoi utilizzare diverse formule, ad esempio la formula di Haversine.
        // Restituisci la distanza in chilometri.
        // ...

        return 0.0; // Implementa la formula corretta
    }

    public static void main(String[] args) {
        RicercaAreeGeografiche ricerca = new RicercaAreeGeografiche();

        // Esempi di utilizzo
        System.out.println("Ricerca per denominazione:");
        List<AreaGeografica> risultatiDenominazione = ricerca.cercaPerDenominazione("Roma");
        for (AreaGeografica area : risultatiDenominazione) {
            System.out.println(area.getNome() + " (" + area.getStato() + ")");
        }

        System.out.println("\nRicerca per stato:");
        List<AreaGeografica> risultatiStato = ricerca.cercaPerStato("Italia");
        for (AreaGeografica area : risultatiStato) {
            System.out.println(area.getNome() + " (" + area.getStato() + ")");
        }

        System.out.println("\nRicerca per coordinate:");
        String risultatoCoordinate = ricerca.cercaPerCoordinate(41.9028, 12.4964);
        System.out.println("Area più vicina: " + risultatoCoordinate);
    }
}*/


