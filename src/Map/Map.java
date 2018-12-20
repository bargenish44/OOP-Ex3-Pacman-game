package Map;
import Geom.Point3D;

public class Map {
	private Point3D leftUp;
	private Point3D RightDown;
	private String map;
	final static int R = 6371;
	public Map() {
		leftUp = new Point3D(32.105770,  35.202469);
		RightDown = new Point3D(32.101899, 35.211588);
		this.setMap("Ariel1.png");
	}
	public Map(Point3D leftUp,Point3D rightDown,String map) {
		this.leftUp=leftUp;
		this.RightDown=rightDown;
		this.setMap(map);
	}//getters and setters
	public String getMap() {
		return map;
	}
	public void setMap(String map) {
		this.map = map;
	}
	public Point3D getLeftUp() {
		return leftUp;
	}
	public void setLeftUp(Point3D leftUp) {
		this.leftUp = leftUp;
	}
	public Point3D getRightDown() {
		return RightDown;
	}
	public void setRightDown(Point3D rightDown) {
		RightDown = rightDown;
	}
	public Point3D PixelToCoords(int x, int y,double high,int width,int hight) {
		double YCoords = (((hight-(double)y)*RightDown.y())+((double)y*leftUp.y()))/hight;
		double XCoords = (((width-(double)x)*RightDown.x())+((double)x*leftUp.x()))/width;
		Point3D p2=new Point3D(XCoords,YCoords,high);
		//		System.out.println(p2.toString());
		return p2;
	}
	public Point3D CoordsToPixel(Point3D p,int width,int hight) {
		int widthcoords=(int) (width*((p.x()-RightDown.x())/(leftUp.x()-RightDown.x())));
		int Heightcoords=(int) (hight*(RightDown.y()-p.y())/((RightDown.y()-leftUp.y())));
		Point3D p2=new Point3D(widthcoords,Heightcoords,p.z());
		return p2;
	}
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

	//	public Point3D calcvector(Point3D p,Point3D p2) {
	//		return new Point3D(p.x()-p2.x(),p.y()-p2.y(),p.z()-p2.z());
	//	}
	//	public Point3D normalvector(Point3D p,Point3D vector) {
	//		double sum=vector.x()*vector.x()+vector.y()*vector.y()+vector.z()*vector.z();
	//		return new Point3D(vector.x()/sum,vector.y()/sum,vector.z()/sum);
	//	}
}
