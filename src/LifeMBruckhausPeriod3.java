/*  Maxwell Bruckhaus, Period 3, February 1st, 2015
 *  This program took me approximately 4 hours to
 *  complete.
 */


import java.io.File;
import java.util.Scanner;

public class LifeMBruckhausPeriod3 {

    int[][] board;
    int x = 0;
    int y = 0;
    int[] horizontal = { -1, 0, 1, -1, 1, -1, 0, 1 };
    int[] vertical = { -1, -1, -1, 0, 0, 0, 1, 1, 1 };

    public static void main(String[] args){
        String path = "/Users/MAXIMILLION/Documents/dev/bluej/src/";
        LifeMBruckhausPeriod3 life = new LifeMBruckhausPeriod3(path + "life100.txt");
        life.runLife(4);
        life.printBoard();
    }

    public LifeMBruckhausPeriod3(String fileName) {
        File file = new File(fileName);
        try {
            Scanner input = new Scanner(file);
            x = input.nextInt();
            y = input.nextInt();
            board = new int[x][y];
            while (input.hasNextLine()) {
                board[input.nextInt()][input.nextInt()] = 1;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }

    }

    public void runLife(int numGenerations) {
        // complete this as many times as the generation you are trying to get
        for (int i = 1; i < numGenerations; i++) {
            int[][] temp = new int[x][y];
            // go through the entire board
            for (int row = 0; row < x; row++) {
                for (int col = 0; col < y; col++) {
                    boolean living;
                    int alive = 0;
                    // check if the spot is living or not
                    if (board[row][col] == 1) living = true;
                    else {
                        living = false;
                    }
                    // for each spot, go through each of the neighbors, and
                    // count how many are living
                    for (int j = 0; j < 8; j++) {
                        // if the position is not out of bounds
                        if ((row + horizontal[j]) >= 0
                                && (row + horizontal[j]) < x
                                && (col + vertical[j]) >= 0
                                && (col + vertical[j]) < y) {
                            // if the position is living
                            if (board[row + horizontal[j]][col + vertical[j]] == 1) {
                                alive++;
                            }
                        }
                    }
                    // decide whether you should make it alive in the new array
                    if (living) {
                        if (alive == 2 || alive == 3) {
                            temp[row][col] = 1;
                        }
                    } else {
                        if (alive == 3) {
                            temp[row][col] = 1;
                        }
                    }
                }
            }
            board = temp;
            printBoard();
        }
    }

    public int rowCount(int row) {
        if (row > x || row < 0) {
            return -1;
        }
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[row][j] == 1) {
                    if (row == i) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public int colCount(int col) {
        if (col > y || col < 0) {
            return -1;
        }
        int count = 0;
        for (int row = 0; row < board.length; row++) {
            for (int j = 0; j < board[row].length; j++) {
                if (board[row][j] == 1) {
                    if (col == j) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public int totalCount() {
        int count = 0;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    public void printBoard() {
        System.out.print("    ");
        for (int i = 0; i <= x; i++) {
            System.out.printf("%4d", i);
        }
        System.out.println();
        for (int row = 0; row < board.length; row++) {
            System.out.printf("%4d", row);
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == 1) {
                    System.out.printf("%4s", "*");
                } else {
                    System.out.printf("%4s", " ");
                }
            }

            System.out.println();
        }
    }
}