package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 11726번: n x 2 타일링
 *
 *	@see https://www.acmicpc.net/problem/11726/
 *
 */
public class Boj11726 {
	private static final int SPARE = 10_007;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] tile = new int[n + 1];		// 타일 배열
		int[] dp = new int[n + 1];			// 메모이제이션
		
		if(n == 1){		// 가로의 길이가 1
			tile[1] = 1;
			dp[1] = 1;
		}
		
		if(n == 2){			// 가로의 길이가 2
			tile[2] = 2;
			dp[2] = 2;
		}
		
		
		
		if(n > 2){
			dp[1] = 1;
			dp[2] = 2;
			
			for(int i = 3; i < n+1; i++){							//	 가로의 길이가 3부터 n까지 메모이제이션을 통한 결과값, 숫자가 커지면 modulation
				dp[i] = (dp[i - 1] + dp[i - 2]) % SPARE;
			}
		}
		
		System.out.println(dp[n]);		// n 번째 타일링 경우의 수를 출력
	}
}
