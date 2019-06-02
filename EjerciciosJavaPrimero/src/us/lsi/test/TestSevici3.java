package us.lsi.test;

import java.util.List;
import java.util.stream.Collectors;

import us.lsi.data.Coordenadas2D;
import us.lsi.sevici.Estacion;
import us.lsi.sevici.Red;
import us.lsi.tools.GraphicsMaps;
import us.lsi.tools.GraphicsMaps.GraphicType;

public class TestSevici3 {

	public static void main(String[] args) {
		Red red = Red.of();
		List<Estacion> cercanas = red.getCercanas(red.getUbicacion(), 0.5);
		List<Coordenadas2D> ubicaciones = cercanas
				.stream()
				.map(x->x.getUbicacion())
				.collect(Collectors.toList());
		System.out.println(red.getEstaciones().size());
		System.out.println(red.getEstaciones().stream().mapToInt(e->e.getNumero()).count());
		GraphicsMaps graphic = GraphicsMaps.of(GraphicType.Google);
		graphic.markers("ficheros/GoogleMarkersOut.html","green", ubicaciones);

	}

}
