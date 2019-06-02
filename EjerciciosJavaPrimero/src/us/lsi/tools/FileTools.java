package us.lsi.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

public class FileTools {
	
	public static List<String> lineas(String file){
		List<String> lineas = null;
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			lineas = bufferedReader.lines().collect(Collectors.toList());
			bufferedReader.close();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		return lineas;
	}
	
	public static String text(String file){
		List<String> lineas = null;
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			lineas = bufferedReader.lines().collect(Collectors.toList());
			bufferedReader.close();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		return lineas.stream().collect(Collectors.joining("\n"));
	}
	
	public static void write(String file, String text){
		try {
			final PrintWriter f =  new PrintWriter(new BufferedWriter(new FileWriter(file)));
			f.println(text);
			f.close();
		} catch (IOException e) {
			throw new IllegalArgumentException("No se ha podido crear el fichero " + file);
		}
	}

}
