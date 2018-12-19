package Map;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Path2KML {
	public static boolean path2kml(Game g) {
		ArrayList<String> content = new ArrayList<String>();
		String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
				"<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n<Document><Style id=\"red\">\r\n" + 
				"<IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle>\r\n" + 
				"</Style><Style id=\"Packman\"><IconStyle><Icon><href>http://www.iconhot.com/icon/png/quiet/256/pac-man.png</href></Icon></IconStyle>\r\n" + 
				"</Style><Style id=\"Fruit\"><IconStyle><Icon><href>http://chittagongit.com//images/cherry-icon/cherry-icon-15.jpg</href></Icon></IconStyle></Style>";
		content.add(kmlstart);

		String kmlend = "\n</Document></kml>";
		try{
			FileWriter fw = new FileWriter("data\\mygamekml.kml");
			BufferedWriter bw = new BufferedWriter(fw);
			for(int i=0;i<g.getArray().size();i++) {
				Fruit tmp=g.getArray().get(i);
				String kmlelement ="<Placemark>\n" +
						"<name>fruit"+tmp.getID()+"</name>\n" +
						"<description>bar genish and elyashiv deri</description>\n" +
						"<styleUrl>"+"Fruit"+"</styleUrl>"+"<Point>\n" +
						"<coordinates>"+tmp.getOrient().y()+","+tmp.getOrient().x()+","+tmp.getOrient().z()+"</coordinates>" +
						"</Point>\n" +
						"<TimeStamp>\n\t<when>"+g.getArray().get(i).getDead()+"</when>"+"\n</TimeStamp>"+
						"</Placemark>";
				content.add(kmlelement);
			}
			for(int i=0;i<g.getArr().size();i++) {
				Packman tmp=g.getArr().get(i);
				String kmlelement ="<Placemark>\n" +
						"<name>Packman"+tmp.getID()+"</name>\n" +
						"<description>bar genish and elyashiv deri</description>\n" +
						"<styleUrl>"+"Packman"+"</styleUrl>"+"<Point>\n" +
						"<coordinates>"+tmp.getOrinet().y()+","+tmp.getOrinet().x()+","+tmp.getOrinet().z()+"</coordinates>" +
						"</Point>\n" +
						"<TimeStamp>\n\t<when>"+g.getArr().get(i).getOrinet().getDead()+"</when>"+"\n</TimeStamp>"+
						"</Placemark>";
				content.add(kmlelement);
			}
			for(int i=0;i<g.getArr().size();i++) {
				Packman tmp=g.getArr().get(i);
				for(int j=0;j<g.getArr().get(i).getPath().getArr().size();j++) {
					String kmlelement ="<Placemark>\n" +
							"<name>Packman"+tmp.getID()+"</name>\n" +
							"<description>bar genish and elyashiv deri</description>\n" +
							"<styleUrl>"+"Packman"+"</styleUrl>"+"<Point>\n" +
							"<coordinates>"+tmp.getPath().getArr().get(j).y()+","+tmp.getPath().getArr().get(j).x()+","+tmp.getPath().getArr().get(j).z()+"</coordinates>" +
							"</Point>\n" +
							"<TimeStamp>\n\t<when>"+tmp.getPath().getArr().get(j).getDead()+"</when>"+"\n</TimeStamp>"+
							"</Placemark>";
					content.add(kmlelement);
				}
			}
			content.add(kmlend);
			String csv = content.toString().replaceAll("</Placemark>, <Placemark>", "</Placemark><Placemark>").replaceAll("</Placemark>, ", "</Placemark>").replaceAll(", <Placemark>", "<Placemark>");
			csv = csv.substring(1, csv.length()-1);
			bw.write(csv);
			bw.close();
		}catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
