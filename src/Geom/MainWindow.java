package Geom;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import Map.Fruit;
import Map.Packman;


public class MainWindow extends JFrame implements MouseListener
{
	public BufferedImage myImage;
	private ImageIcon packmanimage;
	private ImageIcon cherryimage;
	private ArrayList<Packman>Packmanarr=new ArrayList<>();
	private ArrayList<Fruit>Fruitarr=new ArrayList<>();
	public MainWindow() 
	{
		initGUI();		
		this.addMouseListener(this); 
	}

	private void initGUI() 
	{
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu"); 
		MenuItem item1 = new MenuItem("menu item 1");
		MenuItem item2 = new MenuItem("menu item 2");

		menuBar.add(menu);
		menu.add(item1);
		menu.add(item2);
		this.setMenuBar(menuBar);

		try {
			myImage = ImageIO.read(new File("Ariel1.png"));
			packmanimage=new ImageIcon("pacman.jpg");
			cherryimage=new ImageIcon("cherry.png");
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	public void paint(Graphics g)
	{
		g.drawImage(myImage, 0, 0,this.getWidth(),this.getHeight(),null);
		for(int i=0;i<Packmanarr.size();i++) {
			Point3D p=CoordsToPixel(Packmanarr.get(i).getOrinet());
			System.out.println(p);
			g.drawImage(packmanimage.getImage(), p.ix()-25, p.iy()-25,50,50,null);
		}
		for(int i=0;i<Fruitarr.size();i++) {
			Point3D p=CoordsToPixel(Fruitarr.get(i).getOrient());
			g.drawImage(cherryimage.getImage(), p.ix()-25, p.iy()-25,50,50,null);
		}			
	}

	@Override
	public void mouseClicked(MouseEvent arg) {
		if(arg.getButton() == MouseEvent.BUTTON3) {
			int x=arg.getX();
			int y=arg.getY();
			System.out.println("right click you create new fruit X: " + x + " Y: " + y);
			Point3D p=PixelToCoords(arg.getX(), arg.getY(),0);
			double newx = p.x();
			double newy = p.y();
			Fruitarr.add(new Fruit(0,newx,newy,0,0));
			repaint();
		}
		else if(arg.getButton() == MouseEvent.BUTTON1) {
			int x=arg.getX();
			int y=arg.getY();
			System.out.println("left click you create new packman X: " + x + " Y: " + y);
			Point3D p=PixelToCoords(arg.getX(), arg.getY(),0);
			double newx = p.x();
			double newy = p.y();
			Packmanarr.add(new Packman(0,newx,newy,0,0,0));
			repaint();
		}
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
	Point3D leftUp = new Point3D(32.105770,  35.202469);
	Point3D leftDown = new Point3D(32.101899, 35.202469);
	Point3D RightUp = new Point3D(32.105770 , 35.211588);
	Point3D RightDown = new Point3D(32.101899, 35.211588);
	public Point3D PixelToCoords(int x, int y,double high) {
		double widthPixel=this.getWidth();
		double HeightPixel=this.getHeight();
		double YCoords = (((HeightPixel-(double)y)*35.211588)+((double)y*35.202469))/HeightPixel;
		double XCoords = (((widthPixel-(double)x)*32.101899)+((double)x*32.105770))/widthPixel;
		Point3D p2=new Point3D(XCoords,YCoords,high);
		System.out.println(p2.toString());
		return p2;
	}
	public Point3D CoordsToPixel(Point3D p) {
		double widthPixel=this.getWidth();
		double HeightPixel=this.getHeight();
		int widthcoords=(int) (widthPixel*((p.x()-32.101899)/(32.105770-32.101899)));
		int Heightcoords=(int) (HeightPixel*(35.211588-p.y())/((35.211588-35.202469)));
		Point3D p2=new Point3D(widthcoords,Heightcoords,p.z());
		return p2;
	}
}
