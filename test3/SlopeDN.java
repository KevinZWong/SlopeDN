import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class SlopeDN extends JFrame {
    private JTextField textField;
    private Canvas canvas;
    private ArrayList<Point> dotLocations;

    public static double[] extendPoint(double x, double y, double slope, double distance) {
        // calculate the change in x and y for the specified distance increase along the slope
        double delta_x = distance / Math.sqrt(1 + slope*slope);
        double delta_y = slope * delta_x;
        
        // calculate the new x and y coordinates
        double new_x = x + delta_x;
        double new_y = y + delta_y;
        
        return new double[] { new_x, new_y };
    }
    public static void drawSlopeLine(double slope, int x, int y, Graphics g) {
        double change =  10;

        double[] return2 = extendPoint(x, y, -slope, change);
        double[] return1 = extendPoint(x, y, -slope, -change);
        System.out.println("return1[0]" + return1[0]);
        System.out.println("return1[1]" + return1[1]);
        System.out.println("return2[0]" + return2[0]);
        System.out.println("return2[1]" + return2[1]);
        int xSlope1 = (int)(return1[0]);
        int ySlope1= (int)(return1[1]);
        int xSlope2 = (int)(return2[0]);
        int ySlope2 = (int)(return2[1]);
        g.drawLine(xSlope1, ySlope1, xSlope2, ySlope2);
    
    }
    public static int calculatePolynomial(double[] coefficients, int x) {
        int result = 0;
        for (int i = 0; i < coefficients.length; i++) {
            result += coefficients[i] * Math.pow(x, coefficients.length - i - 1);
        }
        return result;
    }
    public SlopeDN() {
        
        super("SlopeDN");

        // Initialize components
        JLabel label = new JLabel("Enter coefficients of a polynomial function (e.g. 1,2,3 for x^2 + 2x + 3):");
        JPanel labelPanel = new JPanel();
        labelPanel.add(label);
        labelPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Add padding to label panel
        textField = new JTextField();
        textField.setPreferredSize(new Dimension(400, 50)); // Set text field size
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                
                
                // Get user input
                String input = textField.getText();
                System.out.println(input.getClass());
                if (input.equals("clear")){
                    canvas.repaint();
                } else {
                    String[] coefficientsString = input.split(",");
                    double[] coefficients = new double[coefficientsString.length];

                    // Convert string coefficients to double coefficients
                    for (int i = 0; i < coefficientsString.length; i++) {
                        coefficients[i] = Double.parseDouble(coefficientsString[i]);
                    }
                    // Draw the line
                    Graphics g = canvas.getGraphics();
                    g.setColor(Color.RED);

                    //drawSlopeLine(1, 30, 30, g);
        

                    // Print dot locations
                    //System.out.println("Dot locations:" + dotLocations.size());
                    int size = 25;
                    int[][][] coordinates = new int[size][size][2];
                    int index = 0;
                    for (int i = 0; i < 25; i++) {
                        for (int j = 0; j < 25; j++) {
                            Point point = dotLocations.get(index);
                            coordinates[j][i][0] = point.x ;
                            coordinates[j][i][1] = point.y ;
                            index += 1;
                        }
                    }

                    for (int i = 0; i < coordinates.length; i++) {
                        for (int j = 0; j < coordinates[i].length; j++) {
                            int result = calculatePolynomial(coefficients, j-12);
                            drawSlopeLine(result, coordinates[i][j][0], coordinates[i][j][1], g);
                        }
                        System.out.println();
                    }
                }



                //for (Point point : dotLocations) {
                //    System.out.println("(" + point.x + ", " + point.y + ")");
                //}
                /* 
                for (int i = 0; i < coordinates.length; i++) {
                    for (int j = 0; j < coordinates[i].length; j++) {
        
                        System.out.print(coordinates[i][j][0] + ",");
                        System.out.print(coordinates[i][j][1] + " | ");
                    }
                    System.out.println();
                }
                
                */

            }
        });
        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Increase padding to text panel
        textPanel.add(textField, BorderLayout.CENTER); // Add text field to text panel
        canvas = new Canvas() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                int width = getWidth();
                int height = getHeight();

                // Draw dots and store their locations
                dotLocations = new ArrayList<>();
                int dotSpacing = 30;
                for (int x = dotSpacing; x < width; x += dotSpacing) {
                    for (int y = dotSpacing; y < height; y += dotSpacing) {
                        dotLocations.add(new Point(x, y));
                        g.fillOval(x - 2, y - 2, 4, 4);
                    }
                }

                // Draw x-axis
                int yCenter = (int) dotLocations.get(dotLocations.size() / 2).getY();
                g.drawLine(0, yCenter, width, yCenter);

                // Draw y-axis
                int xCenter = (int) dotLocations.get(dotLocations.size() / 2 + (width / dotSpacing) / 2).getX();
                g.drawLine(xCenter, 0, xCenter, height);
            }
        };

        // Create nested containers to hold components
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(labelPanel, BorderLayout.NORTH); // Add label panel
        panel.add(textPanel, BorderLayout.CENTER);

        // Set layout and add components
        setLayout(new BorderLayout());
        add(panel, BorderLayout.WEST);
        add(canvas, BorderLayout.CENTER);

        // Set window properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new SlopeDN();
    }
}
