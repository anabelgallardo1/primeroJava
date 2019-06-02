package us.lsi.test;


import us.lsi.ruta.Ruta;
import us.lsi.tools.GraphicsMaps.GraphicType;

public class TestRuta1 {

	public static void main(String[] args) {
		Ruta r = Ruta.of("resources/ruta.csv");
		System.out.println(r.getLongitud());
		r.mostrarMapa("ficheros/BingRutaOut.html", GraphicType.Bing);;
	}

}
