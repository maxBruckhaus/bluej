/*  Maxwell Bruckhaus, Period 3, February 6th, 2015
 *  This program took me approximately 2.5 hours to
 *  complete in total. I found that the book reading
 *  was very helpful towards working on this lab.
 *  I spent a lot of time making sure I knew what
 *  each method did and how to use each tool and in
 *  what situation. I found shorter ways to draw
 *  certain shapes that I hadn't known before. One
 *  example of this is the polygon; I never knew
 *  there was a method for drawing polygons. The
 *  smiley face gave me some trouble when I was
 *  trying to draw the mouth. Also, problem 2B
 *  gave me trouble because it was difficult to
 *  position the circles so that the edges of the
 *  square were covered.
*/

// import libraries

import javax.swing.*;
import java.awt.*;

public class FirstGUIProject {

    public static void main(String[] args) {

        // Create a basic Java window frame
        JFrame window = new JFrame("My Window Title");

        // Decide what to do when the user closes the window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the window size (see API)
        window.setBounds(200, 200, 800, 800);

        // Prevent users from resizing the window
        window.setResizable(false);

        // Create GUI components.
        // For us, create a custom JPanel to draw on.
        MySketchPad panel = new MySketchPad();

        // Add GUI components to the JFrame (window)
        window.add(panel);

        // Make the window visible
        window.setVisible(true);
    }
}

/*
   A JPanel is like a SketchPad in the sense that you can
   draw on it.  It's more powerful though, because it has
   more capabilities than a SketchPad, such as the ability
   to add buttons and GUI elements.  And you can add
   JPanels to JPanels.
*/
class MySketchPad extends JPanel {

    public void paintComponent(Graphics g) {
        g.setColor(Color.red);

        // Set the background color to white (do this yourself)
        this.setBackground(Color.white);

        // Ask our parent to paint itself
        super.paintComponent(g);

        // Next, cast the Graphics parameter back into what
        // it really is - a more powerful Graphics2D object.
        // Or, if you want, you can leave it as a Graphics
        // and only use Graphics class methods.
        Graphics2D g2 = (Graphics2D) g;
        //sample(g2);
        //exercise2A(g2);
        //exercise2B(g2);
        //exercise2C(g2);
        //exercise2D(g2);
        //exercise2E(g2);
        //exercise2F(g2);
        //exercise2G(g2);
        exercise2H(g2);

    }

    private void sample(Graphics2D g2) {
        // Finally, draw stuff
        g2.drawString("This is a rectangle", 100, 50);
        g2.draw3DRect(10, 50, 300, 100, true);
    }

    public void exercise2A(Graphics2D g2) {
        g2.drawRect(350, 350, 100, 70);
        g2.drawOval(375, 300, 50, 50);
    }

    public void exercise2B(Graphics2D g2) {
        g2.fillRect(350, 350, 100, 100);
        g2.setColor(Color.white);

        g2.fillOval(348, 348, 54, 54);
        g2.fillRect(348, 348, 30, 30);

        g2.fillOval(348, 398, 54, 54);
        g2.fillRect(430, 348, 30, 30);

        g2.fillOval(398, 348, 54, 54);
        g2.fillRect(430, 430, 30, 30);

        g2.fillOval(398, 398, 54, 54);
        g2.fillRect(350, 430, 30, 30);


    }

    public void exercise2C(Graphics2D g2) {
        int xPoints[] = {350, 400, 450, 400};
        int yPoints[] = {400, 350, 400, 450};
        int nPoints = 4;
        g2.fillPolygon(xPoints, yPoints, nPoints);
    }

    public void exercise2D(Graphics2D g2) {
        g2.drawOval(400, 400, 50, 50);
        g2.drawOval(410, 410, 30, 30);

        g2.drawOval(500, 400, 50, 50);
        g2.drawOval(510, 410, 30, 30);

        g2.drawLine(425, 400, 525, 400);
        g2.drawLine(425, 450, 525, 450);

    }

    public void exercise2E(Graphics2D g2) {
        // Top
        g2.drawOval(400, 400, 20, 20);
        // Top Right
        g2.drawOval(415, 410, 20, 20);
        // Bottom Right
        g2.drawOval(415, 425, 20, 20);
        // Bottom
        g2.drawOval(400, 435, 20, 20);
        // Bottom Left
        g2.drawOval(385, 425, 20, 20);
        // Top Left
        g2.drawOval(385, 410, 20, 20);
        // Middle
        g2.drawOval(400, 417, 20, 20);
        g2.setColor(Color.red);
        Font boldFont = new Font("Serif", Font.BOLD, 20);
        g2.setFont(boldFont);
        g2.drawString("Sunshines", 370, 475);
    }

    public void exercise2F(Graphics2D g2) {
        g2.drawOval(375, 375, 100, 100);
        g2.fillOval(385, 385, 80, 80);
        g2.setColor(Color.white);
        Font boldFont = new Font("Serif", Font.BOLD, 80);
        g2.setFont(boldFont);
        g2.drawString("J", 405, 450);
    }

    public void exercise2G(Graphics2D g2) {
        g2.fillOval(400, 400, 100, 100);
        g2.setColor(Color.white);
        g2.fillArc(400, 400, 100, 100, 0, 90);
    }

    public void exercise2H(Graphics2D g2) {
        g2.drawOval(400, 400, 100, 100);
        g2.drawOval(425, 430, 10, 10);
        g2.drawOval(460, 430, 10, 10);
        g2.drawArc(430, 460, 60, 15, 200, 100);
    }
}