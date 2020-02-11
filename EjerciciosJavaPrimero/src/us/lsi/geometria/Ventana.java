package us.lsi.geometria;



import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Ventana extends JPanel {
  
	private static final long serialVersionUID = 865807501071430378L;
	
	private ObjetoGeometrico2D objeto;
	private Color color;
		
	public Ventana(ObjetoGeometrico2D objeto, Color color) {
		super();
		this.objeto = objeto;
		this.color = color;
	}

	@Override
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
	  JFrame f = new JFrame("Ventana de dibujo");
	  f.getContentPane().add(new Ventana(objeto,color));
	  f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  f.setSize(1000, 1000);
	  f.setLocation(0, 0);
	  f.setVisible(true);
  }

	
}
