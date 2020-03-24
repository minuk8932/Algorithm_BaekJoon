package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 11066번: 파일 합치기
 *
 * @see https://www.acmicpc.net/problem/11066/
 *
 */
public class Boj11066 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int[] files = new int[N + 1];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= N; i++) {
				files[i] = Integer.parseInt(st.nextToken());
				files[i] += files[i - 1];							// prefix sum
			}
			
			sb.append(get(N, files)).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
	
	private static int get(int n, int[] f) {
		int[][] dp = new int[n + 1][n + 1];

		for(int i = 1; i < n; i++) {
			for(int x = 1; x + i <= n; x++) {					// [~ x - 1][y ~]
				int y = x + i;

				dp[x][y] = Integer.MAX_VALUE;
				for(int z = x; z < y; z++) {
					dp[x][y] = Math.min(dp[x][y], dp[x][z] + dp[z + 1][y] + f[y] - f[x - 1]);		// make next size segment sum
				}
			}
		}

		return dp[1][n];
	}
}
