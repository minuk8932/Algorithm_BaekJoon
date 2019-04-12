import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj15924 {
	private static final int MOD = 1_000_000_009;
	
	private static final char EAST = 'E';
	private static final char SOUTH = 'S';
	private static final char BOTH = 'B';
	private static final char DESTINATION = 'X';
	
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
		dp[0][0] = 1;
		
		for(int i = 1; i < m; i++) {
			
		}
		
		for(int i = 1; i < n; i++) {
			
		}
		
		for(int i = 1; i < n; i++) {
			for(int j = 1; j < m; j++) {
				
			}
		}
		
//		for(int i = 0; i < n; i++) {
//			for(int j = 0; j < m; j++) {
//				System.out.print(dp[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		return dp[n - 1][m - 1] % MOD;
	}
}
