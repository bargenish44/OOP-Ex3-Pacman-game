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
		String s="";
		for(int i=0;i<arr.size();i++) {
			s+=arr.get(i)+"\n";
		}
		for(int i=0;i<array.size();i++) {
			s+=array.get(i)+"\n";
		}
		return s;
	}
}
