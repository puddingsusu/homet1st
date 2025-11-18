import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Scanner;

public class WordCounter {

    public static Map<String, Integer> countWords(String text) {
        Map<String, Integer> wordCount = new HashMap<>();

        if (text == null || text.trim().isEmpty()) {
            return wordCount;
        }

        // Split text by whitespace (spaces, tabs, newlines)
        String[] words = text.split("\\s+");

        for (String word : words) {
            // Skip empty strings that might result from splitting
            if (word.isEmpty()) {
                continue;
            }

            // Remove punctuation and convert to lowercase for case-insensitive counting
            String normalizedWord = word.replaceAll("[^a-zA-Z]", "").toLowerCase();

            // Skip if word becomes empty after removing punctuation
            if (normalizedWord.isEmpty()) {
                continue;
            }

            // Update the count for this word
            wordCount.put(normalizedWord, wordCount.getOrDefault(normalizedWord, 0) + 1);
        }

        return wordCount;
    }

    public static void displayWordCount(String text) {
        Map<String, Integer> wordCount = countWords(text);

        if (wordCount.isEmpty()) {
            System.out.println("No words found in the text.");
            return;
        }

        // Sort the results alphabetically using TreeMap
        Map<String, Integer> sortedWordCount = new TreeMap<>(wordCount);

        // Display the results
        for (Map.Entry<String, Integer> entry : sortedWordCount.entrySet()) {
            System.out.println(entry.getKey() + " → " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Word Counter Program ===");
        System.out.println("Masukkan kalimat atau teks:");
        System.out.print("> ");

        // Read input from user (entire line)
        String inputText = scanner.nextLine();

        System.out.println("\nInput: \"" + inputText + "\"");
        System.out.println("Output:");
        displayWordCount(inputText);

        scanner.close();
    }
}