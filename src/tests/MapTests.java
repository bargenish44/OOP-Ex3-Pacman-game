package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Geom.Point3D;
import Map.Map;

class MapTests {//נשאר למרחקים לעשות

	@Test
	void New_Map_Tests() {
		Map map=new Map();
		Map map2=new Map(new Point3D(4,4,4),new Point3D(6,6,6),"Ariel2.png");
		if(map.getMap().equals(map2.getMap()))
			fail("Constractor problem");
		map2.setMap(map.getMap());
		if(!map.getMap().equals(map2.getMap()))
			fail("Seteer/geeter problem");
	}
	@Test
	void converts_Tests() {
		Map map=new Map();
		Point3D p=map.getLeftUp();
		Point3D p2=map.CoordsToPixel(p, 10, 10);
		p=map.PixelToCoords(p2.ix(), p2.iy(), p2.iz(), 10, 10);
		if(!p.toString().equals(map.getLeftUp().toString()))
			fail("covert problem");
	}

}
