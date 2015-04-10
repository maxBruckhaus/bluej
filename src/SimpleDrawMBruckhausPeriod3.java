/*  Maxwell Bruckhaus, Period 3, February 13th, 2015
 *  This program took me approximately 4.5 hours to
 *  complete in total. I had a lot of struggle while
 *  coding this. The x and y coordinates of the
 *  rectangle gave me a lot of trouble as well as how
 *  to actually distinguish between left and right
 *  clicking. Another thing that gave me trouble
 *  was figuring out where to place all the different
 *  parts of my code. I feel like I'm slowly starting
 *  to understand GUI more and more but I'm still
 *  struggling a little bit.
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

/* 1. Add the capability to capture mouse clicks using MouseListener
 * 2. Add the capability to capture mouse drags using MouseMotionListener
 */

public class SimpleDrawMBruckhausPeriod3 {

    public static void main(String[] args) {
        MyGUI gui = new MyGUI();
    }
}

class MyGUI implements ActionListener, MouseMotionListener, MouseListener {

    // Attributes
    Color color = Color.RED;
    MyDrawingPanel drawingPanel;


    MyGUI() {

        // Create Java Window
        JFrame window = new JFrame("SimpleDraw");
        window.setBounds(100, 100, 445, 600);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create GUI elements

        // JPanel to draw in
        drawingPanel = new MyDrawingPanel();
        drawingPanel.setBounds(20, 20, 400,400);
        drawingPanel.setBorder(BorderFactory.createEtchedBorder());

        // JButton
        JButton button = new JButton("Reset");
        button.setBounds(190, 510, 75, 20);

        // JRadioButtons
        JRadioButton radioButton1 = new JRadioButton("Red", true);
        JRadioButton radioButton2 = new JRadioButton("Green");
        JRadioButton radioButton3 = new JRadioButton("Blue");

        radioButton1.setBounds(50, 75, 100, 20);
        radioButton2.setBounds(50, 100, 100, 20);
        radioButton3.setBounds(50, 125, 100, 20);

        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(radioButton1);
        radioGroup.add(radioButton2);
        radioGroup.add(radioButton3);

        // Add GUI elements to the Java window's ContentPane
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);

        JPanel colorPanel = new JPanel();
        colorPanel.setBorder(BorderFactory.createTitledBorder("Drawing Color"));

        colorPanel.setBounds(120, 425, 200, 70);
        colorPanel.add(radioButton1);
        colorPanel.add(radioButton2);
        colorPanel.add(radioButton3);

        mainPanel.add(drawingPanel);

        mainPanel.add(colorPanel);
        mainPanel.add(button);

        window.getContentPane().add(mainPanel);
        drawingPanel.addMouseMotionListener(this);
        drawingPanel.addMouseListener(this);
        radioButton1.addActionListener(this);
        radioButton2.addActionListener(this);
        radioButton3.addActionListener(this);
        button.addActionListener(this);

        // Let there be light
        window.setVisible(true);

    }




    public void actionPerformed(ActionEvent e) {

        System.out.println("Action -> " + e.getActionCommand());

        if (e.getActionCommand() != null){
            if (e.getActionCommand().equals("Red"))
                color = Color.RED;
            else if (e.getActionCommand().equals("Green"))
                color = Color.GREEN;
            else if (e.getActionCommand().equals("Blue"))
                color = Color.BLUE;
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
        int x = 0;
        int y = 0;
        if (e.getButton() == InputEvent.BUTTON1_DOWN_MASK){
            for(int i = 0; i <= drawingPanel.getWidth(); i += 20){
                if(e.getX() < i){
                    int remainder = (e.getX() % 20);
                    x = e.getX() - remainder;
                    break;
                }
            }
            for(int i = 0; i <= drawingPanel.getHeight(); i += 20){
                if (e.getY() < i) {
                    int remainder = (e.getY() % 20);
                    y = e.getY() - remainder;
                }
            }
            Graphics g = drawingPanel.getGraphics();
            g.setColor(color);
            g.fillRect(x, y, 16, 16);
        }else if (e.getButton() == InputEvent.BUTTON2_DOWN_MASK){
            for(int i = 0; i <= drawingPanel.getWidth(); i += 20){
                if(e.getX() < i){
                    int remainder = (e.getX() % 20);
                    x = e.getX() - remainder;
                    break;
                }
            }
            for(int i = 0; i <= drawingPanel.getHeight(); i += 20){
                if (e.getY() < i) {
                    int remainder = (e.getY() % 20);
                    y = e.getY() - remainder;
                }
            }
            Graphics g = drawingPanel.getGraphics();
            g.setColor(Color.white);
            g.fillRect(x, y, 16, 16);
        }
    }

    public void mouseDragged(MouseEvent e) {
        int x = 0;
        int y = 0;
        if (e.getModifiersEx() == InputEvent.BUTTON1_DOWN_MASK){
            for(int i = 0; i <= drawingPanel.getWidth(); i += 20){
                if(e.getX() < i){
                    int remainder = (e.getX() % 20);
                    x = e.getX() - remainder;
                    break;
                }
            }
            for(int i = 0; i <= drawingPanel.getHeight(); i += 20){
                if (e.getY() < i) {
                    int remainder = (e.getY() % 20);
                    y = e.getY() - remainder;
                }
            }
            Graphics g = drawingPanel.getGraphics();
            g.setColor(color);
            g.fillRect(x, y, 16, 16);
        }else if (e.getModifiersEx() == InputEvent.BUTTON2_DOWN_MASK){
            for(int i = 0; i <= drawingPanel.getWidth(); i += 20){
                if(e.getX() < i){
                    int remainder = (e.getX() % 20);
                    x = e.getX() - remainder;
                    break;
                }
            }
            for(int i = 0; i <= drawingPanel.getHeight(); i += 20){
                if (e.getY() < i) {
                    int remainder = (e.getY() % 20);
                    y = e.getY() - remainder;
                }
            }
            Graphics g = drawingPanel.getGraphics();
            g.setColor(Color.white);
            g.fillRect(x, y, 16, 16);
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
}