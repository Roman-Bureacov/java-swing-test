
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

            (new TextComponent()).fontMetricNoGraphics2D();
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

        private static final
        Font someFont =
                //new Font("Zapfino Forte LT Pro", Font.BOLD, 14);
                new Font("SansSerif", Font.BOLD, 28);

        private static final String message = "Hello World!g";


        /**
         * Demonstration of how to get font metrics in a place without a Graphics2D
         */
        public void fontMetricNoGraphics2D() {
            FontRenderContext context = this.getFontMetrics(someFont).getFontRenderContext();

            Rectangle2D messageBounds = someFont.getStringBounds(message, context);

            LineMetrics metrics = someFont.getLineMetrics(message, context);

            System.out.printf("message \"%s\" has the properties:"
                    + "\nwidth: %f"
                    + "\nheight: %f"
                    + "\nascent: %f"
                    + "\ndescent: %f"
                    + "\nleading: %f",
                    message,
                    messageBounds.getWidth(),
                    messageBounds.getHeight(),
                    metrics.getAscent(),
                    metrics.getDescent(),
                    metrics.getLeading()
            );

        }

        @Override
        public void paintComponent(Graphics g) {

            var g2 = (Graphics2D) g;

            g2.draw(new Line2D.Double(0, 0, BasicFrame.WIDTH, BasicFrame.HEIGHT));
            g2.draw(new Line2D.Double(BasicFrame.WIDTH, 0, 0, BasicFrame.HEIGHT));

            g2.setFont(someFont);

            FontRenderContext context = g2.getFontRenderContext();
            Rectangle2D boundingRect = someFont.getStringBounds(message, context);

            double stringWidth = boundingRect.getWidth();
            double stringHeight = boundingRect.getHeight();

            // metrics
            LineMetrics metrics = someFont.getLineMetrics(message, context);
            float ascent = metrics.getAscent();

            int centeredX = BasicFrame.WIDTH / 2 - (int)stringWidth / 2;
            int centeredY = BasicFrame.HEIGHT / 2 - (int)stringHeight / 2;

            boundingRect.setFrame(centeredX, centeredY, boundingRect.getWidth(), boundingRect.getHeight());

            g2.draw(boundingRect);

            g2.drawString(message, centeredX, centeredY + (int)ascent);

            g2.setPaint(Color.RED);
            g2.draw(new Line2D.Double(
                    boundingRect.getX(),
                    boundingRect.getY() + metrics.getAscent(),
                    boundingRect.getX() + boundingRect.getWidth(),
                    boundingRect.getY() + metrics.getAscent()
                    )
            );
            g2.draw(new Line2D.Double(
                    boundingRect.getX(),
                    boundingRect.getY() + metrics.getAscent() + metrics.getDescent(),
                    boundingRect.getX() + boundingRect.getWidth(),
                    boundingRect.getY() + metrics.getAscent() + metrics.getDescent()
                    )
            );
            g2.draw(new Line2D.Double(
                    boundingRect.getX(),
                    boundingRect.getY() + metrics.getAscent() + metrics.getDescent() + metrics.getLeading(),
                    boundingRect.getX() + boundingRect.getWidth(),
                    boundingRect.getY() + metrics.getAscent() + metrics.getDescent() + metrics.getLeading()
                    )
            );
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(BasicFrame.WIDTH, BasicFrame.HEIGHT);
        }
    }
}
