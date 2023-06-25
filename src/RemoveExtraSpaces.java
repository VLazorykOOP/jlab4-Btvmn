import java.io.*;

public class RemoveExtraSpaces {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String inputFilePath;
        String outputFilePath;

        try {
            System.out.println("Введіть шлях до вхідного файлу:");
            inputFilePath = reader.readLine();
            File inputFile = new File(inputFilePath);

            while (!inputFile.exists()) {
                System.out.println("Файл не знайдено. Введіть шлях до вхідного файлу:");
                inputFilePath = reader.readLine();
                inputFile = new File(inputFilePath);
            }

            System.out.println("Введіть шлях до вихідного файлу:");
            outputFilePath = reader.readLine();
            File outputFile = new File(outputFilePath);

            while (outputFile.exists()) {
                System.out.println("Файл вже існує. Введіть інший шлях до вихідного файлу:");
                outputFilePath = reader.readLine();
                outputFile = new File(outputFilePath);
            }

            BufferedReader fileReader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(outputFile));

            String line;
            while ((line = fileReader.readLine()) != null) {
                String trimmedLine = line.trim().replaceAll("\\s+", " ");
                fileWriter.write(trimmedLine);
                fileWriter.newLine();
            }

            fileReader.close();
            fileWriter.close();

            System.out.println("Зайві пропуски були видалені. Результат збережено в " + outputFilePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
