package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 1964번: 오각형, 오각형, 오각형...
 *
 *	@see https://www.acmicpc.net/problem/1964
 *
 */

public class Boj1964 {
	private static final long MOD = 45678;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		br.close();
		
		long[] dp = new long[N + 1];
		dp[1] = 5;
		
		for(int i = 2; i < N + 1; i++){															// 이전 오각형에 존재하는 점들의 수 + 현재 생긴 오각형에 새로운 점들만의 수
			dp[i] = (dp[1] + (dp[1] * (i - 1)) + dp[i - 1] - ((2 * i) - 1)) % MOD;	// -> 식으로 표현, 나머지 계산을 통하여(% 45678)
		}
		
		System.out.println(dp[N]);		// 결과 값 출력
	}
}
