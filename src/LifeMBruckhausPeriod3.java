/*  Maxwell Bruckhaus, Period 3, February 1st, 2015
 *  This program took me approximately 3 hours to
 *  complete.
 */



import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class LifeMBruckhausPeriod3 {


    public static void main(String[] args) {
        String path = "/Users/MAXIMILLION/Documents/dev/bluej/src/";
        LifeMBruckhausPeriod3 life = new LifeMBruckhausPeriod3(path + "life100.txt");
        life.printGrid();
        life.runLife(5);
        System.out.println(life.rowCount(9));
        System.out.println(life.colCount(9));
        System.out.println(life.totalCount());
        life.printGrid();
    }

    int[][] grid;
    int[][] tempGrid;
    LifeMBruckhausPeriod3(String s){
        Scanner scan = null;
        try {
            String path = "/Users/MAXIMILLION/Documents/dev/bluej/src/";
            scan = new Scanner(new File(path + "life100.txt"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        grid = new int[scan.nextInt()][scan.nextInt()];
        tempGrid = new int[grid.length][grid[0].length];
        while(scan.hasNext()){
            grid[scan.nextInt()][scan.nextInt()] = 1;
        }
    }
    public void runLife(int numGenerations){
        for(int i = 0; i < numGenerations; i++){
            nextGeneration();
        }
    }

    public void nextGeneration(){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                int around = 0;
                int x = (i == 0)?0:-1;
                int endX = (i == 19)?0:1;
                int y = (j == 0)?0:-1;
                int endY = (j == 19)?0:1;
                for(x=x; x <= endX; x++){
                    for(y=y; y <= endY; y++){
                        int check = grid[i+x][j+y];
                        //if(i != 0 && j != 0){
                        //System.out.println("x = " + (i+x) + " y = " + (j+y));
                        //System.out.println(grid[1][0]);
                        if(!(x == 0 && y == 0)){
                            if(grid[i+x][j+y] == 1){
                                around++;
                            }
                        }
                        //}
                    }
                    y = (j == 0)?0:-1;
                }
                if(around == 3){
                    tempGrid[i][j] = 1;
                }else if(around < 2 || around > 3){
                    tempGrid[i][j] = 0;
                }else{
                    tempGrid[i][j] = grid[i][j];
                }
            }
        }
        moveGrid();
    }

    private void moveGrid() {
        for(int i = 0 ; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                grid[i][j] = tempGrid[i][j];
                tempGrid[i][j] = 0;
            }
        }
    }

    public int rowCount(int row) {
        int num = 0;
        if(row < 0 || row > grid.length - 1){
            return -1;
        }
        for(int j = 0; j < grid.length; j++){
            if(grid[row][j] == 1){
                num++;
            }
        }
        return num;
    }
    // Method that returns the number of living cells in the given column
    // or returns -1 if column is out of bounds.  The first column is column 0.
    public int colCount(int col) {
        int num = 0;
        if(col < 0 || col > grid.length - 1){
            return -1;
        }
        for(int j = 0; j < grid.length; j++){
            if(grid[j][col] == 1){
                num++;
            }
        }
        return num;
    }
    // Method that returns the total number of living cells on the board
    public int totalCount() {
        int num = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1){
                    num++;
                }
            }
        }
        return num;
    }

    public void printGrid(){
        System.out.print("  ");
        for(int i = 0; i < grid.length; i++){
            System.out.print(i%10);
        }
        System.out.println();
        for(int i = 0; i < grid.length; i++){
            System.out.printf("%2d",i);
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1){
                    System.out.print("*");
                }else{
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

}