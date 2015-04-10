/*  Maxwell Bruckhaus, Period 3, February 24th, 2015
*  This program took me approximately
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class LifeGUIMBruckhausPeriod3 {

    public static void main(String[] args) {
        MyGUI3 gui = new MyGUI3();
    }
}

class MyGUI3 implements ActionListener, MouseMotionListener, MouseListener {

    // Attributes
    Color color = Color.RED;
    MyDrawingPanel drawingPanel;
    JFrame window;
    Color[][] colors = new Color[25][25];


    MyGUI3() {

        System.out.print("The program is running...\n");
        // Create Java Window
        JFrame window = new JFrame("LifeGUI");
        window.setBounds(100, 100, 445, 600);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create GUI elements

        // JPanel to draw in
        drawingPanel = new MyDrawingPanel();
        drawingPanel.setBounds(20, 20, 400, 400);
        drawingPanel.setBorder(BorderFactory.createEtchedBorder());

        // JButton
        JButton button = new JButton("Reset");
        button.setBounds(190, 510, 75, 20);

        // JRadioButtons
        JRadioButton radioButton1 = new JRadioButton("Red", true);
        JRadioButton radioButton2 = new JRadioButton("Green");
        JRadioButton radioButton3 = new JRadioButton("Blue");
        JRadioButton radioButton4 = new JRadioButton("Custom");

        radioButton1.setBounds(50, 75, 100, 20);
        radioButton2.setBounds(50, 100, 100, 20);
        radioButton3.setBounds(50, 125, 100, 20);
        radioButton4.setBounds(50, 150, 100, 200);

        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(radioButton1);
        radioGroup.add(radioButton2);
        radioGroup.add(radioButton3);
        radioGroup.add(radioButton4);

        // Menu
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");

        JMenuItem menuOpen = new JMenuItem("Open", 'o');
        JMenuItem menuSave = new JMenuItem("Save", 's');
        JMenuItem menuClear = new JMenuItem("Clear", 'c');

        fileMenu.add(menuOpen);
        fileMenu.add(menuSave);
        editMenu.add(menuClear);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        window.setJMenuBar(menuBar);


        // Add GUI elements to the Java window's ContentPane
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);

        JPanel colorPanel = new JPanel();
        colorPanel.setBorder(BorderFactory.createTitledBorder("Drawing Color"));

        colorPanel.setBounds(120, 425, 200, 70);
        colorPanel.add(radioButton1);
        colorPanel.add(radioButton2);
        colorPanel.add(radioButton3);
        colorPanel.add(radioButton4);

        mainPanel.add(drawingPanel);

        mainPanel.add(colorPanel);
        mainPanel.add(button);

        window.getContentPane().add(mainPanel);
        drawingPanel.addMouseMotionListener(this);
        drawingPanel.addMouseListener(this);
        radioButton1.addActionListener(this);
        radioButton2.addActionListener(this);
        radioButton3.addActionListener(this);
        radioButton4.addActionListener(this);
        button.addActionListener(this);
        menuOpen.addActionListener(this);
        menuSave.addActionListener(this);
        menuClear.addActionListener(this);


        // Initialize colors
        System.out.println("colors.length: " + colors.length);
        for (int i = 0; i < colors.length; i++){
            for (int j = 0; j < colors.length; j++){
                colors[i][j] = Color.white;
            }
        }

        // Let there be light
        window.setVisible(true);
    }

    public void loadFile(File file){
        Scanner sc = null;
        try{
            sc = new Scanner(file);
        }
        catch (IOException e){
            e.getMessage();
        }
        colors = new Color[25][25];
        for(int i = 0; i < 3; i++){
            assert sc != null;
            sc.nextLine();
        }
        loadColors(sc);
    }

    private void loadColors(Scanner sc) {
        for(int i = 0; i < 25; i++){
            for(int j = 0; j < 25; j++){
                int red = sc.nextInt();
                int green = sc.nextInt();
                int blue = sc.nextInt();
                color = new Color(red, green, blue);
                applyColor(i * 20, j * 20);
                //System.out.printf("Set color for cell (%d, %d) to (%d, %d, %d)\n",
                //        i, j, red, green, blue);
            }
        }
    }

    public void save(){
        JFileChooser chooser = new JFileChooser();
        chooser.showSaveDialog(null);
        BufferedWriter writer = null;
        try {
            File file = chooser.getSelectedFile();
            writer = new BufferedWriter(new FileWriter(file));
        } catch (IOException e) {
            e.getMessage();
        }
        try {
            writer.write("P3\n25 25\n255\n");
            writeColors(writer);
            writer.close();
        } catch (Exception e) {
            e.getMessage();
            System.out.println("");
        }
    }

    private void writeColors(BufferedWriter writer) throws IOException {
        String colorString;
        for (Color[] colorRow : colors) {
            for (int j = 0; j < colors.length; j++) {
                if (colorRow[j] == null) {
                    colorString = "255\t255\t255\t";
                    writer.write(colorString);
                    System.out.print(colorString);
                } else {
                    Color color = colorRow[j];
                    int red = color.getRed();
                    int green = color.getGreen();
                    int blue = color.getBlue();
                    colorString = red + "\t" + green + "\t" + blue + "\t";
                    writer.write(colorString);
                    System.out.print(colorString);
                }
            }
            writer.write("\n");
            System.out.print("\n");
        }
    }

    public void actionPerformed(ActionEvent e) {

        System.out.println("Action -> " + e.getActionCommand());

        if (e.getActionCommand().equals("Clear")){
            drawingPanel.repaint();
        }
        else if (e.getActionCommand().equals("Open")){
            JFileChooser fc = new JFileChooser();
            fc.showOpenDialog(window);
            File f = fc.getSelectedFile();
            System.out.println("You chose file " + f);
            loadFile(f);
            color = Color.RED;
        }
        else if (e.getActionCommand().equals("Save")){
            save();
        }
        else if (e.getActionCommand() != null){
            if (e.getActionCommand().equals("Red")) {
                color = Color.RED;
            }else if (e.getActionCommand().equals("Green")) {
                color = Color.GREEN;
            }else if (e.getActionCommand().equals("Blue")) {
                color = Color.BLUE;
            }else if (e.getActionCommand().equals("Custom")){
                Color c = JColorChooser.showDialog(null, "Pick", Color.black);
                color = c;
                System.out.println("You chose" + c);
            }
            if (e.getActionCommand().equals("Reset")) {
                clearDraw();
            }
        }
    }

    public void clearDraw() {
        drawingPanel.repaint();
        drawingPanel.paintComponent(drawingPanel.getGraphics());
    }

    public void mouseClicked(MouseEvent e) {
        int x;
        int y;
        if (e.getButton() == InputEvent.BUTTON1_DOWN_MASK){
            x = getCoordinateX(e);
            y = getCoordinateY(e);
            applyColor(x, y);
        }else if (e.getButton() == InputEvent.BUTTON2_DOWN_MASK){
            x = getCoordinateX(e);
            y = getCoordinateY(e);
            color = Color.white;
            applyColor(x, y);
        }
    }

    public void mouseDragged(MouseEvent e) {
        int x;
        int y;
        if (e.getModifiersEx() == InputEvent.BUTTON1_DOWN_MASK){
            x = getCoordinateX(e);
            y = getCoordinateY(e);
            applyColor(x, y);
        }else if (e.getModifiersEx() == InputEvent.BUTTON2_DOWN_MASK){
            x = getCoordinateX(e);
            y = getCoordinateY(e);
            color = Color.white;
            applyColor(x, y);
        }
    }

    public void mousePressed(MouseEvent e) {
        // Nothing
    }

    public void mouseReleased(MouseEvent e) {
        // Nothing
    }

    public void mouseEntered(MouseEvent e) {
        // Nothing
    }

    public void mouseExited(MouseEvent e) {
        // Nothing
    }

    public void mouseMoved(MouseEvent e) {
        // Nothing
    }

    private class MyDrawingPanel extends JPanel {

        // Not required, but gets rid of the serialVersionUID warning.  Google it, if desired.
        static final long serialVersionUID = 1234567890L;

        public void paintComponent(Graphics g) {
            g.setColor(Color.white);
            g.fillRect(2, 2, this.getWidth() - 2, this.getHeight() - 2);
            g.setColor(Color.lightGray);
            for (int x = 0; x < this.getWidth(); x += 20) {
                g.drawLine(x, 0, x, this.getHeight());
            }
            for (int y = 0; y < this.getHeight(); y += 20){
                g.drawLine(0, y, this.getWidth(), y);

            }
        }
    }

    private int getCoordinateX(MouseEvent e) {
        int x = 0;
        for (int i = 0; i <= drawingPanel.getWidth(); i += 20) {
            if (e.getX() < i) {
                int remainder = (e.getX() % 20);
                x = e.getX() - remainder;
                break;
            }
        }
        return x;
    }

    private int getCoordinateY(MouseEvent e) {
        int y = 0;
        for(int i = 0; i <= drawingPanel.getHeight(); i += 20){
            if (e.getY() < i) {
                int remainder = (e.getY() % 20);
                y = e.getY() - remainder;
                break;
            }
        }
        return y;
    }

    private void applyColor(int x, int y) {
        int j = y / 20;
        int i = x / 20;
        if (outOfBounds(j, i)) return;
        colors[i][j] = color;
        Graphics g = drawingPanel.getGraphics();
        g.setColor(color);
        g.fillRect(x + 2, y + 2, 16, 16);
    }

    private boolean outOfBounds(int j, int i) {
        return i < 0 || j < 0 || i >= colors.length || j >= colors.length;
    }
}






/*
---------------------------------------------------------------------------------------------

public class LifeGUIMBruckhausPeriod3 {
    int[][] board;
    int x = 0;
    int y = 0;
    int[] horizontal = { -1, 0, 1, -1, 1, -1, 0, 1 };
    int[] vertical = { -1, -1, -1, 0, 0, 0, 1, 1, 1 };

    public static void main(String[] args){
        String path = "/Users/MAXIMILLION/Documents/dev/bluej/src/";
        LifeMBruckhausPeriod3 life = new LifeMBruckhausPeriod3(path + "life100.txt");
        life.runLife(3);
        life.printBoard();
    }

    public LifeGUIMBruckhausPeriod3(String fileName) {
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
                    if (board[row][col] == 1) {
                        living = true;
                    } else {
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
*/