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
                System.out.println();
            }
            System.out.println();
        }
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        public int[][][] coordinates = new int[31][31][2];
        for(int j = 0; j < 31; j++){
            for (int i = 0; i < 31 ; i++){
                Rectangle2D.Double r = new Rectangle2D.Double (100+i*20, 100 + j*20, 3, 3);
                g2d.setColor(new Color (0, 0,0));
                g2d.fill(r);
                coordinates[j][i][0] = 100+i*20;
                coordinates[j][i][1] = 100+j*20;

            }
        }
        display3DArray(coordinates);

    }
}