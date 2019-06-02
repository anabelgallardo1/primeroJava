package us.lsi.whatsapp;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import us.lsi.tools.CollectionTools;
import us.lsi.tools.FileTools;
import us.lsi.tools.Graphics;
import us.lsi.tools.Preconditions;

public class Mensajes {
	
	public static Mensajes ofFile(String file) {
		return new Mensajes(file);
	}

	private List<Mensaje> mensajes;
	private Set<String> palabrasHuecas;

	private Mensajes(String file) {
		super();	
		this.mensajes = FileTools.lineas(file).stream()
				.filter(x->x.length()>0)
				.map(m->Mensaje.parse(m))
				.collect(Collectors.toList());
		this.palabrasHuecas = FileTools.lineas("resources/palabras_huecas.txt").stream()
				.filter(x->x.length()>0)
				.collect(Collectors.toSet());
	}

	@Override
	public String toString() {
		return CollectionTools.toString(mensajes.stream(), "\n");
	}
	
	private Map<String,List<Mensaje>> mensajesPorUsuario = null;
	
	public Map<String,List<Mensaje>> getMensajesPorUsuario(){
		if(mensajesPorUsuario==null) {
			this.mensajesPorUsuario = this.mensajes.stream().collect(Collectors.groupingBy(x->x.usuario));
		}
		return mensajesPorUsuario;
	}
	
	public Integer getNumeroDeMensajesPorUsuario(String usuario) {
		Preconditions.checkArgument(this.mensajesPorUsuario.containsKey(usuario), 
				String.format("No existe el usuario %s",usuario));
		return this.mensajesPorUsuario.get(usuario).size();
	}
	
	private Map<DayOfWeek,List<Mensaje>> mensajesPorDiaDeSemana = null;
	
	public Map<DayOfWeek,List<Mensaje>> getMensajesPorDiaDeSemana(){
		if(mensajesPorDiaDeSemana==null) {
			this.mensajesPorDiaDeSemana = this.mensajes.stream().collect(Collectors.groupingBy(x->x.fecha.getDayOfWeek()));
		}
		return mensajesPorDiaDeSemana;
	}
	
	public Integer getNumeroDeMensajesPorDiaDeSemana(DayOfWeek diaSemana) {
		Preconditions.checkArgument(this.mensajesPorDiaDeSemana.containsKey(diaSemana), 
				String.format("No existe la fecha %s",diaSemana.toString()));
		return this.mensajesPorDiaDeSemana.get(diaSemana).size();
	}
	
	public void drawNumeroDeMensajesPorDiaDeSemana(String fileOut) {
		List<String> nombresColumna = getMensajesPorDiaDeSemana().keySet().stream()
				.sorted()
				.map(x->x.toString())
				.collect(Collectors.toList());
		
		List<Integer> datos =  getMensajesPorDiaDeSemana().entrySet().stream()
				.sorted(Comparator.comparing(x->x.getKey()))
				.map(x->x.getValue().size())
				.collect(Collectors.toList());
		
		List<String> nombresDatos = Arrays.asList("NumeroDeMensajes");
		
		Graphics.columnsBarChart(fileOut, "MensajesPorDiaDeSemana", nombresDatos, nombresColumna, datos);
	}
	
	private Map<LocalDate,List<Mensaje>> mensajesPorFecha = null;
	
	public Map<LocalDate,List<Mensaje>> getMensajesPorFecha(){
		if(mensajesPorFecha==null) {
			this.mensajesPorFecha = this.mensajes.stream().collect(Collectors.groupingBy(x->x.fecha));
		}
		return mensajesPorFecha;
	}
	
	public Integer getNumeroDeMensajesPorFecha(LocalDate fecha) {
		Preconditions.checkArgument(this.mensajesPorFecha.containsKey(fecha), 
				String.format("No existe la fecha %s",fecha.toString()));
		return this.mensajesPorFecha.get(fecha).size();
	}
	
