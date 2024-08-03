import java.util.*;
import java.io.*;

public class poe {
    public static void main(String[] args) throws FileNotFoundException {
        int numOfWords = 0;
        String frequentWord = "";
        int mostfrequentwordval = 0;
        HashMap<String, Integer> map = new HashMap<>();
        ArrayList<String> uniqueWords = new ArrayList<>();
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
                words.add(str);
            } else {
                map.put(str, 1);
                uniqueWords.add(str);
                words.add(str);
            }
            if (map.get(str) > mostfrequentwordval) { // get most frequent word
                mostfrequentwordval = map.get(str);
                frequentWord = str;
            }
        }
        scanner.close();
        System.out.println("---------------------------");
        System.out.println("Total Number of Words: " + numOfWords);
        System.out.println("Total number of unique words: " + map.size());
        System.out.println(frequentWord + " is the most frequent word appearing: " + mostfrequentwordval + " times");
        sort(uniqueWords);

        Scanner reader = new Scanner(System.in);
        System.out.print("Enter the Word to Search: ");
        String word = reader.nextLine().toLowerCase();
        reader.close();
        search(words, map, word);
    }

    public static void search(ArrayList<String> list, HashMap<String, Integer> map, String word) {
        System.out.println("---------------------------");
        System.out.println("hashmap search");
        long start = System.nanoTime();
        hashmapSearch(map, word);
        long finish = System.nanoTime();
        System.out.println("Time Elasped: " + (double) (finish - start) + " nanoseconds");

        System.out.println("Sequential Search");
        String[] sequentialWords = list.toArray(new String[list.size()]);
        long seqStart = System.nanoTime();
        sequentialSearch(sequentialWords, word);
        long seqEnd = System.nanoTime();
        System.out.println("Time Elasped: " + (double) (seqEnd - seqStart) + " nanoseconds");

        System.out.println("Binary Search");
        String[] binaryWords = list.toArray(new String[list.size()]);
        Arrays.sort(binaryWords);
        long binaryStart = System.nanoTime();
        int results = binarySearch(binaryWords, word, 0, binaryWords.length - 1);
        if (results != 0) {
            System.out.println(word + "WORD FOUND and occured " + results + " times");
        } else {
            System.out.println("WORD NOT FOUND");
        }
        long binaryEnd = System.nanoTime();
        System.out.println("Time Elasped: " + (double) (binaryEnd - binaryStart) + " nanoseconds");

        System.out.println("---------------------------");

    }

    public static void hashmapSearch(HashMap<String, Integer> map, String word) {
        if (map.containsKey(word)) {
            System.out.println(word + " WORD FOUND and occured " + map.get(word) + " times");
        } else {
            System.out.println("WORD NOT FOUND");
        }
    }

    public static void sequentialSearch(String[] list, String word) {
        int wordcount = 0;
        for (String str : list) {
            if (str.equals(word.toLowerCase())) {
                wordcount++;
            }
        }
        if (wordcount != 0) {
            System.out.println(word + " WORD FOUND and occured " + wordcount + " times");
        } else {
            System.out.println("WORD NOT FOUND");
        }
    }

    public static int binarySearch(String[] list, String word, int left, int right) {
        int mid = (right + left) / 2;
        if (left > right) { // if word doesnt exist return 0
            return 0;
        }
        if (list[mid].equals(word)) {
            int count = 1;
            for (int i = mid - 1; i >= left; i--) { // check words to left and right for frequency
                if (list[i].equals(word)) {
                    count++;
                } else {
                    break;
                }
            }
            for (int i = mid + 1; i <= right; i++) {
                if (list[i].equals(word)) {
                    count++;
                } else {
                    break;
                }
            }
            return count;
        } else if (list[mid].compareTo(word) > 0) // word on the left
        {
            return binarySearch(list, word, left, mid - 1);
        } else // word on the right
        {
            return binarySearch(list, word, mid + 1, right);
        }
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