package us.lsi.tools;

import java.util.function.Function;

public class Operators {
	
	/**
	 * @param <E> Tipo de los elementos
	 * @param <R> Tipo del resultado
	 * @param e Un elemento que puede ser null
	 * @param value El valor por defecto que se delverá si e es null
	 * @param expression La expresióbn que se aplicará a e si no es null
	 * @return Si e es null se devolverçá value y si no lo es expression.aply(e)
	 */
	public static <E,R> R ifNull(E e, R value, Function<E,R> expression) {
		return e==null? value: expression.apply(e);
	}
	/**
	 * @param <E> Tipo de los elementos
	 * @param e Un elemento que puede ser null
	 * @param value El valor por defecto que se delverá si e es null
	 * @return Si e es null se devolverçá value y si no lo es e
	 */
	public static <E> E ifNull(E e, E value) {
		return e==null? value: e;
	}

}
