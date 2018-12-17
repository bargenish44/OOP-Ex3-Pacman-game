package Map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.ArrayList;


import Geom.Point3D;

public class Game {
	private ArrayList<Fruit> array=new ArrayList<>();
	private ArrayList<Packman>arr=new ArrayList<>();
	public static String GameName;//for the test.
	public Game(ArrayList<Packman>arr,ArrayList<Fruit> array) {
		this.arr=arr;	
		this.array=array;
	}
	public Game() {
		arr=new ArrayList<>();
		array=new ArrayList<>();
	}
	public Game(Game g) {
		arr=g.arr;
		array=g.array;
	}
	public String toString() {
		String s="Type,id,Lat,Lon,Alt,Speed/Weight,Radius,"+arr.size()+","+array.size()+"\n";
		for(int i=0;i<arr.size();i++) {
			s+="P,"+arr.get(i).toString()+"\n";
		}
		for(int i=0;i<array.size();i++) {
			try {
				s+="F,"+array.get(i).toString()+"\n";
			}catch(IndexOutOfBoundsException e) {}
		}
		return s;
	}
	public static Game load(String CsvFile) 
	{
		String line = "";
		String cvsSplitBy = ",";
		Game g=new Game();
		try (BufferedReader br = new BufferedReader(new FileReader(CsvFile))) 
		{
			line=br.readLine();
			while ((line = br.readLine()) != null) 
			{
				String[] userInfo = line.split(cvsSplitBy);
				if(userInfo[0].equals("P")) {
					g.arr.add(new Packman(Integer.parseInt(userInfo[1]), new Point3D(userInfo[2]+","+userInfo[3]+","+userInfo[4]),Double.parseDouble(userInfo[5]),Double.parseDouble(userInfo[6])));
				}
				else if(userInfo[0].equals("F"))
					g.array.add(new Fruit(Integer.parseInt(userInfo[1]), new Point3D(userInfo[2]+","+userInfo[3]+","+userInfo[4]),Double.parseDouble(userInfo[5])));
			}

		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		return g;
	}
	public static void save(Game g) {
		LocalTime s=LocalTime.now();
		String time=s.toString().replaceAll(":", ".");
		String fileName="game"+time+".csv";
		GameName=fileName;
		String newfilepath="data\\"+fileName;
		PrintWriter pw=null;
		try 
		{
			pw = new PrintWriter(new File(newfilepath));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			return;
		}
		pw.write(g.toString());
		pw.close();
		System.out.println("saved: "+newfilepath);
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
