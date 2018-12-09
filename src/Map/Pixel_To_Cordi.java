package Map;

import Geom.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Pixel_To_Cordi {

	private BufferedImage _ing;
	private int width;
	private int Height;

	public Pixel_To_Cordi () {		
		this.width =_ing.getWidth();
		this.Height = _ing.getHeight();	
	}

	public BufferedImage geImage () {
		return this._ing;
	}

	public int getwidth() {
		return this.width;

	}

	public int getHeight() {
		return this.Height;

	}

	public ArrayList<Game> Point_to_GPS( ArrayList<Game> _List){
		for (int i = 0; i < _List.size(); i++) {
			String [] arr = (_List.get(i).getPoint3D.split(","));
			double new_X = (35.2022 + (Double.parseDouble(arr[0])/125000));
			double new_Y = (32.10565 - (Double.parseDouble(arr[1])/150000));
			_List.get(i).setPoint3D(String.valueOf(new_X)+ ","+String.valueOf(new_Y)+","+0);
		}
		return _List;
	}
	
	public ArrayList<Game> Point_to_Pixel( ArrayList<Game> _List){
		for (int i = 0; i < _List.size(); i++) {
			String [] arr_P = (_List.get(i).getPoint3D.split(","));
			double new_X = (int)((Double.parseDouble(arr_P[0]) - 35.2022*125000));
			double new_Y = (int)(32.10565 - (Double.parseDouble(arr_P[1])*150000));
			_List.get(i).setPoint3D(String.valueOf(new_X)+ ","+String.valueOf(new_Y)+","+0);
		}
		return _List;
	}
	public ArrayList<Packman> Pointp_to_Pixel( ArrayList<Packman> Pa_List){
		for (int i = 0; i < Pa_List.size(); i++) {
			String [] arr_Pa = (((String) Pa_List.get(i).getPoint3D).split(","));
			double new_X = (int)((Double.parseDouble(arr_Pa[0]) - 35.2022*125000));
			double new_Y = (int)(32.10565 - (Double.parseDouble(arr_Pa[1])*150000));
			Pa_List.get(i).setPoint3D(String.valueOf(new_X)+ ","+String.valueOf(new_Y)+","+0);
		}
		return Pa_List;
	}
	
	public ArrayList<Fruit> PointF_to_Pixel( ArrayList<Fruit> Fruit_List){
		for (int i = 0; i < Fruit_List.size(); i++) {
			String [] arr_F = (((String) Fruit_List.get(i).getPoint3D).split(","));
			double new_X = (int)((Double.parseDouble(arr_F[0]) - 35.2022*125000));
			double new_Y = (int)(32.10565 - (Double.parseDouble(arr_F[1])*150000));
			Fruit_List.get(i).setPoint3D(String.valueOf(new_X)+ ","+String.valueOf(new_Y)+","+0);
		}
		return Fruit_List;
	}
}
