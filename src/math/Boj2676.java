package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2676번: 라스칼의 삼각형
 *
 *	@see https://www.acmicpc.net/problem/2676/
 *
 */
public class Boj2676 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			if(n / 2 < m) m = n - m;
			sb.append((n - m) * m + 1).append(NEW_LINE);		// similar with Catalan Number
		}
		
		System.out.println(sb);
	}
}
