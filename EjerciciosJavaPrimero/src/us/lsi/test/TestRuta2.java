package us.lsi.test;



import us.lsi.ruta.Ruta;


public class TestRuta2 {

	
	public static void main(String[] args) {
		Ruta r = Ruta.of("resources/ruta.csv");
		System.out.println(r.getLongitud());
		r.mostrarAltitud("ficheros/RutaAlturasOut.html");
	}

}
