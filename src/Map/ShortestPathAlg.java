package Map;

import java.util.ArrayList;

import Geom.Circle;
import Geom.Point3D;
import Map.Map;
public class ShortestPathAlg {
	private Game game;
	public ShortestPathAlg(Game g) {
		setGame(g);
	}
	public ShortestPathAlg(ArrayList<Packman>arr,ArrayList<Fruit> array) {
		setGame(new Game(arr,array));
	}

	private static double Calculatetime(Packman p,Fruit f) {
		Circle c=new Circle(p.getOrinet(),p.getRadius());
		double dist=Map.distance3d(c.get_cen(),f.getOrient())-c.get_radius();
		if(dist<=0)return 0;
		return dist/p.getSpeed();
	}
	//	public static void main(String[] args) {
	//		Point3D p=new Point3D(1,8,8);
	//		Point3D p2=new Point3D(5,4,4);
	//		Circle c=new Circle(p, 2);
	//		System.out.println(c.distance3D(p2));
	//	}
	public double Shortalgo(Game g) {
		return greedy(g);
	}
	private double greedy(Game g) {
		double time=0;
		setGame(g);
		double min=Double.MAX_VALUE;
		int packmanindex=0;
		int fruitindex=0;
		Fruit fruittmp;
		double tmp=0;
		while(!g.getArray().isEmpty()) {
			for(int i=0;i<g.getArr().size();i++) {
				for(int j=0;j<g.getArray().size();j++) {
					tmp=Calculatetime(g.getArr().get(i),g.getArray().get(j));
					if(tmp<min) {
						min=tmp;
						packmanindex=i;
						fruitindex=j;
					}
				}
			}
			//			System.out.println(tmp);
			fruittmp=g.getArray().get(fruitindex);
			g.getArr().get(packmanindex).setScore(g.getArray().get(fruitindex).getWeight());
			g.getArr().get(packmanindex).getPath().getArr().add(fruittmp.getOrient());
			g.getArr().get(packmanindex).setOrinet(fruittmp.getOrient());
			//			Point3D p=calcvector(g.getArr().get(packmanindex).getOrinet(),fruittmp.getOrient());
			//			p=normalvector(p);
			//			Point3D temp=g.getArr().get(packmanindex).getOrinet();
			time+=min;
			//			while(min>0) {
			//				temp.add(p);
			//				g.getArr().get(packmanindex).getPath().getArr().add(temp);
			//				g.getArr().get(packmanindex).setOrinet(temp);
			//				min=Calculatetime(g.getArr().get(packmanindex),g.getArray().get(fruitindex));
			//				System.out.println(min+" min");
			//				time+=min;
			//			}
			g.getArray().remove(fruitindex);
			min=Double.MAX_VALUE;
			System.out.println(time);
		}
		return time;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	//	public static Point3D calcvector(Point3D p,Point3D p2) {
	//		return new Point3D(p2.x()-p.x(),p2.y()-p.y(),p2.z()-p.z());
	//	}
	//	public static Point3D normalvector(Point3D vector) {
	//		double sum=vector.x()*vector.x()+vector.y()*vector.y()+vector.z()*vector.z();
	//		sum=Math.sqrt(sum);
	//		return new Point3D(vector.x()/sum,vector.y()/sum,vector.z()/sum);
	//	}
	//	public static void main(String[] args) {
	//		Point3D p=new Point3D(1,4,6);
	//		Point3D p2=new Point3D(5,8,8);
	//		Point3D p3=calcvector(p,p2);
	//		System.out.println(p3.toString());
	//		p3=normalvector(p3);
	//		System.out.println(p3.toString());
	//	}
}