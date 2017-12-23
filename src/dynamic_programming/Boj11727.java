package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * @author minchoba
 *	백준 11727번 : nx2 타일링 2
 *	
 *	@see https://www.acmicpc.net/problem/11727/
 *
 */

public class Boj11727 {
	private static final int MOD = 10_007;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] dp = new int[n + 1];		// n : 사각형의 가로 길이
							
		dp[1] = 1;
		
		if(n >= 2){							// n이 2일 경우 경유
			dp[2] = 3;
		}
		
		for(int i = 3; i < n + 1; i++){							// n이 3 이상일 경우 : 
			dp[i] = (dp[i - 1] + dp[i - 2] * 2) % MOD;	//	이전 블럭에서 (세로길이 2 가로길이 1 인 경우) 1가지 
																		//	+ 2번째 전 블럭에서(세로 길이 1 가로 길이 2, 세로 길이 2 가로 길이 2 인 경우) 2가지
		}																//	지속적으로 MOD로 나눈 나머지를 할당 함으로써 runtime error 방지
		
		System.out.println(dp[n]);							// 결과
	}
}
