package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Geom.Point3D;
import Packman_Game.Fruit;
import Packman_Game.Game;
import Packman_Game.Packman;
import Packman_Game.ShortestPathAlg;

class ShortestPathAlgTest {
	/**
	 * ShortestPathAlg tests.
	 * @author Bar Genish
	 * @author Elyashiv Deri
	 */
	@Test
	void Timetest() {
		Game g=new Game();
		ShortestPathAlg s=new ShortestPathAlg();
		if(s.Shortalgo(g)!=0)
			fail("Time fail");
		g.getArr().add(new Packman(0,32.10290536,35.20950241,0,1,1));
		g.getArray().add(new Fruit(0,32.10290536,35.20950241,0,1));
		if(s.Shortalgo(g)!=0)
			fail("Time fail");
		if(g.getArr().get(0).getScore()!=1)
			fail("Score fail");
		g.getArray().clear();
		g.getArray().add(new Fruit(0,32.10290536,36.20950241,0,1));
		if(s.Shortalgo(g)==0)
			fail("Time fail");
		g.getArray().clear();
		g.getArray().add(new Fruit(0,32.10290536,35.20950241,10,1));
		if(s.Shortalgo(g)==0)
			fail("Time fail");
		g.getArray().add(new Fruit(0,32.12290536,35.20450241,10,1));
		g.getArray().add(new Fruit(0,33.12290536,35.20450241,10,1));
		g.getArray().add(new Fruit(0,31.12290536,35.20450241,10,1));
		Point3D p=new Point3D(g.getArr().get(0).getOrinet());
		g.getArray().add(new Fruit(0,32.12290536,35.20450241,10,1));
		g.getArray().add(new Fruit(0,33.12290536,35.20450241,10,1));
		g.getArray().add(new Fruit(0,31.12290536,35.20450241,10,1));
		s.Shortalgo(g);
		if(g.getArr().get(0).getOrinet().toString().equals(p.toString()))
			fail("Algo fail");
		g.getArr().clear();
		g.getArray().clear();
		g.getArr().add(new Packman(0,32.10290536,35.20950241,0,1,1));
		g.getArray().add(new Fruit(0,32.12290536,35.20450241,10,1));
		g.getArray().add(new Fruit(0,33.12290536,35.20450241,10,1));
		g.getArray().add(new Fruit(0,31.12290536,35.20450241,10,1));
		int a=g.getArr().get(0).getPath().getArr().size();
		s.Shortalgo(g);
		int b=g.getArr().get(0).getPath().getArr().size();
		if(b==a)
			fail("Algo/pathcalc fail");
	}
}
