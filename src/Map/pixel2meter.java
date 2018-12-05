package Map;

public class pixel2meter {
	public static void main(String[] args) {
		double lon = -89.08805;
		double lat = 30.6393;
		double zoom = 6; // 6.5156731549786215 would be possible too

		double lon_rad = Math.toRadians(lon);
		double lat_rad = Math.toRadians(lat);
		double n = Math.pow(2.0, zoom);

		double tileX = ((lon + 180) / 360) * n;
		double tileY = (1 - (Math.log(Math.tan(lat_rad) + 1.0/Math.cos(lat_rad)) / Math.PI)) * n / 2.0;

		System.out.println("tileX = "+tileX+" tileY = "+tileY);
	}
}