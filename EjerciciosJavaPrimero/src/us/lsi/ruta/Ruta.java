package us.lsi.ruta;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import us.lsi.data.Coordenadas2D;
import us.lsi.data.Coordenadas3D;
import us.lsi.tools.FileTools;
import us.lsi.tools.Graphics;
import us.lsi.tools.GraphicsMaps;
import us.lsi.tools.GraphicsMaps.GraphicType;

public class Ruta {
	
	public static Ruta of(List<Marca> marcas) {
		return new Ruta(marcas);
	}
	
	public static Ruta of(String file) {
		List<Marca> marcas = FileTools.lineas(file).stream().map(x->Marca.parse(x)).collect(Collectors.toList());
		return of(marcas);
	}
	

	private List<Marca> marcas;

	private Ruta(List<Marca> marcas) {
		super();
		this.marcas = marcas;
	}

	public List<Marca> getMarcas() {
		return marcas;
	}
	
	public Double getLongitud() {
		return IntStream.range(0,marcas.size()-1)
				.mapToDouble(i->Coordenadas3D.distance(marcas.get(i).getCoordenadas(),marcas.get(i+1).getCoordenadas()))
				.sum();
	}
	
	@SuppressWarnings("unchecked")
	public void mostrarAltitud(String fileOut) {
		List<Double> alturas = 
				Stream.iterate(0,e->e < this.getMarcas().size(),e->e+10)
				.map(x->this.getMarcas().get(x).getCoordenadas().getAltitude())
				.collect(Collectors.toList());
		List<String> campos = Arrays.asList("Posicion","Altura");
		List<Double> position = IntStream.range(0,alturas.size()).boxed().map(x->x.doubleValue()).collect(Collectors.toList());
		Graphics.lineChart(fileOut,"Ruta Ronda",campos,position,alturas);
	}
	
	public void mostrarMapa(String fileOut, GraphicType type) {
		List<Coordenadas2D> coordenadas = 
				this.getMarcas()
				.stream()
				.map(x->x.getCoordenadas().to2D())
				.collect(Collectors.toList());
		GraphicsMaps.of(type).polyline(fileOut,coordenadas);
	}

	@Override
	public String toString() {
		return marcas.stream().map(x->x.toString()).collect(Collectors.joining("\n"));
	}
	

}
