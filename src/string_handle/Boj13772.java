package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 13772번: Holes
 *
 *	@seeh https://www.acmicpc.net/problem/13772/
 *
 */
public class Boj13772 {
	private static final int[] ALPHA = {1, 2, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0};
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());

		while(N-- > 0) {
			int result = 0;
			
			for(char a: br.readLine().toCharArray()) {
				if(a >= 'A' && a <= 'Z') result += ALPHA[a - 'A'];
			}
			
			sb.append(result).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
}
