package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 2747번 : 피보나치수
 *
 *	@see https://www.acmicpc.net/problem/2747/
 *
 */
public class Boj2747 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		long[] dp = new long[46];				// 최대 크기 +1 만큼 배열 크기 지정
		
		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 1;
		
		for(int i = 3; i < n + 1; i++){				// DP 알고리즘 실행
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		
		System.out.println(dp[n]);				// n번째 피보나치 수 출력
	}
}
