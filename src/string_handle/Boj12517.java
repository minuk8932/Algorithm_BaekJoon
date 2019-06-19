package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 12517번: Centauri Prime
 *
 *	@see https://www.acmicpc.net/problem/12517/
 *
 */
public class Boj12517 {
	private static final String CASE = "Case #";
	private static final String COLON = ": ";
	private static final String K = " is ruled by a king.\n";
	private static final String Q = " is ruled by a queen.\n";
	private static final String N = " is ruled by nobody.\n";
	
	private static final int[] ALPHA = {1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, -1, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine()) + 1;
		
		for(int t = 1; t < T; t++) {
			String country = br.readLine();
			sb.append(CASE).append(t).append(COLON).append(country).append(ruled(country.charAt(country.length() - 1)));
		}
		
		System.out.print(sb.toString());
	}
	
	private static String ruled(char tail) {
		return ALPHA[tail - 'a'] == 0 ? K: ALPHA[tail - 'a'] == 1 ? Q: N;
	}
}
