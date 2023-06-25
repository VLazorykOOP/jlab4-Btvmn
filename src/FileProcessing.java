import java.io.*;
import java.util.*;

public class FileProcessing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть назву вхідного файлу: ");
        String inputFileName = scanner.nextLine();

        System.out.print("Введіть назву вихідного файлу для відсортованих чисел: ");
        String outputSortedFileName = scanner.nextLine();

        System.out.print("Введіть назву вихідного файлу для суми та чисел, з яких вона складається: ");
        String outputSumFileName = scanner.nextLine();

        List<Double> numbers = readNumbersFromFile(inputFileName);
        Collections.sort(numbers);
        double sum = calculateSum(numbers);

        writeSortedNumbersToFile(outputSortedFileName, numbers);
        writeSumAndNumbersToFile(outputSumFileName, sum, numbers);

        System.out.println("Обробка файлів завершена.");
    }

    private static List<Double> readNumbersFromFile(String fileName) {
        List<Double> numbers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                double number = Double.parseDouble(line);
                numbers.add(number);
            }
        } catch (IOException e) {
            System.err.println("Помилка при зчитуванні файлу: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Помилка перетворення числа: " + e.getMessage());
        }

        return numbers;
    }

    private static double calculateSum(List<Double> numbers) {
        int count = Math.min(10, numbers.size());
        double sum = 0.0;

        for (int i = numbers.size() - 1; i >= numbers.size() - count; i--) {
            sum += numbers.get(i);
        }

        return sum;
    }

    private static void writeSortedNumbersToFile(String fileName, List<Double> numbers) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Double number : numbers) {
                writer.println(number);
            }
        } catch (IOException e) {
            System.err.println("Помилка при записі до файлу: " + e.getMessage());
        }
    }

    private static void writeSumAndNumbersToFile(String fileName, double sum, List<Double> numbers) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println("Сума: " + sum);
            writer.println("Числа, з яких складається сума:");

            for (Double number : numbers) {
                writer.println(number);
            }
        } catch (IOException e) {
            System.err.println("Помилка при записі до файлу: " + e.getMessage());
        }
    }
}
