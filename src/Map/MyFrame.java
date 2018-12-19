package Map;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.event.*;
import javax.swing.*;
import Geom.Point3D;

public class MyFrame implements ActionListener{
	private ArrayList<Packman>Packmanarr=new ArrayList<>();
	private ArrayList<Fruit>Fruitarr=new ArrayList<>();
	private ArrayList<Packman>Packmanarrtemp=new ArrayList<>();
	private ArrayList<Fruit>Fruitarrtemp=new ArrayList<>();
	//	private double[] times;
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
	//	private gameTimer timer;
	//	private String str="";
	private JFrame frame;
	private Map map;
	private boolean ans2=false;
	//	private Thread thread;
	//	private Runnable target;

	public static void main(String[] args) {
		new MyFrame();
	}
	public MyFrame(){
		try {
			map=new Map();
			img = ImageIO.read(new File(map.getMap()));
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
			//			timer = new gameTimer();

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
			g.drawImage(img, 0, 0,this.getWidth(),this.getHeight(),null);
			for(int i=0;i<Packmanarr.size();i++) {
				Point3D p=map.CoordsToPixel(Packmanarr.get(i).getOrinet(),width,hight);
				g.drawImage(packmanimage.getImage(), p.ix()-25, p.iy()-25,50,50,null);
			}
			for(int i=0;i<Fruitarr.size();i++) {
				Point3D p=map.CoordsToPixel(Fruitarr.get(i).getOrient(),width,hight);
				g.drawImage(cherryimage.getImage(), p.ix()-25, p.iy()-25,50,50,null);
			}
			if(ans) {//להדפיס מרחק של כל פקמן
				int count=0;
				//				double dist=0;
				for(int i=0;i<Packmanarr.size();i++) {
					Packman tmp=Packmanarr.get(i);
					Point3D p=map.CoordsToPixel(tmp.getOrinet(),width,hight);
					tmp.setOrinet(p);
					if(tmp.getPath().getArr().size()>0) {
						System.out.println(tmp.getPath().getArr().size());
						if(count==6)
							count=0;
						for(int j=1;j<tmp.getPath().getArr().size();j++) {
							Point3D p1=map.CoordsToPixel(tmp.getPath().getArr().get(j-1),width,hight);
							//							try {
							Point3D p2=map.CoordsToPixel(tmp.getPath().getArr().get(j),width,hight);
							g.setColor(colors[count]);
							g.drawLine(p1.ix(), p1.iy(),p2.ix(),p2.iy());
							//							System.out.println(p1.ix()+": p1x "+p1.iy()+": p1y "+p2.ix()+": p2x "+p2.iy()+": p2y .");
							//							Packmanarr.get(i).setOrinet(p2);
							//dist+=p1.distance3D(p2);
							//							}catch(IndexOutOfBoundsException e) {System.out.println("wrong");}
						}
						count++;
						//str+=time.getActionCommand();
						//System.out.println(dist+" count is: "+tmp.getID());
					}
					//					dist=0;
				}
				ans2=true;
			}
//			if(ans2) {
//				System.out.println("arr pos: "+Packmanarr.get(0).getOrinet());
//				System.out.println("arrtemp pos: "+Packmanarrtemp.get(0).getOrinet());
//				int max=MaxPathSize(Packmanarr);
//				for(int k=0;k<max;k++) {
//					frame.repaint();
//					int j=0;
//					for(int i=0;i<Packmanarr.size();i++) {
//						try {
//							Packmanarr.get(i).setOrinet(Packmanarr.get(i).getPath().getArr().get((int)(j*10*Packmanarr.get(i).getSpeed())));
//						}catch (Exception e) {}
//						j++;
//					}
//					frame.repaint();
//					try {
//						Thread.sleep(10);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//				ans2=false;
//			}
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
					Point3D p=map.PixelToCoords(x, y, high,width,hight);
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
					Point3D p=map.PixelToCoords(x, y,high,width,hight);
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

		//		@Override
		//		public void run() {
		//			if(ans3) {
		//				System.out.println(1);
		//				target=new Runnable() {
		//					@Override
		//					public void run() {
		//						for(int i=0;i<Packmanarr.size();i++) {
		//							for(int j=0;j<Packmanarr.get(i).getPath().getArr().size();j++){
		//								System.out.println(Packmanarr.get(i).getPath().getArr().get(j).toString());
		//								frame.repaint();
		//								Packmanarr.get(i).setOrinet(Packmanarr.get(i).getPath().getArr().get(j));
		//								frame.repaint();
		//								try {
		//									System.out.println(2);
		//									Thread.sleep(100000);
		//								} catch (InterruptedException e) {
		//									System.out.println(1);
		//									e.printStackTrace();
		//								}
		//							}
		//						}
		//						ans3=false;
		//					}											
		//				};
		//				frame.repaint();
		//			}
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
				
			}
		}
		if(e.getSource()==save) {
			System.out.println("save");
			Game.save(new Game(Packmanarr,Fruitarr));
		}
		if(e.getSource()==run) {
			System.out.println("run");
			play_Sound("pacman.wav");
			Packmanarrtemp.clear();
			Packmanarrtemp.addAll(Packmanarr);
			//			Fruitarrtemp.clear();
			Fruitarrtemp.addAll(Fruitarr);
			Game g=new Game(Packmanarr, Fruitarr);
			//			System.out.println(ShortestPathAlg.Shortalgo(g));
			System.out.println(ShortestPathAlg.Shortalgo(g));
			Packmanarr=g.getArr();
			Fruitarr=Fruitarrtemp;
			for(int i=0;i<Packmanarr.size();i++) {
				Packmanarr.get(i).setOrinet(Packmanarrtemp.get(i).getOrinet());
			}
			ans=true;
			//			Fruitarr=g.getArray();
			//			ans3=true;
			//			thread=new Thread(target);
			//			thread.start();
			//			ans2=true;
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
			Fruitarr.clear();
			Fruitarr.addAll(Fruitarrtemp);
			Packmanarr.clear();
			Packmanarr.addAll(Packmanarrtemp);
			//Packmanarr=Packmanarrtemp;
			ans=false;
		}
		if(e.getSource()==Save_as_kml) {
			Game g=new Game(Packmanarr,Fruitarr);
			System.out.println("Save as kml");
			Path2KML.path2kml(g);
		}
		frame.repaint();
	}
	public void play_Sound(String path) {
		try {
			//			@SuppressWarnings("unused")
			PlaySound p = new PlaySound(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static int MaxPathSize(ArrayList<Packman>arr) {
		int max=0;
		for(int i=0;i<arr.size();i++) {
			if(arr.get(i).getPath().getArr().size()>max)
				max=arr.get(i).getPath().getArr().size();
		}
		return max;
	}
}