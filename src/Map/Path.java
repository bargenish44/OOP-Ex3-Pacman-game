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
	private ArrayList<Packman>packarr=new ArrayList<Packman>();
	private ArrayList<Fruit>fruitarr=new ArrayList<Fruit>();
	
	public Path(ArrayList<Packman> a, ArrayList<Fruit> b) {//constractor
		setPackarr(a);
		setFruitarr(b);
	}
	public Path(Path p) {//constractor
		setPackarr(p.getPackarr());
		setFruitarr(p.getFruitarr());
	}
//getters and setters
	public ArrayList<Packman> getPackarr() {
		return packarr;
	}
	public void setPackarr(ArrayList<Packman> packarr) {
		this.packarr = packarr;
	}
	public ArrayList<Fruit> getFruitarr() {
		return fruitarr;
	}
	public void setFruitarr(ArrayList<Fruit> fruitarr) {
		this.fruitarr = fruitarr;
	}

}
