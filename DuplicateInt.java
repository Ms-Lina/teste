import java.io.*;
import java.util.*;

public class DuplicateInt {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java DuplicateInt <input file> <output file>");
            return;
        }

        String inputFilePath = args[0];
        String outputFilePath = args[1];

        try {
            List<Integer> duplicates = findDuplicates(inputFilePath);
            writeDuplicatesToFile(duplicates, outputFilePath);
            System.out.println("Duplicate integers have been written to: " + outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Integer> findDuplicates(String filePath) throws IOException {
        Set<Integer> seen = new HashSet<>();
        Set<Integer> duplicates = new HashSet<>();

        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split("\\s+");
            if (parts.length != 1) continue;

            try {
                int num = Integer.parseInt(parts[0]);
                if (!seen.add(num)) {
                    duplicates.add(num);
                }
            } catch (NumberFormatException e) {
                continue;
            }
        }
        reader.close();

        List<Integer> sortedDuplicates = new ArrayList<>(duplicates);
        Collections.sort(sortedDuplicates);
        return sortedDuplicates;
    }

    private static void writeDuplicatesToFile(List<Integer> duplicates, String filePath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (int num : duplicates) {
            writer.write(String.valueOf(num));
            writer.newLine();
        }
        writer.close();
    }
}

