/*  Maxwell Bruckhaus, Period 3, February 9th, 2015
 *  It took me a approximately 2 hours to
 *  finish the program. I abandoned my old code to
 *  start fresh. I had a red background with a green
 *  circle before but realized after doing other
 *  labs that my code was messy and I didn't
 *  really understand what I was doing. After restarting
 *  I managed to finish the lab without too much difficulty.
 *  I was having trouble with the thick and thin button and
 *  how to make the draw button work but I finally got it
 *  all down with some help.
*/

import javax.swing.event.ChangeListener;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;
import java.awt.*;

public class CircleDrawMBruckhausPeriod3 implements ChangeListener, ActionListener {

    JPanel panel;
    MyJPanel jPanel = new MyJPanel();
    JToggleButton lWidth;
    Graphics g;
    ButtonGroup color;
    JTextArea info;
    JTextArea display;
    String thick = "thin";
    String shading = "black";
    JSlider slider;
    Graphics2D g2;
    JRadioButton red;
    JRadioButton green;
    JRadioButton blue;
    JButton drawer = new JButton("Draw");


    public static void main(String[] args) {
        CircleDrawMBruckhausPeriod3 circle = new CircleDrawMBruckhausPeriod3();
        circle.createGUI();
    }

    public void createGUI() {
        // creating the window
        JFrame window = new JFrame("Circle Drawer");
        window.setBounds(200, 200, 800, 500);
        window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
        // adding the buttons of color options
        panel = new JPanel();
        panel.setLayout(null);
        lWidth = new JToggleButton("Thick Line!");
        lWidth.addActionListener(this);
        panel.add(lWidth).setBounds(20, 20, 150, 50);

        //button colors
        red = new JRadioButton();
        green = new JRadioButton();
        blue = new JRadioButton();
        red.addActionListener(this);
        green.addActionListener(this);
        blue.addActionListener(this);
        color = new ButtonGroup();
        color.add(red);
        color.add(green);
        color.add(blue);

        // outline for the subtitles
        JLabel title = new JLabel("Colors:");
        JLabel p = new JLabel("Red");
        JLabel m = new JLabel("Green");
        JLabel b = new JLabel("Blue");

        panel.add(title).setBounds(20, 100, 100, 30);
        panel.add(p).setBounds(20, 140, 100, 30);
        panel.add(m).setBounds(20, 180, 100, 30);
        panel.add(b).setBounds(20, 220, 100, 30);
        panel.add(red).setBounds(80, 140, 30, 30);
        panel.add(green).setBounds(80, 180, 30, 30);
        panel.add(blue).setBounds(80, 220, 30, 30);

        slider = new JSlider(10, 100);
        panel.add(slider).setBounds(20, 300, 100, 30);
        slider.addChangeListener(this);
        drawer.addActionListener(this);
        panel.add(drawer).setBounds(200, 20, 100, 30);

        // displaying the current status of the drawing the circle
        info = new JTextArea();
        display = new JTextArea();
        panel.add(info).setBounds(20, 350, 200, 40);
        panel.add(display).setBounds(20, 400, 400, 40);

        window.getContentPane().setLayout(new GridLayout(1, 2));
        window.getContentPane().add(panel);
        window.getContentPane().add(jPanel);
        window.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == drawer) {
            jPanel.paintComponent(jPanel.getGraphics());
        }
        else if(e.getSource() == lWidth){
            if(lWidth.isSelected()){
                lWidth.setText("Thin Line!");
            }
            else{
                lWidth.setText("Thick Line!");
            }
        }
    }

    public void stateChanged(ChangeEvent e){
        jPanel.paintComponent(jPanel.getGraphics());
    }

    private class MyJPanel extends JPanel {
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g2 = (Graphics2D) g;
            if (lWidth.isSelected()) {
                Stroke s = new BasicStroke(3);
                thick = "thick";
                g2.setStroke(s);
            } else {
                thick = "thin";
            }
            if (red.isSelected()) {
                g2.setColor(Color.red);
                shading = "red";
            } else if (green.isSelected()) {
                g2.setColor(Color.green);
                shading = "green";
            } else if (blue.isSelected()) {
                g2.setColor(Color.BLUE);
                shading = "blue";
            }
            int radius = slider.getValue();
            g.drawOval(50, 100, radius * 2, radius * 2);
            display.setText("Congratulations " + info.getText() + "! You drew a " + thick + " "
                    + shading + " circle of radius " + slider.getValue() * 2 + "!");
        }
    }
}