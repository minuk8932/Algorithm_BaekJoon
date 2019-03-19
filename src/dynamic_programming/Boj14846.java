package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14846 {
	private static final String NEW_LINE = "\n";
	private static final int INF = 11;
	
	private static int[][][] dp;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[N + 1][N + 1];
		for(int i = 1; i < N + 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 1; j < N + 1; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp = new int[N + 1][N + 1][INF];
		process(N, arr);
		
		int Q = Integer.parseInt(br.readLine());
		while(Q-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] coordinate = {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())
					, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			
			sb.append(countArea(coordinate)).append(NEW_LINE);
		}
		
		System.out.print(sb);
	}
	
	private static void process(int n, int[][] arr) {
		for(int i = 1; i < n + 1; i++) {
			for(int j = 1; j < n + 1; j++) {
				for(int k = 1; k < INF; k++) {
					dp[i][j][k] += (dp[i - 1][j][k] + dp[i][j - 1][k] - dp[i - 1][j - 1][k]);	// 0부터 각 칸까지의 숫자의 갯수
				}
				
				dp[i][j][arr[i][j]]++;			// i, j에서 arr[i][j]의 값을 가지는 수의 갯수
			}
		}
	}
	
	private static int countArea(int[] coor) {			// coor[0]: x1, coor[1]: y1, coor[2]: x2, coor[3]: y2
		int[] count = new int[INF];
		
		for(int i = 1; i < INF; i++) {					// 해당 범위 전체 사각형에서 i값을 가지는 수의 갯수
			count[i] = dp[coor[2]][coor[3]][i];
		}
		
		for(int i = 1; i < INF; i++) {					// 전체 범위를 쿼리에 맞게 구역 제한	
			count[i] += dp[coor[0] - 1][coor[1] - 1][i] - (dp[coor[0] - 1][coor[3]][i] + dp[coor[2]][coor[1] - 1][i]);
		}
		
		int result = 0;
		for(int i = 1; i < INF; i++) {
			if(count[i] > 0) result++;
		}
		
		return result;
	}
}
