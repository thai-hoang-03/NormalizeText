package NormalizeText;
import java.io.*;
public class TextNormalizer {
    public static void main(String[] args) {
       
        try {
            // Read the input file
            FileReader fileReader = new FileReader("input.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Create the output file
            FileWriter fileWriter = new FileWriter("output.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            String line;
            boolean newSentence = true;

            while ((line = bufferedReader.readLine()) != null) {
                // Remove leading and trailing white spaces
                line = line.trim();

                if (!line.isEmpty()) {
                    // Normalize spaces between words
                    line = line.replaceAll("\\s+", " ");

                    // Normalize spaces after comma, dot, and colon
                    line = line.replaceAll("(\\s?)([.,:])", "$2 ");

                    // Capitalize the first letter of each sentence
                    if (newSentence) {
                        line = line.substring(0, 1).toUpperCase() + line.substring(1);
                        newSentence = false;
                    }

                    // Check for quotes and remove spaces inside them
                    if (line.contains("\"")) {
                        String[] parts = line.split("\"");
                        for (int i = 0; i < parts.length; i++) {
                            if (i % 2 == 0) {
                                parts[i] = parts[i].trim();
                            }
                        }
                        line = String.join("\"", parts);
                    }

                    // Detect the end of a sentence
                    if (line.endsWith(".")) {
                        newSentence = true;
                    }

                    // Write the normalized line to the output file
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                }
            }

            // Close the input and output files
            bufferedReader.close();
            bufferedWriter.close();

            System.out.println("Normalization completed. Output written to output.txt");
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("An error occurred while reading/writing the file: " + e.getMessage());
        }
    }
    
    
}
