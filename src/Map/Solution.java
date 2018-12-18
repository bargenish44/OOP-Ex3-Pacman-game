package Map;

import java.util.ArrayList;

import Geom.Path;

public class Solution {
	private ArrayList<Path>arr;
	public Solution() {
		arr=new ArrayList<Path>();
	}
	public Solution(ArrayList<Path>arr) {
		setArr(arr);
	}
	public Solution Solutionistime(double time) {
		//להריץ את המסלול עד השעה המסויימת ולהחזיר את המערך בנקודה הזאת
		for(int i=0;i<time;i++) {

		}
	}
	public ArrayList<Path> getArr() {
		return arr;
	}
	public void setArr(ArrayList<Path> arr) {
		this.arr = arr;
	}
}
