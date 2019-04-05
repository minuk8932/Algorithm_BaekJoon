package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 13136번: Do not touch Anything!
 *
 *	@see https://www.acmicpc.net/problem/13136/
 *
 */
public class Boj13136 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		System.out.println(getCamCount(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken())));
	}
	
	private static long getCamCount(long R, long C, long N) {
		R = R % N == 0 ? R / N: R / N + 1;
		C = C % N == 0 ? C / N: C / N + 1;
		
		return R * C;
	}
}
