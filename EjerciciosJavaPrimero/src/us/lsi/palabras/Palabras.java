package us.lsi.palabras;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import us.lsi.tools.FileTools;
import us.lsi.tools.MutableType;

public class Palabras {
	
	public static Set<String> palabrasDistintas(String file){
		List<String> text = FileTools.lineas(file);
		Set<String> words = text.stream()
				.flatMap(w->Arrays.stream(w.split("[ ,;.\n()?¿!¡:\"]")))
				.filter(w->w.length()>0)
				.collect(Collectors.toSet());
		return words;
	}
	
	public static Long numeroDePalabrasDistintas(String file) {
		List<String> text = FileTools.lineas(file);
		Long nwords = text.stream()
				.flatMap(w->Arrays.stream(w.split("[ ,;.\n()?¿!¡:\"]")))
				.filter(w->w.length()>0)
				.distinct()
				.count();
		return nwords;
	}
	
	public static SortedMap<String,Integer> frecuenciasDePalabras(String file){
		List<String> text = FileTools.lineas(file);
		SortedMap<String,Integer> fwords = text.stream()
				.flatMap(w->Arrays.stream(w.split("[ ,;.\n()?¿!¡:\"]")))
				.filter(w->w.length()>0)
				.collect(Collectors.groupingBy(x->x,()->new TreeMap<>(),Collectors.summingInt(x->1)));
		return fwords;
	}
	
	public static SortedMap<Integer,Set<String>> gruposDePalabrasPorFrecuencias(String file){
		SortedMap<String,Integer> fwords = frecuenciasDePalabras(file);
		return fwords.entrySet().stream()
				.collect(Collectors.groupingBy(x->x.getValue(),()->new TreeMap<Integer,Set<String>>(Comparator.reverseOrder()),
								 				Collectors.mapping(Map.Entry::getKey,Collectors.toSet())));
	}
	

	public static SortedMap<String,Set<Integer>> gruposDeLineas(String file){
		Function<Par,Stream<Par>> tp = q->Arrays.stream(q.text.split("[ ,;.\n()?¿!¡:\"]")).filter(x->x.length()>0).map(x->Par.of(q.linea,x));
		MutableType<Integer> n = MutableType.of(0);
		List<String> text = FileTools.lineas(file);	 
		SortedMap<String,Set<Integer>> words	=	text.stream()
				.map(ln->Par.of(n.newValue(n.value+1),ln))
				.flatMap(tp)
				.collect(Collectors.groupingBy(q->q.text,()->new TreeMap<String,Set<Integer>>(Comparator.reverseOrder()),
												Collectors.mapping(q->q.linea,Collectors.toSet())));			
		return words;
	}
	
	
	static class Par {
		Integer linea;
		String text;
		public static Par of(Integer linea, String text) {
			return new Par(linea, text);
		}
		public Par(Integer linea, String text) {
			super();
			this.linea = linea;
			this.text = text;
		}
	}
}
