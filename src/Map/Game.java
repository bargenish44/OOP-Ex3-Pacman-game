package Map;

import java.util.ArrayList;

public class Game {
	private ArrayList<Packman>arr=new ArrayList<>();
	private ArrayList<Fruit> array=new ArrayList<>();
	public Game(ArrayList<Packman>arr,ArrayList<Fruit> array) {
		this.arr=arr;	
		this.array=array;
	}
	public String toString() {
		String s="Type,id,Lat,Lon,Alt,Speed/Weight,Radius,"+arr.size()+","+array.size()+"\n";
		for(int i=0;i<arr.size();i++) {
			s+="P,"+arr.get(i).toString()+"\n";
		}
		for(int i=0;i<array.size();i++) {
			s+="F,"+array.get(i).toString()+"\n";
		}
		return s;
	}
	public static void main(String[] args) {
		Packman p=new Packman(0,4, 2, 4, 4, 2);
		Packman p2=new Packman(1,4, 2, 4, 4, 2);
		Fruit f=new Fruit(0, 6, 6, 6, 6);
		Fruit f2=new Fruit(1, 4, 6, 2, 6);
		ArrayList<Packman>arr=new ArrayList<>();
		ArrayList<Fruit> array=new ArrayList<>();
		arr.add(p);
		arr.add(p2);
		array.add(f);
		array.add(f2);
		Game g=new Game(arr, array);
		System.out.println(g.toString());
	}
}
