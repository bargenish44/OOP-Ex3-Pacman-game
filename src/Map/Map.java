package Map;

import java.util.*;

import Geom.Circle;
import Geom.Point3D;
import Map.Packman;

public class Map {
	public Map() {

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
