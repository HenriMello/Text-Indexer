import java.io.*;
import java.text.Normalizer;
import java.util.regex.*;

public class Utils {

    public static String normalizeWord(String word) {
        word = word.toLowerCase();
        word = Normalizer.normalize(word, Normalizer.Form.NFD);
        word = word.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        word = word.replaceAll("[^a-z0-9]", "");
        return word.trim();
    }

    public static void loadWordsFromFile(String filename, BinarySearchTree bst) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] words = line.split("\\s+");
            for (String rawWord : words) {
                String cleanWord = normalizeWord(rawWord);
                if (!cleanWord.isEmpty()) {
                    bst.insert(cleanWord);
                }
            }
        }
        reader.close();
    }
}
