import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Define file paths
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter file 1");
        String inputFile1 = scanner.nextLine();
        System.out.println("Enter file 2");
        String inputFile2 = scanner.nextLine();
        String mergedFile = "merged.txt";
        String commonFile = "common.txt";

        // Initialize lists to store integers from both files
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        // Read integers from the first input file
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile1))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    list1.add(Integer.parseInt(line.trim()));
                } catch (NumberFormatException e) {
                    System.err.println("Invalid number format in file: " + inputFile1);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + inputFile1);
        } catch (IOException e) {
            System.err.println("Error reading file: " + inputFile1);
        }

        // Read integers from the second input file
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile2))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    list2.add(Integer.parseInt(line.trim()));
                } catch (NumberFormatException e) {
                    System.err.println("Invalid number format in file: " + inputFile2);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + inputFile2);
        } catch (IOException e) {
            System.err.println("Error reading file: " + inputFile2);
        }

        // Merge the contents of both lists
        List<Integer> mergedList = new ArrayList<>(list1);
        mergedList.addAll(list2);

        // Write the merged list to the output file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(mergedFile))) {
            for (int number : mergedList) {
                bw.write(String.valueOf(number));
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + mergedFile);
        }

        // Find common integers in both lists
        Set<Integer> commonSet = new HashSet<>(list1);
        commonSet.retainAll(list2);

        // Write the common integers to the output file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(commonFile))) {
            for (int number : commonSet) {
                bw.write(String.valueOf(number));
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + commonFile);
        }

        System.out.println("Processing complete. Check the output files: " + mergedFile + " and " + commonFile);
    }
}
