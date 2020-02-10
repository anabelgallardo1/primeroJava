
/**
 * @author migueltoro
 *
 */
module java_primero {
	exports us.lsi.geometria;
	exports us.lsi.montecarlo;
	exports us.lsi.pojo;
	exports us.lsi.tools;
	exports us.lsi.test;
	exports us.lsi.data;
	exports us.lsi.ruta;
	exports us.lsi.sevici;
	exports us.lsi.palabras;
	exports us.lsi.whatsapp;

	requires jackson.annotations;
	requires jackson.core;
	requires jackson.databind;
	requires transitive java.desktop;	
}