package us.lsi.test;

import java.util.Arrays;
import java.util.List;

import us.lsi.tools.Graphics;

public class TestPieChart {

	public static void main(String[] args) {
		List<String> campos = Arrays.asList("Curso","Numero de alumnos");
		List<String> nombres = Arrays.asList("Primero","Segundo","Tercero","Cuarto");
		List<Integer> datos = Arrays.asList(500,200, 90,60);		
		Graphics.pieChart("ficheros/PieChartOut.html","Piechart",campos, nombres, datos);

	}

}
