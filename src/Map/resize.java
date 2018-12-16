package Map;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import java.awt.event.*;
import javax.swing.*;
import Geom.Point3D;

public class resize implements ActionListener{
	private ArrayList<Packman>Packmanarr=new ArrayList<>();
	private ArrayList<Fruit>Fruitarr=new ArrayList<>();
	private ArrayList<Packman>Packmanarrtemp=new ArrayList<>();
	private ArrayList<Fruit>Fruitarrtemp=new ArrayList<>();
	private double[] times;
	private boolean ans=false;
	private ImageIcon packmanimage;
	private ImageIcon cherryimage;
	private int counter=0;
	private Color[] colors = {Color.BLUE,Color.GREEN,Color.ORANGE,Color.red,Color.YELLOW,Color.GRAY};
	private int count=0;
	private JMenuItem load;
	private JMenuBar menubar;
	private JMenuItem save;
	private JMenuItem run;
	private JMenuItem how_to_run;
	private JMenuItem about_the_game;
	private JMenuItem clear;
	private JMenuItem Save_as_kml;
	private JMenu menu2;
	private JMenu menu;
	private Image img;
	private int width;
	private int hight;
	private gameTimer timer;
	private String str="";
	private JFrame frame;

	public static void main(String[] args) {
		new resize();
	}
	public resize(){
		try {
			img = ImageIO.read(new File("Ariel1.png"));
			packmanimage=new ImageIcon("pacman.jpg");
			cherryimage=new ImageIcon("cherry.png");
			frame = new JFrame("OOP-EX3");
			menubar = new JMenuBar();
			menu = new JMenu("Help");
			menubar.add(menu);
			about_the_game=new JMenuItem("About the game");
			about_the_game.addActionListener(this);
			menu.add(about_the_game);
			how_to_run =new JMenuItem("How to run");
			how_to_run.addActionListener(this);
			menu.add(how_to_run);
			menu2=new JMenu("Option");
			clear=new JMenuItem("Clear");
			clear.addActionListener(this);
			menu2.add(clear);
			Save_as_kml=new JMenuItem("Save as kml");
			Save_as_kml.addActionListener(this);
			menu2.add(Save_as_kml);
			load=new JMenuItem("Load");
			load.addActionListener(this);
			menu2.add(load);
			save=new JMenuItem("Save");
			save.addActionListener(this);
			menu2.add(save);
			run=new JMenuItem("Run");
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
			timer = new gameTimer();

		} catch (IOException | HeadlessException exp) {
			exp.printStackTrace();
		}
	}

	class ImagePanel extends JPanel implements MouseListener{

		private static final long serialVersionUID = 1L;
		private Image img;
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
		}

