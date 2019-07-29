package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 *	백준 14650번: 걷다보니 신천역 삼 small
 *
 *	@see https://www.acmicpc.net/problem/14650/
 *
 */
public class Boj14650 {
	private static int[][] dp;
	private static int N;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N + 1][3];
		
		System.out.println(recursion(N, 0, 0, ""));
	}
	
	private static int recursion(int n, int x, int sum, String num) {
		if(num.length() >= 1) {
            if(num.charAt(0) == '0') return 0;			// 앞자리가 0으로 시작하는 경우
        }
		
		if(n == 0) {			
			if(sum % 3 == 0) return 1;					// 3의 배수
			else return 0;
		}
		
		if(dp[n][x] != 0) return dp[n][x];
		
		dp[n][x] += recursion(n - 1, 0, sum + 0, num + 0);
		dp[n][x] += recursion(n - 1, 1, sum + 1, num + 1);
		dp[n][x] += recursion(n - 1, 2, sum + 2, num + 2);
		
		return dp[n][x];
	}
}
