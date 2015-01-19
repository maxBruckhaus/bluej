/*  Maxwell Bruckhaus, Period 3, January 15th, 2014
 *  This program took me approximately 3.5 hours to
 *  complete in total. On thursday when this homework
 *  was initially assigned I was tired from my other
 *  homework and couldn't end up finishing it after working
 *  until 1 AM. Today I finally finished the lab and
 *  understood the work I did. I was stuck on the loadFile
 *  method for a while but finally completed it. I
 *  also set my myStore ArrayList incorrectly at first
 *  which took me some time to realize.
 */

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StoreDriverMBruckhausPeriod3 {

    public static void clearScreen(){
        System.out.print('\u000C');
    }
    public static void main(String[] args) throws IOException {
        clearScreen();
        String path = "/Users/MAXIMILLION/Documents/dev/bluej/src/";
        Store s = new Store(path+"file50.txt");
        s.sort();
        s.displayStore();
    }
}


class Item implements Comparable<Item> {

    private int myId;
    private int myInv;

    /**
     *  Constructor for the Item object
     *
     * @param  id   id value
     * @param  inv  inventory value
     */
    public Item(int id, int inv){
        myId = id;
        myInv = inv;
    }

    /**
     *  Gets the id attribute of the Item object
     *
     * @return    The id value
     */
    public int getId(){
        return myId;
    }

    /**
     *  Gets the inv attribute of the Item object
     *
     * @return    The inv value
     */
    public int getInv(){
        return myInv;
    }

    /**
     *  Compares this item to another item based on id number. Returns the
     *  difference between this item's id and the other item's id. A
     *  difference of zero means the items' ids are equal in value.
     *
     * @param  other  Item object to compare to
     * @return        positive int if myId > other.myId
     *                0 if myId == other.myId
     *                negative int if myId < other.myId
     */
    public int compareTo(Item other){
        if (this.myId > other.myId){
            return 1;
        }else if (this.myId == other.myId){
            return 0;
        }else{
            return -1;
        }
    }

    /**
     *  Compares the Item to the specified object
     *
     * @param  otherObject  Item object to compare to
     * @return              true if equal, false otherwise
     */
    public boolean equals(Item otherObject){
        return (this.myId == otherObject.myId);
    }

    /**
     *  Overrides the default toString() of Object.
     *  Returns a String representation of this object. It's up to you
     *  exactly what this looks like.
     */
    public String toString(){
        return ("ID: " + myId + ", Inv: " + myInv);
    }
}


class Store {

    private ArrayList <Item> myStore = new ArrayList <> ();

    /**
     *  Creates a Store object from data stored in the given file name
     *
     *  @param  fName  name of the file containing id/inv pairs of data
     */
    public Store(String fName) throws IOException {
        loadFile(fName);
    }

    /**
     *  Reads a file containing id/inv data pairs one pair per line.
     *
     *  @param  inFileName  name of file containing id/inv pairs of data
     */
    private void loadFile(String inFileName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(inFileName))) {
            String line = br.readLine();

            while (line != null) {
                String[] splitArray = line.split("\\s+");
                int id = Integer.parseInt(splitArray[1]);
                int inv = Integer.parseInt(splitArray[2]);
                Item item = new Item(id, inv);
                myStore.add(item);
                line = br.readLine();
            }
        }
    }

    /**
     *  Prints the store contents in the format shown below
     *  Line #       Id          Inv
     *  1            184         14
     *  2            196         60
     */
    public void displayStore(){
        System.out.printf("\n%-10s%5s%11s", "Line #", "Id", "Inv");
        for (int i = 0; i < 50; i++){
            if (i % 10 == 0 && i != 0){
                System.out.println();
            }
            Item item = myStore.get(i);
            System.out.printf("\n%5s%10d%11d", i + 1, item.getId(), item.getInv());
        }
    }

    /**
     *  Sorts the store ArrayList using recursive mergesort
     */
    public void sort(){
        int last = myStore.size() - 1;
        mergeSort(myStore, 0, last);
    }

    private void merge(ArrayList <Comparable> a, ArrayList <Comparable> b, ArrayList <Comparable> c){
        int i = 0;
        int j = 0;
        while (true){
            if (i == a.size() && j == b.size()){
                break;
            }else if (i == a.size() || (j  < b.size() && (a.get(i).compareTo(b.get(j)) > 0))){
                c.add(b.get(j));
                j++;
            }else {
                c.add(a.get(i));
                i++;
            }
        }
    }

    /**
     *  Recursive mergesort of an ArrayList of Items
     *
     * @param  a      reference to an ArrayList of Items to be sorted
     * @param  first  starting index of range of values to be sorted
     * @param  last   ending index of range of values to be sorted
     */
    public void mergeSort(ArrayList a, int first, int last){
        if (first != last) { // recursion, divide list into two halves
            int mid = (first + last) / 2;
            mergeSort(a, first, mid);
            mergeSort(a, mid + 1, last);
            ArrayList<Comparable> b = new ArrayList<Comparable> (a.subList(first, mid + 1));
            ArrayList<Comparable> c = new ArrayList<Comparable> (a.subList(mid + 1, last + 1));
            ArrayList<Comparable> d = new ArrayList<Comparable>();
            merge(b, c, d);
            for (int i = 0; i < d.size(); i++){
                Comparable val = d.get(i);
                a.set(first + i, val);
            }
        }
    }
}
