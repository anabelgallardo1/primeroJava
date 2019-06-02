package us.lsi.montecarlo;

import java.util.Arrays;
import java.util.List;

import us.lsi.tools.Checkers;

public class Card implements Comparable<Card>{
	
	public static Card of(String text) {
		Character pc = text.charAt(text.length()-1);
		String ind = text.substring(0,text.length()-1);
		int palo = symbolsPalos.indexOf(pc);
		int id = nombreValores.indexOf(ind);
		return Card.of(id, palo);
	}
	
	public static Card of(Integer id) {
		return new Card(id);
	}
	
	public static Card of(Integer valor, Integer palo) {
		return new Card(valor, palo);
	}

	public static List<String> nombreValores = Arrays.asList("2","3","4","5","6","7","8","9","10","J","Q","K","A");
	public static List<Character> symbolsPalos = Arrays.asList('C', 'H', 'S', 'D');
	public static List<String> nombrePalos = Arrays.asList("clubs","hearts","spades","diamonds");
	

	Integer palo; // [0,4)
	Integer valor; // [0,14)
	Integer id; // [0,54)
	
	
	private Card(Integer valor, Integer palo) {
		super();
		this.palo = palo;
		this.valor = valor;
		this.id = palo*4+valor;
	}
	
	private Card(Integer id) {
		super();
		Checkers.check("No es posible", id >= 0 && id < 54);
		this.palo = id % 4;
		this.valor = id % 13;
		if(id==52) {
			this.palo = null;
			this.valor = 13;
		}
		if(id==53) {
			this.palo = null;
			this.valor = 14;
		}
	}

	@Override
	public String toString() {
		return nombreValores.get(valor)+symbolsPalos.get(palo);
	}
	
	public String nameFile() {
		String r = null;
		
		if(valor<9)
			r = String.format("resources/images/%s_of_%s.svg",nombreValores.get(valor),nombrePalos.get(palo));
		else {
			switch(valor) {
			case 9: r = String.format("resources/images/jack_of_%s.svg",nombrePalos.get(palo)); break;
			case 10: r = String.format("resources/images/queen_of_%s.svg",nombrePalos.get(palo)); break;
			case 11: r = String.format("resources/images/king_of_%s.svg",nombrePalos.get(palo)); break;
			case 12: r = String.format("resources/images/ace_of_%s.svg",nombrePalos.get(palo)); break;
			case 13: r = "resources/images/red_joker.svg"; break;
			case 14: r = "resources/images/black_joker.svg"; break;
			default:  Checkers.check("Indentificador nop posible",false);
			}
		}
		return r;
	}

	@Override
	public int compareTo(Card card) {
		return this.valor.compareTo(card.valor);
	}
		
	
}
