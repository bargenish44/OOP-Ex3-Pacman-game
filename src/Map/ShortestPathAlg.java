package Map;

import java.util.ArrayList;

import Coords.MyCoords;
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

	private double Calculatetime(Packman p,Fruit f) {
		Circle c=new Circle(p.getOrinet(),p.getRadius());
		double dist=Map.distance3d(c.get_cen(),f.getOrient())-c.get_radius();
		if(dist<=0)return 0;
		return dist/p.getSpeed();
	}
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
			fruittmp=g.getArray().get(fruitindex);
			g.getArr().get(packmanindex).setScore(g.getArray().get(fruitindex).getWeight());
			Point3D p=calcvector(g.getArr().get(packmanindex).getOrinet(),fruittmp.getOrient());
			p=normalvector(g.getArr().get(packmanindex).getOrinet(),p);
			MyCoords my=new MyCoords(g.getArr().get(packmanindex).getOrinet());
			Point3D temp;
			while(min>0) {
				temp=my.add(my.getPoint3D(), p);
				g.getArr().get(packmanindex).getPath().getArr().add(temp);
				g.getArr().get(packmanindex).setOrinet(temp);
				min=Calculatetime(g.getArr().get(packmanindex),g.getArray().get(fruitindex));
				time+=min;
			}
			g.getArray().remove(fruitindex);
			min=Double.MAX_VALUE;
		}
		return time;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public Point3D calcvector(Point3D p,Point3D p2) {
		return new Point3D(p.x()-p2.x(),p.y()-p2.y(),p.z()-p2.z());
	}
	public Point3D normalvector(Point3D p,Point3D vector) {
		double sum=vector.x()*vector.x()+vector.y()*vector.y()+vector.z()*vector.z();
		return new Point3D(vector.x()/sum,vector.y()/sum,vector.z()/sum);
	}
}