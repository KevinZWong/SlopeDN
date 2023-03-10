import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.ArrayList;
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

    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int size = 31;

        int[][][] coordinates = new int[size][size][2];
        for(int j = 0; j < 31; j++){
            for (int i = 0; i < 31 ; i++){
                Rectangle2D.Double r = new Rectangle2D.Double (100+i*20, 100 + j*20, 3, 3);
                g2d.setColor(new Color (0, 0,0));
                g2d.fill(r);
                coordinates[j][i][0] = 100+i*20;
                coordinates[j][i][1] = 100+j*20;

            }
        }

        //coordinates[0][15][0], coordinates[0][15][1]
        g2d.drawLine(coordinates[0][15][0], coordinates[0][15][1], coordinates[30][15][0], coordinates[30][15][1]);
        g2d.drawLine(coordinates[15][0][0], coordinates[15][0][1], coordinates[15][30][0], coordinates[15][30][1]);
        display3DArray(coordinates);

        

    }
}