	private Map<LocalTime,List<Mensaje>> mensajesPorHora = null;
	
	public Map<LocalTime,List<Mensaje>> getMensajesPorHora(){
		if(mensajesPorHora==null) {
			this.mensajesPorHora = this.mensajes.stream().collect(Collectors.groupingBy(x->x.hora));
		}
		return mensajesPorHora;
	}
	
	public Integer getNumeroDeMensajesPorHora(LocalTime hora) {
		Preconditions.checkArgument(this.mensajesPorHora.containsKey(hora), 
				String.format("No existe la fecha %s",hora.toString()));
		return this.mensajesPorHora.get(hora).size();
	}
	
	private Map<String,Integer> frecuenciasDePalabras = null;
	
	public Map<String,Integer> getFrecuenciasDePalabras(){
		if(frecuenciasDePalabras==null) {
			this.frecuenciasDePalabras = this.mensajes.stream()
					.map(x->x.texto)
					.flatMap(x->Arrays.stream(x.split("[ \".,:();¿?¡!\\\"]")))
					.filter(x->x.length()>0)
					.filter(x->!this.palabrasHuecas.contains(x))
					.collect(Collectors.groupingBy(x->x,Collectors.summingInt((x->1))));
		}
		return frecuenciasDePalabras;
	}
	
	private Integer numeroDePalabras;
	
	public Integer getNumeroDePalabras(){
		if(numeroDePalabras==null) {
			numeroDePalabras = this.getFrecuenciasDePalabras().values().stream().mapToInt(x->x).sum();
		}
		return numeroDePalabras;
	}
	
	private Map<PalabraUsuario,Integer> frecuenciasDePalabrasPorUsuario = null;
	
	public Map<PalabraUsuario,Integer> getFrecuenciasDePalabrasPorUsuario(){
		if(frecuenciasDePalabrasPorUsuario==null) {
			this.frecuenciasDePalabrasPorUsuario = this.mensajes
					.stream()
					.map(m->PalabraUsuario.of(m.texto,m.usuario))
					.flatMap(p->Arrays.stream(p.palabra.split("[ \".,:();¿?¡!\\\"]"))
							.filter(x->x.length()>0)
							.filter(x->!this.palabrasHuecas.contains(x))
							.map(x->PalabraUsuario.of(x,p.usuario)))
					.collect(Collectors.groupingBy(x->x,Collectors.summingInt((x->1))));
		}
		return frecuenciasDePalabrasPorUsuario;
	}
	
	private Map<String,Integer> numeroDePalabrasPorUsuario;
	
	public Map<String,Integer> getNumeroDePalabrasPorUsuario(){
		if(numeroDePalabrasPorUsuario==null) {
			numeroDePalabrasPorUsuario = this.getFrecuenciasDePalabrasPorUsuario().entrySet().stream()
				.collect(Collectors.groupingBy(e->e.getKey().usuario,
									Collectors.summingInt(e->e.getValue())));
		}
		return numeroDePalabrasPorUsuario;
	}
	
	private Map<PalabraUsuario,Integer> frecuenciasDePalabrasPorRestoDeUsuarios = null;
	
	public Map<PalabraUsuario,Integer> getFrecuenciasDePalabrasPorRestoDeUsuarios(){
		if(frecuenciasDePalabrasPorRestoDeUsuarios==null) {
			this.frecuenciasDePalabrasPorRestoDeUsuarios = new HashMap<>();
			this.getFrecuenciasDePalabrasPorUsuario().entrySet().stream()
			.forEach(e->this.frecuenciasDePalabrasPorRestoDeUsuarios
				.put(e.getKey(),getFrecuenciasDePalabras().get(e.getKey().palabra)
						-getFrecuenciasDePalabrasPorUsuario().get(e.getKey())
					));
		}
		return frecuenciasDePalabrasPorRestoDeUsuarios;
	}
	
