package Map;

import Geom.Point3D;

public class Packman {
	private int ID;
	private Point3D orinet;
	private double speed;
	private double Radius;

	public Packman(int ID,double x,double y,double z, double speed, double Radius) {
		setOrinet(new Point3D(x, y, z));
		setSpeed(speed);
		setRadius(Radius);
		setID(ID);
	}
	public Packman(int ID,Point3D p,double spped,double Raduis) {
		setID(ID);
		setOrinet(p);
		setSpeed(spped);
		setRadius(Raduis);
	}
	public Point3D getOrinet() {
		return orinet;
	}
	public void setOrinet(Point3D orinet) {
		this.orinet = orinet;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public double getRadius() {
		return Radius;
	}
	public void setRadius(double radius) {
		Radius = radius;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String toString() {
		return ID+","+orinet.toString()+","+speed+","+Radius;
	}
}
