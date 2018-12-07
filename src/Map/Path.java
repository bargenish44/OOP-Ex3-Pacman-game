package Map;

import java.util.ArrayList;
import java.util.Iterator;

import Geom.Point3D;
/**
 * This class represents a path- arraylist of 3D point in space.
 * @author Bar Genish
 * @author Elyashiv Deri
 */
public class Path{
	private ArrayList<Point3D> arr=new ArrayList<Point3D>();
	public Path(ArrayList<Point3D> a) {//constractor
		this.setArr(a);
	}
	public Path(Point3D a) {//constractor
		arr.add(a);
	}
	public Path(double x,double y,double z) {//constractor
		arr.add(new Point3D(x,y,z));
	}
//getters and setters
	public ArrayList<Point3D> getArr() {
		return arr;
	}

	public void setArr(ArrayList<Point3D> arr) {
		this.arr = arr;
	}

}
