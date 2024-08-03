import java.util.*;
import java.io.*;

public class poe {
    public static void main(String[] args) throws FileNotFoundException {
        int numOfWords = 0;
        String frequentWord = "";
        int mostfrequentwordval = 0;
        HashMap<String, Integer> map = new HashMap<>();
        ArrayList<String> words = new ArrayList<>();
        Scanner scanner = new Scanner(
                new File("/Users/rogerzhu/Documents/APCSA/AP-CSA-Summer-work/POE/poemodified.txt"));
        while (scanner.hasNext()) {
            String str = scanner.next();
            // str = str.replace(";", "").replace("--", "").toLowerCase(); // get rid of
            // puncuation
            str = str.replace(";", "").toLowerCase().replaceAll("^-+|-+$", "");
            if (str.trim().isEmpty()) {
                continue; // if word is empty once you trim empty space, skip iteration
            }
            numOfWords++;
            if (map.containsKey(str)) {
                map.put(str, map.get(str) + 1);
            } else {
                map.put(str, 1);
                words.add(str);
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
        search(map);
        sort(words);
    }

    public static void search(HashMap<String, Integer> map) {
        System.out.println("---------------------------");
        System.out.println("hashmap search");
        Scanner reader = new Scanner(System.in);
        System.out.print("Enter the Word to Search: ");
        String word = reader.nextLine().toLowerCase();
        long start = System.currentTimeMillis();
        if (map.containsKey(word)) {
            System.out.println(word + " WORD FOUND and occured " + map.get(word) + " times");
        } else {
            System.out.println("WORD NOT FOUND");
        }
        long finish = System.currentTimeMillis();
        reader.close();
        System.out.println("Time Elasped: " + (double) (finish - start) / 1000);
        System.out.println("---------------------------");
    }

    public static void sort(ArrayList<String> list) {
        String[] arraySort = list.toArray(new String[list.size()]);
        String[] bubbleSort = list.toArray(new String[list.size()]);

        System.out.println("---------------------------");
        System.out.println("SORTING");

        // Array.Sort - Merge Sort
        long start = System.currentTimeMillis();
        Arrays.sort(arraySort);
        long finish = System.currentTimeMillis();
        System.out.println("Array Sort Time Elasped: " + (double) (finish - start) / 1000);

        // BubbleSwap
        long bubbleStart = System.currentTimeMillis();
        boolean swapped;
        for (int i = 0; i < bubbleSort.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < bubbleSort.length - i - 1; j++) {
                if (bubbleSort[j].compareTo(bubbleSort[j + 1]) > 0) {
                    String temp = bubbleSort[j];
                    bubbleSort[j] = bubbleSort[j + 1];
                    bubbleSort[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
        long bubbleEnd = System.currentTimeMillis();
        System.out.println("BubbleSort Time Elasped: " + (double) (bubbleEnd - bubbleStart) / 1000);

        System.out.println("---------------------------");

    }

}