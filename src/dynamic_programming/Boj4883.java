package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author minchoba
 * 백준 4883번: 삼각 그래프
 * 
 * @see https://www.acmicpc.net/problem/4883/
 *
 */
public class Boj4883 {
	private static final String STOP = "0";
	private static final String DOT = ". ";
	private static final String NEW_LINE = "\n";
	
	private static final long INF = Long.MAX_VALUE;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String line = "";
		int loop = 1;
		
		while(!(STOP.equals(line = br.readLine()))){
			int N = Integer.parseInt(line);
			
			long[][] graph = new long[N][3];
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				for(int j = 0; j < 3; j++) {
					graph[i][j] = Long.parseLong(st.nextToken());
				}
			}
			
			sb.append(loop++).append(DOT).append(getCost(N, graph)).append(NEW_LINE);
		}
		
		System.out.print(sb);
	}
	
	private static long getCost(int n, long[][] arr) {
		long[][] dp = new long[n][3];
		
		dp[0][1] = arr[0][1];
		dp[0][2] = dp[0][1] + arr[0][2];
		
		for(int i = 1; i < n; i++) {			// [0][0]은 [1][0], [1][1]에 영향을 줄 수 없으므로 제외하고 계산
			dp[i][0] = i == 1 ? dp[i - 1][1] + arr[i][0] : getMin(dp[i - 1][0], dp[i - 1][1], INF, INF) + arr[i][0];
			dp[i][1] = i == 1 ? getMin(dp[i][0], dp[i - 1][1], dp[i - 1][2], INF) + arr[i][1]
					: getMin(dp[i][0], dp[i - 1][0], dp[i - 1][1], dp[i - 1][2]) + arr[i][1];
			dp[i][2] = getMin(dp[i][1], dp[i - 1][1], dp[i - 1][2], INF) + arr[i][2];
		}
		
		return dp[n - 1][1];			// [0][1]에서 [n-1][1]까지 최저 비용
	}
	
	private static long getMin(long a, long b, long c, long d) {		// 최솟값 메소드
		long min1 = Math.min(a, b);
		long min2 = Math.min(c, d);
		
		return Math.min(min1, min2);
	}
}
