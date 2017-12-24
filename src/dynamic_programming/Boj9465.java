package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author minchoba
 *	백준 9465번 : 스티커
 *
 *	@see https://www.acmicpc.net/problem/9465
 *
 */

public class Boj9465 {
	private static final int LINE = 2;			// 스티커의 행수(고정)

	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";

	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();

		while (T-- > 0) {											// 테스트 케이스
			int n = Integer.parseInt(br.readLine());
			int[][] stkr = new int[LINE + 1][n + 1];			// 스티커 배치를 나타낼 배열

			for (int i = 1; i < LINE + 1; i++) {														// 스티커 값을 공백으로 구분해 잘라서 입력
				StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
				for (int j = 1; j < n + 1; j++) {
					stkr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int[][] dp = new int[LINE + 1][n + 1];
			
			dp[1][1] = stkr[1][1];				// 1열의 스티커 값
			dp[2][1] = stkr[2][1];

			if (n >= 2) {
				dp[1][2] = stkr[1][2] + stkr[2][1];		// 2열의 스티커 값
				dp[2][2] = stkr[2][2] + stkr[1][1];
			}
            
            if(n > 2){
			    for (int i = 3; i < n + 1; i++) {												// 3열 이상 스티커 값
				    dp[1][i] = Math.max(dp[2][i - 1], dp[2][i - 2]) + stkr[1][i];		//		i번째 스티커가 1행으로 끝날 시 총 제거된 스티커들의 최대 값 
				    dp[2][i] = Math.max(dp[1][i - 1], dp[1][i - 2]) + stkr[2][i];		//		i번째 스티커가 2행으로 끝날 시 총 제거된 스티커들의 최대 값
			    }
            }
        
			int res = 0;
			for (int i = 1; i < n + 1; i++) {						// dp 배열에 저장된 값들 중 최대 값을 res 변수에 할당
				res = Math.max(dp[1][i], dp[2][i]);
			}
			
			sb.append(res).append(NEW_LINE);
		}

		System.out.println(sb.toString());				// 결과 값 출력
	}
}
