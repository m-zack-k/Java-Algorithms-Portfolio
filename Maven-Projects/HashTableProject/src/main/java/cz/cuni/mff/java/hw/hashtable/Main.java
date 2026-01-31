package cz.cuni.mff.java.hw.hashtable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Main class for the word frequency counter.
 * Reads text from standard input and outputs word counts using MyHash.
 */
public class Main {
    /**
     * The main  program.
     * @param args command line arguments (not actually used)
     */
    public static void main(String[] args){
        MyHash hash = new MyHash();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Skip empty line
                if (line.trim().isEmpty()) continue;
                // Split by whitespace
                String[] words = line.trim().split("\\s+");
                
                for (String word : words) {
                    if (word.isEmpty()) continue;

                    Object existingValue = hash.get(word);
                    if (existingValue == null) {
                        // First time seeing this word, set count to 1
                     hash.set(word, 1);
                    } else {
                        // Increment count
                        int count = (Integer) existingValue;
                     hash.set(word, count + 1);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading input: " + e.getMessage());
        }

        // Output results
        for (String key : hash) {
            System.out.println(key + ": " + hash.get(key));
        }

    }
}
