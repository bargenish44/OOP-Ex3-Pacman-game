package Map;

import java.util.ArrayList;


import Geom.Circle;
import Geom.Point3D;
import Map.Map;
public class ShortestPathAlg {
	//	private static Game game;
	//	public ShortestPathAlg(Game g) {
	//		setGame(g);
	//	}
	//	public ShortestPathAlg(ArrayList<Packman>arr,ArrayList<Fruit> array) {
	//		setGame(new Game(arr,array));
	//	}
	private static double Calculatetime(Packman p,Fruit f) {
		Circle c=new Circle(p.getOrinet(),p.getRadius());
		double dist=Map.distance3d(c.get_cen(),f.getOrient())-c.get_radius();
		if(dist<=0)return 0;
		return dist/p.getSpeed();
	}
	public static double Shortalgo(Game g) {
		return greedy(g);
	}
	private static double greedy(Game g) {
		long start = System.nanoTime();
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
			//p=normalvector(p);
			p=new Point3D(p.x()/100,p.y()/100,p.z()/100);
			//			Point3D temp=g.getArr().get(packmanindex).getOrinet();
			time+=min;
			patheat(arr.get(packmanindex),fruittmp,p);
			System.out.println(arr.get(packmanindex).getOrinet().toString());
			//			System.out.println(start);
			g.getArray().get(fruitindex).setDead(System.nanoTime()-start);
			//			System.out.println(g.getArray().get(fruitindex).getDead());
			//			System.out.println(System.nanoTime());
			g.getArray().get(fruitindex).setDead(System.nanoTime()-start);
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
		System.out.println(pack.getOrinet().toString());
		Circle c=new Circle(pack.getOrinet(),pack.getRadius());
		double dist=c.distance3D(f.getOrient());
		int i=0;
		while(dist>0) {//להוסיף פור שירןץ לפי המהירות של הפקמן ולחשב שהפקמן ילך מטר ולא סתם צעד בנרמול 
			c.get_cen().add(norm);
			dist=c.distance3D(f.getOrient());
			pack.getPath().getArr().add(c.get_cen());
			//			System.out.println(pack.getPath().getArr().get(i++));
			pack.setOrinet(c.get_cen());
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