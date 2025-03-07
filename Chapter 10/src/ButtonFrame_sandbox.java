import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * A frame witrh a button panel.
 *
 * @author Cay Horstmann
 */
public class ButtonFrame_sandbox extends JFrame {
    private JPanel buttonPanel;
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    public static void main(String... args) {
        EventQueue.invokeLater( () -> {
            var frame = new ButtonFrame_sandbox();
            frame.setTitle("ColorButtons");
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            frame.setVisible(true);
        });
    }

    public ButtonFrame_sandbox() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        buttonPanel = new JPanel();

        // add buttons to panel
        makeButton("Yellow", Color.YELLOW);
        makeButton("Blue", Color.BLUE);
        makeButton("<html><i>Red</i></html>", Color.RED); // text string can even be in HTML!

        // add panel to frame
        add(buttonPanel);

        // add window listener
        addWindowListener(new Terminator());
        addWindowStateListener(new Terminator());
    }

    /**
     * Helper method to create the instance of a class that implements the ActionListener interface,
     * and have the actionPerformed actions reference the backGroundColor value stored within the listener object.
     */
    public void makeButton(String name, Color backgroundColor) {
        var button = new JButton(name);
        buttonPanel.add(button);
        button.addActionListener( event -> // notice the use of the lambda expression
                buttonPanel.setBackground(backgroundColor));
    }

    class Terminator extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            if (JOptionPane.showConfirmDialog(null,
                            "Close?", "Close?", JOptionPane.YES_NO_OPTION) == 0) {
                System.exit(0);
            }
        }

        @Override
        public void windowClosed(WindowEvent e) {
            System.out.println("Goodbye!");
        }

        @Override
        public void windowIconified(WindowEvent e) {
            System.out.println("I have been iconified!");
        }

        @Override
        public void windowDeiconified(WindowEvent e) {
            System.out.println("Hello again!");
        }

        @Override
        public void windowStateChanged(WindowEvent e) {
            System.out.println("Something changed!");
        }
    }
}
