package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 8394번: 악수
 *
 *	@see https://www.acmicpc.net/problem/8394/
 *
 */
public class Boj8394 {
	private static final int MOD = 10;
	private static final int INF = 10_000_001;
	
	private static int[] dp = new int[INF];
	
	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		dp[1] = 1;
		dp[2] = 2;

		for (int i = 3; i < n + 1; i++) {					// 피보나치 수열에 따른 값을 1의 자리만 구함
			dp[i] = (dp[i - 1] % MOD + dp[i - 2] % MOD);
		}

		System.out.println(dp[n] % MOD);		// 결과값 출력

	}
}
