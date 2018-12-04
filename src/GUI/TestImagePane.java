package GUI;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.event.*;
import javax.swing.*;

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
		double lat=0;
		double lon=0;
		private Image img;
		private Image scaled;
		double x=-1;
		double y=-1;
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
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(scaled, 0, 0, null);
			double x = getWidth();
			double y = getHeight();
			lat = ((y/y/180)-90)/-1;
			lon=x/(x/360)-180;
			System.out.println("[lon: " + lon + " lat: " + lat + "]: X: " + x + " Y: " + y);
			g.setColor(Color.RED);
			g.drawOval((int) x, (int) y, 5, 5);

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON1) {
				x = e.getX()/lat;
				y = e.getY()/lon;
				System.out.println("left click X: " + x + " Y: " + y);
				repaint();	
			}
			else {
				x = e.getX()/lat;
				y = e.getY()/lon;
				System.out.println("right click X: " + x + " Y: " + y);
				repaint();			
			}
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
