package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 *	백준 2718번: 타일 채우기
 *
 *	@see https://www.acmicpc.net/problem/2718/
 *
 */
public class Boj2718 {
	private static int[] dp = new int[25];
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		filling();
		
		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			sb.append(dp[N]).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
	
	private static void filling() {		
		dp[0] = dp[1] = 1;
		
		for (int i = 2; i < dp.length; i++) {
			int tmp = dp[i - 2] * 4 + dp[i - 1];
		 
			for (int j = 3; j <= i; j++) {				// 타일 채우기 3가지 경우
				if (j % 2 == 1) tmp += dp[i - j] * 2;
				else tmp += dp[i - j] * 3;
			}
			
			dp[i] = tmp;
		}
	}
}
