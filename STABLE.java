import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MyProgram extends JFrame {
    private JTextField textField;
    private Canvas canvas;

    public MyProgram() {
        super("My Program");

        // Initialize components
        JLabel label = new JLabel("Enter a function");
        JPanel labelPanel = new JPanel();
        labelPanel.add(label);
        labelPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Add padding to label panel
        textField = new JTextField();
        textField.setPreferredSize(new Dimension(400, 50)); // Set text field size
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
                ArrayList<Point> dotLocations = new ArrayList<>();
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
        setVisible(true);
    }

    public static void main(String[] args) {
        new MyProgram();
    }
}
