package us.lsi.sevici;

import us.lsi.data.Coordenadas2D;
import us.lsi.pojo.Station;
import static us.lsi.tools.Operators.*;

public class Estacion {
	
	private static Integer n =0;
	
	public static Estacion of(Integer numero, String name, Integer slots, Integer empty_slots,
			Coordenadas2D ubicacion) {
		return new Estacion(numero,name, slots, empty_slots, ubicacion);
	}
	
	public static Estacion of(Station station) {
		Integer numero = ifNull(station.getExtra().getUid(),n++,s->Integer.parseInt(s));
		String name = station.getName(); 
		Integer empty_slots = ifNull(station.getEmpty_slots(),0,s->Integer.parseInt(s));
		Integer slots = ifNull(station.getFree_bikes(),0,s->Integer.parseInt(s)+empty_slots);
		Coordenadas2D ubicacion = Coordenadas2D.of(Double.parseDouble(station.getLatitude()), Double.parseDouble(station.getLongitude()));
		return new Estacion(numero,name, slots, empty_slots, ubicacion);
	}
	
	public static Estacion parse(String linea) {
			String[] campos = linea.split(",");
			String nameCompuesto = campos[0] ;
			String[] partes = nameCompuesto.split("_");
			Integer numero = Integer.parseInt(partes[0]);
			String name = partes[1];
			Integer slots = Integer.parseInt(campos[1]) ;
			Integer empty_slots = Integer.parseInt(campos[2]);
			Coordenadas2D ubicacion = Coordenadas2D.of(
					Double.parseDouble(campos[4]), 
					Double.parseDouble(campos[5]));
		return new Estacion(numero,name, slots, empty_slots, ubicacion);
	}

	private Integer numero;
	private String name;
	private Integer slots;
	private Integer empty_slots;
	private Coordenadas2D ubicacion;
	
	private Estacion(Integer numero, String name, Integer slots, Integer empty_slots, Coordenadas2D ubicacion) {
		super();
		this.numero = numero;
		this.name = name;
		this.slots = slots;
		this.empty_slots = empty_slots;
		this.ubicacion = ubicacion;
	}

	public String getName() {
		return name;
	}

	public Integer getNumero() {
		return numero;
	}

	public Integer getSlots() {
		return slots;
	}

	public Integer getEmpty_slots() {
		return empty_slots;
	}

	public Integer getFree_bikes() {
		return slots-empty_slots;
	}

	public Coordenadas2D getUbicacion() {
		return ubicacion;
	}

	@Override
	public String toString() {
		return String.format("%35s  %2d  %2d  %2d  %s",name,slots,empty_slots,getFree_bikes(),ubicacion.toString());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
		Estacion other = (Estacion) obj;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}
	
	

}
