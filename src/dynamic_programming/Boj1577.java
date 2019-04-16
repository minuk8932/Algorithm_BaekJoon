package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1577번: 도로의 개수
 *
 *	@see https://www.acmicpc.net/problem/1577/
 *
 */
public class Boj1577 {
	private static final int GHOST = -2;
	private static final int BLOCK = -1;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken()) * 2 + 1;			// 도로 확장
		int N = Integer.parseInt(st.nextToken()) * 2 + 1;
		int K = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(i % 2 == 1 || j % 2 == 1) map[i][j] = GHOST;		// 연산에 포함 x
			}
		}
		
		while(K-- > 0) {
			st = new StringTokenizer(br.readLine());
			int col1 = Integer.parseInt(st.nextToken()) * 2;
			int row1 = Integer.parseInt(st.nextToken()) * 2;
			int col2 = Integer.parseInt(st.nextToken()) * 2;
			int row2 = Integer.parseInt(st.nextToken()) * 2;
			
			map[(row1 + row2) / 2][(col1 + col2) / 2] = BLOCK;		// 공사중
		}
		
		System.out.println(getPath(N, M, map));
	}
	
	private static long getPath(int n, int m, int[][] arr) {
		long[][] dp = new long[n][m];
		
		for(int i = 0; i < n; i++) {
			if(arr[i][0] == BLOCK) break;			// 공사중이면 끝
			if(arr[i][0] == GHOST) continue;		// 빈공간은 값 x
			
			dp[i][0] = 1;
		}
		
		for(int i = 0; i < m; i++) {
			if(arr[0][i] == BLOCK) break;
			if(arr[0][i] == GHOST) continue;
			
			dp[0][i] = 1;
		}
		
		for(int i = 2; i < n; i += 2) {
			for(int j = 2; j < m; j += 2) {
				if(arr[i - 1][j] == BLOCK && arr[i][j - 1] == BLOCK) continue;		// 완전 가로막힌 경우
				
				if(arr[i - 1][j] == BLOCK || arr[i][j - 1] == BLOCK) {		// 한 방향만 막힌 경우
					if(arr[i - 1][j] == BLOCK) {
						dp[i][j] += dp[i][j - 2];
					}
					else if(arr[i][j - 1] == BLOCK) {
						dp[i][j] += dp[i - 2][j];
					}
				}
				else {														// 다 뚫린 경우
					dp[i][j] += dp[i - 2][j] + dp[i][j - 2];
				}
			}
		}
		
		return dp[n - 1][m - 1];
	}
}
