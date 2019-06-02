package us.lsi.data;

import java.util.List;


public class Coordenadas2D {
	
	public static Coordenadas2D of(Double latitude, Double longitude) {
		return new Coordenadas2D(latitude, longitude);
	}

	private Double latitude;
	private Double longitude;
	
	private Coordenadas2D(Double latitude, Double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}
	
	public Coordenadas2D toRadians() {
		Double latitude = Math.toRadians(this.latitude);
		Double longitude = Math.toRadians(this.longitude);
		return Coordenadas2D.of(latitude, longitude);
	}

	public Double distance(Coordenadas2D c) {
		return distance(this,c);
	}
	
	public Boolean esCercana(Coordenadas2D c, Double d) {
		return distance(this,c) <=d;
	}
	
	public static Double distance(Coordenadas2D c1, Coordenadas2D c2) {
		Double radio_tierra = 6373.0;
		Coordenadas2D c1R = c1.toRadians();
		Coordenadas2D c2R = c2.toRadians();		
		Double incLat  = c2R.latitude-c1R.latitude;
		Double incLong = c2R.longitude-c1R.longitude;
		Double a = Math.pow(Math.sin(incLat/2),2)+
				Math.cos(c1R.latitude)*Math.cos(c2R.latitude)*Math.pow(Math.sin(incLong/2),2);
		Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return radio_tierra*c;
	}
	public static Coordenadas2D center(List<Coordenadas2D> coordenadas) {
		Double averageLat = coordenadas.stream().mapToDouble(x->x.latitude).average().getAsDouble();
		Double averageLng = coordenadas.stream().mapToDouble(x->x.longitude).average().getAsDouble();
		return Coordenadas2D.of(averageLat,averageLng);
	}
	
	@Override
	public String toString() {
		return String.format("(%f,%f)",latitude,longitude);
	}
	
}
