package Map;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Path2KML {
	//	private ArrayList<Path>arr=new ArrayList<>();
	private static ArrayList<String> content;
	public static void path2kml(Game g) {
		content = new ArrayList<String>();
		String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
				"<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n<Document><Style id=\"red\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style><Style id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style><Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style><Folder><name>Wifi Networks</name>\n";
		content.add(kmlstart);

		String kmlend = "\n</Folder></Document></kml>";
		try{
			FileWriter fw = new FileWriter("mygamekml.kml");
			BufferedWriter bw = new BufferedWriter(fw);
			//			for (int i = 1; i < a.size(); i++) {
			for(int i=0;i<g.getArray().size();i++) {
				Fruit tmp=g.getArray().get(i);
				String kmlelement ="<Placemark>\n" +
						"<name><![CDATA["+"fruit"+tmp.getID()+"]]></name>\n" +
						"<description><![CDATA[BSSID: <b>"+"</b><br/>Capabilities: <b>"+"</b><br/>Frequency: <b>"+"</b><br/>Timestamp: <b>+"+tmp.getDead()+"+</b><br/>Date: <b>"+"</b>]]></description>\n" +
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
						"<description><![CDATA[BSSID: <b>"+"</b><br/>Capabilities: <b>"+"</b><br/>Frequency: <b>"+"</b><br/>Timestamp: <b>+"+tmp.getOrinet().getDead()+"+</b><br/>Date: <b>"+"</b>]]></description>\n" +
						"<styleUrl>"+"yellow"+"</styleUrl>"+"<Point>\n" +
						"<coordinates>"+tmp.getOrinet().y()+","+tmp.getOrinet().x()+","+tmp.getOrinet().z()+"</coordinates>" +
						"</Point>\n" +
						"</Placemark>";
				content.add(kmlelement);
			}
			//			for(int i=0;i<g.getArr().size();i++) {
			//				Packman tmp=g.getArr().get(i);
			//				for(int j=0;j<g.getArr().get(i).getPath().getArr().size();j++) {
			//					String kmlelement ="<Placemark>\n" +
			//							"<name><![CDATA["+"Packman"+tmp.getID()+"]]></name>\n" +
			//							"<description><![CDATA[BSSID: <b>"+"</b><br/>Capabilities: <b>"+"</b><br/>Frequency: <b>"+"</b><br/>Timestamp: <b>+"+tmp.getPath().getArr().get(j).getLive()+"+</b><br/>Date: <b>"+"</b>]]></description>\n" +
			//							"<styleUrl>"+"yellow"+"</styleUrl>"+"<Point>\n" +
			//							"<coordinates>"+tmp.getPath().getArr().get(j).y()+","+tmp.getPath().getArr().get(j).x()+","+tmp.getPath().getArr().get(j).z()+"</coordinates>" +
			//							"</Point>\n" +
			//							"</Placemark>";
			//					content.add(kmlelement);
			//				}
			//			}
			content.add(kmlend);
			String csv = content.toString().replaceAll("</Placemark>, <Placemark>", "</Placemark><Placemark>").replaceAll("</Placemark>, ", "</Placemark>").replaceAll(", <Placemark>", "<Placemark>");
			csv = csv.substring(1, csv.length()-1);
			bw.write(csv);
			bw.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	//	public void path2kml(Game g) {
	//		int i=0;
	//		while(!g.getArr().isEmpty()) {
	//			arr.add(g.getArr().get(i).getPath());
	//			g.getArr().remove(i);
	//			i++;
	//		}
	//	}
	//	public void path2kml(Game g) {
	//		String fileName="game2kml"+".csv";
	//		String newfilepath="data\\"+fileName;
	//		PrintWriter pw=null;
	//		try 
	//		{
	//			pw = new PrintWriter(new File(newfilepath));
	//		} 
	//		catch (FileNotFoundException e) 
	//		{
	//			e.printStackTrace();
	//			return;
	//		}
	//		pw.write(g.toString());
	//		pw.close();
	//		convertCsv2Kml(newfilepath,"data\\game2kml.kml");
	//		System.out.println("done");
	//	}
	//		private static void writeFileKML(ArrayList<String[]> a, String output) {
	//			ArrayList<String> content = new ArrayList<String>();
	//			String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
	//					"<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n<Document><Style id=\"red\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style><Style id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style><Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style><Folder><name>Wifi Networks</name>\n";
	//			content.add(kmlstart);
	//	
	//			String kmlend = "\n</Folder></Document></kml>";
	//			try{
	//				FileWriter fw = new FileWriter(output);
	//				BufferedWriter bw = new BufferedWriter(fw);
	//				for (int i = 1; i < a.size(); i++) {
	//					String[] s = a.get(i);
	//					String kmlelement ="<Placemark>\n" +
	//							"<name><![CDATA["+s[1]+"]]></name>\n" +
	//							"<description><![CDATA[BSSID: <b>"+s[0]+"</b><br/>Capabilities: <b>"+s[2]+"</b><br/>Frequency: <b>"+s[4]+"</b><br/>Timestamp: <b>+"+s[5]+"+</b><br/>Date: <b>"+s[3]+"</b>]]></description>\n" +
	//							"<styleUrl>"+"green"+"</styleUrl>"+"<Point>\n" +
	//							"<coordinates>"+s[7]+","+s[6]+"</coordinates>" +
	//							"</Point>\n" +
	//							"</Placemark>";
	//					content.add(kmlelement);
	//				}
	//				content.add(kmlend);
	//				String csv = content.toString().replaceAll("</Placemark>, <Placemark>", "</Placemark><Placemark>").replaceAll("</Placemark>, ", "</Placemark>").replaceAll(", <Placemark>", "<Placemark>");
	//				csv = csv.substring(1, csv.length()-1);
	//				bw.write(csv);
	//				bw.close();
	//			} 
	//			catch (IOException e) {
	//				e.printStackTrace();
	//			}
	//		}
	//	/**
	//	 * This help function copy all the lines in the csv to a new arraylist of strings[].
	//	 * @param csvPath the path of the csv that we want to convert.
	//	 * @return a ArrayList<String[]> with all the lines that we copy from the csv.
	//	 */
	//	private static ArrayList<String[]> getCSVList(String csvPath){
	//		ArrayList<String[]> a = new ArrayList<String[]>();
	//		String line = "";
	//		String cvsSplitBy = ",";
	//		try (BufferedReader br = new BufferedReader(new FileReader(csvPath))) 
	//		{
	//			line=br.readLine();
	//			while ((line = br.readLine()) != null) 
	//			{
	//				a.add(line.split(","));
	//			}
	//
	//		} catch (IOException e) 
	//		{
	//			e.printStackTrace();
	//		}
	//		return a;
	//	}
	//	/**
	//	 * This function convert csv file to kml.
	//	 * @param csvPath the path of the csv that we want to convert.
	//	 * @param kmlPath the path of new kml that we create.
	//	 */
	//	public static void convertCsv2Kml(String csvPath, String kmlPath) {
	//		ArrayList<String[]> a = getCSVList(csvPath);
	//		writeFileKML(a, kmlPath);
	//	}
}
