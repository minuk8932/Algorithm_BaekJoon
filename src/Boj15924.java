import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj15924 {
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
		long[][][] dp = new long[2][n][m];
		
		char tmp = arr[0][0];
		dp[0][0][0] = tmp == 'E' || tmp == 'B' ? 1: 0;
		dp[1][0][0] = tmp == 'S' || tmp == 'B' ? 1: 0;
		
		for(int i = 1; i < m; i++) {
			char way = arr[0][i];
			
			dp[0][0][i] = (way == 'E' || way == 'B' ? 1: 0) + dp[0][0][i - 1];
			dp[1][0][i] = 0;
		}
		
		return dp[0][n - 1][m - 1] + dp[1][n - 1][m - 1];
	}
}
