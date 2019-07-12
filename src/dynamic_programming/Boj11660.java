package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 11660번: 구간 합 구하기 5
 *
 *	@see https://www.acmicpc.net/problem/11660
 *
 */
public class Boj11660 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] section = new int[N + 1][N + 1];
		for(int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j < N + 1; j++) {
				section[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] dp = initMap(N, section);
		
		StringBuilder sb = new StringBuilder();
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			sb.append(dp[x2][y2] - dp[x1-1][y2] - dp[x2][y1-1] + dp[x1-1][y1-1]).append(NEW_LINE);		// 구간 합
		}
	
		System.out.print(sb.toString());
	}
	
	private static int[][] initMap(int n, int[][] arr) {		
		for(int row = 1; row < n + 1; row++) {				// 구간 합 소스 구하기
			for(int col = 1; col < n + 1; col++) {
				arr[row][col] += arr[row - 1][col] + arr[row][col - 1] - arr[row - 1][col - 1];
			}
		}
		
		return arr;
	}
}
