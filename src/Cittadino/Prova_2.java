import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.io.IOException;

public class CSVColumnSelector {
    public static void main(String[] args) {
        String file1Path = "file1.csv";
        String file2Path = "file2.csv";

        try {
            String[] selectedColumns = { "Column1", "Column3", "Column5" }; // Specifica i nomi delle colonne che desideri visualizzare

            System.out.println("File 1:");
            printSelectedColumns(file1Path, selectedColumns);

            System.out.println("File 2:");
            printSelectedColumns(file2Path, selectedColumns);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printSelectedColumns(String filePath, String[] selectedColumns) throws IOException {
        FileReader fileReader = new FileReader(filePath);
        CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withHeader());

        // Stampa l'intestazione con i nomi delle colonne selezionate
        System.out.println(String.join(", ", selectedColumns));

        for (CSVRecord record : csvParser) {
            // Per ogni record, stampa solo i valori delle colonne selezionate
            for (String column : selectedColumns) {
                System.out.print(record.get(column) + ", ");
            }
            System.out.println(); // Vai a una nuova riga per il prossimo record
        }
    }
}
