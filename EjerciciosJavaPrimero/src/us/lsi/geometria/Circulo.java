package us.lsi.geometria;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Circulo implements ObjetoGeometrico2D {
	
	public static Circulo of(Punto2D centro, Double radio) {
		return new Circulo(centro, radio);
	}

	private Punto2D centro;
	private Double radio;
	
	private Circulo(Punto2D centro, Double radio) {
		super();
		this.centro = centro;
		this.radio = radio;
	}
	
	public Punto2D getCentro() {
		return centro;
	}

	public Double getRadio() {
		return radio;
	}
	
	public Double getArea() {
		return Math.PI*this.radio*this.radio;
	}

	public Double getPerimetro() {
		return 2*Math.PI*this.radio;
	}

	@Override
	public Circulo rota(Punto2D p, Double angulo) {		
		return Circulo.of(this.centro.rota(p,angulo), this.radio);
	}

	@Override
	public Circulo traslada(Vector2D v) {
		return Circulo.of(this.centro.traslada(v), this.radio);
	}
	
	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.draw(new Ellipse2D.Double(this.centro.getX(), this.centro.getY(),this.radio,this.radio));		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((centro == null) ? 0 : centro.hashCode());
		result = prime * result + ((radio == null) ? 0 : radio.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Circulo other = (Circulo) obj;
		if (centro == null) {
			if (other.centro != null)
				return false;
		} else if (!centro.equals(other.centro))
			return false;
		if (radio == null) {
			if (other.radio != null)
				return false;
		} else if (!radio.equals(other.radio))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return String.format("(%s,%.2f)",this.centro,this.radio);
	}


}
