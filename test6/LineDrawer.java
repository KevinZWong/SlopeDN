import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LineDrawer extends JFrame implements MouseListener {
    private JPanel canvas;

    public LineDrawer() {
        setTitle("Line Drawer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);

        canvas = new JPanel();
        canvas.addMouseListener(this);
        getContentPane().add(canvas, BorderLayout.CENTER);
    }

    public void mouseClicked(MouseEvent e) {
        Graphics g = canvas.getGraphics();

        int x = e.getX();
        int y = e.getY();

        // Draw line extending 10 pixels from each side
        g.drawLine(x - 10, y, x + 10, y);
        g.drawLine(x, y - 10, x, y + 10);
    }

    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}

    public static void main(String[] args) {
        LineDrawer app = new LineDrawer();
        app.setVisible(true);
    }
}
