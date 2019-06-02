package us.lsi.test;

import java.util.List;
import java.util.stream.Collectors;

import us.lsi.data.Coordenadas2D;
import us.lsi.sevici.Red;
import us.lsi.sevici.Redes;
import us.lsi.tools.GraphicsMaps;
import us.lsi.tools.GraphicsMaps.GraphicType;

public class TestSevici5 {

	public static void main(String[] args) {
		Red red = Redes.byCountryAndCity("ES","Montilla");
		List<Coordenadas2D> ubicaciones = red.getEstaciones()
				.stream()
				.map(x->x.getUbicacion())
				.collect(Collectors.toList());
		System.out.println(red.getEstaciones().size());
		System.out.println(red.getEstaciones().stream().mapToInt(e->e.getNumero()).count());
		GraphicsMaps graphic = GraphicsMaps.of(GraphicType.Google);
		graphic.markers("ficheros/MontillaGoogleMarkersOut.html","green",ubicaciones);
		System.out.println(Redes.existRedCityInCountry("FR","Paris"));
		System.out.println(Redes.allByCountryAndCity("FR","Paris").size());
		System.out.println(Redes.citiesWhithSeveralNetworks());
		System.out.println(Redes.allByCountryAndCity("ES","Las Palmas de Gran Canaria").size());
		List<Red> redes = Redes.allByCountryAndCity("GB","London");
		ubicaciones = redes.stream()
				.map(r->r.getUbicacion()).collect(Collectors.toList());
		System.out.println(ubicaciones);
		graphic.markers("ficheros/LondonGoogleMarkersOut.html","green", ubicaciones);
		System.out.println(redes.stream()
				.map(r->String.format("(%s,%s,%s)",r.getName(),r.getCountry(),r.getCity()))
				.collect(Collectors.joining("\n")));
		Red red2 = Redes.byCountryAndCity("GB","London","Nextbike");			
		System.out.println(String.format("%s,%s,%s,%d",red2.getCountry(),red2.getName(),red2.getHref(),red2.getEstaciones().size()));
	}

}
