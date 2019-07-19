package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 17208번: 카우버거 알바생
 *
 *	@see https://www.acmicpc.net/problem/17208/
 *
 */
public class Boj17208 {
	private static int[][][] dp;
	private static Order[] or;
	
	private static class Order{
		int burger;
		int fry;
		
		private Order(int burger, int fry) {
			this.burger = burger;
			this.fry = fry;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		or = new Order[N];
		dp = new int[N][M+ 1][K + 1];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			or[i] = new Order(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		System.out.println(recursion(N - 1, M, K));
	}
	
	private static int recursion(int n, int m, int k) {
		if(m < 0 || k < 0 || n == -1) return 0;
		if(dp[n][m][k] != 0) return dp[n][m][k];
		
		int flag = 1;
		if(m < or[n].burger || k < or[n].fry) flag = 0;		// 주문 처리 전 수량이 부족한 경우
		// 현재 해당 주문을 처리한 것과 처리하지 않고 스킵하는 것 중 최댓값
		return dp[n][m][k] = Math.max(recursion(n - 1, m - or[n].burger, k - or[n].fry) + flag, recursion(n - 1, m, k));
	}
}
