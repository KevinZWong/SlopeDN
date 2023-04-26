import javax.swing.*;
import java.awt.event.*;
public class DrawingTester {
    public static void main(String[] args) {
        int w = 800;
        int h = 850;
        
        JFrame f = new JFrame();
        DrawingCanvas dc = new DrawingCanvas (w, h);
        f.setSize(w, h);
        f.setTitle("Drawing in Java");
        f.add(dc);
        f.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE) ;
        f.setVisible(true);
    }
} 