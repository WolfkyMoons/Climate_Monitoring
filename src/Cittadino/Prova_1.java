import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MergeCSVFiles {
    public static void main(String[] args) {
        String inputFilePath1 = "file1.csv"; // Ganti dengan path file CSV pertama
        String inputFilePath2 = "file2.csv"; // Ganti dengan path file CSV kedua
        String outputFilePath = "output.csv"; // Ganti dengan path file CSV hasil gabungan

        try {
            BufferedReader reader1 = new BufferedReader(new FileReader(inputFilePath1));
            BufferedReader reader2 = new BufferedReader(new FileReader(inputFilePath2));
            FileWriter writer = new FileWriter(outputFilePath);

            String line;
            while ((line = reader1.readLine()) != null) {
                writer.write(line + "\n");
            }

            while ((line = reader2.readLine()) != null) {
                writer.write(line + "\n");
            }

            reader1.close();
            reader2.close();
            writer.close();

            System.out.println("File CSV telah digabungkan.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
