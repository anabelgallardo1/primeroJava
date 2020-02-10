package us.lsi.geometria;

public class Linea2D {
	
	public static Linea2D of(Punto2D punto, Vector2D vector) {
		return new Linea2D(punto, vector);
	}

	private Punto2D punto;
	private Vector2D vector;
	
	
	private Linea2D(Punto2D punto, Vector2D vector) {
		super();
		this.punto = punto;
		this.vector = vector;
	}


	public Punto2D getPunto() {
		return punto;
	}


	public Vector2D getVector() {
		return vector;
	}


	@Override
	public String toString() {
		return String.format("%s,%s)",this.punto,this.vector);
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((punto == null) ? 0 : punto.hashCode());
		result = prime * result + ((vector == null) ? 0 : vector.hashCode());
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
		Linea2D other = (Linea2D) obj;
		if (punto == null) {
			if (other.punto != null)
				return false;
		} else if (!punto.equals(other.punto))
			return false;
		if (vector == null) {
			if (other.vector != null)
				return false;
		} else if (!vector.equals(other.vector))
			return false;
		return true;
	}
	
	

}