	private Map<String,Integer> numeroDePalabrasPorRestoDeUsuarios;
	
	public Map<String,Integer> getNumeroDePalabrasPorRestoDeUsuarios(){
		if(numeroDePalabrasPorRestoDeUsuarios==null) {
			numeroDePalabrasPorRestoDeUsuarios = this.getFrecuenciasDePalabrasPorRestoDeUsuarios().entrySet().stream()
				.collect(Collectors.groupingBy(e->e.getKey().usuario,
									Collectors.summingInt(e->e.getValue())));
		}
		return numeroDePalabrasPorRestoDeUsuarios;
	}

	public Double importanciaDePalabrasDeUsuario(String palabra,String usuario){
		return (1.*this.getFrecuenciasDePalabrasPorUsuario().get(PalabraUsuario.of(palabra, usuario))
				/this.getNumeroDePalabrasPorUsuario().get(usuario))*
				(1.*this.getNumeroDePalabrasPorRestoDeUsuarios().get(usuario)
				/this.getFrecuenciasDePalabrasPorRestoDeUsuarios().get(PalabraUsuario.of(palabra, usuario)));
	}
	
	public Map<String,Double> palabrasCaracteristicasDeUsuario(String usuario, Integer umbral){
		return this.getFrecuenciasDePalabrasPorUsuario().entrySet().stream()
				.filter(e->e.getKey().usuario.equals(usuario))
				.filter(x->this.getFrecuenciasDePalabras().get(x.getKey().palabra)>umbral)
				.filter(x->x.getValue()>umbral)
				.filter(e->this.getFrecuenciasDePalabrasPorRestoDeUsuarios().getOrDefault(e.getKey(),0)>umbral)
				.collect(Collectors.groupingBy(e->e.getKey().palabra,
						Collectors.summingDouble(e->this.importanciaDePalabrasDeUsuario(e.getKey().palabra,e.getKey().usuario))));
				
	}
	
	
	
	public static void main(String[] args) {
		Mensajes m = Mensajes.ofFile("resources/bigbangtheory_es.txt");
//		System.out.println(m);
		Comparator<Map.Entry<PalabraUsuario,Integer>> cmp = Comparator.comparing(e->e.getKey().usuario);
		var cmp2 = cmp.thenComparing(Comparator.comparing(e->e.getValue()));
		@SuppressWarnings("unused")
		var cmp3 = cmp2.reversed();
//		CollectionTools.toConsole(
//				m.getFrecuenciasDePalabrasPorRestoDeUsuarios().entrySet()
//				.stream()
//				.filter(e->e.getValue()>2)
//				.sorted(cmp3).limit(5),"\n");
//		CollectionTools.toConsole(m.palabrasCaracteristicasDeUsuario("Sheldon",2).entrySet()
//				.stream()
//				.sorted(Comparator.comparing((Map.Entry<String,Double> x)->x.getValue()).reversed())
//				.limit(15),"\n");
		
		m.drawNumeroDeMensajesPorDiaDeSemana("ficheros/Mensajes.html");
	}
	
	public static class PalabraUsuario {
		public final String palabra;
		public final String usuario;
		public static PalabraUsuario of(String palabra, String usuario){
			return new PalabraUsuario(palabra,usuario);
		}
		public PalabraUsuario(String palabra, String usuario) {
			super();
			this.palabra = palabra;
			this.usuario = usuario;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((palabra == null) ? 0 : palabra.hashCode());
			result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
			PalabraUsuario other = (PalabraUsuario) obj;
			if (palabra == null) {
				if (other.palabra != null)
					return false;
			} else if (!palabra.equals(other.palabra))
				return false;
			if (usuario == null) {
				if (other.usuario != null)
					return false;
			} else if (!usuario.equals(other.usuario))
				return false;
			return true;
		}
		@Override
		public String toString() {
			return String.format("(%s,%s)",palabra, usuario);
		}	
		
		
	}

}
