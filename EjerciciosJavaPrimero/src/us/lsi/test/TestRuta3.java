package us.lsi.test;


import us.lsi.ruta.Ruta;
import us.lsi.tools.GraphicsMaps.GraphicType;


public class TestRuta3 {

	public static void main(String[] args) {
		Ruta r = Ruta.of("resources/ruta.csv");
		System.out.println(r.getLongitud());
		r.mostrarMapa("ficheros/GoogleRutaOut.html", GraphicType.Google);;
	}

}
