package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 12400번: 구글어스
 *
 *	@see https://www.acmicpc.net/problem/12400/
 *
 */
public class Boj12400 {
	private static final String CASE = "Case #";
	private static final String COLON = ": ";
	private static final String NEW_LINE = "\n";
	
	private static final char[] alpha = {'y', 'h', 'e', 's', 'o', 'c', 'v', 'x', 'd', 'u', 'i', 'g', 'l',
			'b', 'k', 'r', 'z', 't', 'n', 'w', 'j', 'p', 'f', 'm', 'a', 'q'};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < N; t++) {
			sb.append(CASE).append(t + 1).append(COLON);
			
			for(char c: br.readLine().toCharArray()) {
				if(c >= 'a' && c <= 'z') sb.append(alpha[c - 'a']);
				else sb.append(c);
			}
			
			sb.append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
}
