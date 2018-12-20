package Map;
import java.util.ArrayList;
import Map.Time;
import Geom.Circle;
import Geom.Point3D;

public class test {
	static Map map=new Map();
	private static double Calculatetime(Packman p,Fruit f) {
		Circle c=new Circle(p.getOrinet(),p.getRadius());
		double dist=map.Distance_IN_Pixels(c.get_cen(),f.getOrient())-c.get_radius();
		if(dist<=0)return 0;
		return dist/p.getSpeed();
	}
	public static double Shortalgo(Game g) {
		return greedy(g);
	}
	private static double greedy(Game g) {
		double time=0;
		double min=Double.MAX_VALUE;
		int packmanindex=0;
		int fruitindex=0;
		ArrayList<Packman>arr=new ArrayList<>();
		ArrayList<Fruit>array=new ArrayList<>();
		array.clone();
		array.addAll(g.getArray());
		for(int i=0;i<g.getArr().size();i++) {
			Point3D t=map.CoordsToPixel(g.getArr().get(i).getOrinet(), 200, 200);
			g.getArr().get(i).setOrinet(t);
		}
		for(int i=0;i<g.getArray().size();i++) {
			Point3D t=map.CoordsToPixel(g.getArray().get(i).getOrient(), 200, 200);
			g.getArray().get(i).setOrient(t);
		}
		arr.clone();
		arr.addAll(g.getArr());
		Fruit fruittmp;
		double tmp=0;
		while(!g.getArray().isEmpty()) {
			for(int i=0;i<arr.size();i++) {
				for(int j=0;j<g.getArray().size();j++) {
					tmp=Calculatetime(arr.get(i),g.getArray().get(j));
					if(tmp<min) {
						min=tmp;
						packmanindex=i;
						fruitindex=j;
					}
				}
			}
			fruittmp=g.getArray().get(fruitindex);
			g.getArr().get(packmanindex).setScore(g.getArray().get(fruitindex).getWeight());
			Point3D p=calcvector(g.getArr().get(packmanindex).getOrinet(),fruittmp.getOrient());
						p=normalvector(p);
			double speed=g.getArr().get(packmanindex).getSpeed();
			p=new Point3D(p.x()*speed,p.y()*speed,p.z()*speed);
//			p=new Point3D(p.x()/speed,p.y()/speed,p.z()/speed);
			time+=min;
			patheat(arr.get(packmanindex),fruittmp,p);
			g.getArray().remove(fruitindex);
			min=Double.MAX_VALUE;
			//			g.setArr(arr);
		}
		//				for(int i=0;i<g.getArr().size();i++) {
		//					Point3D p=map.PixelToCoords(g.getArr().get(i).getOrinet().ix(), g.getArr().get(i).getOrinet().iy(), g.getArr().get(i).getOrinet().iz(), 200, 200);
		//					g.getArr().get(i).setOrinet(p);
		//				}
		g.getArray().addAll(array);
		for(int i=0;i<g.getArray().size();i++) {
			Point3D p=map.PixelToCoords(g.getArray().get(i).getOrient().ix(), g.getArray().get(i).getOrient().iy(), g.getArray().get(i).getOrient().iz(), 200, 200);
			g.getArray().get(i).setOrient(p);
		}
		return time;
	}
	private static void patheat(Packman pack,Fruit f,Point3D norm) {//לסדר
		Circle c=new Circle(pack.getOrinet(),pack.getRadius());
		double dist=c.distance3D(f.getOrient());
		int i=0;
		boolean ans=false;
		while(dist>0) {//להוסיף פור שירןץ לפי המהירות של הפקמן ולחשב שהפקמן ילך מטר ולא סתם צעד בנרמול 
			for(int j=1;j<=pack.getSpeed();j++) {
				c.get_cen().add(norm);
				dist=c.distance3D(f.getOrient());
				if(dist<=0) {
					Point3D p=c.get_cen();
					p=map.PixelToCoords(c.get_cen().ix(), c.get_cen().iy(), c.get_cen().iz(), 200, 200);
					pack.getPath().getArr().add(p);
					//			System.out.println(pack.getPath().getArr().get(i++));
					pack.setOrinet(p);
					pack.getTime().setSecond(1/j);
					ans=true;
				}
			}
			if(!ans) {
				pack.getTime().setSecond(1);
				Point3D p=c.get_cen();
				p=map.PixelToCoords(c.get_cen().ix(), c.get_cen().iy(), c.get_cen().iz(), 200, 200);
				pack.getPath().getArr().add(p);
				pack.setOrinet(p);
			}
		}
	}
	public static Point3D calcvector(Point3D p,Point3D p2) {
		return new Point3D(p2.x()-p.x(),p2.y()-p.y(),p2.z()-p.z());
	}
	public static Point3D normalvector(Point3D vector) {
		double sum=vector.x()*vector.x()+vector.y()*vector.y()+vector.z()*vector.z();
		sum=Math.sqrt(sum);
		return new Point3D(vector.x()/sum,vector.y()/sum,vector.z()/sum);
	}
	public static void main(String[] args) {
		Game g=Game.load("C:\\Users\\barge\\Desktop\\מונחה עצמים מטלה 3\\data\\game_1543685769754.csv");
		System.out.println(Shortalgo(g));
	}
}

