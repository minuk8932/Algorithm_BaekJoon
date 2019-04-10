package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 9954번: Cedric's Cypher
 *
 *	@see https://www.acmicpc.net/problem/9954/
 *
 */
public class Boj9954 {
	private static final String NEW_LINE = "\n";
	private static final String TERMINATE = "#";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String line = br.readLine();
			if(line.equals(TERMINATE)) break;
			
			int length = line.length();
			int code = line.charAt(length - 1) - 'A';
			
			line = line.substring(0, length - 1);
			
			for(char c: line.toCharArray()) {
				if((c < 'A' || c > 'Z') && (c < 'a' || c > 'z')) {
					sb.append(c);
				}
				else {
					int value = c - code;

					if('A' <= c && c <= 'Z') {
						if(value < 65) sb.append((char)('Z' + (value - 64)));		// 코드에 맞춰 변형
						else sb.append((char) value);
					}
					else {
						if(value < 97) sb.append((char)('z' + (value - 96)));
						else sb.append((char) value);
					}
				}
			}
			
			sb.append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
}
