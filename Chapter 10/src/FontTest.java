
import javax.swing.*;
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

/**
 * showcase of different fonts.
 *
 * @author Roman Bureacov
 * @version February 2025
 */
public class FontTest {
    public static void main(String... args) {
        //printFonts();

        EventQueue.invokeLater( () -> {
            JFrame frame = new BasicFrame();
            frame.setTitle("TEXT!!!");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

    /**
     * Method that prints out font names.
     *
     * @author Cay Horstmann
     */
    public static void printFonts() {
        String[] fontNames = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getAvailableFontFamilyNames();
        for (String fontName : fontNames)
            System.out.println(fontName);
    }

    static class BasicFrame extends JFrame {
        protected static final int WIDTH = 200;
        protected static final int HEIGHT = 200;

        public BasicFrame() {
            super();
            this.setSize(WIDTH, HEIGHT);
            add(new TextComponent());
            pack();
        }
    }

    static class TextComponent extends JComponent {

        @Override
        public void paintComponent(Graphics g) {
            var g2 = (Graphics2D) g;

            g2.draw(new Line2D.Double(0, 0, BasicFrame.WIDTH, BasicFrame.HEIGHT));
            g2.draw(new Line2D.Double(BasicFrame.WIDTH, 0, 0, BasicFrame.HEIGHT));

            Font someFont =
                //new Font("Zapfino Forte LT Pro", Font.BOLD, 14);
                new Font("SansSerif", Font.BOLD, 14);
            g2.setFont(someFont);
            String message = "Hello World!";

            FontRenderContext context = g2.getFontRenderContext();
            Rectangle2D boundingRect = someFont.getStringBounds(message, context);

            int centeredX = BasicFrame.WIDTH / 2 - (int)boundingRect.getWidth() / 2;
            int centeredY = BasicFrame.HEIGHT / 2 - (int)boundingRect.getHeight() / 2;


            boundingRect.setFrame(centeredX, centeredY, boundingRect.getWidth(), boundingRect.getHeight());

            g2.draw(boundingRect);

            g2.drawString(message, centeredX, centeredY + (int)boundingRect.getHeight());
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(BasicFrame.WIDTH, BasicFrame.HEIGHT);
        }
    }
}
