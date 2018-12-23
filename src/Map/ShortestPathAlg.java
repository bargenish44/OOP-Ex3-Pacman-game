package Map;

import Geom.Circle;
import Geom.Point3D;
import Map.Map;
public class ShortestPathAlg {
	/**
	 * A class that calculates the shortest path so that all the fruits to eat as soon as possible.
	 * @author Bar Genish
	 * @author Elyashiv Deri
	 */
	/**
	 * Calculate time between Fruit to Packman.
	 * @param Packman p - the packman.
	 * @param Fruit f - the fruit.
	 * @return double the time that it takes to the packman to eat the fruit based on the packman speed and raduis.
	 */
	private double Calculatetime(Packman p,Fruit f) {
		Circle c=new Circle(p.getOrinet(),p.getRadius());
		Map m=new Map();
		double dist=m.distance3d(c.get_cen(),f.getOrient())-c.get_radius();
		if(dist<=0)return 0;
		return dist/p.getSpeed();
	}
	/**
	 * Calculates the shortest path so that all the fruits to eat as soon as possible and updates all the packman paths.
	 * @param g Game - the game that we want to calculate his path.
	 * @return double - how many seconds the path takes.
	 */
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
			Map m=new Map();
			double dist = m.distance3d(pacP, fruP)-g.getArr().get(packmanindex).getRadius();
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
	/**
	 * Calculate thie packman next step (the point that the packman will be in the next second) based on his speed and raduis.
	 * @param Packman pacman - the packman.
	 * @param Fruit fruit - the fruit.
	 * @param distance - the distance between the packman to the fruit in meters.
	 * @param speed - the packman speed.
	 * @return Point3D the position of the next step of the packman.
	 */
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