package us.lsi.test;

import static us.lsi.tools.CollectionTools.toConsole;
import us.lsi.palabras.Palabras;

public class TestPalabras {
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		var p = Palabras.palabrasDistintas("resources/quijote.txt");
//		toConsole(p.stream(),"\n");
		var f = Palabras.frecuenciasDePalabras("resources/quijote.txt");
//		toConsole(f.entrySet().stream(),"\n");
		var g = Palabras.gruposDePalabrasPorFrecuencias("resources/quijote.txt");
//		toConsole(g.entrySet().stream(),"\n");
		var h = Palabras.gruposDeLineas("resources/quijote.txt");
		toConsole(h.entrySet().stream(),"\n");
	}

}
