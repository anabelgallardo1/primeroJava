package us.lsi.tools;

import java.util.Random;

public class Math2 {
	
	private static Random random = new Random(System.nanoTime());
	
	/**
	 * @param limitExcluded Un entero
	 * @return Un entero aleatorio en el intervalo 0..limitExcluded con el extremo derecho excluido
	 */
	public static Integer intRandom(Integer limitExcluded) {	
		Double r = random.nextDouble();
		Integer ri = (int) (r*limitExcluded);
		return ri;
	}

}
