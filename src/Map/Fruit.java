package Map;

import Geom.Point3D;

public class Fruit {
	private int ID;
	private Point3D orient;
	private double Weight;
	private double Radius;
	public Fruit(int ID,double x,double y,double z, double weight, double Radius) {
		setID(ID);
		setOrient(new Point3D(x, y,z));
		setWeight(weight);
		setRadius(Radius);
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public Point3D getOrient() {
		return orient;
	}
	public void setOrient(Point3D orient) {
		this.orient = orient;
	}
	public double getWeight() {
		return Weight;
	}
	public void setWeight(double weight) {
		Weight = weight;
	}
	public double getRadius() {
		return Radius;
	}
	public void setRadius(double radius) {
		Radius = radius;
	}
}
