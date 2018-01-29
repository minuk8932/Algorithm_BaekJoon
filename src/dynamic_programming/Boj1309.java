package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	
 *	백준 1309번 : 동물원
 *
 *	@see https://www.acmicpc.net/problem/1309
 *
 */
public class Boj1309 {
	private static final int MOD = 9901;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		br.close();
		
		int[] dp = new int[N + 1];
		
		if(N == 1){
			dp[1] = 3;
		}
		
		if(N == 2){
			dp[1] = 3;
			dp[2] = dp[1] + 4;
		}
		
		if(N > 2){
			dp[1] = 3;
			dp[2] = dp[1] + 4;
			
			for(int i = 3; i < N + 1; i++){
				dp[i] = ((dp[i - 1] * 2) + dp[i - 2]) % MOD;			// 사자의 배치에 따라
																					// X X ---- 1)									dp[n - 1]					: 맨 상단에 사자가 없는 경우 (즉, 이전 dp에 정의되어있는 가짓 수와 같음)
																					// X O | ??, O X | ?? ---- 2)				dp[n - 1] * 2				: 맨 상단에 1마리의 사자만 배치 할 경우 (이전 dp와 같은 가짓수가 2개)
																					// X O | X O, O X | O X ---- 3)			dp[n - 1] - dp[n - 2]	: 맨 상단의 2칸의 우리를 1칸의 우리로 보고 배치할 때, 둘중 한 우리에는 사자가 있을 경우
																					// 결과 : 1) + 2) - 3) = dp[n - 1] * 2 + dp[n - 2]
			}
		}
		
		System.out.println(dp[N]);										// 결과 값 출력
	}
}
