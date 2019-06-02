package us.lsi.tools;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import us.lsi.data.Coordenadas2D;

public class GraphicsBingMaps extends AbstractGaphicsMaps implements GraphicsMaps {
	
	
	public GraphicsBingMaps() {}

	private static Integer n = -1;
	
	public String toPoint(Coordenadas2D coordenadas) {
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		DecimalFormat formateador = new DecimalFormat("####.######",simbolos);
		String lat = formateador.format(coordenadas.getLatitude());
		String lng = formateador.format(coordenadas.getLongitude());
		return String.format("new Microsoft.Maps.Location(%s,%s)",lat,lng);
	}

	public String toMarker(String color, String text,Coordenadas2D coordenadas) {
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		DecimalFormat formateador = new DecimalFormat("####.######",simbolos);
		String lat = formateador.format(coordenadas.getLatitude());
		String lng = formateador.format(coordenadas.getLongitude());
		n++;
		return String.format(" var pin%d = new Microsoft.Maps.Pushpin(new Microsoft.Maps.Location(%s,%s), {\r\n" + 
				"color: '%s'," +
				"text: '%s'\r\n });\r\n" + 
				"map.entities.push(pin%d);",n,lat,lng,color,text,n);
	}

	@Override
	public String getKey() {
		return FileTools.lineas("resources/privateBing.txt").get(0);
	}

	@Override
	protected String getPolylinePattern() {
		return FileTools.text("resources/BingPolylinePattern.html");
	}

	@Override
	protected String getMarkersPattern() {
		return FileTools.text("resources/BingMarkersPattern.html");
	}

}
