import java.io.*;
import java.util.*;

public class DuplicateInt {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the input file path:");
        String inputFilePath = scanner.nextLine().trim();

        System.out.println("Enter the output file path:");
        String outputFilePath = scanner.nextLine().trim();

        try {
            Set<Integer> uniqueIntegers = new HashSet<>();
            Set<Integer> duplicateIntegers = new TreeSet<>();
            BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\s+");
                if (parts.length != 1) continue;

                try {
                    int number = Integer.parseInt(parts[0].trim());
                    if (!uniqueIntegers.add(number)) {
                        duplicateIntegers.add(number);
                    }
                } catch (NumberFormatException e) {
                    continue;
                }
            }
            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));
            for (int number : duplicateIntegers) {
                writer.write(Integer.toString(number));
                writer.newLine();
            }
            writer.close();

            System.out.println("Duplicate integers have been written to: " + outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
