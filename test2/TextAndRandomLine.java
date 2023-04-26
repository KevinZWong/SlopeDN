import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.util.*;
public class TextAndRandomLine extends JComponent implements KeyListener {

    private JTextField textField;
    private JPanel panel;
    private Random random;
    public int[][][] coordinates;


    public static int[][][] getCoordinates(int size) {
        int[][][] coordinates = new int[size][size][2];
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
                Rectangle2D.Double r = new Rectangle2D.Double(100 + i * 30, 100 + j * 30, 3, 5);
                g2d.setColor(new Color(0, 0, 0));
                g2d.fill(r);
                coordinates[j][i][0] = 100 + i * 30;
                coordinates[j][i][1] = 100 + j * 30;
            }
        }
        return coordinates;
    }
    

    public TextAndRandomLine() {
        // Create a JTextField
        textField = new JTextField();
        textField.addKeyListener(this);

        // Create a JPanel
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int size = 21;
        
                coordinates = getCoordinates(size);
                int mid = size/2;
                System.out.println("MID: " + mid);
        
                //coordinates[0][15][0], coordinates[0][15][1]
                g2d.drawLine(coordinates[0][mid+1][0], coordinates[0][mid+1][1], coordinates[size-1][mid+1][0], coordinates[size-1][mid+1][1]);
                g2d.drawLine(coordinates[mid][0][0], coordinates[mid][0][1], coordinates[mid][size-1][0], coordinates[mid][size-1][1]);
            }
        };

        // Set the layout of this JComponent to BorderLayout and add the JTextField to the top and the JPanel to the center
        setLayout(new BorderLayout());
        add(textField, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        // Create a Random object
        random = new Random();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Text and Random Line");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 850);
        frame.setContentPane(new TextAndRandomLine());
        frame.setVisible(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            String text = textField.getText();

            String[] polyinput = text.split("\\s+");

            int[] polycoefficients = new int[polyinput.length];
            for (int i = 0; i < polyinput.length; i++) {
                polycoefficients[i] = Integer.parseInt(polyinput[i]);
        
            }
            // Draw a random line
            Graphics g = panel.getGraphics();
            g.setColor(Color.BLACK);
            int x1 = random.nextInt(panel.getWidth());
            int y1 = random.nextInt(panel.getHeight());
            int x2 = random.nextInt(panel.getWidth());
            int y2 = random.nextInt(panel.getHeight());
            g.drawLine(x1, y1, x2, y2);

            
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
