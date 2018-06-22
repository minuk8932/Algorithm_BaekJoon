package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 15624번: 피보나치 수 7
 *
 *	@see https://www.acmicpc.net/problem/15624/
 *
 */
public class Boj15624 {
	private static final int MOD = 1_000_000_007;
	private static final int INF = 1_000_001;
	private static final int[] fibo = new int[INF];
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		fibo[0] = 0;
		fibo[1] = 1;
		
		for(int i = 2; i < N + 1; i++) {				// DP 실행
			fibo[i] = (fibo[i - 1] + fibo[i - 2]) % MOD;
		}
		
		System.out.println(fibo[N]);		// N번째 피보나치 수 출력
	}
}
