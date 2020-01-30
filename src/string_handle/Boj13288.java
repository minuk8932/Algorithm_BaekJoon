package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * 
 * 	@author exponential-e
 *	백준 13288번: A New Alphabet
 *
 *	@see https://www.acmicpc.net/problem/13288/
 *
 */
public class Boj13288 {
	private static HashMap<Character, String> newWords = new HashMap<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		StringBuilder sb = new StringBuilder();
		
		init();
		
		for(char c: br.readLine().toCharArray()) {
			char word = c;
			
			if((word < 'A' || word > 'Z') && (word < 'a' || word > 'z')) {
				sb.append(c);
				continue;
			}
			
			if(word >= 'A' && word <= 'Z') word = (char) (word + 32);
			sb.append(newWords.get(word));
		}
		
		System.out.println(sb.toString());
	}
	
	private static void init() {				// init
		newWords.put('a', "@");
		newWords.put('b', "8");
		newWords.put('c', "(");
		newWords.put('d', "|)");
		newWords.put('e', "3");
		newWords.put('f', "#");
		newWords.put('g', "6");
		newWords.put('h', "[-]");
		newWords.put('i', "|");
		newWords.put('j', "_|");
		newWords.put('k', "|<");
		newWords.put('l', "1");
		newWords.put('m', "[]\\/[]");
		newWords.put('n', "[]\\[]");
		newWords.put('o', "0");
		newWords.put('p', "|D");
		newWords.put('q', "(,)");
		newWords.put('r', "|Z");
		newWords.put('s', "$");
		newWords.put('t', "']['");
		newWords.put('u', "|_|");
		newWords.put('v', "\\/");
		newWords.put('w', "\\/\\/");
		newWords.put('x', "}{");
		newWords.put('y', "`/");
		newWords.put('z', "2");
	}
}
