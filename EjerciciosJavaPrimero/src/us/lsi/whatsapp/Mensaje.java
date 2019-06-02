package us.lsi.whatsapp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import us.lsi.tools.Preconditions;

public class Mensaje {
	
	public static Mensaje of(LocalDate fecha, LocalTime hora, String usuario, String texto) {
		return new Mensaje(fecha, hora, usuario, texto);
	}

	public static Mensaje parse(String mensaje) {
		return new Mensaje(mensaje);
	}

	
	private static String RE = "(?<fecha>\\d\\d?/\\d\\d?/\\d\\d?) (?<hora>\\d\\d?:\\d\\d) - (?<usuario>[^:]+): (?<texto>.+)";
	private static Pattern pattern = Pattern.compile(RE);
	
	public final LocalDate fecha;
	public final LocalTime hora;
	public final String usuario;
	public final String texto;
	
	
	private Mensaje(LocalDate fecha, LocalTime hora, String usuario, String texto) {
		super();
		this.fecha = fecha;
		this.hora = hora;
		this.usuario = usuario;
		this.texto = texto;
	}
	
	private Mensaje(String mensaje) {
		Matcher m = Mensaje.pattern.matcher(mensaje);
		
		Preconditions.checkArgument(m.find() && m.groupCount() == 4, 
				String.format("Formato incorrecto en grupos = %d, mensaje = %s", m.groupCount(), mensaje));
		String[] pf = m.group("fecha").split("/");
		this.fecha = LocalDate.of(Integer.parseInt("20"+pf[2]),Integer.parseInt(pf[1]),Integer.parseInt(pf[0]));
		String[] ph =  m.group("hora").split(":");
		this.hora = LocalTime.of(Integer.parseInt(ph[0]),Integer.parseInt(ph[1]));
		this.usuario = m.group("usuario");
		this.texto = m.group("texto");
	}

	
	@Override
	public String toString() {
		return String.format("%s,%s,%10s,%s",fecha.toString(),hora.toString(),usuario,texto);
	}

	public static void main(String[] args) {
		Mensaje m = Mensaje.parse("26/02/16 16:16 - Sheldon: No tiene sentido, solo creo que es una buena idea para una camiseta.");
		System.out.println(m);
	}

	
}
