
/*
  Originally written by Bruce A. Maxwell a long time ago.
  Updated by Brian Eastwood and Stephanie Taylor more recently
  Updated by Bruce again in Fall 2018

  Creates a window using the JFrame class.

  Creates a drawable area in the window using the JPanel class.

  The JPanel calls the Landscape's draw method to fill in content, so the
  Landscape class needs a draw method.
  
  Students should not *need* to edit anything outside of the main method, 
  but are free to do so if they wish. 
*/
package Lab02;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Displays a Landscape graphically using Swing. The Landscape
 * contains a grid which can be displayed at any scale factor.
 * 
 * @author bseastwo
 */
public class LandscapeDisplay implements ActionListener {
    JFrame win;
    protected Landscape scape;
    private LandscapePanel canvas;
    private int gridScale; // width (and height) of each square in the grid
    private JButton button, button2, button3, button4, button5, button6;
    private JTextField density, speed;
    private JPanel panel;
    private JLabel label, label2;
    private boolean runLoop = true;
    private int milli = 250;

    /**
     * Initializes a display window for a Landscape.
     * 
     * @param scape the Landscape to display
     * @param scale controls the relative size of the display
     */
    public LandscapeDisplay(Landscape scape, int scale) {
        // setup the window
        this.win = new JFrame("Game of Life");
        this.win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.scape = scape;
        this.gridScale = scale;

        // Create JButtons and JPanels
        this.button = new JButton("Start Simulation");
        this.button2 = new JButton("Stop Simulation");
        this.button3 = new JButton("Pause Simulation");
        this.button4 = new JButton("Reset");
        this.density = new JTextField();
        this.panel = new JPanel();
        this.label = new JLabel("Density 0-1:");
        this.button5 = new JButton("Set");
        this.label2 = new JLabel("Speed (Milliseconds):");
        this.speed = new JTextField();
        this.button6 = new JButton("Set");

        // Button Panel oriented on the right side of the screen
        this.panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // create a panel in which to display the Landscape
        // put a buffer of two rows around the display grid
        this.canvas = new LandscapePanel((int) (this.scape.getCols() + 4) * this.gridScale,
                (int) (this.scape.getRows() + 4) * this.gridScale);

        // add the buttons and panels to the window, layout, and display
        this.button.addActionListener(this);
        this.button2.addActionListener(this);
        this.button3.addActionListener(this);
        this.button4.addActionListener(this);
        this.button5.addActionListener(this);
        this.button6.addActionListener(this);
        panel.add(button, BorderLayout.EAST);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(button2);
        button2.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(button3);
        button3.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(button4);
        button4.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(density);
        density.setMaximumSize(new Dimension(Integer.MAX_VALUE, density.getMinimumSize().height));
        panel.add(button5);
        button5.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label2);
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(speed);
        speed.setMaximumSize(new Dimension(Integer.MAX_VALUE, density.getMinimumSize().height));
        panel.add(button6);
        button6.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.win.add(this.canvas, BorderLayout.CENTER);
        this.win.add(panel, BorderLayout.EAST);
        this.win.pack();

        // display
        this.win.setVisible(true);
    }

    /**
     * Saves an image of the display contents to a file. The supplied
     * filename should have an extension supported by javax.imageio, e.g.
     * "png" or "jpg".
     *
     * @param filename the name of the file to save
     */
    public void saveImage(String filename) {
        // get the file extension from the filename
        String ext = filename.substring(filename.lastIndexOf('.') + 1, filename.length());

        // create an image buffer to save this component
        Component tosave = this.win.getRootPane();
        BufferedImage image = new BufferedImage(tosave.getWidth(), tosave.getHeight(),
                BufferedImage.TYPE_INT_RGB);

        // paint the component to the image buffer
        Graphics g = image.createGraphics();
        tosave.paint(g);
        g.dispose();

        // save the image
        try {
            ImageIO.write(image, ext, new File(filename));
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    /**
     * This inner class provides the panel on which Landscape elements
     * are drawn.
     */
    private class LandscapePanel extends JPanel {
        /**
         * Creates the panel.
         * 
         * @param width  the width of the panel in pixels
         * @param height the height of the panel in pixels
         */
        public LandscapePanel(int width, int height) {
            super();
            this.setPreferredSize(new Dimension(width, height));
            this.setBackground(Color.lightGray);
        }

        /**
         * Method overridden from JComponent that is responsible for
         * drawing components on the screen. The supplied Graphics
         * object is used to draw.
         * 
         * @param g the Graphics object used for drawing
         */
        public void paintComponent(Graphics g) {
            // take care of housekeeping by calling parent paintComponent
            super.paintComponent(g);

            // call the Landscape draw method here
            scape.draw(g, gridScale);
        } // end paintComponent

    } // end LandscapePanel

    // repaints the landscape
    public void repaint() {
        this.win.repaint();
    }

    // a never ending loop that runs outside of the code that keeps the simulation
    // going.
    public void repaintLoop() {
        while (runLoop) {
            try {
                Thread.sleep(milli);
                scape.advance();
                repaint();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    // Setting responses to buttons being pressed, i.e exit => close window...
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == button) {
            runLoop = true;
            new Thread(this::repaintLoop).start();
        } else if (e.getSource() == button2) {
            System.exit(0);
        } else if (e.getSource() == button3) {
            runLoop = false;
        } else if (e.getSource() == button4) {
            scape.reset();
            repaint();
        } else if (e.getSource() == button5) {
            double newChance = Double.parseDouble(density.getText());
            scape = new Landscape(scape.getRows(), scape.getCols(), newChance);
            scape.reset();
            repaint();
        } else if (e.getSource() == button6) {
            int newSpeed = Integer.parseInt(speed.getText());
            milli = newSpeed;
        }
    }

    // starting an infinite loop of simulations that happen outside of main
    public void beginLoop() {
        runLoop = true;
        new Thread(this::repaintLoop).start();
    }

    public static void main(String[] args) throws InterruptedException {
        Landscape scape = new Landscape(100, 100, .25);
        // System.out.println(scape);
        LandscapeDisplay display = new LandscapeDisplay(scape, 10);
        display.beginLoop();
        // start the loop
        // Have a different thread call printLoop() so the main thread can handle button
        // presses

    }

}
