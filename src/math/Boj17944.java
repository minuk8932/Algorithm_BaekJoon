package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 17944번: 퐁당퐁당1
 *
 *	@see https://www.acmicpc.net/problem/17944/
 *
 */
public class Boj17944 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		System.out.println(count(N, T));
	}
	
	/**
	 * 
	 * @param n
	 * @param t
	 * @return find regularity
	 */
	private static int count(int n, int t) {
		int src = n * 2;
		int jud = --t / --src;
		int mod = t % src;
		
		return jud % 2 == 0 ? mod + 1: 2 * n - mod;
	}
}
