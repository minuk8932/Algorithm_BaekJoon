package search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 15728번: 에리-카드
 *
 *	@see https://www.acmicpc.net/problem/15728
 *
 */
public class Boj15728 {	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] share = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			share[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] team = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			team[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(share);
		Arrays.sort(team);
		System.out.println(result(N, K, share, team));
	}
	
	private static int result(int n, int k, int[] s, int[] t) {
		int prev = 0;
		int post = n - 1;
		
		while (k-- > 0) {
			int[] mul = {s[0] * t[prev], s[0] * t[post], s[n - 1] * t[prev], s[n - 1] * t[post]};

			if (compare(mul[0], mul[1], mul[2], mul[3]) || compare(mul[2], mul[0], mul[1], mul[3])) prev++;		// search max k times
			else post--;
		}
		
		return Math.max(Math.max(s[0] * t[prev], s[0] * t[post]), Math.max(s[n - 1] * t[prev], s[n - 1] * t[post]));
	}
	
	private static boolean compare(int a, int b, int c, int d) {
		return a >= b && a >= c && a >= d;
	}
}
