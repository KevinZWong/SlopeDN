import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextBoxExample implements ActionListener {

    private JTextField textField;

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

    public static void main(String[] args) {
        new TextBoxExample();
    }
}
