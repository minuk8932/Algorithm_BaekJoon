package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 *	백준 18245번: 이상한 나라의 암호
 *
 *	@see https://www.acmicpc.net/problem/18245/
 *
 */
public class Boj18245 {
	private static final String TERMINATE = "Was it a cat I saw?";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		int interval = 2;
		
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			input = br.readLine();
			if(input.equals(TERMINATE)) break;
			
			char[] pass = input.toCharArray();
			for(int i = 0; i < pass.length; i += interval) {
				sb.append(pass[i]);
			}
			
			sb.append(NEW_LINE);
			interval++;
		}
		
		System.out.println(sb.toString());
	}
}
