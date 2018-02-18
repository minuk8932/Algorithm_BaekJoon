package realization;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 1788번 : 피보나치 수의 확장
 *
 *	@see https://www.acmicpc.net/problem/1788
 *
 */
public class Boj1788 {
	private static final int MOD = 1_000_000_000;
	private static final int MAX = 1_000_001;
	
	private static final int ZERO = 0;
	private static final int POSITIVE = 1;
	private static final int NEGATIVE = -1;
	
	private static final String NEXT = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		br.close();
		
		int[] dp = new int[MAX];
		dp[0] = 0;
		dp[1] = 1;
		
		int absN = Math.abs(N);
		
		for(int i = 2; i < absN + 1; i++){					// 피보나치 수 입력
			dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
		}
		
		if(N > 0){																		// 양수일 경우
			System.out.println(POSITIVE + NEXT + dp[N]);
		}
		else if(N < 0){																// 음수일 경우
			if(absN % 2 == 0){															// 음수면서 해당 수가 짝수인 경우 (dp[N] < 0)
				System.out.println(NEGATIVE + NEXT + dp[absN]);
			}
			else{																				// 음수면서 해당 수가 홀수인 경우 (dp[N] > 0)
				System.out.println(POSITIVE + NEXT + dp[absN]);
			}
		}
		else{																				// 0일 경우
			System.out.println(ZERO + NEXT + ZERO);
		}
	}
}
