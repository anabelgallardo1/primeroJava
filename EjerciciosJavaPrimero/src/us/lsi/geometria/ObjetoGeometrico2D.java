package us.lsi.geometria;

import java.awt.Graphics;

public interface ObjetoGeometrico2D {
	ObjetoGeometrico2D rota(Punto2D p, Double angulo);
	ObjetoGeometrico2D traslada(Vector2D v);
	ObjetoGeometrico2D homotecia(Punto2D p, Double factor);
	ObjetoGeometrico2D proyectaSobre(Recta2D r);
	ObjetoGeometrico2D simetrico(Recta2D r);
	void draw(Graphics g);
}
