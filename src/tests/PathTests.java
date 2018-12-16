package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Geom.Path;
import Geom.Point3D;

class Pathtests {

	@Test
	void NewPattest() {
		Path p=new Path();
		ArrayList<Point3D>arr=new ArrayList<>();
		arr.add(new Point3D(0,0,0));
		if(!p.getArr().isEmpty())
			fail("Constractor problems");
		Path p2=new Path(arr);
		Path p3=new Path(p2);
		if(!p2.getArr().equals(p3.getArr()))
			fail("Constractor problems");
	}
	@Test
	void GetDist() {
		Path p=new Path();
		if(p.GetDist()!=0)
			fail("distance problem");
		p.getArr().add(new Point3D(0,0,0));
		p.getArr().add(new Point3D(10,0,0));
		p.getArr().add(new Point3D(0,0,0));
		p.getArr().add(new Point3D(10,0,0));
		p.getArr().add(new Point3D(0,0,0));
		p.getArr().add(new Point3D(40,0,0));
		if(p.GetDist()!=80.0)
			fail("distance problem");
		ArrayList<Point3D>arr=new ArrayList<>();
		arr.add(new Point3D(0,0,0));
		p.setArr(arr);
		if(p.GetDist()!=0)
			fail("distance problem");
	}
}
