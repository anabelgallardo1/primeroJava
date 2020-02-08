package us.lsi.geometria;

import java.awt.Graphics;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class AgregadoGeometrico implements ObjetoGeometrico2D {

	public static AgregadoGeometrico empty() {
		return new AgregadoGeometrico();
	}
	
	public static AgregadoGeometrico of(ObjetoGeometrico2D... objetos) {
		return new AgregadoGeometrico(Arrays.stream(objetos).collect(Collectors.toSet()));
	}

	private Set<ObjetoGeometrico2D> objetos;
	
	private AgregadoGeometrico() {
		super();
		this.objetos = new HashSet<>();
	}	

	public AgregadoGeometrico(Set<ObjetoGeometrico2D> objetos) {
		super();
		this.objetos = objetos;
	}

	@Override
	public AgregadoGeometrico rota(Punto2D p, Double angulo) {
		return new AgregadoGeometrico(this.objetos.stream().map(x->x.rota(p,angulo)).collect(Collectors.toSet()));
	}

	@Override
	public ObjetoGeometrico2D traslada(Vector2D v) {
		return new AgregadoGeometrico(objetos.stream().map(x->x.traslada(v)).collect(Collectors.toSet()));
	}

	@Override
	public void draw(Graphics g) {
		objetos.stream().forEach(x->x.draw(g));
	}

}
