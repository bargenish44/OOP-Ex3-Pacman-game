package Map;

import java.util.ArrayList;
import Geom.Circle;
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
			time+=min;
			g.getArr().get(packmanindex).setScore(g.getArray().get(fruitindex).getWeight());
			g.getArr().get(packmanindex).getPath().getArr().add(fruittmp.getOrient());
			g.getArray().remove(fruitindex);
			g.getArr().get(packmanindex).setOrinet(fruittmp.getOrient());//לשנות בהתאם לזויית ולמצוא נקודה מדויקת
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
}