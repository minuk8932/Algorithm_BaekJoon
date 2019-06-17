package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 11104번: Fridge of Your Dreams
 */
public class Boj11104 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		while(N-- > 0) {
			int value = 0;
			int length = 23;
			
			for(char a: br.readLine().toCharArray()) {
				value += (a - '0') * Math.pow(2, length--);
			}
			
			sb.append(value).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
}
