package us.lsi.tools;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import us.lsi.data.Coordenadas2D;

public class GraphicsGoogleMaps extends AbstractGaphicsMaps implements GraphicsMaps {
	
	public GraphicsGoogleMaps() {}
	
	private static Integer n = -1;
	@Override
	public String toMarker(String color, String text, Coordenadas2D coordenadas) {
		String url = "\"http://maps.google.com/mapfiles/ms/icons/";
		  url += color + "-dot.png\"";
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		DecimalFormat formateador = new DecimalFormat("####.######",simbolos);
		String lat = formateador.format(coordenadas.getLatitude());
		String lng = formateador.format(coordenadas.getLongitude());
		n++;
		return String.format("marker%d = new google.maps.Marker({\r\n" + 
				"    map: map,\r\n" + 
				"    position: {lat: %s, lng: %s},\r\n" + 
				"    title: '%s' ,\r\n" +
				"    icon: {\r\n url: " + url+ "}\r\n" + 
				"  });",n,lat,lng,text);
	}

	@Override
	public String toPoint(Coordenadas2D coordenadas) {
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		DecimalFormat formateador = new DecimalFormat("####.######",simbolos);
		String lat = formateador.format(coordenadas.getLatitude());
		String lng = formateador.format(coordenadas.getLongitude());
		return String.format("{lat: %s, lng: %s}",lat,lng);
	}

	@Override
	public String getKey() {
		return FileTools.lineas("resources/privateGoogle.txt").get(0);
	}
	

	@Override
	protected String getPolylinePattern() {
		return FileTools.text("resources/GooglePolylinePattern.html");
	}
	
	@Override
	protected String getMarkersPattern() {
		return FileTools.text("resources/GoogleMarkersPattern.html");
	}
}
