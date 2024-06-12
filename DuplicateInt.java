import java.io.*;
import java.util.*;

public class DuplicateInt {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the path of the input file: ");
        String inputFilePath = scanner.nextLine();
        
        System.out.print("Enter the path of the output file: ");
        String outputFilePath = scanner.nextLine();
        
        try {
            List<Integer> duplicates = findDuplicates(inputFilePath);
            writeDuplicatesToFile(duplicates, outputFilePath);
            System.out.println("Duplicate integers have been written to: " + outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Integer> findDuplicates(String filePath) throws IOException {
        Set<Integer> uniqueNumbers = new HashSet<>();
        Set<Integer> duplicateNumbers = new HashSet<>();
        
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;
            
            String[] parts = line.split("\\s+");
            if (parts.length != 1) continue;
            
            try {
                int number = Integer.parseInt(parts[0].trim());
                if (!uniqueNumbers.add(number)) {
                    duplicateNumbers.add(number);
                }
            } catch (NumberFormatException e) {
                // Skip lines with non-integer inputs
            }
        }
        
        reader.close();
        
        List<Integer> duplicates = new ArrayList<>(duplicateNumbers);
        Collections.sort(duplicates);
        return duplicates;
    }

    private static void writeDuplicatesToFile(List<Integer> duplicates, String outputFilePath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));
        
        for (int number : duplicates) {
            writer.write(Integer.toString(number));
            writer.newLine();
        }
        
        writer.close();
    }
}
