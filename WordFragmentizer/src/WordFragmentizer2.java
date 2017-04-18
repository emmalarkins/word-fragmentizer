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
            String line;
            BufferedReader reader = new BufferedReader(new FileReader("input/words"));

            while ((line = reader.readLine()) != null) {
                processWord(line.toLowerCase());
            }
            printResults();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printResults() {
        List<Map.Entry<String, Integer>> list1 = new ArrayList<>(map1.entrySet());
        list1.sort((o1, o2) -> {

            int valueComparison = o2.getValue().compareTo(o1.getValue());
            if (valueComparison != 0) return valueComparison;

            return o1.getKey().compareToIgnoreCase(o2.getKey());
        });

        String filename1 = "output/results-1-letter-" + System.currentTimeMillis() + ".csv";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename1));
            for (Map.Entry<String, Integer> entry : list1) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        map1.clear();

        List<Map.Entry<String, Integer>> list2 = new ArrayList<>(map2.entrySet());
        list2.sort((o1, o2) -> {
            int valueComparison = o2.getValue().compareTo(o1.getValue());
            if (valueComparison != 0) return valueComparison;

            return o1.getKey().compareToIgnoreCase(o2.getKey());
        });

        String filename = "output/results-2-letters-" + System.currentTimeMillis() + ".csv";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            for (Map.Entry<String, Integer> entry : list2) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        map2.clear();

        List<Map.Entry<String, Integer>> list3 = new ArrayList<>(map3.entrySet());
        list3.sort((o1, o2) -> {

            int valueComparison = o2.getValue().compareTo(o1.getValue());
            if (valueComparison != 0) return valueComparison;

            return o1.getKey().compareToIgnoreCase(o2.getKey());
        });

        String filename3 = "output/results-3-letters-" + System.currentTimeMillis() + ".csv";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename3));
            for (Map.Entry<String, Integer> entry : list3) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        map3.clear();
    }

    private static void processWord(String word) {
        getLetterSegments(word, 1);
        getLetterSegments(word, 2);
        getLetterSegments(word, 3);
    }

    private static void addToMap(String chunk, int size) {
        if (size == 1) {
            Integer value = map1.get(chunk) == null ? 1 : map1.get(chunk) + 1;
            map1.put(chunk, value);
        }
        if (size == 2) {
            Integer value = map2.get(chunk) == null ? 1 : map2.get(chunk) + 1;
            map2.put(chunk, value);
        }
        if (size == 3) {
            Integer value = map3.get(chunk) == null ? 1 : map3.get(chunk) + 1;
            map3.put(chunk, value);
        }
    }

    private static void getLetterSegments(String word, int size) {
        for (int i = 0; i < word.length() + 1 - size; i++) {
            addToMap(word.substring(i, i + size), size);
        }
    }
}
