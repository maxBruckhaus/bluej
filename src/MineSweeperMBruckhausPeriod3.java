/*  Maxwell Bruckhaus, Period 3, March 5th, 2015
 *  This program took me approximately 5 hours
 *  to almost complete. I understand the logic
 *  behind the minesweeper game but I have been
 *  having trouble translating everything into
 *  code. I still need to learn how to transfer my
 *  console minesweeper into gui. I came up with a plan
 *  and tried to follow it as closely as possible but
 *  couldn't end up finishing the program in time. My brain
 *  was getting a little bit fried after working on the code
 *  for a long time. I like challenges like this but I think
 *  I need more time in order to actually finish the game to
 *  get it to the way I would like it to be.
 */



/* Plan:
   - Randomly place mines throughout
   - Make a for loop that calculates number of mine neighbors
     and sets that grid to the num of neighbors which will initialize the board

   Bottom layer has mines, numbers, and blank squares.
   Top layer has all the solid grey tiles waiting to be uncovered.
   When you click on the top layer it becomes transparent, revealing the
   bottom layer underneath it.
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;


/*

class boardTopLayer{
    // The top layer of the minesweeper board

    private boolean revealed;
    private char value;

    public boardTopLayer(char v){
        revealed=false;
        value=v;
    }

    public String toString(){
        if (revealed)
            return (value+"");
        else
            return "-";
    }
}



class  boardBottomLayer{

    // The bottom layer of the minesweeper board
    private int rowSize;
    private int colSize;
    private int numMines;
    private int uncovered = 0;
    private boardTopLayer[][] board;

    public boardBottomLayer(int row, int col, int mines){
        //public constructor to initialize board size and mine count to parameters.
        int rowSize = row;
        int colSize = col;
        numMines = mines;
        board = new boardTopLayer[rowSize][colSize];
        uncovered = 0;
        placeMines();

    }

    public boardBottomLayer(){
        //default constructor to create a 5x5 game with 6 mines.
        this(5,5,6);
    }

    private void placeMines(){
        int r = 0;
        int c = 0;
        int mines = numMines;
        Random rand = new Random();
        for (int row = 0; row < rowSize; row++)
            for (int col = 0 ; col < colSize; col++) {
                board[r][c]=new boardTopLayer('?');          //just to instantiate the squares
            }

        while (mines>0){                            //loop to place the mines.
            r = rand.nextInt(rowSize);
            c = rand.nextInt(colSize);
            if ((board[r][c]).getValue()!='*'){
                board[r][c]=new boardTopLayer('*');
                mines--;
            }
        }

    }

    private int mineCount(int r, int c){
        // private method to return an integer for the number of mines
        //  surrounding the square at [r][c].
        int count = 0;
        for (int y = -1; y <= 1; y++){
            for (int x =- 1; x <= 1; x++){
                if (y == 0 && x == 0) continue;
                if (r+y < 0 || r+y >= rowSize) continue;
                if (c+x < 0 || c+x >= colSize) continue;
                if (board[r+y][c+x].getValue()=='*'){
                    count++;
                }
            }
        }
        return count;
    }

    private boolean reveal(int row, int col){
        //public method that reveals the square at the current spot

        //checks to see if already revealed or out of bounds
        if (row < 0 || row >= rowSize || col < 0 || col >= colSize || board[row][col].isRevealed()){
            return true;
        }

        board[row][col].setRevealed(true);

        if (board[row][col].getValue()=='*'){   // Hit a mine
            return false;
        }else{
            // Set value
            board[row][col].setValue((char)(mineCount(row,col)+'0'));
            uncovered++;
            if (board[row][col].getValue()=='0'){
                reveal(row - 1, col - 1);
                reveal(row - 1, col);
                reveal(row - 1, col + 1);
                reveal(row, col - 1);
                reveal(row, col + 1);
                reveal(row + 1, col - 1);
                reveal(row + 1, col);
                reveal(row + 1, col+1);
            }
            return true;
        }
        // show the second layer of the spot you're at
    }

    public boolean hasWon(){
        return (uncovered == (rowSize * colSize - numMines));
    }

    public String toString(){
        // Converts the game state into a string for output
        int r;
        int c;
        String out="  |";

        for (c = 0; c < colSize;c++)
            out+=Integer.toString(c)+" ";
        out+="\n==+";
        for (c = 0; c < colSize; c++)
            out+="==";
        out+="\n";

        for (r = 0; r < rowSize; r++){
            out+=(r+" |");
            for (c = 0; c < colSize; c++){
                out+=(board[r][c]+ " ");
            }
            out+="\n";
        }
        return out;
    }





    OLD METHOD

    public void addMineCount(int x, int y){
        if(!outOfBounds(x - 1, y)){
            board[x-1][y] += 1;
        }
        if(!outOfBounds(x + 1, y)){
            board[x+1][y] += 1;
        }
        if(!outOfBounds(x - 1, y + 1)){
            board[x-1][y+1] += 1;
        }
        if(!outOfBounds(x + 1, y - 1)){
            board[x+1][y-1] += 1;
        }
        if(!outOfBounds(x, y - 1)){
            board[x][y-1] += 1;
        }
        if(!outOfBounds(x, y + 1)){
            board[x][y+1] += 1;
        }
        if(!outOfBounds(x + 1, y + 1)){
            board[x+1][y+1] += 1;
        }
        if(!outOfBounds(x - 1, y - 1)){
            board[x-1][y-1] += 1;
        }
    }

    private int adjacentMines(int row, int col){
        int mineCount = 0;
        for (int j = 0; j < 8; j++) {
            // if the position is not out of bounds
            if (true){ //(mine is adjacent from 1/8 possibilities){
                mineCount++;
            }else if (true){ //(other 7/8){
                mineCount++;
            }else if (true){ //(board[row + horizontal[j]][col + vertical[j]] >= 1) {
                // Nothing
            }
        }
        return mineCount;
    }

    private void uncover() {
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                if (adjacentMines(row, col) == 0) {
                    uncover(); // reveal all the adjacent squares
                    if (adjacentMines(row, col) == 0){
                        reveal();
                    }else{
                        reveal();
                    }
                }
            }
        }
    }

}



class General {

    MineBoard game;

    public General(int rows, int cols, int mines) throws IOException {
        int r;
        int c;
        StringTokenizer strTok;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        game = new MineBoard(rows, cols, mines);
        boolean won = false;

        do {
            System.out.println(game);
            System.out.print("Enter row and column>");
            strTok = new StringTokenizer(in.readLine(), " ,");
            if (!strTok.hasMoreTokens()) r = -1;
            else r = Integer.parseInt(strTok.nextToken());
            if (!strTok.hasMoreTokens()) c = -1;
            else c = Integer.parseInt(strTok.nextToken());

        } while (game.reveal(r, c) && !(won = game.hasWon()));

        for (r = 0; r < rows; r++)
            for (c = 0; c < cols; c++)
                game.reveal(r, c);
        System.out.println(game);

        if (won) {
            System.out.println(" Congratulations!\n    You win!");
        } else {
            System.out.println(" Uh oh!\n  You Lose!");
        }
    }

    public static void main(String args[]) throws IOException {
        int row = 5;
        int col = 5;
        int mines = 6;
        if (args.length == 3) {
            row = Integer.parseInt(args[0]);
            col = Integer.parseInt(args[1]);
            mines = Integer.parseInt(args[2]);
        }
        new General(10, 10, 10);
    }

}

*/


