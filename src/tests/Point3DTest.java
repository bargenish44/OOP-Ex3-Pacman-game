package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Geom.Point3D;

class Point3DTest {
	/**
	 * Point3D tests.
	 * @author Bar Genish
	 * @author Elyashiv Deri
	 */

	@Test
	void testAddPoint3D() {

		Point3D test1 = new Point3D(4,2,0);

		Point3D test2 = new Point3D(3,2,0);

		Point3D ans = new Point3D(7,4,0);

		test1.add(test2);

		if(!ans.toString().equals(test1.toString()))

			fail("Error: Insert incorrect");
	}

	@Test
	void testDistance3DPoint3D() {

		Point3D test1=new Point3D(3,5,6);

		Point3D test2=new Point3D(2,3,4);
		if(test1.distance3D(test2)!=3)

			fail("ERROR: The distance calculation is incorrect");
	}

	@Test
	void testRescalePoint3DPoint3D() {

		Point3D test1 = new Point3D(2.5,3.0,0);

		Point3D test2 = new Point3D(5.5,4.5,0);

		Point3D test3 = new Point3D(3.5,1,0);

		test3.rescale(test1, test2);

		Point3D ans = new Point3D(8.0,-6.0,0.0);

		if(!ans.toString().equals(test3.toString()))

			fail("Error: Wrong calculation");
	}


	@Test
	void testAngleXY() {

		Point3D test1 = new Point3D(2.5,3.0,0);

		Point3D test2 = new Point3D(5.5,4.5,0);

		double ans = test2.angleXY(test1);

		if(ans!=-2.677945044588987)

			fail("Error: Wrong calculation");
	}

	@Test
	void testNorth_angle() {

		Point3D test1 = new Point3D(2.5,3.0,0);

		Point3D test2 = new Point3D(5.5,4.5,0);

		double ans = test2.north_angle(test1);

		if(ans!=243.43494882292202)

			fail("Error: Wrong calculation");
	}

}
