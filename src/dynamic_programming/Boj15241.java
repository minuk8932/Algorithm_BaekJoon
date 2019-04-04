package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15241번: Counting Paths
 *
 *	@see https://www.acmicpc.net/problem/15241/
 *
 */
public class Boj15241 {
	private static final int MOD = 1_000_000_007;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		boolean[][] obstacle = new boolean[N][M];
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			for(int j = 0; j < M; j++) {
				if(line.charAt(j) == 'X') obstacle[i][j] = true;
			}
		}
		
		System.out.println(countPaths(N, M, obstacle));
	}
	
	private static long countPaths(int n, int m, boolean[][] arr) {
		long[][] dp = new long[n][m];
		dp[0][0] = 1;
		
		int way = 1;
		for(int i = 0; i < m; i++) {		// 경로가 막혀있으면 이후엔 해당 경로를 사용하지 못함
			if(arr[0][i]) way = 0;
			dp[0][i] = way;
		}
		
		way = 1;
		for(int i = 0; i < n; i++) {
			if(arr[i][0]) way = 0;
			dp[i][0] = way;
		}
		
		for(int i = 1; i < n; i++) {
			for(int j = 1; j < m; j++) {		// 총 경로 구하기
				if(arr[i][j]) continue;
				dp[i][j] = ((dp[i - 1][j] % MOD) + (dp[i][j - 1] % MOD)) % MOD;
			}
		}
		
		
		return dp[n - 1][m - 1];
	}
}
