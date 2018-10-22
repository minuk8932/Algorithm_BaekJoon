package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 9184번: 신나는 함수 실행
 *
 *	@see https://www.acmicpc.net/problem/9184/
 *
 */
public class Boj9184 {
	private static final String TERMINATE = "-1 -1 -1";
	private static final String NEW_LINE = "\n";
	
	private static final String FUNCTION_EXPRESSION_OPEN = "w(";
	private static final String COMMA = ", ";
	private static final String FUNCTION_EXPRESSION_CLOSE = ") = ";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int[][][] dp = new int[21][21][21];
		
		for(int i = 0; i < dp.length; i++) {
			for(int j = 0; j < dp.length; j++) {
				for(int k = 0; k < dp.length; k++) {				// 조건에 따라 0이하의 값이 들어온 경우 dp 배열에 1 저장
					if(i < 1 || j < 1 || k < 1) dp[i][j][k] = 1;
				}
			}
		}
		
		for(int i = 1; i < dp.length; i++) {				// 1 이상 20 이하의 수가 들어온 경우의 값들을 배열에 저장
			for(int j = 1; j < dp.length; j++) {
				for(int k = 1; k < dp.length; k++) {
					if(i < j && j < k) dp[i][j][k] = dp[i][j][k - 1] + dp[i][j - 1][k - 1] - dp[i][j - 1][k];
					else dp[i][j][k] = dp[i - 1][j][k] + dp[i - 1][j - 1][k] + dp[i - 1][j][k - 1] - dp[i - 1][j - 1][k - 1];
				}
			}
		}
		
		while(true) {
			String cmd = br.readLine();
			if(TERMINATE.equals(cmd)) break;			// 입력으로 종료 문자열이 들어온 경우
			
			StringTokenizer st = new StringTokenizer(cmd);
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if(a < 1 || b < 1 || c < 1) {				// 적어도 1개가 1미만의 수인 경우 1을 버퍼에 저장
				sb.append(FUNCTION_EXPRESSION_OPEN)
				.append(a).append(COMMA).append(b).append(COMMA).append(c)
				.append(FUNCTION_EXPRESSION_CLOSE)
				.append(1).append(NEW_LINE);
			}
			else if(a > 20 || b > 20 || c > 20) {		// 적어도 1개가 20보다 큰 수인 경우 dp[20][20][20]을 버퍼에 저장
				sb.append(FUNCTION_EXPRESSION_OPEN)
				.append(a).append(COMMA).append(b).append(COMMA).append(c)
				.append(FUNCTION_EXPRESSION_CLOSE)
				.append(dp[20][20][20]).append(NEW_LINE);
			}
			else {										// 그 외의 수들은 배열의 인덱스로 참조해 값을 버퍼에 저장
				sb.append(FUNCTION_EXPRESSION_OPEN)
				.append(a).append(COMMA).append(b).append(COMMA).append(c)
				.append(FUNCTION_EXPRESSION_CLOSE)
				.append(dp[a][b][c]).append(NEW_LINE);
			}
		}
		
		System.out.println(sb);		// 결과 값 한번에 출력
	}
}
