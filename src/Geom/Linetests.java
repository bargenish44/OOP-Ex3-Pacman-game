package Geom;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Geom.Line;
import Geom.Point3D;

class Linetests {//למחוק אם לא צריך

	@Test
	void NewLine_and_to_stirngtest() {
		Line l=new Line(new Point3D(4,2,3), new Point3D(9,3,3));
		Line l2=new Line(4,2,3,9,3,3);
		if(!l.toString().equals(l2.toString())) {
			fail("You have problem with the constractor");
		}
	}
	@Test
	void distanc3detest() {
		Line l=new Line(new Point3D(4,2,0), new Point3D(9,2,0));
		System.out.println(l.distance3D(new Point3D(9,6,0)));
		if(l.distance3D(new Point3D(13,2,0))!=4.0)
			fail("You have problem with the distance3d");
//		if(c.distance3D(new Point3D(10,3,0))!=1.0) {
//			fail("You have problem with the distance3d");
//		}
	}
//	@Test
//	void distanc2detest() {
//		Geom.Circle c=new Geom.Circle(new Point3D(4,3,0),5);
//		if(c.distance2D(new Point3D(9,3,0))!=0.0)
//			fail("You have problem with the distance3d");
//		if(c.distance2D(new Point3D(10,3,0))!=1.0) {
//			fail("You have problem with the distance3d");
//		}
//	}

}
