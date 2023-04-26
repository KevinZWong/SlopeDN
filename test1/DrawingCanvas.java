
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
public class DrawingCanvas extends JComponent {
    public TextBoxExample() {
        JFrame frame = new JFrame("Text Box Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        JLabel label = new JLabel("Enter your name:");
        panel.add(label);

        textField = new JTextField();
        textField.addActionListener(this);
        panel.add(textField);

        frame.getContentPane().add(panel);

        frame.pack();
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String userInput = textField.getText();
        System.out.println("User entered: " + userInput);
    }
    public DrawingCanvas() {
        setPreferredSize(new Dimension(800, 850));

        setLayout(new BorderLayout());
        JButton button = new JButton("Click me");
        add(button, BorderLayout.WEST);
    }
    public static void display3DArray(int[][][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                for (int k = 0; k < arr[i][j].length; k++) {
                    System.out.print(arr[i][j][k] + " ");
                }
                System.out.print(" | ");
            }
            System.out.println();
        }
    }
    public static double[] extendPoint(double x, double y, double slope, double distance) {
        // calculate the change in x and y for the specified distance increase along the slope
        double delta_x = distance / Math.sqrt(1 + slope*slope);
        double delta_y = slope * delta_x;
        
        // calculate the new x and y coordinates
        double new_x = x + delta_x;
        double new_y = y + delta_y;
        
        return new double[] { new_x, new_y };
    }
    public static void drawSlopeLine(double slope, int x, int y, Graphics2D g2d) {
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
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(xSlope1, ySlope1, xSlope2, ySlope2);
    

    }
    public static int calculatePolynomial(int[] coefficients, int x) {
        int result = 0;
        for (int i = 0; i < coefficients.length; i++) {
            result += coefficients[i] * Math.pow(x, coefficients.length - i - 1);
        }
        return result;
    }

    public static int[] calculateDerivative(int[] coefficients) {
        if (coefficients.length <= 1) {
            return new int[] {0};
        }
        int[] derivativeCoefficients = new int[coefficients.length - 1];
        for (int i = 0; i < derivativeCoefficients.length; i++) {
            derivativeCoefficients[i] = coefficients[i] * (derivativeCoefficients.length - i);
        }
        return derivativeCoefficients;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        ////////////////////////////////////////////////////

        Graphics2D g2d = (Graphics2D) g;
        int size = 21;

        int[][][] coordinates = new int[size][size][2];
        for(int j = 0; j < size; j++){
            for (int i = 0; i < size ; i++){
                Rectangle2D.Double r = new Rectangle2D.Double (100+i*30, 100 + j*30, 3, 5);
                g2d.setColor(new Color (0, 0,0));
                g2d.fill(r);
                coordinates[j][i][0] = 100+i*30;
                coordinates[j][i][1] = 100+j*30;

            }
        }
        int mid = size/2;
        System.out.println("MID: " + mid);

        //coordinates[0][15][0], coordinates[0][15][1]
        g2d.drawLine(coordinates[0][mid+1][0], coordinates[0][mid+1][1], coordinates[size-1][mid+1][0], coordinates[size-1][mid+1][1]);
        g2d.drawLine(coordinates[mid][0][0], coordinates[mid][0][1], coordinates[mid][size-1][0], coordinates[mid][size-1][1]);
        new TextBoxExample();
        //display3DArray(coordinates);
        //###########################################################################################################
        /* 
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the coefficients of the polynomial separated by spaces: ");
        String[] input = scanner.nextLine().split("\\s+");
        int[] coefficients = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            coefficients[i] = Integer.parseInt(input[i]);
        }
        int[] derivativeCoefficients = calculateDerivative(coefficients);
        System.out.print("The coefficients of the derivative of the function are: ");

        String input2 = "";
        for (int i = 0; i < derivativeCoefficients.length; i++) {
            input2 += derivativeCoefficients[i] + " ";
        }
        System.out.println()
        */
        //###########################################################################################################
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the coefficients of y prime separated by spaces: ");
        String[] polyinput = scanner.nextLine().split("\\s+");

        int[] polycoefficients = new int[polyinput.length];
        for (int i = 0; i < polyinput.length; i++) {
            polycoefficients[i] = Integer.parseInt(polyinput[i]);
        }


        for (int i = 0; i < coordinates.length; i++) {
            for (int j = 0; j < coordinates[i].length; j++) {

                int result = calculatePolynomial(polycoefficients, j-11);
                drawSlopeLine(result, coordinates[i][j][0], coordinates[i][j][1], g2d);
            }
        }
        

        
        //////////////////////////////////////////////////
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Drawing Canvas Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(new DrawingCanvas());

        frame.pack();
        frame.setVisible(true);
    }
}