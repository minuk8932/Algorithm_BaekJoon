package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 12865번: 평범한 배낭
 *
 *	@see https://www.acmicpc.net/problem/12865/
 *
 */
public class Boj12865 {	
	private static class Sack{
		int w;
		int c;
		
		public Sack(int w, int c) {
			this.w = w;
			this.c = c;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		Sack[] map = new Sack[N + 1];
		map[0] = new Sack(-1, -1);
		
		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			int weigh = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());

			map[i] = new Sack(weigh, value);
		}
	
		System.out.println(search(N, K, map));
	}
	
	private static int search(int N, int W, Sack[] knap) {
		int[][] dp = new int[N + 1][W + 1];
		
		for(int i = 1; i < N + 1; i++) {
			for(int j = 0; j < W + 1; j++) {
				if(j < knap[i].w) {					// j보다 i번째 무게가 더 무거운 경우: 여유 공간이 없다.
					dp[i][j] = dp[i - 1][j];		// 이전 값으로 유지
					continue;
				}
				
				dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - knap[i].w] + knap[i].c);	// 다음에 얻을 수 있는 최대 이익
			}
		}
		
		return dp[N][W];
	}
}
