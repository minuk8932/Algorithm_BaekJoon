package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15924번: 욱제는 사과 팬이야!!
 *
 *	@see https://www.acmicpc.net/problem/15924/
 *
 */
public class Boj15924 {
	private static final int MOD = 1_000_000_009;
	
	private static final char EAST = 'E';
	private static final char SOUTH = 'S';
	private static final char BOTH = 'B';
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[N][M];
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
		System.out.println(path(N, M, map));
	}
	
	private static long path(int n, int m, char[][] arr) {
		long[][] dp = new long[n][m];
		dp[n - 1][m - 1] = 1;
		
		for(int i = m - 1; i >= 0; i--) {											// 도착점부터 이동 가능한 위치에 1로 흔적을 남김
			if(arr[n - 1][i] == EAST || arr[n - 1][i] == BOTH) dp[n - 1][i] = 1;
		}
		
		for(int i = n - 1; i >= 0; i--) {
			if(arr[i][m - 1] == SOUTH || arr[i][m - 1] == BOTH) dp[i][m - 1] = 1;
		}
		
		for(int i = n - 2; i >= 0; i--) {											// 역탐색 하면서 경우의 수를 더하기
			for(int j = m - 2; j >= 0; j--) {
				long sum = (arr[i][j] == EAST || arr[i][j] == BOTH ? dp[i][j + 1] : 0) +
						(arr[i][j] == SOUTH || arr[i][j] == BOTH ? dp[i + 1][j] : 0);
				
				dp[i][j] = ((dp[i][j] % MOD) + sum) % MOD;
			}
		}
		
		long total = 0;
		
		for(int i = 0; i < n; i++) {								// 전체 배열의 합 -> 모든 위치에서 이동 가능한 경우의 수
			for(int j = 0; j < m; j++) {
				total = ((total % MOD) + (dp[i][j] % MOD) % MOD);
			}
		}
		
		return total;
	}
}
