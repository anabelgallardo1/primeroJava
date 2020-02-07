package us.lsi.geometria;

public class Segmento implements ObjetoGeometrico2D {

	public static Segmento of(Punto2D p1, Punto2D p2) {
		return new Segmento(p1, p2);
	}

	private Punto2D p1;
	private Punto2D p2;
	
	private Segmento(Punto2D p1, Punto2D p2) {
		super();
		this.p1 = p1;
		this.p2 = p2;
	}

	public Punto2D getP1() {
		return p1;
	}

	public Punto2D getP2() {
		return p2;
	}
	
	public Double getLongitud(){
		return p1.getDistanciaA(p2);
	}
	
	@Override
	public Segmento rota(Punto2D p, Double angulo) {
		return Segmento.of(this.p1.rota(p,angulo), this.p2.rota(p,angulo));
	}

	@Override
	public Segmento traslada(Vector2D v) {
		return Segmento.of(this.p1.traslada(v), this.p2.traslada(v));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((p1 == null) ? 0 : p1.hashCode());
		result = prime * result + ((p2 == null) ? 0 : p2.hashCode());
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
		Segmento other = (Segmento) obj;
		if (p1 == null) {
			if (other.p1 != null)
				return false;
		} else if (!p1.equals(other.p1))
			return false;
		if (p2 == null) {
			if (other.p2 != null)
				return false;
		} else if (!p2.equals(other.p2))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "(" + p1 + ", " + p2 + ")";
	}

}
