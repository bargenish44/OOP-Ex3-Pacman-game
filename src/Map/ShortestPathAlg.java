package Map;

import Geom.Circle;
import Geom.Point3D;
import Map.Map;
public class ShortestPathAlg {

	private double Calculatetime(Packman p,Fruit f) {
		Circle c=new Circle(p.getOrinet(),p.getRadius());
		double dist=Map.distance3d(c.get_cen(),f.getOrient())-c.get_radius();
		if(dist<=0)return 0;
		return dist/p.getSpeed();
	}
	public double Shortalgo(Game g) {
		double time=0;
		double min=Double.MAX_VALUE;
		int packmanindex=0;
		int fruitindex=0;
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
			g.getArr().get(packmanindex).setScore(g.getArray().get(fruitindex).getWeight());
			Point3D pacP = g.getArr().get(packmanindex).getOrinet();
			Point3D fruP = g.getArray().get(fruitindex).getOrient();
			double dist = Map.distance3d(pacP, fruP)-g.getArr().get(packmanindex).getRadius();
			double speed = g.getArr().get(packmanindex).getSpeed();
			Point3D step = calcStepVec(pacP, fruP, dist,speed);
			Packman pTemp = g.getArr().get(packmanindex);
			Point3D next=fruP;
			for(int i = 0;i<(dist/speed);i++) {
				next =new Point3D(pacP.x()+(i*step.x()),pacP.y()+(i*step.y()),pacP.z()+(i*step.z()));
				next.getTime().setSecond(1);
				pTemp.getPath().getArr().add(next);
			}
			pTemp.setOrinet(next);
			g.getArray().get(fruitindex).setTime(next.getTime());
			g.getArray().remove(fruitindex);
			min=Double.MAX_VALUE;
			time+=dist/speed;
		}
		Packman p;
		for(int i=0;i<g.getArr().size();i++) {
			p=g.getArr().get(i);
			for(int j=0;j<g.getArr().get(i).getPath().getArr().size();j++) {
				try {
					p.getPath().getArr().get(j).setTime(p.getPath().getArr().get(j+1).getTime());
				}catch(Exception e) {}
			}
		}
		return time;
	}
	private Point3D calcStepVec(Point3D pacman,Point3D fruit,double distance,double speed) {
		double A = speed/distance;
		double B = 1-A;
		double newX = pacman.x()*B+fruit.x()*A;
		double newY = pacman.y()*B+fruit.y()*A;
		double newZ = pacman.z()*B+fruit.z()*A;
		return new Point3D(newX-pacman.x(),newY-pacman.y(),newZ-pacman.z());
	}
	//	public static void main(String[] args) {
	//		Game g=Game.load("C:\\Users\\barge\\Desktop\\מונחה עצמים מטלה 3\\data\\game_1543685769754.csv");
	//		System.out.println(Shortalgo(g));
	//	}
}