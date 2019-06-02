package us.lsi.sevici;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import us.lsi.data.Coordenadas2D;
import us.lsi.pojo.Network;
import us.lsi.pojo.OneNetwork;
import us.lsi.pojo.Station;
import us.lsi.tools.FileTools;
import us.lsi.tools.JsonTools;

public class Red {
	
	public static String url = "http://api.citybik.es";
	public static String file = "resources/estaciones.csv";
	
	public static Red of(Network network) {
			String name = network.getName(); 
			String href = network.getHref();
			String country = network.getLocation().getCountry();
			String city = network.getLocation().getCity();
			Coordenadas2D ubicacion = Coordenadas2D.of(Double.parseDouble(network.getLocation().getLatitude()),
					Double.parseDouble(network.getLocation().getLongitude()));
		return new Red(name, href, country, city, ubicacion);
	}
	
	public static Red of(String sufix) {
		OneNetwork red = JsonTools.fromJSON_URL(url+sufix, OneNetwork.class);
		return Red.of(red.getNetwork());
	}
	
	public static Red of() {
		String name = "Sevici"; 
		String href = null;
		String country = "ES";
		String city = "Sevilla";
		Coordenadas2D ubicacion = Coordenadas2D.of(37.388096,-5.982330);
		return new Red(name, href, country, city, ubicacion);
	}

	private static List<Estacion> ofFile(String fichero) {
		List<String> lineas = FileTools.lineas(fichero);
		return lineas.subList(1, lineas.size()).stream()
				.map(linea -> Estacion.parse(linea))
				.collect(Collectors.toList());
	}
	
	private static List<Estacion> ofUrl(String sufix) {
		OneNetwork sevici = JsonTools.fromJSON_URL(url+sufix, OneNetwork.class);
		Station[] stations = sevici.getNetwork().getStations();
		return Arrays.stream(stations)
				.map((Station s)->Estacion.of(s))
				.collect(Collectors.toList());
	}
	
	private String name;

	private String href;
	
	private String country;

	private String city;
	
	private Coordenadas2D ubicacion;
	
	private List<Estacion> estaciones = null;

	public Red() {
		super();
	}

	private Red(String name, String href, String country, String city, Coordenadas2D ubicacion) {
		super();
		this.name = name;
		this.href = href;
		this.country = country;
		this.city = city;
		this.ubicacion = ubicacion;
		this.estaciones = null;
	}
	
	public String getName() {
		return name;
	}

	public String getHref() {
		return href;
	}

	public String getCountry() {
		return country;
	}

	public String getCity() {
		return city;
	}
	
	public Coordenadas2D getUbicacion() {
		return ubicacion;
	}

	public List<Estacion> getEstaciones() {
		if(this.estaciones == null) {
			if(this.getHref() == null) {
				this.estaciones = Red.ofFile(file);
			} else {
				this.estaciones = Red.ofUrl(this.getHref());
			}
		}
		return this.estaciones;
	}
	
	public Estacion byNumero(Integer numero) {
		return this.estaciones.stream().filter(e->e.getNumero().equals(numero)).findFirst().orElse(null);
	}
	
	public List<Estacion> getCercanas(Coordenadas2D ubicacion, Double d){
		return getEstaciones().stream().filter(x->x.getUbicacion().esCercana(ubicacion, d)).collect(Collectors.toList());
	}
	
	
	public static String showAll(List<Estacion> estaciones) {
		return estaciones.stream()
				.map(e->String.format("%35s  %10d  %15d  %15d  %s",e.getName(),e.getSlots(),e.getEmpty_slots(),e.getFree_bikes(),e.getUbicacion().toString()))
				.collect(Collectors.joining("\n",String.format("%35s  %10s  %15s  %15s %s\n","name","slots","empty_slots","free_bikes","ubicacion"),"\n_____________________"));
	}
	
	@Override
	public String toString() {
		return "Red [name=" + name + ", href=" + href + ", country=" + country + ", city=" + city + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Red other = (Red) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	

}
