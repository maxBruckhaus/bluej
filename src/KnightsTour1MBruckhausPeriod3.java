/*  Maxwell Bruckhaus, Period 3, January 26th, 2015
 *  This program took me approximately 90 minutes to
 *  complete in total. I knew that this lab was going
 *  to be challenging but it was much more of a struggle
 *  than I had originally thought. This lab contains
 *  many of my weak spots. I still need to improve on
 *  building arrays and manipulating them. I had trouble
 *  understanding what made a move legal. In chess terms
 *  I understand how to play but I had trouble translating
 *  that into code, although the example code from the
 *  lab instructions was helpful. It was also difficult
 *  for me to figure out how to try out all possible
 *  moves while still making the move random.
 */

import java.util.Random;

public class KnightsTour1MBruckhausPeriod3 {

    private int[][] board = new int[9][9];
    private int[] vertical = new int[]{+1, +2, +2, +1, -1, -2, -2, -1};
    private int[] horizontal = new int[]{-2, -1, +1, +2, +2, +1, -1, -2};
    private Random r = new Random();
    private int row = 1;
    private int col = 1;
    private int moveCounter = 1;
    private boolean stuckFlag = false;

    public static void main(String[] args) {
        KnightsTour1MBruckhausPeriod3 knight = new KnightsTour1MBruckhausPeriod3();
        knight.solve();
    }

    public KnightsTour1MBruckhausPeriod3() {
        this.board[1][1] = 1;
    }

    public void solve() {
        while (!stuck()) {
            move();
        }
        show();
    }

    private void move() {
        int moveNumber = r.nextInt(8);
        for (int tries = 0; tries < 8; tries++) {
            int movePossibility = (moveNumber + tries) % 8;
            if (legalMove(movePossibility)) {
                moveCounter++;
                row = row + vertical[movePossibility];
                col = col + horizontal[movePossibility];
                board[row][col] = moveCounter;
                return;
            }
        }
        stuckFlag = true;
    }

    private boolean legalMove(int movePossibility) {
        int nextRow = row + vertical[movePossibility];
        int nextCol = col + horizontal[movePossibility];
        //System.out.println("movePossibility = " + movePossibility);
        //System.out.println("nextRow = " + nextRow);
        //System.out.println("nextCol = " + nextCol);
        return nextRow >= 1 &&
                nextRow <= 8 &&
                nextCol >= 1 &&
                nextCol <= 8 &&
                board[nextRow][nextCol] == 0;
    }

    private void show() {
        System.out.println("     1  2  3  4  5  6  7  8\n");

        for (int i = 1; i <= 8; i++) {
            System.out.print(i + "  ");
            for (int j = 1; j <= 8; j++) {
                System.out.printf("%3d", board[i][j]);
            }
            System.out.println();
        }
        System.out.println("\n" + moveCounter + " squares were visited.");
    }

    private boolean stuck() {
        return stuckFlag;
    }
}