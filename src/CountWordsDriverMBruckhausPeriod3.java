/*  Maxwell Bruckhaus, Period 3, January 21st, 2015
 *  This program took me approximately 4 hours to
 *  complete in total.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*  19.3 steps
    * Represent words as store items
    * Each item has the word and the count of the word
       * Add a member for getting the number of store items
       * Add a member for getting sum of counts

    * Iterate through the text
    * For each word, increment the word count of the store item for the word

    * Need a way to check if a store item already exists for the current word
    * If it exists, increment the count
    * If it does not exist, create store item and set count to 1

    * After processing the text, sort the store items by count
    * Implement method in the store item class to sort items by count

    * Add up the total number of words
       * All of the counts of each item added together
    * Add up the total number of unique words
       * All of the items added together
*/


public class CountWordsDriverMBruckhausPeriod3 {

    public static void clearScreen() {
        System.out.print('\u000C');
    }

    public static void main(String[] args) throws IOException {
        clearScreen();
        String path = "/Users/MAXIMILLION/Documents/dev/bluej/src/";
        WordStore words = new WordStore(path + "lincoln.txt");
        words.sort();
        words.displayWordStore();
    }
}


class WordCount implements Comparable<WordCount> {

    private String word;
    private int count = 1;

    public int getCount() {
        return count;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Constructor for the WordCount object
     *
     * @param word word itself
     */
    public WordCount(String word) {
        this.word = word;
        this.count = 1;
    }

    /**
     * Compares this Word to another Word based on word count. A
     * difference of zero means the Word counts are equal in value.
     *
     * @param other Word object to compare to
     * @return positive int if count > other.count
     * 0 if myId == other.myId
     * negative int if count < other.count
     */
    public int compareTo(WordCount other) {
        if ((this.count < other.count) || (this.count == other.count && this.word.compareTo(other.word) > 0)) {
            return 1;
        } else if ((this.count == other.count) && (this.word.equals(other.word))) {
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * Overrides the default toString() of Object.
     * Returns a String representation of this object. It's up to you
     * exactly what this looks like.
     */
    public String toString() {
        return ("Word: " + word + ", Word count: " + count);
    }
}


class WordStore {

    public WordStore(String fileName) throws IOException {
        loadFile(fileName);
    }

    private ArrayList<WordCount> myWordStore = new ArrayList<>();
    private int uniqueWords = 0;
    private int totalWords = 0;

    /**
     * Reads a file containing id/inv data pairs one pair per line.
     *
     * @param inFileName name of file containing id/inv pairs of data
     */
    private void loadFile(String inFileName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(inFileName))) {
            String line = br.readLine();
            while (line != null) {
                String[] splitArray = line.split("[\\s,.]+");
                //System.out.println("splitArray = " + Arrays.toString(splitArray));
                for (String word : splitArray) {
                    if (!word.equals("") && !word.equals("-")){
                        countWord(word.toLowerCase());
                    }
                }
                line = br.readLine();
            }
        }
    }

    public void countWord(String word) {
        totalWords++;
        if (hasWord(word)) {
            incrementCount(word);
        } else {
            uniqueWords++;
            WordCount wordCount = new WordCount(word);
            myWordStore.add(wordCount);
        }
    }

    private boolean hasWord(String word) {
        for (WordCount wordCount : myWordStore) {
            if (wordCount.getWord().equals(word)) {
                return true;
            }
        }
        return false;
    }

    private void incrementCount(String word) {
        for (WordCount wordCount : myWordStore) {
            if (wordCount.getWord().equals(word)) {
                wordCount.setCount(wordCount.getCount() + 1);
                break;
            }
        }
    }

    /**
     * Prints the WordStore contents in the format shown below
     * Line #       Word Count         Word
     * 1            13                 the
     * 2            12                 that
     */
    public void displayWordStore() {
        System.out.printf("\n%-10s%5s%11s", "Line #", "Word Count", "Word");
        int i = 0;
        for (WordCount wordCount : myWordStore) {
            i++;
            System.out.printf("\n%5s%10d%16s", i, wordCount.getCount(), wordCount.getWord());
            if (i >= 30){
                break;
            }
        }
        System.out.println("\n\nNumber of unique words used = " + uniqueWords);
        System.out.println("Total # of words = " + totalWords);
    }

    /**
     * Sorts the WordStore ArrayList using recursive mergesort
     */
    public void sort() {
        int last = myWordStore.size() - 1;
        mergeSort(myWordStore, 0, last);
    }

    private void merge(ArrayList<Comparable> a, ArrayList<Comparable> b, ArrayList<Comparable> c) {
        int i = 0;
        int j = 0;
        while (true) {
            if (i == a.size() && j == b.size()) {
                break;
            } else if (i == a.size() || (j < b.size() && (a.get(i).compareTo(b.get(j)) > 0))) {
                c.add(b.get(j));
                j++;
            } else {
                c.add(a.get(i));
                i++;
            }
        }
    }

    /**
     * Recursive mergesort of an ArrayList of Words
     *
     * @param a     reference to an ArrayList of Words to be sorted
     * @param first starting index of range of values to be sorted
     * @param last  ending index of range of values to be sorted
     */
    public void mergeSort(ArrayList a, int first, int last) {
        if (first != last) { // recursion, divide list into two halves
            int mid = (first + last) / 2;
            mergeSort(a, first, mid);
            mergeSort(a, mid + 1, last);
            ArrayList<Comparable> b = new ArrayList<Comparable>(a.subList(first, mid + 1));
            ArrayList<Comparable> c = new ArrayList<Comparable>(a.subList(mid + 1, last + 1));
            ArrayList<Comparable> d = new ArrayList<Comparable>();
            merge(b, c, d);
            for (int i = 0; i < d.size(); i++) {
                Comparable val = d.get(i);
                a.set(first + i, val);
            }
        }
    }
}