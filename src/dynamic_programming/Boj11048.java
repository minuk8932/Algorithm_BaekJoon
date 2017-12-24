package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author minchoba
 *	백준 11048번 : 이동하기
 *
 *	@see https://www.acmicpc.net/problem/11048
 *
 */
public class Boj11048 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		
		int N = Integer.parseInt(st.nextToken());					// 행
		int M = Integer.parseInt(st.nextToken());				// 열
		
		int[][] path = new int[N + 2][M + 2];
		
		for(int i = 1; i < N + 1; i++){
			st = new StringTokenizer(br.readLine(), SPACE);
			
			for(int j = 1; j < M + 1; j++){
				path[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		br.close();
		
		int[][] dp = new int[N + 1][M + 1];
		
		
		for(int i = 1; i < N + 1; i ++){
			for(int j = 1; j < M + 1; j++){
				dp[i][j] = threeWaysMax(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + path[i][j];		// 3방향에서 오는 값 들의 최대 값을 직접 정의한 함수로 찾아냄
				
			}
		}
		System.out.println(dp[N][M]);				// 결과값 출력
	}
	
	private static int threeWaysMax(int a, int b, int c){		// 값 3개를 동시에 비교 (최대 값 도출)
		return Math.max(a, Math.max(b, c));
	}
}
