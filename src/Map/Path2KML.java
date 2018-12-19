package Map;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Path2KML {
	public static boolean path2kml(Game g) {
		ArrayList<String> content = new ArrayList<String>();
		String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
				"<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n<Document><Style id=\"red\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style><Style id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style><Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style><Folder><name>Wifi Networks</name>\n";
		content.add(kmlstart);

		String kmlend = "\n</Folder></Document></kml>";
		try{
			FileWriter fw = new FileWriter("data\\mygamekml.kml");
			BufferedWriter bw = new BufferedWriter(fw);
			for(int i=0;i<g.getArray().size();i++) {
				Fruit tmp=g.getArray().get(i);
				String kmlelement ="<Placemark>\n" +
						"<name><![CDATA["+"fruit"+tmp.getID()+"]]></name>\n" +
						"<description><![CDATA[BSSID: <b>"+"</b><br/>Capabilities: <b>"+"</b><br/>Frequency: <b>"+"</b><br/>Timestamp: <b>+"+LocalDateTime.now().toString()+"+</b><br/>Date: <b>"+"</b>]]></description>\n" +
						"<styleUrl>"+"red"+"</styleUrl>"+"<Point>\n" +
						"<coordinates>"+tmp.getOrient().y()+","+tmp.getOrient().x()+","+tmp.getOrient().z()+"</coordinates>" +
						"</Point>\n" +
						"</Placemark>";
				content.add(kmlelement);
			}
			for(int i=0;i<g.getArr().size();i++) {
				Packman tmp=g.getArr().get(i);
				String kmlelement ="<Placemark>\n" +
						"<name><![CDATA["+"Packman"+tmp.getID()+"]]></name>\n" +
						"<description><![CDATA[BSSID: <b>"+"</b><br/>Capabilities: <b>"+"</b><br/>Frequency: <b>"+"</b><br/>Timestamp: <b>+"+LocalDateTime.MIN.toString()+"+</b><br/>Date: <b>"+"</b>]]></description>\n" +
						"<styleUrl>"+"yellow"+"</styleUrl>"+"<Point>\n" +
						"<coordinates>"+tmp.getOrinet().y()+","+tmp.getOrinet().x()+","+tmp.getOrinet().z()+"</coordinates>" +
						"</Point>\n" +
						"</Placemark>";
				content.add(kmlelement);
			}
			for(int i=0;i<g.getArr().size();i++) {
				Packman tmp=g.getArr().get(i);
				for(int j=0;j<g.getArr().get(i).getPath().getArr().size();j++) {
					String kmlelement ="<Placemark>\n" +
							"<name><![CDATA["+"Packman"+tmp.getID()+"]]></name>\n" +
							"<description><![CDATA[BSSID: <b>"+"</b><br/>Capabilities: <b>"+"</b><br/>Frequency: <b>"+"</b><br/>Timestamp: <b>+"+tmp.getPath().getArr().get(j).getLive()+"+</b><br/>Date: <b>"+"</b>]]></description>\n" +
							"<styleUrl>"+"yellow"+"</styleUrl>"+"<Point>\n" +
							"<coordinates>"+tmp.getPath().getArr().get(j).y()+","+tmp.getPath().getArr().get(j).x()+","+tmp.getPath().getArr().get(j).z()+"</coordinates>" +
							"</Point>\n" +
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
