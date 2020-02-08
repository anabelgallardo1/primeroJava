package us.lsi.geometria;



import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class GraphicsHelp extends JPanel {
  
	private static final long serialVersionUID = 865807501071430378L;
	
	private ObjetoGeometrico2D objeto;
	Color color;
		
	public GraphicsHelp(ObjetoGeometrico2D objeto, Color color) {
		super();
		this.objeto = objeto;
		this.color = color;
	}

	public void paint(Graphics g) {
	  Graphics2D g2 = (Graphics2D) g;
	  g.setFont(new Font("Seqoe UI", Font.PLAIN, 16));
	  g.setColor(Color.BLACK);
	  g.drawString("Figuras Geométricas", 10, 20);
	  g.setColor(this.color);
	  g2.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
	  this.objeto.draw(g);
	}
  
	public static void draw(ObjetoGeometrico2D objeto) {
	  JFrame f = new JFrame("Hello");
	  f.getContentPane().add(new GraphicsHelp(objeto,Color.RED));
	  f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  f.setSize(1000, 1000);
	  f.setLocation(0, 0);
	  f.setVisible(true);
  }

	public static void main(String[] args) {
		Circulo c1 = Circulo.of(Punto2D.of(20.,30.),15.);
		Circulo c2 = Circulo.of(Punto2D.of(60.,70.),30.);
		Punto2D p1 = Punto2D.of(500.,150.);
		Punto2D p2 = Punto2D.of(100.,150.);
		Punto2D p3 = Punto2D.of(500.,200.);
		Poligono2D pl = Poligono2D.rectanguloHorizontal(p2, 40., 50.);
		Poligono2D pl2 = pl.rota(p2, Math.PI/3);
		Poligono2D pl3 = pl2.traslada(Vector2D.ofXY(100.,200.));
		Segmento2D s = Segmento2D.of(p1, p2);
		AgregadoGeometrico a = AgregadoGeometrico.of(c1,c2,p3,pl,pl3,s);
		draw(a);
   }
}
