package us.lsi.sevici;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import us.lsi.pojo.Network;
import us.lsi.pojo.Networks;
import us.lsi.tools.JsonTools;

public class Redes {
	
	public static String url = "http://api.citybik.es/v2/networks";
	private static List<Red> redes;
	
	private static void leeUrl() {
		Networks predes = JsonTools.fromJSON_URL(url, Networks.class);
		Network[] rds = predes.getNetworks();
		redes = Arrays.stream(rds).map(r->Red.of(r)).collect(Collectors.toList());
	}

	public static List<Red> getRedes() {
		if(redes == null)
			Redes.leeUrl();
		return redes;
	}
	
	public static String showAll(List<Red> redes) {
		if(redes == null)
			Redes.leeUrl();
		return redes.stream().map(r->String.format("%50s,%15s,%60s",r.getCity(),r.getCountry(),r.getHref()))
				.collect(Collectors.joining("\n",String.format("%50s,%15s,%60s,%10s\n","Ciudad","Pais","Url"),"\n_____________________"));
	}
	
	public static List<Red> byCountry(String country){
		if(redes == null)
			Redes.leeUrl();
		return Redes.getRedes().stream().filter(x->x.getCountry().equals(country)).collect(Collectors.toList());
	}
	
	public static Boolean existRedCityInCountry(String country,String city){
		if(redes == null)
			Redes.leeUrl();
		return Redes.getRedes().stream().anyMatch(x->x.getCountry().equals(country)&&x.getCity().equals(city));
	}
	
	public static List<String> citiesWhithSeveralNetworks() {
		if(redes == null)
			Redes.leeUrl();
		Map<String, Long> m = Redes.getRedes().stream()
				.collect(Collectors.groupingBy(r -> r.getCity(), Collectors.counting()));
		return m.entrySet().stream().filter(x -> x.getValue() > 1).map(x -> x.getKey()).collect(Collectors.toList());
	}
	
	public static Red byCountryAndCity(String country,String city){
		Red red = null;
		List<Red> redes = allByCountryAndCity(country,city);
		if(!redes.isEmpty()) red = redes.get(0);
		return red;
	}
	
	public static Red byCountryAndCity(String country,String city, String name){
		Red red = null;
		List<Red> redes = allByCountryAndCity(country,city);
		if(!redes.isEmpty()) {
			red = redes.stream().filter(r->r.getName().equals(name)).findFirst().orElse(null);
		}		
		return red;
	}
	
	public static List<Red> allByCountryAndCity(String country,String city){
		if(redes == null)
			Redes.leeUrl();
		return Redes.getRedes().stream().filter(x->x.getCountry().equals(country)&&x.getCity().equals(city)).collect(Collectors.toList());
	}
	
	public static Set<String> countries(){
		if(redes == null)
			Redes.leeUrl();
		return redes.stream().map(r->r.getCountry()).collect(Collectors.toSet());
	}

	public static Set<String> cities(String country){
		if(redes == null)
			Redes.leeUrl();
		return redes.stream().filter(r->r.getCountry().equals(country)).map(r->r.getCity()).collect(Collectors.toSet());
	}
	
}
