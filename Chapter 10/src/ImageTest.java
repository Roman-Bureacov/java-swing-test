import javax.swing.*;
import java.awt.*;

/**
 * showcase of the ImageIcon class
 */
public class ImageTest {
    public static void main(String... args) {
        EventQueue.invokeLater( () -> {
            ImageFrame frame = new ImageFrame();
            frame.setTitle("ImageIconTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class ImageFrame extends JFrame {
    ImageFrame() {
        add(new ImageComponent());
        setSize(300, 300);
    }
}

class ImageComponent extends JComponent {
    private static final int
            WIDTH = 300,
            HEIGHT = 300;

    public void paintComponent(Graphics g) {

        var g2D = (Graphics2D) g;

        //Image image = new ImageIcon("assets/pencil.png").getImage();
        //Image image = new ImageIcon("assets\\pencil.png").getImage();
        //Image image = new ImageIcon("C:\\Users\\iwant\\IdeaProjects\\java-swing-test\\Chapter 10\\src\\assets\\pencil.png").getImage();
        Image image = new ImageIcon("Chapter 10\\src\\assets\\pencil.png").getImage();

        // System.out.println(System.getProperty("user.dir")); // find the working directory

        // System.out.println(image.getHeight(null)); // see if there even is an image

        g2D.drawImage(image, 0, 0, null);

        final int imageWidth = image.getWidth(null);
        final int imageHeight = image.getHeight(null);
        for (int i = 0; i * imageWidth <= getWidth(); i++) {
            for (int j = 0; j * imageHeight <= getHeight(); j++) {
                if (i + j > 0)
                    g2D.copyArea(0, 0,
                            imageWidth, imageHeight,
                            i * imageWidth, j * imageHeight);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }
}
