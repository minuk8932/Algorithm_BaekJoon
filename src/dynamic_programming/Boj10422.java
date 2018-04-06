package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 10422번 : 괄호
 *
 *	@see https://www.acmicpc.net/problem/10422/
 *
 */
public class Boj10422 {
	private static final int MOD = 1_000_000_007;		// 나머지 상수
	private static final int ODD_LENGTH = 0;				// 홀수 일 경우
	private static final int INF = 5_001;						// 배열 최대 크기 상수
	
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(T-- > 0){
			int L = Integer.parseInt(br.readLine());
			
			if(L % 2 == 1){														// 괄호의 길이가 홀수인 경우, 0을 버퍼에 담고 다음 반복문 실행
				sb.append(ODD_LENGTH).append(NEW_LINE);
				
				continue;
			}
			
																	// 그 외의 경우 : Using Catalan Numbers, 공식 : Cn = (2n)! / ((n+1)! * n!)
			
			long[] dp = new long[INF];
			dp[0] = 1;											// 처음 값에 0을 담아주고
			
			for(int i = 1; i < L/2 + 1; i++){				// 짝수에 해당하는 값만 인덱스로 사용하면서 돌려줌
				dp[i] = 0;
				
				for(int j = 0; j < i; j++){					// i 까지만 반복문을 돌리며 메모이제이션 실행, 단 MOD로 나눈 나머지로 계산
					dp[i] = (dp[i] + dp[j] * dp[i - j - 1]) % MOD;
				}
			}
			
			sb.append(dp[L/2] % MOD).append(NEW_LINE);		// 해당 길이의 괄호 갯수 % MOD를 버퍼에 담아줌 
		}
		
		System.out.println(sb.toString());							// 결과값 한번에 출력 
	}
}
