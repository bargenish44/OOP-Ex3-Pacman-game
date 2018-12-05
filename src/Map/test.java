package Map;

import java.awt.Point;

public class test{
	static final int mapWidth = 614, mapHeight = 1141;
	//offsets
	static final double mapLongitudeStart = 33.5, mapLatitudeStart = 33.5;
	//length of map in long/lat
	static final double mapLongitude = 36.5-mapLongitudeStart, 
			// invert because it decreases as you go down
			mapLatitude = mapLatitudeStart-29.5;

	private static Point getPositionOnScreen(double longitude, double latitude){
		// use offsets
		longitude -= mapLongitudeStart;
		// do inverse because the latitude increases as we go up but the y decreases as we go up.
		// if we didn't do the inverse then all the y values would be negative.
		latitude = mapLatitudeStart-latitude;

		// set x & y using conversion
		int x = (int) (mapWidth*(longitude/mapLongitude));
		int y = (int) (mapHeight*(latitude/mapLatitude));

		return new Point(x, y);
	}

	public static void main(String[] args) {
		System.out.println(getPositionOnScreen(33.5, 33.5).toString());
		System.out.println(getPositionOnScreen(35, 32).toString());
		System.out.println(getPositionOnScreen(36.5, 29.5).toString());
	}
}