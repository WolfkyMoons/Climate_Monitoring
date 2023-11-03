import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.exceptions.CsvException;

// Una classe che rappresenta una riga di un file CSV
    public class CsvRow {
        // Una colonna unica che serve come chiave per il join
        @CsvBindByName(column = "id")
        private String id;

        // Altre colonne del file CSV
        @CsvBindByName(column = "name")
        private String name;

        @CsvBindByName(column = "age")
        private int age;

        @CsvBindByName(column = "city")
        private String city;

        // Getter e setter per le proprietà della classe
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        // Un metodo che restituisce una rappresentazione in formato CSV della riga
        public String toCsv() {
            return StringUtils.join(new Object[] {id, name, age, city}, ",");
        }
    }

    // Una classe che contiene il metodo principale per eseguire il join tra due file CSV
    public class CsvJoiner {
        public static void main(String[] args) throws IOException, CsvException {
            // I nomi dei file CSV da unire
            String file1 = "file1.csv";
            String file2 = "file2.csv";

            // Il nome del file CSV di output
            String output = "output.csv";

            // Leggere i file CSV e creare delle liste di oggetti CsvRow
            List<CsvRow> list1 = new CsvToBeanBuilder<CsvRow>(new FileReader(file1))
                    .withType(CsvRow.class)
                    .build()
                    .parse();

            List<CsvRow> list2 = new CsvToBeanBuilder<CsvRow>(new FileReader(file2))
                    .withType(CsvRow.class)
                    .build()
                    .parse();

            // Eseguire il join tra le liste basandosi sulla colonna "id"
            List<CsvRow> joinedList = StringUtils.join(list1, list2, CsvRow::getId);

            // Creare un file writer per scrivere il file CSV di output
            FileWriter writer = new FileWriter(output);

            // Scrivere l'intestazione del file CSV di output
            writer.write("id,name,age,city\n");

            // Scrivere le righe del file CSV di output
            for (CsvRow row : joinedList) {
                writer.write(row.toCsv() + "\n");
            }

            // Chiudere il file writer
            writer.close();

            // Stampare un messaggio di successo
            System.out.println("File CSV uniti con successo!");
        }
    }