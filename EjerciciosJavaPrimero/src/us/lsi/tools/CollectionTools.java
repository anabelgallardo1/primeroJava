package us.lsi.tools;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionTools {
	
	public static <E> String toString(Stream<E> collection, Function<E,String> toString, String delimiter, String prefix, String suffix) {
		return collection.map(toString).collect(Collectors.joining(delimiter, prefix, suffix));
	}
	
	public static <E> String toString(Stream<E> collection, String delimiter) {
		return collection.map(x->x.toString()).collect(Collectors.joining(delimiter));
	}
	
	public static <E> void toConsole(Stream<E> collection, Function<E,String> toString, String delimiter, String prefix, String suffix) {
		System.out.println(collection.map(toString).collect(Collectors.joining(delimiter, prefix, suffix)));
	}
	
	public static <E> void toConsole(Stream<E> collection, Function<E,String> toString, String delimiter) {
		System.out.println(collection.map(toString).collect(Collectors.joining(delimiter)));
	}
	
	public static <E> void toConsole(Stream<E> collection, String delimiter) {
		System.out.println(collection.map(x->x.toString()).collect(Collectors.joining(delimiter)));
	}
	
	public static <E,M extends Map<?,?>> void toConsole(M collection, String delimiter) {
		System.out.println(
				collection.entrySet().stream()
				.map(e->String.format("(%s,%s)", e.getKey(),e.getValue()))
				.collect(Collectors.joining("\n")));
	}
	
	public static <E> List<E> toList(Stream<E> collection){
		return collection.collect(Collectors.toList());
	}
	
	public static <E> Set<E> toSet(Stream<E> collection){
		return collection.collect(Collectors.toSet());
	}
	
	public static <E> SortedSet<E> toSortedSet(Stream<E> collection, Comparator<E> cmp){
		return collection.collect(Collectors.toCollection(()->new TreeSet<>(cmp)));
	}
	
	public static <E> int size(Stream<E> collection){
		return (int) collection.count();
	}

}
