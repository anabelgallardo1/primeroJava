package us.lsi.geometria;



import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Locale;

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
  
	public static void draw(ObjetoGeometrico2D objeto, Color color) {
	  JFrame f = new JFrame("Hello");
	  f.getContentPane().add(new GraphicsHelp(objeto,color));
	  f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  f.setSize(1000, 1000);
	  f.setLocation(0, 0);
	  f.setVisible(true);
  }

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Circulo2D c1 = Circulo2D.of(Punto2D.of(20.,30.),15.);
		Circulo2D c2 = Circulo2D.of(Punto2D.of(60.,70.),30.);
		Punto2D p1 = Punto2D.of(500.,150.);
		Punto2D p2 = Punto2D.of(100.,150.);
		Punto2D p3 = Punto2D.of(510.,200.);
		Punto2D p4 = Punto2D.of(650.,400.);
		Poligono2D pl = Poligono2D.ofPuntos(p1,p3,p4,p2)
				.rota(p2, Math.PI/3)
				.traslada(Vector2D.ofXY(100.,200.));
		Poligono2D pl2 = pl.homotecia(Punto2D.of(500.,0.), 0.5);
		System.out.println(pl);
		Segmento2D s = Segmento2D.of(p1, p2);
		Poligono2D cd = Poligono2D.cuadrado(Punto2D.of(50., 50.),Vector2D.ofXY(20., 20.));
		Poligono2D tr = Poligono2D.trianguloEquilatero(Punto2D.of(200., 200.), Vector2D.ofXY(150., 100.));
		Poligono2D tr2 = tr.traslada(Vector2D.ofXY(0., 200.));
		System.out.println(tr);
		System.out.println(tr2);
		AgregadoGeometrico2D a = AgregadoGeometrico2D.of(c1,c2,pl,pl2,s,cd,tr,tr2,Punto2D.of(200., 200.));
		draw(a,Color.BLUE);
   }
}
