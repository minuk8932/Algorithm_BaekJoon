package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 2749번: 피보나치 수 3
 *
 *	@see https://www.acmicpc.net/problem/2749/
 *
 */
public class Boj2749 {
	private static final int MOD = 1_000_000;
	private static final int PERIOD = 1_500_000;

	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long n = Long.parseLong(br.readLine());
		int[] dp = new int[3];
		
		dp[0] = 0;
		dp[1] = 1;
		n %= PERIOD;	// MOD에 해당하는 피사노 주기
		
		for (int i = 0; i < n - 1; i++) {		// 피사노 주기에 따른 값을 공식을 통해 구함
            dp[(i + 2) % dp.length] = (dp[(i + 1) % dp.length] + dp[i % dp.length]) % MOD;
        }
		
		System.out.println(dp[(int) n % 3] % MOD);		// 해당하는 결과 값을 출력
	}
}
