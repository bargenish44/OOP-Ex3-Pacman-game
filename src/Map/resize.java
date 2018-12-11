package Map;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.event.*;
import javax.swing.*;
import Geom.Path;

public class resize implements ActionListener{
	private ArrayList<Packman>Packmanarr=new ArrayList<>();
	private ArrayList<Fruit>Fruitarr=new ArrayList<>();
	private ArrayList<Packman>Packmanarrtemp=new ArrayList<>();
	private ArrayList<Fruit>Fruitarrtemp=new ArrayList<>();
	private ImageIcon packmanimage;
	private ImageIcon cherryimage;
	private int counter=0;
	private int count=0;
	private JMenuItem load;
	private JMenuBar menubar;
	private JMenuItem save;
	private JMenuItem run;
	private JMenuItem how_to_run;
	private JMenuItem about_the_game;
	private JMenuItem clear;
	private JMenu menu2;
	private JMenu menu;
	private Image img;
	private int width;
	private int hight;
	public static void main(String[] args) {
		new resize();
	}
	public resize(){
		try {
			img = ImageIO.read(new File("Ariel1.png"));
			packmanimage=new ImageIcon("pacman.jpg");
			cherryimage=new ImageIcon("cherry.png");
			JFrame frame = new JFrame("OOP-EX3");
			menubar = new JMenuBar();
			menu = new JMenu("help");
			menubar.add(menu);
			about_the_game=new JMenuItem("about the game");
			about_the_game.addActionListener(this);
			menu.add(about_the_game);
			how_to_run =new JMenuItem("how to run");
			how_to_run.addActionListener(this);
			menu.add(how_to_run);
			menu2=new JMenu("option");
			clear=new JMenuItem("clear");
			clear.addActionListener(this);
			menu2.add(clear);
			load=new JMenuItem("load");
			load.addActionListener(this);
			menu2.add(load);
			save=new JMenuItem("save");
			save.addActionListener(this);
			menu2.add(save);
			run=new JMenuItem("run");
			run.addActionListener(this);
			menu2.add(run);
			menubar.add(menu2);
			frame.setJMenuBar(menubar);
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
			width = getWidth();
			hight = getHeight();

			if (width > 0 && hight > 0) {
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
			g.drawImage(scaled, 0, 0, this.getWidth(),this.getHeight(),this);
			//			double x = getWidth();
			//			double y = getHeight();
			//lat= (35.2022 + (x/125000));
			//lon = (32.10565 - (y/150000));	
			//	System.out.println("[lon: " + lon + " lat: " + lat + "]: X: " + x + " Y: " + y);
			for(int i=0;i<Packmanarr.size();i++) {
				g.drawImage(packmanimage.getImage(), Packmanarr.get(i).getOrinet().ix()-25, Packmanarr.get(i).getOrinet().iy()-25,50,50,null);
			}
			for(int i=0;i<Fruitarr.size();i++) {
				g.drawImage(cherryimage.getImage(), Fruitarr.get(i).getOrient().ix()-25, Fruitarr.get(i).getOrient().iy()-25,50,50,null);
			}
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON1) {
				x = e.getX();
				y = e.getY();
				System.out.println("left click you create new packman X: " + x + " Y: " + y);
				String test1= JOptionPane.showInputDialog("Please input packman speed : ");
				double speed=-1,radius=-1,high=0;
				boolean ans=true;
				boolean ans2=true;
				try {
					speed=Double.parseDouble(test1);
				}catch (NullPointerException n) {ans=false;}
				//catch(NumberFormatException ex) {speed=-1;}
				catch(Exception a) {speed=-1;}
				while(speed<=0&&ans) {
					test1= JOptionPane.showInputDialog("Please input packman speed(larger than 0) : ");	
					try {
						speed=Double.parseDouble(test1);
					}catch (NullPointerException n) {ans=false;}
					//catch(NumberFormatException ex) {speed=-1;}
					catch(Exception a) {speed=-1;}
				}
				if(ans) {
					String test2= JOptionPane.showInputDialog("Please input packman radius : ");
					try {
						radius=Double.parseDouble(test2);
					}catch (NullPointerException n) {ans=false;}
					//catch(NumberFormatException ex) {radius=-1;}
					catch(Exception a) {radius=-1;}
					while(radius<=0&&ans) {
						test2= JOptionPane.showInputDialog("Please input packman radius(larger than 0) : ");
						try {
							radius=Double.parseDouble(test2);
						}catch (NullPointerException n) {ans=false;}
						//catch(NumberFormatException ex) {radius=-1;}
						catch(Exception a) {radius=-1;}
					}
				}
				if(ans) {
					String test3=JOptionPane.showInputDialog("Please input packman high above ground : ");
					try {
						high=Double.parseDouble(test3);
					}catch (NullPointerException n) {ans=false;}
					//catch(NumberFormatException ex) {ans2=false;}
					catch(Exception a) {ans2=false;}
					while(!ans2&&ans) {
						test3= JOptionPane.showInputDialog("Please input packman high above ground(0 or lrager) : ");
						try {
							high=Double.parseDouble(test3);
						}catch (NullPointerException n) {ans=false;}
						//catch(NumberFormatException ex) {ans2=false;}
						catch(Exception a) {ans2=false;}
					}
				}
				if(ans) {
					Packmanarr.add(new Packman(count,x,y,high,speed,radius));
					count++;
					repaint();
				}
				else 
					System.out.println("you quit before crete new packman");
			}
			else if(e.getButton() == MouseEvent.BUTTON3) {
				x = e.getX();
				y = e.getY();
				System.out.println("right click you create new fruit X: " + x + " Y: " + y);
				String test1= JOptionPane.showInputDialog("Please input fruit weight : ");
				double weight=-1,high=0;
				boolean ans=true;
				boolean ans2=true;
				try {
					weight=Double.parseDouble(test1);
				}//catch(NumberFormatException n) {weight=-1;}
				catch(NullPointerException n) {ans=false;}
				catch(Exception a) {weight=-1;}
				while(weight<=0&&ans) {
					test1= JOptionPane.showInputDialog("Please input fruit weight(larger than 0) : ");
					try {
						weight=Double.parseDouble(test1);
					}catch (NullPointerException n) {ans=false;}
					//catch(NumberFormatException ex) {weight=-1;}
					catch(Exception a) {weight=-1;}
				}
				if(ans) {
					String test2=JOptionPane.showInputDialog("Please input fruit high  : ");
					try {
						high=Double.parseDouble(test2);
					}catch (NullPointerException n) {ans=false;}
					//catch(NumberFormatException ex) {ans2=false;}
					catch(Exception a) {ans2=false;}
					while(!ans2&&ans) {
						test2= JOptionPane.showInputDialog("Please input valid packman high  : ");
						try {
							high=Double.parseDouble(test2);
						}catch (NullPointerException n) {ans=false;}
						//catch(NumberFormatException ex) {ans2=false;}
						catch(Exception a) {ans2=false;}
					}
				}
				if(ans) {
					Fruitarr.add(new Fruit(counter,x,y,high,weight));
					counter++;
					repaint();			
				}
				else
					System.out.println("you quit before crete new fruit");
			}
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==load) {
			System.out.println("load");
			JFileChooser fileChooser = new JFileChooser();
			int returnValue = fileChooser.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				Game g=new Game(Game.load(selectedFile.toString()));
				Packmanarr=g.getArr();
				Fruitarr=g.getArray();
				System.out.println(Fruitarr.size());
				System.out.println(Packmanarr.size());
			}
		}
		if(e.getSource()==save) {
			System.out.println("save");
			Packmanarr=Pixel_To_Cordi.Pointp_to_Pixel(Packmanarr);
			Fruitarr=Pixel_To_Cordi.PointF_to_Pixel(Fruitarr);
			Game.save(new Game(Packmanarr,Fruitarr));
		}
		if(e.getSource()==run) {
			System.out.println("run");
			Packmanarrtemp=new ArrayList<>(Packmanarr);
			Fruitarrtemp=new ArrayList<>(Fruitarr);
			Path p=new Path(Packmanarr, Fruitarr);
			ShortestPathAlg s=new ShortestPathAlg(p);
			System.out.println(s.Shortalgo(p));
			Packmanarr=s.getArr();
			Fruitarr=s.getArray();
			System.out.println(Fruitarrtemp.size());
		}
		if(e.getSource()==how_to_run)
			JOptionPane.showMessageDialog(null, "For new Packman pressed left click on mouse on the place in the map that you want"
					+ ",\nFor new Fruit pressed right click on mouse on the place in the map that you want,"
					+ "\nFor run the game pressed on run button on menu under option."
					+ "\nIf you want to go back before you run the game click run button on menu under option.",
					"how to play", JOptionPane.PLAIN_MESSAGE);
		if(e.getSource()==about_the_game) {
			JOptionPane.showMessageDialog(null, "This is a packman game:\nthe purpose is to eat all the fruit /nThe borad game is map, while the game start you can see on kml the path of the packmans and it prints the min time that we make our packmans eat all the fruit on board. \nCreated & Designed by :\nBar Genish and Elyashiv Deri." ,
					"about the game", JOptionPane.PLAIN_MESSAGE);
		}
		if(e.getSource()==clear) {//לסדר שחזרו למקום שלהם
			Fruitarr=Fruitarrtemp;
			Packmanarr.clear();
			Packmanarr=Packmanarrtemp;
		}
	}
}
