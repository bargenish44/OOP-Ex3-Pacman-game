package Map;

import java.util.ArrayList;


import Geom.Circle;
import Geom.Point3D;
import Map.Map;
public class ShortestPathAlg {

	private static double Calculatetime(Packman p,Fruit f) {
		Circle c=new Circle(p.getOrinet(),p.getRadius());
		double dist=Map.distance3d(c.get_cen(),f.getOrient())-c.get_radius();
		if(dist<=0)return 0;
		return dist/p.getSpeed();
	}
	public static double Shortalgo(Game g) {
		//		long start = System.nanoTime();
		double time=0;
		double min=Double.MAX_VALUE;
		int packmanindex=0;
		int fruitindex=0;
		ArrayList<Packman>arr=new ArrayList<>();
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
			if(!p.toString().equals("0.0,0.0,0.0")){
				System.out.println(p.toString());
				p=normalvector(p);
				System.out.println("p is: "+p.toString());
				//				g.getArr().get(packmanindex).setTime(min);
				patheat(arr.get(packmanindex),fruittmp,p);
			}
			//			g.getArray().get(fruitindex).setDead(System.nanoTime()-start);
			//			System.out.println(g.getArray().get(fruitindex).getDead());
			//			System.out.println(System.nanoTime());
			//			g.getArray().get(fruitindex).setDead(System.nanoTime()-start);
			g.getArray().remove(fruitindex);
			min=Double.MAX_VALUE;
			//			g.setArr(arr);
		}
		for(int i=0;i<g.getArr().size();i++) {
			g.getArr().get(i).setPath(arr.get(i).getPath());
		}
		return time;
	}
	private static void patheat(Packman pack,Fruit f,Point3D norm) {//לסדר
		Circle c=new Circle(pack.getOrinet(),pack.getRadius());
		double dist=Map.distance3d(c.get_cen(),f.getOrient())-c.get_radius();
		int i=0;
		boolean ans=false;
		while(dist>0) {//להוסיף פור שירןץ לפי המהירות של הפקמן ולחשב שהפקמן ילך מטר ולא סתם צעד בנרמול 
			for(int j=1;j<=pack.getSpeed();j++) {
				c.get_cen().add(norm);
				dist=Map.distance3d(c.get_cen(),f.getOrient())-c.get_radius();
				if(dist<=0) {
					pack.setTime(1/j);
					pack.getPath().getArr().add(c.get_cen());
					System.out.println(pack.getPath().getArr().get(i++));
					pack.setOrinet(c.get_cen());
					ans=true;
				}
			}
			if(!ans) {
				pack.setTime(1);
				pack.getPath().getArr().add(c.get_cen());
				pack.setOrinet(c.get_cen());
			}
		}
	}
	public static Point3D calcvector(Point3D p,Point3D p2) {
		return new Point3D(p.x()-p2.x(),p.y()-p2.y(),p.z()-p2.z());
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