import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SlopeFieldGUI extends JFrame implements ActionListener {

    private JTextField derivativeField;
    private JPanel graphPanel;
    private JButton drawButton;

    public SlopeFieldGUI() {
        setTitle("Slope Field Graph");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        derivativeField = new JTextField();
        derivativeField.setPreferredSize(new Dimension(200, 30));

        drawButton = new JButton("Draw");
        drawButton.addActionListener(this);

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Derivative:"));
        topPanel.add(derivativeField);
        topPanel.add(drawButton);

        graphPanel = new JPanel();
        graphPanel.setBackground(Color.WHITE);

        add(topPanel, BorderLayout.NORTH);
        add(graphPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == drawButton) {
            String derivative = derivativeField.getText();
            GraphingPanel graph = new GraphingPanel(derivative);
            graphPanel.removeAll();
            graphPanel.add(graph);
            graphPanel.revalidate();
            graphPanel.repaint();
        }
    }

    class GraphingPanel extends JPanel {

        private static final int WIDTH = 400;
        private static final int HEIGHT = 400;
        private static final int STEP_SIZE = 10;

        private String derivative;

        public GraphingPanel(String derivative) {
            this.derivative = derivative;
            setPreferredSize(new Dimension(WIDTH, HEIGHT));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.drawLine(0, HEIGHT/2, WIDTH, HEIGHT/2);
            g.drawLine(WIDTH/2, 0, WIDTH/2, HEIGHT);

            double xMin = -10;
            double xMax = 10;
            double yMin = -10;
            double yMax = 10;
            double xRange = xMax - xMin;
            double yRange = yMax - yMin;

            for (double x = xMin; x <= xMax; x += STEP_SIZE) {
                for (double y = yMin; y <= yMax; y += STEP_SIZE) {
                    double slope = evaluateDerivative(derivative, x, y);
                    double dx = STEP_SIZE * Math.sqrt(1 / (1 + slope*slope));
                    double dy = slope * dx;
                    g.drawLine((int) ((x + xRange/2) * WIDTH / xRange), (int) (HEIGHT - (y + yRange/2) * HEIGHT / yRange),
                            (int) ((x + xRange/2 - dx) * WIDTH / xRange), (int) (HEIGHT - (y + yRange/2 - dy) * HEIGHT / yRange));
                }
            }
        }

        private double evaluateDerivative(String derivative, double x, double y) {
            // Implement your code to evaluate the derivative at (x,y)
            return 0.5; // Replace with actual slope value
        }
    }

    public static void main(String[] args) {
        new SlopeFieldGUI();
    }
}
