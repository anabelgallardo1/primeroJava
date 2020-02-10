package us.lsi.geometria;


import java.awt.Graphics;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class AgregadoGeometrico2D implements ObjetoGeometrico2D {

	public static AgregadoGeometrico2D empty() {
		return new AgregadoGeometrico2D();
	}
	
	public static AgregadoGeometrico2D of(ObjetoGeometrico2D... objetos) {
		return new AgregadoGeometrico2D(Arrays.stream(objetos).collect(Collectors.toSet()));
	}
	
	public static AgregadoGeometrico2D of(Set<ObjetoGeometrico2D> objetos) {
		return new AgregadoGeometrico2D(objetos);
	}

	private Set<ObjetoGeometrico2D> objetos;
	
	private AgregadoGeometrico2D() {
		super();
		this.objetos = new HashSet<>();
	}	

	public AgregadoGeometrico2D(Set<ObjetoGeometrico2D> objetos) {
		super();
		this.objetos = objetos;
	}

	@Override
	public AgregadoGeometrico2D rota(Punto2D p, Double angulo) {
		return new AgregadoGeometrico2D(this.objetos.stream().map(x->x.rota(p,angulo)).collect(Collectors.toSet()));
	}

	@Override
	public ObjetoGeometrico2D traslada(Vector2D v) {
		return new AgregadoGeometrico2D(objetos.stream().map(x->x.traslada(v)).collect(Collectors.toSet()));
	}
	
	@Override
	public AgregadoGeometrico2D homotecia(Punto2D p, Double factor) {
		return AgregadoGeometrico2D.of(this.objetos.stream().map(x->x.homotecia(p, factor)).collect(Collectors.toSet()));
	}

	@Override
	public void draw(Graphics g) {
		objetos.stream().forEach(x->x.draw(g));
	}

}
