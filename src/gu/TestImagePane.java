package gu;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class TestImagePane {

    public static void main(String[] args) {
        new TestImagePane();
    }

    public TestImagePane() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                try {
                    Image img = null;
                    img = ImageIO.read(new File("Ariel1.png"));

                    JFrame frame = new JFrame("Testing");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setLayout(new BorderLayout());
                    frame.add(new ImagePanel(img));
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                } catch (IOException | HeadlessException exp) {
                    exp.printStackTrace();
                }
            }
        });
    }

    class ImagePanel extends JPanel implements MouseListener {

        private static final long serialVersionUID = 1L;

        private Image img;
        private Image scaled;
        int x=-1;
        int y=-1;
        public ImagePanel(String img) {
            this(new ImageIcon(img).getImage());
            this.addMouseListener(this);
        }

        public ImagePanel(Image img) {
            this.img = img;
            this.addMouseListener(this);
        }

        @Override
        public void invalidate() {
            super.invalidate();
            int width = getWidth();
            int height = getHeight();

            if (width > 0 && height > 0) {
                scaled = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return img == null ? new Dimension(200, 200) : new Dimension(img.getWidth(this), img.getHeight(this));
        }
        double lat=0;
        double lon=0;
        @Override
        public void paintComponent(Graphics g) {
            g.drawImage(scaled, 0, 0, null);
            double x = getWidth();
	        double y = getHeight();
	        lat = ((y/y/180)-90)/-1;
	        lon=x/(x/360)-180;
	        System.out.println("[lon: " + lon + " lat: " + lat + "]: X: " + x + " Y: " + y);
	        g.drawImage(img, 0, 0, null);
	        g.setColor(Color.RED);
	        g.drawOval((int) x, (int) y, 5, 5);
	        
        }

		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("cliked:[lon: " + lon + " lat: " + lat + "]: X: " + x + " Y: " + y);
			x = e.getX();
			y = e.getY();
			repaint();			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
    }

}
