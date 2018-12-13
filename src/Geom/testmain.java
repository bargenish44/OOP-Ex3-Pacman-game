package Geom;
import javax.swing.JFrame;

public class testmain {
	public static void main(String[] args)
	{
		test window = new test();
		window.setVisible(true);
		window.setSize(window.myImage.getWidth()+20,window.myImage.getHeight()+20);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
