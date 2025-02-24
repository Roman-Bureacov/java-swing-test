import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * A frame with a button panel.
 *
 * @author Cay Horstmann
 */
public class ButtonFrame_sandbox_AbstractAction extends JFrame {
    private JPanel buttonPanel;
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    public static void main(String... args) {
        EventQueue.invokeLater( () -> {
            var frame = new ButtonFrame_sandbox_AbstractAction();
            frame.setTitle("ColorButtons");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

    public ButtonFrame_sandbox_AbstractAction() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        buttonPanel = new JPanel();

        // add panel to frame
        add(buttonPanel);

        // create button actions
        var yellowAction = new ColorAction("Yellow", new ImageIcon("blue-ball.gif"), Color.YELLOW);
        var blueAction = new ColorAction("Blue", null, Color.BLUE);
        var redAction = new ColorAction("Red", null, Color.RED);

        // create buttons
        var yellowButton = new JButton(yellowAction);
        var blueButton = new JButton(blueAction);
        var redButton = new JButton(redAction);

        // add buttons to panel
        buttonPanel.add(yellowButton);
        buttonPanel.add(blueButton);
        buttonPanel.add(redButton);

        // keystrokes are made with the convenience class
        KeyStroke ctrlBKey = KeyStroke.getKeyStroke("ctrl B");

        // the input map does not directly map KeyStroke objects to Action objects
        // instead it maps to arbitrary objects and a second map maps objects to actions
        // this makes it easier to share the same actions among keystrokes that come from different input maps
        InputMap imap = buttonPanel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        imap.put(ctrlBKey, "buttonPanel.blue");

        ActionMap amap = buttonPanel.getActionMap();
        amap.put("buttonPanel.blue", blueAction);

        // it is customary to put "non" for a do-nothing action, making it easy to deactivate a key
        imap.put(KeyStroke.getKeyStroke("ctrl C"), "none");
    }

    /**
     * An action listener that sets the panel's background color.
     */
    private class ColorAction extends AbstractAction {
        public ColorAction(String name, Icon icon, Color c) {
            putValue(Action.NAME, name);
            putValue(Action.SMALL_ICON, icon);
            putValue("color", c);
            putValue(Action.SHORT_DESCRIPTION, "Set panel color to " + name.toLowerCase());
        }

        public void actionPerformed(ActionEvent event) {
            Color c = (Color) getValue("color");
            buttonPanel.setBackground(c);
        }
    }
}