		@Override
		public Dimension getPreferredSize() {
			return img == null ? new Dimension(200, 200) : new Dimension(img.getWidth(this), img.getHeight(this));
		}
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(resize.this.img, 0, 0,this.getWidth(),this.getHeight(),null);
			for(int i=0;i<Packmanarr.size();i++) {
				Point3D p=CoordsToPixel(Packmanarr.get(i).getOrinet());
				g.drawImage(packmanimage.getImage(), p.ix()-25, p.iy()-25,50,50,null);
			}
			for(int i=0;i<Fruitarr.size();i++) {
				Point3D p=CoordsToPixel(Fruitarr.get(i).getOrient());
				g.drawImage(cherryimage.getImage(), p.ix()-25, p.iy()-25,50,50,null);
			}
			if(ans) {//להדפיס מרחק של כל פקמן
				int count=0;
				//double dist=0;
				for(int i=0;i<Packmanarr.size();i++) {
					Packman tmp=Packmanarr.get(i);
					Point3D p=CoordsToPixel(tmp.getOrinet());
					tmp.setOrinet(p);
					if(tmp.getPath().getArr().size()>1) {
						for(int j=0;j<tmp.getPath().getArr().size();j++) {
							if(count==7)
								count=0;
							Point3D p1=CoordsToPixel(tmp.getPath().getArr().get(j));
							try {
								Point3D p2=CoordsToPixel(tmp.getPath().getArr().get(j+1));
								g.setColor(colors[count]);
								g.drawLine(p1.ix(), p1.iy(),p2.ix(),p2.iy());
								Packmanarr.get(i).setOrinet(p2);
								//dist+=p1.distance3D(p2);
							}catch(IndexOutOfBoundsException e) {}
						}
						count++;
						//str+=time.getActionCommand();
						//System.out.println(dist+" count is: "+tmp.getID());
					}
					//dist=0;
				}
				//time.stop();
				//System.out.println(str);
			}
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON1) {
				int x = e.getX();
				int y = e.getY();
				System.out.println("left click you create new packman X: " + x + " Y: " + y);
				String test1= JOptionPane.showInputDialog("Please input packman speed : ");
				double speed=-1,radius=-1,high=0;
				boolean ans=true;
				boolean ans2=true;
				try {
					speed=Double.parseDouble(test1);
				}catch (NullPointerException n) {ans=false;}
				catch(Exception a) {speed=-1;}
				while(speed<=0&&ans) {
					test1= JOptionPane.showInputDialog("Please input packman speed(larger than 0) : ");	
					try {
						speed=Double.parseDouble(test1);
					}catch (NullPointerException n) {ans=false;}
					catch(Exception a) {speed=-1;}
				}
				if(ans) {
					String test2= JOptionPane.showInputDialog("Please input packman radius : ");
					try {
						radius=Double.parseDouble(test2);
					}catch (NullPointerException n) {ans=false;}
					catch(Exception a) {radius=-1;}
					while(radius<=0&&ans) {
						test2= JOptionPane.showInputDialog("Please input packman radius(larger than 0) : ");
						try {
							radius=Double.parseDouble(test2);
						}catch (NullPointerException n) {ans=false;}
						catch(Exception a) {radius=-1;}
					}
				}
				if(ans) {
					String test3=JOptionPane.showInputDialog("Please input packman high above ground : ");
					try {
						high=Double.parseDouble(test3);
					}catch (NullPointerException n) {ans=false;}
					catch(Exception a) {ans2=false;}
					while(!ans2&&ans) {
						test3= JOptionPane.showInputDialog("Please input packman high above ground(0 or lrager) : ");
						try {
							high=Double.parseDouble(test3);
						}catch (NullPointerException n) {ans=false;}
						catch(Exception a) {ans2=false;}
					}
				}
				if(ans) {
					Point3D p=PixelToCoords(x, y, high);
					Packmanarr.add(new Packman(count,p.x(),p.y(),high, speed, radius));
					count++;
					repaint();
				}
				else 
					System.out.println("you quit before crete new packman");
			}
			else if(e.getButton() == MouseEvent.BUTTON3) {
				int x = e.getX();
				int y = e.getY();
				System.out.println("right click you create new fruit X: " + x + " Y: " + y);
				String test1= JOptionPane.showInputDialog("Please input fruit weight : ");
				double weight=-1,high=0;
				boolean ans=true;
				boolean ans2=true;
				try {
					weight=Double.parseDouble(test1);
				}catch(NullPointerException n) {ans=false;}
				catch(Exception a) {weight=-1;}
				while(weight<=0&&ans) {
					test1= JOptionPane.showInputDialog("Please input fruit weight(larger than 0) : ");
					try {
						weight=Double.parseDouble(test1);
					}catch (NullPointerException n) {ans=false;}
					catch(Exception a) {weight=-1;}
				}
				if(ans) {
					String test2=JOptionPane.showInputDialog("Please input fruit high  : ");
					try {
						high=Double.parseDouble(test2);
					}catch (NullPointerException n) {ans=false;}
					catch(Exception a) {ans2=false;}
					while(!ans2&&ans) {
						test2= JOptionPane.showInputDialog("Please input valid packman high  : ");
						try {
							high=Double.parseDouble(test2);
						}catch (NullPointerException n) {ans=false;}
						catch(Exception a) {ans2=false;}
					}
				}
				if(ans) {
					Point3D p=PixelToCoords(x, y,high);
					Fruitarr.add(new Fruit(counter,p.x(),p.y(),high,weight));
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
		//		@Override
		//		public void actionPerformed(ActionEvent arg0) {
		//			str+=new Game(Packmanarr,Fruitarr).toString();
		//			System.out.println("1");
		//		}
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
				//ans=false;
			}
		}
		if(e.getSource()==save) {
			System.out.println("save");
			Game.save(new Game(Packmanarr,Fruitarr));
		}
		if(e.getSource()==run) {

			System.out.println("run");
			ans=true;
			Packmanarrtemp=Packmanarr;
			Fruitarrtemp=Fruitarr;
			Game g=new Game(Packmanarr, Fruitarr);
			ShortestPathAlg s=new ShortestPathAlg(g);
			TimerTask task = new TimerTask() {
				@Override
				public void run() {
					Game game=new Game(Packmanarr,Fruitarr);
					str+=game.toString();
					frame.repaint();
				}
			};
			timer.startTimer(task);
			System.out.println(s.Shortalgo(g));
			timer.endTimer();
			Packmanarr=g.getArr();
			Fruitarr=g.getArray();
			//Fruitarr=Fruitarrtemp;
			System.out.println(str);
		}
		if(e.getSource()==how_to_run)
			JOptionPane.showMessageDialog(null, "For new Packman pressed left click on mouse on the place in the map that you want"
					+ ",\nFor new Fruit pressed right click on mouse on the place in the map that you want,"
					+ "\nFor run the game pressed on run button on menu under option,"
					+ "\nIf you want to go back before you run the game click clear button on menu under option.",
					"How to play", JOptionPane.PLAIN_MESSAGE);
		if(e.getSource()==about_the_game) {
			JOptionPane.showMessageDialog(null, "This is a packman game:\n"
					+ "The purpose is to eat all the fruit \n"
					+ "The borad game is map, while the game start you can see on kml the path of the packmans and it"
					+ " prints the min time that we make our packmans eat all the fruit on board."
					+ " \nCreated & Designed by :\nBar Genish and Elyashiv Deri." ,
					"About the game", JOptionPane.PLAIN_MESSAGE);
		}
		if(e.getSource()==clear) {//לסדר שחזרו למקום שלהם
			System.out.println("clear");
			Fruitarr=Fruitarrtemp;
			Packmanarr=Packmanarrtemp;
			//Packmanarr=Packmanarrtemp;
			ans=false;
		}
		if(e.getSource()==Save_as_kml) {
			System.out.println("Save as kml");
		}

		frame.repaint();
	}

	Point3D leftUp = new Point3D(32.105770,  35.202469);
	Point3D leftDown = new Point3D(32.101899, 35.202469);
	Point3D RightUp = new Point3D(32.105770 , 35.211588);
	Point3D RightDown = new Point3D(32.101899, 35.211588);
	public Point3D PixelToCoords(int x, int y,double high) {
		double widthPixel=width;
		double HeightPixel=hight;
		double YCoords = (((HeightPixel-(double)y)*35.211588)+((double)y*35.202469))/HeightPixel;
		double XCoords = (((widthPixel-(double)x)*32.101899)+((double)x*32.105770))/widthPixel;
		Point3D p2=new Point3D(XCoords,YCoords,high);
		System.out.println(p2.toString());
		return p2;
	}
	public Point3D CoordsToPixel(Point3D p) {
		double widthPixel=width;
		double HeightPixel=hight;
		int widthcoords=(int) (widthPixel*((p.x()-32.101899)/(32.105770-32.101899)));
		int Heightcoords=(int) (HeightPixel*(35.211588-p.y())/((35.211588-35.202469)));
		Point3D p2=new Point3D(widthcoords,Heightcoords,p.z());
		return p2;
	}
	final static int R = 6371;
	public double Distance_IN_Pixels(Point3D p1, Point3D p2) {
		final int R = 6371; // Radius of the earth
		double latDistance = Math.toRadians(p2.x() - p1.x());
		double lonDistance = Math.toRadians(p2.y() - p1.y());
		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
				+ Math.cos(Math.toRadians(p1.x())) * Math.cos(Math.toRadians(p2.x()))
				* Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = R * c * 1000; // convert to meters
		double height = p1.z() - p2.z();
		distance = Math.pow(distance, 2) + Math.pow(height, 2);
		return Math.sqrt(distance);
	}
	public  double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {
		double [] ans = {0,0,0};
		ans[0] = gps0.north_angle(gps1);
		ans[1] = gps0.up_angle(gps1); 
		ans[2] =(distance3d(gps0, gps1));
		return ans;
	}
	public static double distance3d(Point3D gps0, Point3D gps1) {
		double LonNorm=Math.cos(gps0.x()*Math.PI/180);
		double diff = gps1.x()-gps0.x();
		double radian = (diff*Math.PI)/180;
		double tometer1 = Math.sin(radian)*R*1000;
		double diff2 = gps1.y()-gps0.y();
		double radian2 = (diff2*Math.PI)/180;
		double tometer2 = Math.sin(radian2)*R*1000*LonNorm;
		return Math.sqrt((tometer1*tometer1) + (tometer2*tometer2));
	}
}