package us.lsi.geometria;

import java.awt.Graphics;

public interface ObjetoGeometrico2D {
	ObjetoGeometrico2D rota(Punto2D p, Double angulo);
	ObjetoGeometrico2D traslada(Vector2D v);
	ObjetoGeometrico2D homotecia(Punto2D p, Double factor);
	void draw(Graphics g);
}
