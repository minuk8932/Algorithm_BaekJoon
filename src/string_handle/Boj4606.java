package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * 
 * 	@author minchoba
 *	백준 4606번: The Seven Percent Solution
 *
 *	@see https://www.acmicpc.net/problem/4606/
 *
 */
public class Boj4606 {
	private static final String TERMINATE = "#";
	private static final String PREFIX = "%2";
	private static final char NEW_LINE = '\n';
	
	public static void main(String[] args) throws Exception{
		HashMap<Character, Character> encoding = init();
		System.out.println(makeSentence(encoding));
	}
	
	private static HashMap<Character, Character> init(){
		HashMap<Character, Character> hm = new HashMap<>();
		hm.put(' ', '0');
		hm.put('!', '1');
		hm.put('$', '4');
		hm.put('%', '5');
		hm.put('(', '8');
		hm.put(')', '9');
		hm.put('*', 'a');
		
		return hm;
	}
	
	private static StringBuilder makeSentence(HashMap<Character, Character> hm) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String line = br.readLine();
			if(line.equals(TERMINATE)) return sb;
			
			for(char w: line.toCharArray()) {
				if(hm.containsKey(w)) {					// 해쉬의 키에 존재하는 단어라면 값으로 바꿔줌
					sb.append(PREFIX).append(hm.get(w));
				}
				else {
					sb.append(w);
				}
			}
			
			sb.append(NEW_LINE);
		}
	}
}
