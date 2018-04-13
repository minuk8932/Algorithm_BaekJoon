package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 13301번 : 타일 장식물
 *
 *	@see https://www.acmicpc.net/problem/13301/
 *
 */
public class Boj13301 {
	private static int INF = 81;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		long[][] dp = new long[2][INF];
		
		dp[0][1] = 1;
		dp[1][1] = 4;
		
		for(int i = 2; i < N + 1; i++){
			dp[0][i] = dp[0][i - 1] + dp[0][i - 2];				// 각 사각형 별 변의 길이를 Dynamic programming을 통해 계산 후
			dp[1][i] = dp[0][i] * 4 + dp[0][i - 1] * 2;			//	규칙에 따라서 순서별로 둘레를 구해줌
		}
		
		System.out.println(dp[1][N]);							// 최종 N번째 사각형의 둘레 출력
	}
}
