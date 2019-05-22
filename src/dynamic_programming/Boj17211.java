package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 17211번: 좋은 날 싫은 날
 *
 *	@see https://www.acmicpc.net/problem/17211/
 *
 */
public class Boj17211 {
	private static double[] result = new double[2];
	
	private static final int FORMAT = 1_000;
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int day = Integer.parseInt(st.nextToken());
		
		double[] after = new double[4];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++) {
			after[i] = Double.parseDouble(st.nextToken());
		}
		
		getCase(N, day, after, 0, 1);
		System.out.println(getResult());
	}
	
	private static void getCase(int n, int day, double[] poss, int count, double value) {
		double[][] dp = new double[n][4];
		
		if(day == 0) {				// 첫 날이 좋은 경우
			dp[0][0] = poss[0];
			dp[0][1] = poss[1];
			dp[0][2] = 0;
			dp[0][3] = 0;
		}
		else {						// 첫 날이 싫은 경우
			dp[0][0] = 0;
			dp[0][1] = 0;
			dp[0][2] = poss[2];
			dp[0][3] = poss[3];
		}
		
		for(int i = 1; i < n; i++) {
			dp[i][0] = (dp[i - 1][0] + dp[i - 1][2]) * poss[0];			// 0 0
			dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) * poss[1];			// 0 1
			dp[i][2] = (dp[i - 1][1] + dp[i - 1][3]) * poss[2];			// 1 0
			dp[i][3] = (dp[i - 1][1] + dp[i - 1][3]) * poss[3];			// 1 1
		}
		
		result[0] = dp[n - 1][0] + dp[n - 1][2];			// 최종 좋은날
		result[1] = dp[n - 1][1] + dp[n - 1][3];			// 최종 싫은날
	}
	
	private static StringBuilder getResult() {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < 2; i++) {
			sb.append(Math.round(result[i] * FORMAT)).append(NEW_LINE);
		}
		
		return sb;
	}
}
