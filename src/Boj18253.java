import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj18253 {
	private static long[][] dp;
	
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		makePath(N, M, arr);
		
		StringBuilder sb = new StringBuilder(); 
		int Q = Integer.parseInt(br.readLine());
		
		while(Q-- > 0) {
			
		}
	}
	
	private static void makePath(int n, int m, int[][] arr) {		// 4 방향 다 고려해야함.
		dp = new long[n][m];
		dp[0][0] = arr[0][0];
		
		for(int i = 1; i < n; i++) {
			dp[i][0] = dp[i - 1][0] + arr[i][0];
		}
		
		for(int i = 1; i < m; i++) {
			dp[0][i] = dp[0][i - 1] + arr[0][i];
		}
		
		for(int i = 1; i < n; i++) {
			for(int j = 1; j < m; j++) {
				dp[i][j] = Math.min(dp[i - 1][j], dp[j][i - 1]) + arr[i][j];
			}
		}
	}
}
