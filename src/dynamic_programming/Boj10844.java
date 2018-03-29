package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 10844번 : 쉬운계단수
 *
 *	@see https://www.acmicpc.net/problem/10844/
 *
 */
public class Boj10844 {
	private static final int DIV = 1_000_000_000;
	private static final int NUMS = 10;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		long[][] dp = new long[N + 1][NUMS];	// 인접한 숫자의 두 차이에의해 발생, 그리고 각 숫자의 범위는 0~9까지
		
		for(int i = 1; i < NUMS; i++){
			dp[1][i] = 1;
		}
		
		for(int i = 2; i < N + 1; i++){
			for(int j = 0; j < NUMS; j++){		// 각 경우의 수별로 값들을 임시 변수에 담고, 나머지값을 구해 dp 배열에 할당
				long tmp = 0;
				
				if(j + 1 <= 9){
					tmp += dp[i - 1][j + 1];
				}
				
				if(j - 1 >= 0){
					tmp += dp[i - 1][j - 1];
				}
				
				dp[i][j] = tmp % DIV;
			}
		}
		
		long res = 0;
		
		for(int i = 0; i < NUMS; i++){
			res += dp[N][i] % DIV;
		}
		
		System.out.println(res % DIV);		// 결과값 출력
	}
}
