/*  Maxwell Bruckhaus, Period 3, January 18th, 2015
 *  This program took me approximately 1.5 hours to
 *  complete in total. I have always been interested
  *  in learning further about recursive methods. I had
  *  a little bit of trouble like usual when finishing
  *  the recursive method but ultimately finished.
 */

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SearchDriverMBruckhausPeriod3 {

    public static void clearScreen(){
        System.out.print('\u000C');
    }
    public static void main(String[] args) throws IOException {
        clearScreen();
        String path = "/Users/MAXIMILLION/Documents/dev/bluej/src/";
        SearchStore s = new SearchStore(path + "file50.txt");
        s.sort();
        s.displaySearchStore();
    }
}


class SearchItem implements Comparable<SearchItem> {

    private int myId;
    private int myInv;

    /**
     *  Constructor for the SearchItem object
     *
     * @param  id   id value
     * @param  inv  inventory value
     */
    public SearchItem(int id, int inv){
        myId = id;
        myInv = inv;
    }

    /**
     *  Gets the id attribute of the SearchItem object
     *
     * @return    The id value
     */
    public int getId(){
        return myId;
    }

    /**
     *  Gets the inv attribute of the SearchItem object
     *
     * @return    The inv value
     */
    public int getInv(){
        return myInv;
    }

    /**
     *  Compares this SearchItem to another SearchItem based on id number. Returns the
     *  difference between this SearchItem's id and the other SearchItem's id. A
     *  difference of zero means the SearchItems' ids are equal in value.
     *
     * @param  other  SearchItem object to compare to
     * @return        positive int if myId > other.myId
     *                0 if myId == other.myId
     *                negative int if myId < other.myId
     */
    public int compareTo(SearchItem other){
        if (this.myId > other.myId){
            return 1;
        }else if (this.myId == other.myId){
            return 0;
        }else{
            return -1;
        }
    }

    /**
     *  Compares the SearchItem to the specified object
     *
     * @param  otherObject  SearchItem object to compare to
     * @return              true if equal, false otherwise
     */
    public boolean equals(SearchItem otherObject){
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


class SearchStore {

    private ArrayList<SearchItem> mySearchStore = new ArrayList<>();

    /**
     * Creates a SearchStore object from data SearchStored in the given file name
     *
     * @param fName name of the file containing id/inv pairs of data
     */
    public SearchStore(String fName) throws IOException {
        loadFile(fName);
    }

    /**
     * Reads a file containing id/inv data pairs one pair per line.
     *
     * @param inFileName name of file containing id/inv pairs of data
     */
    private void loadFile(String inFileName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(inFileName))) {
            String line = br.readLine();

            while (line != null) {
                String[] splitArray = line.split("\\s+");
                int id = Integer.parseInt(splitArray[1]);
                int inv = Integer.parseInt(splitArray[2]);
                SearchItem SearchItem = new SearchItem(id, inv);
                mySearchStore.add(SearchItem);
                line = br.readLine();
            }
        }
    }

    /**
     * Prints the SearchStore contents in the format shown below
     * Line #       Id          Inv
     * 1            184         14
     * 2            196         60
     */
    public void displaySearchStore() {
        System.out.printf("\n%-10s%5s%11s", "Line #", "Id", "Inv");
        for (int i = 0; i < 50; i++) {
            if (i % 10 == 0 && i != 0) {
                System.out.println();
            }
            SearchItem SearchItem = mySearchStore.get(i);
            System.out.printf("\n%5s%10d%11d", i + 1, SearchItem.getId(), SearchItem.getInv());
        }
    }

    /**
     * Sorts the SearchStore ArrayList using recursive mergesort
     */
    public void sort() {
        int last = mySearchStore.size() - 1;
        mergeSort(mySearchStore, 0, last);
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
     * Recursive mergesort of an ArrayList of SearchItems
     *
     * @param a     reference to an ArrayList of SearchItems to be sorted
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

    public void testSearch() {
        int idToFind;
        int invReturn;
        int index;
        Scanner in = new Scanner(System.in);

        System.out.println("Testing search algorithm\n");
        do {
            System.out.println();
            System.out.print("Enter Id value to search for (-1 to quit) ---> ");
            idToFind = in.nextInt();
            index = bsearch(new SearchItem(idToFind, 0));
            //index = bsearch(new SearchItem(idToFind, 0));
            //recursive version call
            System.out.print("Id # " + idToFind);
            if (index == -1) {
                System.out.println(" No such part in stock");
            } else {
                System.out.println(" Inventory = " + mySearchStore.get(index).getInv());
            }
        } while (idToFind >= 0);
    }



    /**
     * Searches the mySearchStore ArrayList of SearchItem Objects for the specified
     * SearchItem object using a iterative binary search algorithm
     *
     * @param idToSearch SearchItem object containing id value being searched for
     * @return index of SearchItem if found, -1 if not found
     */

    private int bsearch(SearchItem idToSearch) {
        System.out.println(mySearchStore);
        int checkWith = mySearchStore.size() / 2;
        SearchItem idCheck = mySearchStore.get(checkWith - 1);
        SearchItem id = idToSearch;

        while (idCheck.compareTo(id) != 0) {
            int diff = idCheck.getId() - id.getId();
            if (diff > 0) {
                System.out.println("idCheck: " + idCheck + "id: " + id);

                checkWith = checkWith / 2;
                diff = idCheck.getId() - id.getId();
                if (id.getId() == idCheck.getId()) {
                    break;
                }
            } else if (diff < 0) {
                System.out.println("idCheck: " + idCheck + "id: " + id);
                checkWith = (checkWith + mySearchStore.size()) / 2;
                diff = idCheck.getId() - id.getId();
                if (id.getId() == idCheck.getId()) {
                    break;
                }
            }
        }

        if (idCheck.compareTo(id) == 0) {
            return checkWith - 1;
        }

        System.out.println("first: " + idCheck);
        return -1;
    }

    /**
     * Searches the specified ArrayList of SearchItem Objects for the specified
     * id using a recursive binary search algorithm
     *
     * @param idToSearch Id value being search for
     * @param first      Starting index of search range
     * @param last       Ending index of search range
     * @return index of SearchItem if found, -1 if not found
     */

    private int bsearch(SearchItem idToSearch, int first, int last) {
        SearchItem input = new SearchItem(idToSearch.getId(), idToSearch.getInv());
        int indexOfCheck = (first + last) / 2;
        int idOfSearch = input.getId();
        int idOfCheck = mySearchStore.get(indexOfCheck).getId();

        if (idOfSearch == idOfCheck) {
            return indexOfCheck;
        } else if (idOfSearch < idOfCheck) {
            bsearch(input, first, indexOfCheck);
        } else { // recursion, divide list into two halves
            //indexOfCheck = (indexOfCheck + last) / 2;
            bsearch(input, indexOfCheck, last);
        }
        return -1;
    }
}