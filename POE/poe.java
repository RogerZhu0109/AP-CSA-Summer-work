import java.util.*;
import java.io.*;

public class poe {
    public static void main(String[] args) throws FileNotFoundException {
        int numOfWords = 0;
        String frequentWord = "";
        int mostfrequentwordval = 0;
        HashMap<String, Integer> map = new HashMap<>();
        Scanner scanner = new Scanner(
                new File("/Users/rogerzhu/Documents/APCSA/AP-CSA-Summer-work/POE/poemodified.txt"));
        while (scanner.hasNext()) {
            String str = scanner.next();
            str = str.replace(";", "").toLowerCase().replace("--", ""); // get rid of puncuation
            if (str.trim().isEmpty()) {
                continue; // if word is empty once you trim empty space, skip iteration
            }
            numOfWords++;
            if (map.containsKey(str)) {
                map.put(str, map.get(str) + 1);
            } else {
                map.put(str, 1);
            }
            if (map.get(str) > mostfrequentwordval) { // get most frequent word
                mostfrequentwordval = map.get(str);
                frequentWord = str;
            }
        }
        scanner.close();
        System.out.println("Total Number of Words: " + numOfWords);
        System.out.println("Total number of unique words: " + map.size());
        System.out.println(frequentWord + " is the most frequent word appearing: " + mostfrequentwordval + " times");
    }

    public void search() {

    }
}