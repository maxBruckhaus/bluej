import java.util.*;

public class RandomIntegers {

    public void printLists() {
        List list = new ArrayList();
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            list.add(2 + rand.nextInt(6));
        }
        System.out.println("Original list:" + list);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == 5) {
                list.remove(i--);
            }
        }
        System.out.println("Final List:" + list);
    }

    public void printLists2() {
        ArrayList<Integer> list = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            list.add(2 + rand.nextInt(6));
        }
        System.out.println("Original list:" + list);
        for (int i = 0; i < list.size(); i++) {
            int temp2 = list.get(i);
            if (temp2 == 5 || temp2 == 3) {
                list.remove(i--);
            }
        }
        System.out.println("Final List:" + list);
    }

    public void printLists3() {
        List list = new ArrayList();
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            list.add(2 + rand.nextInt(6));
        }
        System.out.println("Original list:" + list);
        Iterator x = list.iterator();
        while (x.hasNext()) {
            int temp3 = (int) x.next();
            if (temp3 == 5 || temp3 == 3) {
                x.remove();
            }
        }
        System.out.println("Final List:" + list);
    }

    public static void clearScreen() {
        System.out.print('\u000C');
    }

    public static void main(String[] args) {
        clearScreen();
        RandomIntegers rand = new RandomIntegers();
        rand.printLists();
        rand.printLists2();
        rand.printLists3();
    }
}