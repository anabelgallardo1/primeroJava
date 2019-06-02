package us.lsi.tools;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import us.lsi.montecarlo.Card;

public class GraphicsCards {

	public static void cartas(String fileOut, List<Card> cartas, Double fuerza, String tipo) {
		String result = FileTools.text("resources/CartasPattern.html");
		String cartasText = cartas.stream()
				.map(c->String.format("<img src=\"../%s\" width=\"120px\" height=\"180px\">",c.nameFile()))
				.collect(Collectors.joining("\n","\n","\n"));
		Map<String,String> reglas = new HashMap<>();
		reglas.put("cartas",cartasText);
		reglas.put("fuerza",String.format("%.3f", fuerza));
		reglas.put("tipo",tipo);
		result = StringTools.transform(result,reglas);
		FileTools.write(fileOut,result);
	}

}
