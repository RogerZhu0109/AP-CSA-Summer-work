import java.util.*;

public class ListofNumbers {
    private Random rand = new Random();
    private ArrayList<Integer> list;
    private int size; // size of array
    private HashMap<Integer, Integer> numOcc = new HashMap<>();

    public ListofNumbers(int size) {
        list = new ArrayList<Integer>();
        for (int i = 1; i <= size; i++) { // initialize hashmap
            numOcc.put(i, 0);
        }
        this.size = size;
        for (int i = 0; i < this.size; i++) {
            int num = rand.nextInt(size) + 1;
            list.add(num);
            numOcc.put(num, numOcc.get(num) + 1);
        }

    }

    public int max() {
        return Collections.max(list);
    }

    public int min() {
        return Collections.min(list);
    }

    public void printLs() {
        for (int x : list) {
            System.out.print(x + " ,");
        }
    }

    public void printMode() { // iterate through and find highest occurence
        int maxval = 0;
        int mode = 0;
        for (int i = 1; i <= size; i++) {
            if (numOcc.get(i) > maxval) {
                mode = i;
                maxval = numOcc.get(i);
            }

        }
        System.out.println("Mode: " + mode + " Occurences: " + maxval);
    }

}
