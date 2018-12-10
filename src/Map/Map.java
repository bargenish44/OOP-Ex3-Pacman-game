package Map;

import Geom.Point3D;

public class Map {

	Point3D leftUp = new Point3D(32.105770,  35.202469);
	Point3D RightUp = new Point3D(32.105770 , 35.211588);
	Point3D leftDown = new Point3D(32.101899, 35.202469);
	Point3D RightDown = new Point3D(32.101899, 35.211588);
	double x_length = this.RightUp.y()-this.leftUp.y();
	double y_length = this.leftDown.x()-this.leftUp.x();
	
}
