package us.lsi.test;

import java.util.List;
import java.util.stream.Collectors;

import us.lsi.data.Coordenadas2D;
import us.lsi.sevici.Red;
import us.lsi.tools.GraphicsMaps;
import us.lsi.tools.GraphicsMaps.GraphicType;

public class TestSevici1 {

	public static void main(String[] args) {
		Red red = Red.of();
		List<Coordenadas2D> ubicaciones = red.getEstaciones()
				.subList(10,25)
				.stream()
				.map(x->x.getUbicacion())
				.collect(Collectors.toList());
		System.out.println(red.getEstaciones());
		System.out.println(red.getEstaciones().stream().mapToInt(e->e.getNumero()).count());
		GraphicsMaps.of(GraphicType.Bing).polyline("ficheros/BingPolylineOut.html", ubicaciones);
	}
}
