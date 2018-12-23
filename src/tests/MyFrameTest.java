package tests;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import Packman_Game.MyFrame;
import Packman_Game.Packman;


class MyFrameTest {
	ArrayList<Packman> arr_test = new ArrayList<>();
	Class<MyFrame> test_1 ;
	MyFrame test_4 ;
	MyFrame test_3 ;
	MyFrame test_7 ;
	int x=7,y=3;
	String test_2;
	ArrayList<Packman> arr_test_2 = new ArrayList<>();


	@Test
	void testMyFrame() {

		test_1 = MyFrame.class;

		test_2 = "class Packman_Game.MyFrame";

		if(test_1.equals(test_2))

			fail("Not yet implemented");
	}


	@Test
	void testActionPerformed() {

		ActionEvent test_5 = new ActionEvent(7, 3, null);

		ActionEvent test_6 = new ActionEvent(x, y, null);

		if(test_5 == test_6)

			fail("Not yet implemented");
	}

	@Test
	void testMaxPathSize() {

		MyFrame newtest = new MyFrame();

		MyFrame.MaxPathSize(arr_test);

		MyFrame newtest_1 = new MyFrame();

		MyFrame.MaxPathSize(arr_test_2);

		if(newtest.hashCode()==newtest_1.hashCode())
			
			fail("Not yet implemented");
	}

}
