package tests;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import Geom.Point3D;
import Map.Fruit;
import Map.Game;
import Map.Packman;

class GameTests {

	@Test
	void NewGame_and_to_stirng_tests() {
		Game g=new Game();
		ArrayList<Fruit> array=new ArrayList<>();
		ArrayList<Packman>arr=new ArrayList<>();
		arr.add(new Packman(0, new Point3D(4,4,4), 4, 4));
		arr.add(new Packman(1, new Point3D("2,2,2"), 2, 2));
		array.add(new Fruit(0, new Point3D(4,4,4), 4));
		array.add(new Fruit(1, new Point3D("2,2,2"), 2));
		Game g2=new Game(arr,array);
		if(g.toString().equals(g2.toString()))
			fail("Constractor problem");
		g=new Game(g2);
		if(!g.toString().equals(g2.toString()))
			fail("Constractor problem");
		arr.remove(0);
		g.setArr(arr);
		if(!g.toString().equals(g2.toString()))
			fail("Constractor problem");
	}
	@Test
	void Save_and_load_tests() {
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
		g.save(g);
		Game g2=new Game();
		g2=g2.load("data\\"+g.GameName);
		if(!g.toString().equals(g2.toString())) 
			fail("load/save problem");
	}
}