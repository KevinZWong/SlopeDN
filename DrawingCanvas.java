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
    private int width;
    private int height;
    public DrawingCanvas (int w, int h) {
        width = w;
        height = h;
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
    public static void drawSlopeLine(double slope, int x, int y, Graphics2D g2d) {
        double change = (int)(slope * 5);
        int xSlope1 = (int)(x+10);
        int ySlope1= (int)(y-change);
        int xSlope2 = (int)(x-10);
        int ySlope2 = (int)( y+change);
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


    protected void paintComponent(Graphics g) {
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
        g2d.drawLine(coordinates[0][mid][0], coordinates[0][mid][1], coordinates[size-1][mid][0], coordinates[size-1][mid][1]);
        g2d.drawLine(coordinates[mid][0][0], coordinates[mid][0][1], coordinates[mid][size-1][0], coordinates[mid][size-1][1]);
        //display3DArray(coordinates);
        //###########################################################################################################
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
        System.out.println();
        //###########################################################################################################

        String[] polyinput = input2.split("\\s+");
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
        

        

    }
}