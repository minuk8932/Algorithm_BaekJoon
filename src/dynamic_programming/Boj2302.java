package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 2302번: 극장 좌석
 *
 *	@see https://www.acmicpc.net/problem/2302/
 *
 */
public class Boj2302 {
	private static int[] dp = new int[41];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		fibo();
		
		int res = 1;
		int norm = 0;
		
		while(M-- > 0) {
			int vip = Integer.parseInt(br.readLine());		// vip좌석 기준으로 각 위치별 경우의 수 (동시)
			res *= dp[vip - norm - 1];
			norm = vip;
		}
		
		System.out.println(res * dp[N - norm]);
	}
	
	private static void fibo() {
		dp[0] = dp[1] = 1;
		dp[2] = 2;
		
		for(int i = 3; i < dp.length; i++) {		// vip좌석이 없을 땐 피보나치 수열
			dp[i] = dp[i - 1] + dp[i - 2];
		}
	}
}
