package us.lsi.test;

import java.util.stream.Collectors;

import us.lsi.pojo.OneNetwork;
import us.lsi.sevici.Estacion;
import us.lsi.sevici.Red;
import us.lsi.tools.JsonTools;

public class TestJson {

	public static void main(String[] args) {	
		OneNetwork sevici = JsonTools.fromJSON_URL("http://api.citybik.es/v2/networks/sevici", OneNetwork.class);
		System.out.println(sevici.getNetwork().getStations()[0]);
		Estacion e = Estacion.of(sevici.getNetwork().getStations()[0]);
		System.out.println(e);
		Red red = Red.of(sevici.getNetwork());
		System.out.println(red.getEstaciones().subList(0,10).stream().map(x->x.toString()).collect(Collectors.joining("\n")));
		System.out.println(red.getUbicacion());
	}

}
