/**
 * From chapter 10, Listing 10.3
 */

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
                        // swing was originally an extension of java

/**
 * @version 1.34 2018-04-10
 * @author Cay Horstmann
 * @author Roman Bureacov
 */
public class DrawTest_Sandbox {
    public static void main(String[] args) {
        EventQueue.invokeLater( () -> {
            var frame = new DrawFrame();
            frame.setTitle("DrawTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

    /**
     * A frame that contains a panel with drawings.
     */
    static class DrawFrame extends JFrame {
        public DrawFrame() {
            add(new DrawComponent());
            pack();
        }
    }

    /**
     * A component that displays rectangles and ellipses.
     */
    static class DrawComponent extends JComponent {
        private static final int DEFAULT_WIDTH = 400;
        private static final int DEFAULT_HEIGHT = 400;

        public void paintComponent(Graphics g) {
            var g2 = (Graphics2D) g;

            // draw a rectangle

            double leftX = 100;
            double topY = 100;
            double width = 200;
            double height = 150;

            var rect = new Rectangle2D.Double(leftX, topY, width, height);

            g2.draw(rect);
            g2.setPaint(Color.GREEN);
            g2.fill(rect);
            g2.setPaint(Color.BLACK);

            // draw the enclosed ellipse

            var ellipse = new Ellipse2D.Double();
            ellipse.setFrame(rect);
            g2.draw(ellipse);

            // draw a diagonal line

            g2.draw(new Line2D.Double(leftX, topY, leftX + width, topY + height));

            // draw a circle with the same center

            double centerX = rect.getCenterX();
            double centerY = rect.getCenterY();
            double radius = 150;

            var circle = new Ellipse2D.Double();
            circle.setFrameFromCenter(centerX, centerY, centerX + radius, centerY + radius);
            g2.draw(circle);

            // add additional lines

            g2.setPaint(Color.RED);

            var line2 = new Line2D.Double(leftX + width, topY, leftX, topY + height);
            g2.draw(line2);

            g2.setPaint(new Color(200, 155, 200));

            var lineMiddleVerticle = new Line2D.Double(leftX + width / 2, topY, leftX + width / 2, topY + height);
            g2.draw(lineMiddleVerticle);
            var lineMiddleHorizontal = new Line2D.Double(leftX, topY + height / 2, leftX + width, topY + height / 2);
            g2.draw(lineMiddleHorizontal);

            g2.draw(new Line2D.Double(0, 0, this.getParent().getWidth(), this.getParent().getHeight()));

            this.setOpaque(true);
            this.setBackground(new Color(150, 220, 32));
            this.setForeground(new Color(150, 220, 32));
        }

        public Dimension getPreferredSize() {
            return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        }
    }
}
