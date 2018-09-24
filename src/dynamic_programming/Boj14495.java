package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 14495번: 피보나치 비스무리한 수열
 *
 *	@see https://www.acmicpc.net/problem/14495/
 *
 */
public class Boj14495 {
	private static final int INF = 120;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		long[] dp = new long[INF];
		dp[1] = dp[2] = dp[3] = 1;
		
		for(int i = 4; i < n + 1; i++) {		// 피보나치 비스무리 수열 공식 적용
			dp[i] = dp[i - 1] + dp[i - 3];
		}
		
		System.out.println(dp[n]);		// n번째 피보나치수 출력
	}
}
