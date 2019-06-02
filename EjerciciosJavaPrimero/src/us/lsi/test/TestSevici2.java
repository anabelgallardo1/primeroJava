package us.lsi.test;

import java.util.List;
import java.util.stream.Collectors;

import us.lsi.data.Coordenadas2D;
import us.lsi.sevici.Red;
import us.lsi.tools.GraphicsMaps;
import us.lsi.tools.GraphicsMaps.GraphicType;

public class TestSevici2 {

	public static void main(String[] args) {
		Red red = Red.of();
		List<Coordenadas2D> ubicaciones = red.getEstaciones()
				.subList(10,15)
				.stream()
				.map(x->x.getUbicacion())
				.collect(Collectors.toList());
		System.out.println(red.getEstaciones().size());
		System.out.println(red.getEstaciones().stream().mapToInt(e->e.getNumero()).count());
		GraphicsMaps graphic = GraphicsMaps.of(GraphicType.Bing);
		graphic.markers("ficheros/BingMarkersOut.html","green", ubicaciones);
	}

}