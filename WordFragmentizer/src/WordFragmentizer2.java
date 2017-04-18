import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordFragmentizer2 {

    private static Map<String, Integer> map = new HashMap<>();
    private static Map<String, Integer> map1 = new HashMap<>();
    private static Map<String, Integer> map2 = new HashMap<>();
    private static Map<String, Integer> map3 = new HashMap<>();

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input/words"));

            String line;
            while ((line = reader.readLine()) != null) {
                processWord(line.toLowerCase());
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void printResults() {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((o1, o2) -> {
            Integer o1WordSize = o1.getKey().length();
            Integer o2WordSize = o2.getKey().length();
            int sizeComparison = o2WordSize.compareTo(o1WordSize);
            if (sizeComparison != 0) return sizeComparison;

            int valueComparison = o2.getValue().compareTo(o1.getValue());
            if (valueComparison != 0) return valueComparison;

            return o1.getKey().compareToIgnoreCase(o2.getKey());
        });

        String filename = "output/results-" + System.currentTimeMillis() + ".csv";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            for (Map.Entry<String, Integer> entry : list) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        map.clear();
    }

    private static void printResults1() {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((o1, o2) -> {
            Integer o1WordSize = o1.getKey().length();
            Integer o2WordSize = o2.getKey().length();
            int sizeComparison = o2WordSize.compareTo(o1WordSize);
            if (sizeComparison != 0) return sizeComparison;

            int valueComparison = o2.getValue().compareTo(o1.getValue());
            if (valueComparison != 0) return valueComparison;

            return o1.getKey().compareToIgnoreCase(o2.getKey());
        });

        String filename = "output/results-" + System.currentTimeMillis() + ".csv";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            for (Map.Entry<String, Integer> entry : list) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        map.clear();
    }

    private static void processWord(String word) {
        getLetterSegments(word, 1);
        printResults();
      /**  getLetterSegments(word, 2);
        getLetterSegments(word,3); **/
    }

    private static void addToMap(String chunk) {
        Integer value = map.get(chunk) == null ? 1 : map.get(chunk) + 1;
        map.put(chunk, value);
    }

    private static void getLetterSegments(String word, int size) {
        for (int i = 0; i < word.length() + 1 - size; i++) {
            addToMap(word.substring(i, i + size));
        }
    }
}
