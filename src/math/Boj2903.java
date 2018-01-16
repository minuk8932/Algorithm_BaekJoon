package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 2903번: 중앙 이동 알고리즘
 *
 *	@see https://www.acmicpc.net/problem/2903
 *
 */

public class Boj2903 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		br.close();
		
		int[] dp = new int[N + 1];
		dp[0] = 4;
		
		for(int i = 1; i < N + 1; i++){						// i 번째 값(y라 가정), i - 1 번째 결과 값(x라 가정) y = (( 2 * (x^(1/2)) ) - 1)^2 이므로
			int sqrt = (int) Math.sqrt(dp[i - 1]);		// * 계산식 정의
			int raw = sqrt + sqrt - 1;
			dp[i] = (int)Math.pow(raw, 2);
		}
		
		System.out.println(dp[N]);						// 결과값 출력
	}
}
