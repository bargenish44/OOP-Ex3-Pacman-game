package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Geom.Point3D;
import Map.Fruit;
import Map.Game;
import Map.Packman;
import Map.Path2KML;

class Path2kmlTests {

	@Test
	void test() {
		Game g=new Game();
		boolean ans=Path2KML.path2kml(g);
		if(!ans)
			fail("Path2kml problem");
	}
	@Test
	void test2() {
		ArrayList<Fruit> array=new ArrayList<>();
		ArrayList<Packman>arr=new ArrayList<>();
		arr.add(new Packman(0, new Point3D(32.1045513,35.2035022,10), 4, 4));
		arr.add(new Packman(1, new Point3D("32.10451344,35.21019738,0"), 2, 2));
		array.add(new Fruit(0, new Point3D(32.10462702,35.20573393,10), 4));
		array.add(new Fruit(1, new Point3D("32.10478793,35.20498036,0"), 2));
		arr.get(0).getTime().setSecond(200);
		arr.get(1).getTime().setSecond(20);
		array.get(0).getTime().setSecond(20);
		array.get(1).getTime().setSecond(1);
		Game g=new Game(arr,array);
		boolean ans=Path2KML.path2kml(g);
		if(!ans)
			fail("Path2kml problem");
	}

}
