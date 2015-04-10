/*  Maxwell Bruckhaus, Period 3, February 20th, 2015
 *  It took me a approximately 3 hours to
 *  finish the program. The middle and left panel were
 *  easier to code than the right panel. I really understood
 *  what was going on with the panels and the sliders for
 *  the first time. However, adjusting the buttons to
 *  fit to the needs of the instructions was difficult.
 *  I still do not fully understand the left clicking and
 *  right clicking to draw shapes yet so I will resubmit this
 *  lab in the future when I have everything working fully.
*/

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


class ClassworkGUIMBruckhausPeriod3{ //extends JPanel implements MouseListener, MouseMotionListener */
    int x;
    int y;
    JFrame window;
    private class MouseClickin implements MouseListener {
        public void mouseClicked(java.awt.event.MouseEvent e) {
            ;
        }
        public void mouseEntered(java.awt.event.MouseEvent e) {
            ;
        }
        public void mouseExited(java.awt.event.MouseEvent e) {
            ;
        }

        public void mousePressed(java.awt.event.MouseEvent e) {
            // TODO Auto-generated method stub
            x = e.getX();
            y = e.getY();

            if(e.getButton() == 1){
                System.out.println("BUTTON1");
                Graphics g = column3.getGraphics();
                g.drawOval(x - slider.getValue(), y- slider.getValue(), slider.getValue(), slider.getValue());
            }
            if(e.getButton() == 3){
                System.out.println("BUTTON3");
                Graphics g = column3.getGraphics();
                g.drawRect(x - slider.getValue(), y- slider.getValue(), slider.getValue(), slider.getValue());
            }
        }

        public void mouseReleased(java.awt.event.MouseEvent e) {
            ;
        }
    }


    public static void main(String[] args) {
        ClassworkGUIMBruckhausPeriod3 gui = new ClassworkGUIMBruckhausPeriod3();
        gui.createGUI();
    }

    JSlider slider;
    JPanel column3;
    public void createGUI(){
        // Create a basic Java window frame
        window = new JFrame("GUI");
        // Decide what to do when the user closes the window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Get the screen dimensions
        int screenWidth = (int)(window.getToolkit().getScreenSize().getWidth());
        int screenHeight = (int)(window.getToolkit().getScreenSize().getHeight());
        // Our window size
        int frameWidth = 800;
        int frameHeight = 600;
        // Centers the JFrame, regardless of screen resolution
        window.setBounds(screenWidth/2 - frameWidth/2, screenHeight/2 - frameHeight/2, frameWidth, frameHeight);
        // Decide whether to allow users to resize the window
        window.setResizable(true);
        // Define the overall layout
        window.setLayout(new GridLayout(1,3));
        // Create GUI components
        JPanel column1 = new JPanel();
        column1.setLayout(null);
        column1.setBorder(new BevelBorder(BevelBorder.LOWERED));
        JPanel column2 = new JPanel();
        column2.setLayout(null);
        column2.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        column3 = new JPanel();
        column3.setLayout(null);
        column3.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        column1.setBackground(new Color(235,199,225));
        column2.setBackground(new Color(199,235,209));
        column3.setBackground(new Color(188,231,235));
        column1.setLayout(new GridLayout(3,1));
        column2.setLayout(new BorderLayout());
        JPanel columnTop = new JPanel();
        column1.add(columnTop);
        columnTop.setLayout(new BorderLayout(20,20));
        JPanel whiteBoard = new JPanel();
        JPanel tresButtons = new JPanel();
        JPanel threeButtonsTop = new JPanel();
        tresButtons.setLayout(new GridLayout(3,1));
        threeButtonsTop.setLayout(new GridLayout(1,3));
        JButton a = new JButton("a");
        JButton b = new JButton("b");
        JButton c = new JButton("c");
        threeButtonsTop.add(a);
        threeButtonsTop.add(b);
        threeButtonsTop.add(c);
        tresButtons.add(threeButtonsTop);
        tresButtons.setBackground(new Color(235,199,225));
        column1.add(tresButtons);
        columnTop.add(whiteBoard, BorderLayout.CENTER);

        columnTop.setBackground(new Color(235,199,225));
        whiteBoard.setBounds(20,20,20,20);
        whiteBoard.setBackground(Color.WHITE);

        whiteBoard.setBorder(new BevelBorder(BevelBorder.RAISED));
        slider = new JSlider();
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(2);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setOrientation(SwingConstants.VERTICAL);
        slider.setBackground(new Color(235,199,225));
        column1.add(slider);

        column2.setLayout(new GridLayout(3,1));
        JPanel notepad1 = new JPanel();
        notepad1.setBackground(null);
        column2.add(notepad1);
        JPanel notepad = new JPanel();
        notepad.setSize(new Dimension(200,300));
        notepad.setBackground(new Color(255,255,190));
        notepad.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "Blackboard") );
        column2.add(notepad);
        window.add(column1);
        window.add(column2);
        window.add(column3);

        //column3.addMouseListener(this);
        // Add GUI components to JFrame (window)

        // Make the window visible
        window.setVisible(true);
    }
}