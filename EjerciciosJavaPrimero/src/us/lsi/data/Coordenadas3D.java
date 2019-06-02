package us.lsi.data;

public class Coordenadas3D {
	public static Coordenadas3D of(Double latitude, Double longitude, Double altitude) {
		return new Coordenadas3D(latitude, longitude, altitude);
	}

	private Double latitude;
	private Double longitude;
	private Double altitude;
	
	private Coordenadas3D(Double latitude, Double longitude, Double altitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public Double getAltitude() {
		return altitude;
	}

	public Double distance(Coordenadas3D c) {
		return distance(this,c);
	}
	
	
	public Coordenadas2D to2D() {
		return Coordenadas2D.of(latitude, longitude);
	}
	
	public static Double distance(Coordenadas3D c1, Coordenadas3D c2) {
		Coordenadas2D c12D = Coordenadas2D.of(c1.latitude, c1.longitude);
		Coordenadas2D c22D = Coordenadas2D.of(c2.latitude, c2.longitude);
		return Math.sqrt(Math.pow(Coordenadas2D.distance(c12D,c22D),2)+Math.pow(c1.altitude-c1.altitude,2));
	}
	
	@Override
	public String toString() {
		return String.format("(%f,%f,%f)",latitude,longitude,altitude);
	}
	
}
