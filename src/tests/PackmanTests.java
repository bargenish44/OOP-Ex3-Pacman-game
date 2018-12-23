package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import Geom.Path;
import Geom.Point3D;
import Packman_Game.Packman;

class PackmanTests {

	@Test
	void Packman_Tests() {
		Packman p=new Packman(0,2,2,2,5,5);
		Packman p2=new Packman(0,new Point3D(2,2,2),5,5);
		if(!p.toString().equals(p2.toString()))
			fail("Constractor/tostring problem");
		Packman p3=new Packman(1,3,3,3,6,6);
		if(p.toString().equals(p3.toString()))
			fail("Constractor/tostring problem");
		p3.setID(0);
		if(p.toString().equals(p3.toString()))
			fail("Constractor/tostring problem");
		p3.setOrinet(new Point3D(2,2,2));
		if(p.toString().equals(p3.toString()))
			fail("Constractor/tostring problem");
		p3.setRadius(5);
		if(p.toString().equals(p3.toString()))
			fail("Constractor/tostring problem");
		p3.setSpeed(5);
		if(!p.toString().equals(p3.toString()))
			fail("Constractor/tostring problem");
		double a=p3.getScore();
		p3.setScore(2);
		if(a==p3.getScore())
			fail("Constractor/tostring problem");
		p3.resetScore();
		if(a!=p3.getScore())
			fail("Constractor/tostring problem");
		Path path=p.getPath();
		Path path2=p.getPath();
		if(!path.getArr().equals(path2.getArr()))
			fail("Constractor/tostring problem");
		path2=new Path(path);
		path.getArr().add(new Point3D(2,2,2));
		if(path.getArr().equals(path2.getArr()))
			fail("Constractor/tostring problem");
	}
}
