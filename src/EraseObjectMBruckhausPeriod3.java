/*  Maxwell Bruckhaus, Period 3, February 4th, 2015
 *  I turned the program in at 12:01 because I realized
 *  I forgot to name my file with my name and period.
 *  I wasn't able to change the name so I panicked and
 *  turned it in how it was a minute too late and now
 *  I am turning it in again with the name fixed.
 *
 *  This program took me approximately 1.5 hours to
 *  complete in total. When I first received the
 *  assignment I was really lost; I didn't know
 *  where to start. After looking over the
 *  worksheet from class many times and
 *  asking my dad for help I finally eventually
 *  understood the program and was able to complete
 *  it. I think the directions of the lab
 *  confused me when I read them the first time
 *  but with the help of others I was able to
 *  be more clear on what was going on.
 */

import java.io.*;
import java.util.*;


public class EraseObjectMBruckhausPeriod3 {
    char[][] grid = new char[20][20];

    public static void main(String[] args) {
        EraseObjectMBruckhausPeriod3 max = new EraseObjectMBruckhausPeriod3();
        String path = "/Users/MAXIMILLION/Documents/dev/bluej/src/";
        max.loadFile(path + "digital.txt");
        int cont;
        do{
            Scanner in = new Scanner(System.in);
            System.out.print("Enter the row to start erasing: ");
            int r = in.nextInt();
            System.out.print("Enter the col to start erasing: ");
            int c = in.nextInt();
            System.out.println();
            max.erase(r - 1, c - 1);
            max.print();
            System.out.print("Press 1 to continue erasing ");
            cont = in.nextInt();
        } while(cont == 1);
    }

    public void erase(int r, int c){
        if(grid[r][c] == '_'){
            return;
        } else {
            grid[r][c] = '_';
            if (r != 0){
            erase(r - 1, c);
            }else if(c != 19){
            erase(r, c + 1);
            }else if(r != 19){
                erase(r + 1, c);
            }else if(c != 0) {
                erase(r, c - 1);
            }
        }
    }

    public void loadFile(String fileName){
        File f = new File(fileName);
        Scanner file = null;
        try{
            file = new Scanner(f);
        } catch(IOException e){
            e.printStackTrace();
        }

        for(int i = 0; i < 20; i++)
            for(int j = 0; j < 20; j++)
                grid[i][j] = '_';

        Integer.parseInt(file.nextLine());

        while(file.hasNextLine()){
            String l = file.nextLine();
            Scanner line = new Scanner(l);
            grid[line.nextInt() - 1][line.nextInt() - 1] = '@';

        }
        print();
    }

    public void print(){
        System.out.printf("%3s", " ");
        for(int i = 1; i < 21; i++){
            System.out.printf("%3s", i);
        }
        System.out.println();
        for(int i = 0; i < grid.length; i++){
            System.out.printf("%3s", i + 1);
            for(int j = 0; j < grid[0].length; j++){
                System.out.printf("%3s", grid[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
