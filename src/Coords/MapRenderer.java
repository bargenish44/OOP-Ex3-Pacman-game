package Coords;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MapRenderer extends JFrame {

    public static void main(String... args) throws IOException {

        new MapRenderer();

    }

    public MapRenderer() throws IOException {
        setSize(new Dimension(Window.WIDTH, Window.HEIGHT));
        add(new TestPane());
        setVisible(true);
    }

}

class TestPane extends JPanel {

    private BufferedImage image;

    public TestPane() throws IOException {
        File file = new File("Ariel1.png");
        BufferedImage image = ImageIO.read(file);
        this.image = image;
    }

    @Override
    public void paintComponent(Graphics g) {
        double lon = 34;
        double lat = 33;

        int mapW = 614;
        int mapH = 1141;

        double x = (lon + 180) * (mapW / 360);

        double latRad = lat * Math.PI / 180;

        double mercN = Math.log( Math.tan( (Math.PI  / 4) + (latRad / 2)) );

        double y = (mapH / 2) - (mapW * mercN / (2 * Math.PI));

        System.out.println("[lon: " + lon + " lat: " + lat + "]: X: " + x + " Y: " + y);

        g.drawImage(image, 0, 0, null);
        g.setColor(Color.RED);
        g.drawOval((int) x, (int) y, 5, 5);
    }
}