package us.lsi.test;

import java.util.List;

import us.lsi.sevici.Estacion;
import us.lsi.sevici.Red;
import us.lsi.sevici.Redes;

public class TestSevici4 {

	public static void main(String[] args) {
		System.out.println(Redes.countries());
		System.out.println(Redes.cities("ES"));
		Red red = Redes.byCountryAndCity("ES", "Montilla");
		List<Estacion> estaciones = Red.of(red.getHref()).getEstaciones();
		System.out.println(Red.showAll(estaciones));
	}

}
