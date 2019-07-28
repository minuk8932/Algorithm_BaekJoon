package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 14863번: 서울에서 경산까지
 *
 *	@see https://www.acmicpc.net/problem/14863/
 *
 */
public class Boj14863 {
	private static long[][] dp;
	private static Method[] tour;
	
	private static class Volunteer{
		int time;
		int cost;
		
		public Volunteer(int time, int cost) {
			this.time = time;
			this.cost = cost;
		}
	}
	
	private static class Method{
		Volunteer walk;
		Volunteer bike;
		
		public Method(Volunteer walk, Volunteer bike) {
			this.walk = walk;
			this.bike = bike;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		tour = new Method[N + 1];
		for(int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			tour[i] = new Method(new Volunteer(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())),
					new Volunteer(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		dp = new long[N + 1][K + 1];
		System.out.println(recursion(N, K));
	}

	private static long recursion(int n, int k) {
		if(k < 0) return Long.MIN_VALUE;			// 불가능 할 때, 0을 넣으면 안돈다...
		if(n == 0) return 0;
		
		if(dp[n][k] != 0) return dp[n][k];
		
		return dp[n][k] = Math.max(recursion(n - 1, k - tour[n].walk.time) + tour[n].walk.cost,		// 걷기와 자전거 중 많은 모금액
				recursion(n - 1, k - tour[n].bike.time) + tour[n].bike.cost);
	}
}
