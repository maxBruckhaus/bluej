/*  Maxwell Bruckhaus, Period 3, January 29th, 2015
 *  This program took me approximately 30 minutes
 *  to complete in total. It took a very short
 *  amount of time since I already coded
 *  knightstour1 and understood what I was doing
 *  in that program. I just needed to implement
 *  the best move to possible out of all the moves
 *  in this program using bestMove. Once I did that
 *  the rest was easy. The only hard part was figuring
 *  out where to code the bestMove because I was
 *  confused with the row and column being one integer
 *  instead of a point on a graph.r
 */

import static java.lang.System.out;

import java.util.Random;

public class KnightsTour2MBruckhausPeriod3 {

    private int[][] board = new int[9][9];
    private int[] vertical = new int[]{+1, +2, +2, +1, -1, -2, -2, -1};
    private int[] horizontal = new int[]{-2, -1, +1, +2, +2, +1, -1, -2};
    private Random r = new Random();
    private int row = 1;
    private int col = 1;
    private int moveCounter = 1;
    private boolean stuckFlag = false;

    public static void main(String[] args) {
        KnightsTour2MBruckhausPeriod3 knight = new KnightsTour2MBruckhausPeriod3();
        //knight.solve();
        knight.showNeighbors();
    }

    public KnightsTour2MBruckhausPeriod3() {
        this.board[1][1] = 1;
    }

    public void solve() {
        while (!stuck()) {
            move();
        }
        show();
    }

    private void move() {
        int bestMove = bestMove();
        if (bestMove != -1) {
            moveKnight(bestMove);
        } else {
            stuckFlag = true;
        }
    }

    private int bestMove() {
        int bestMove = -1;
        int minNeighbors = Integer.MAX_VALUE;
        int moveNumber = r.nextInt(8);
        for (int tries = 0; tries < 8; tries++) {
            int movePossibility = (moveNumber + tries) % 8;
            int neighbors = numNeighbors(movePossibility);
            if (legalMove(movePossibility) && neighbors < minNeighbors) {
                minNeighbors = neighbors;
                bestMove = movePossibility;
            }
        }
        return bestMove;
    }

    private void moveKnight(int move) {
        moveCounter++;
        row = row + vertical[move];
        col = col + horizontal[move];
        board[row][col] = moveCounter;
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

    private int numNeighbors(int move) {
        int i = row + vertical[move];
        int j = col + horizontal[move];
        return numNeighbors(i, j);
    }

    private int numNeighbors(int i, int j) {
        return i == 1 || i == 8 ?
                Math.min(4, Math.min(j + 1, 10 - j)) :
                i == 2 || i == 7 ?
                        j >= 3 && j <= 6 ?
                                6 : Math.min(j + 2, 11 - j) :
                        j >= 3 && j <= 6 ?
                                8 : j == 1 || j == 8 ? 4 : 6;
    }

    private boolean stuck() {
        return stuckFlag;
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

    private void showNeighbors() {
        out.println("         1   2   3   4   5   6   7   8\n");
        for (int i = 1; i <= 8; i++) {
            out.printf("%4d  ", i);
            for (int j = 1; j <= 8; j++) {
                out.printf("%4d", numNeighbors(i, j));
            }
            out.println();
        }
    }
}