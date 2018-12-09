package Map;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Geom.Point3D;


public class Pixel_To_Cordi {

	private BufferedImage img;
	private int width;
	private int Height;

	public Pixel_To_Cordi () {		
		this.width =img.getWidth();
		this.Height = img.getHeight();	
	}

	public BufferedImage geImage () {
		return this.img;
	}

	public int getwidth() {
		return this.width;

	}

	public int getHeight() {
		return this.Height;

	}

	public ArrayList<Game> Point_to_GPS( ArrayList<Game> arr){//ìñãø
		for (int i = 0; i < arr.size(); i++) {
			String [] str = (arr.get(i).getPoint3D.split(","));//ìñãø
			double x = (35.2022 + (Double.parseDouble(str[0])/125000));
			System.out.println(x);
			double y = (32.10565 - (Double.parseDouble(str[1])/150000));
			System.out.println(y);
			arr.get(i).setPoint3D(String.valueOf(x)+ ","+String.valueOf(y)+","+0);
			System.out.println(arr.get(i).toString());
		}
		return arr;
	}
	
	public ArrayList<Game> Point_to_Pixel( ArrayList<Game> arr){//ìñãø
		for (int i = 0; i < arr.size(); i++) {
			String [] str = (arr.get(i).getPoint3D.split(","));//ìñãø
			double x = (double)((Double.parseDouble(str[0]) - 35.2022*125000));
			double y = (double)(32.10565 - (Double.parseDouble(str[1])*150000));
			arr.get(i).setPoint3D(String.valueOf(x)+ ","+String.valueOf(y)+","+0);
		}
		return arr;
	}
	public static ArrayList<Packman> Pointp_to_Pixel( ArrayList<Packman> arr){
		for (int i = 0; i < arr.size(); i++) {
			String [] str = (( arr.get(i).getOrinet().toString().split(",")));
			double x = (double)((Double.parseDouble(str[0]) - 35.2022*125000));
			double y = (double)(32.10565 - (Double.parseDouble(str[1])*150000));
			arr.get(i).setOrinet(new Point3D(x+ ","+y+","+0));
		}
		return arr;
	}
	
	public static ArrayList<Fruit> PointF_to_Pixel( ArrayList<Fruit> arr){
		for (int i = 0; i < arr.size(); i++) {
			String [] str = (( arr.get(i).getOrient().toString().split(",")));
			double x = (double)((Double.parseDouble(str[0]) - 35.2022*125000));
			double y = (double)(32.10565 - (Double.parseDouble(str[1])*150000));
			arr.get(i).setOrient(new Point3D(x+ ","+y+","+0));
		}
		return arr;
	}
}
