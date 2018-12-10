package Map;

import java.util.ArrayList;
import Geom.Circle;

public class ShortestPathAlg {
	private Path p;
	private double time=0;
	private ArrayList<Packman>arr;
	private ArrayList<Fruit>array;
	public ShortestPathAlg(Path p,ArrayList<Packman>arr,ArrayList<Fruit>array) {
		setP(p);
	}
	public ShortestPathAlg(Path p) {
		setP(p);
	}
	public ShortestPathAlg(ArrayList<Packman>arr,ArrayList<Fruit>array){
		Path p=new Path(arr, array);
		setP(p);
	}
	public ShortestPathAlg(ShortestPathAlg ot) {
		setP(ot.getP());
		setTime(ot.getTime());
	}
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}
	public Path getP() {
		return p;
	}
	public void setP(Path p) {
		this.p = p;
		arr=p.getPackarr();
		array=p.getFruitarr();
	}
	private double Calculatetime(Packman p,Fruit f) {
		double time=0;
		Circle c=new Circle(p.getOrinet(),p.getRadius());
		double dist=c.distance3D(f.getOrient());
		if(dist==0)return 0;
		return dist/p.getSpeed();
	}
	public double greedy(Path p) {
		double time=0;
		setP(p);
		double min=Double.MAX_VALUE;
		int packmanindex=0;
		Fruit fruittmp;
		double tmp;
		while(!array.isEmpty()) {
			fruittmp=array.get(0);
			for(int i=0;i<arr.size();i++) {
				tmp=Calculatetime(arr.get(i),array.get(0));
				if(tmp<min) {
					min=tmp;
					packmanindex=i;
				}
			}
			time+=min;
			array.remove(0);
			arr.get(packmanindex).setOrinet(fruittmp.getOrient());//לשנות בהתאם לזויית ולמצוא נקודה מדויקת
		}
		return time;
	}
	public ArrayList<Packman> getArr() {
		return arr;
	}
	public void setArr(ArrayList<Packman> arr) {
		this.arr = arr;
	}
	public ArrayList<Fruit> getArray() {
		return array;
	}
	public void setArray(ArrayList<Fruit> array) {
		this.array = array;
	}
}