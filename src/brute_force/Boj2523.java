package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 2523번: 별찍기 13
 *
 *	@see https://www.acmicpc.net/problem/2523/
 *
 */
public class Boj2523 {
	private static final char STAR = '*';
	private static final char NEW_LINE = '\n';
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		System.out.println(staring(N));		// 규칙을 유추해 별을 찍어보자.
	}
	
	private static StringBuilder staring(int n) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i < n * 2; i++) {
			int loop = i <= n ? i : n - (i % n);
			
			for(int j = 0; j < loop; j++) {
				sb.append(STAR);
			}
			
			sb.append(NEW_LINE);
		}
		
		return sb;
	}
}
