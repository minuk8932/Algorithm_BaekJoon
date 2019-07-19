package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 *	백준 15483번: 최소 편집
 *
 *	@see https://www.acmicpc.net/problem/15483/
 *
 */
public class Boj15483 {
	private static final int INF = 1_001;
	private static char[] src = new char[INF];
	private static char[] snk = new char[INF];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = {br.readLine(), br.readLine()};
		
		src = new char[input[0].length()];
		snk = new char[input[1].length()];
		
		src = init(input[0], src.length);
		snk = init(input[1], snk.length);
		
		System.out.println(count());
	}
	
	private static char[] init(String input, int leng) {
		char[] arr = new char[leng];
		int idx = 0;
		
		for(char c: input.toCharArray()) {
			arr[idx++] = c;
		}
		
		return arr;
	}
	
	private static int count() {
		int[][] dp = new int[src.length + 1][snk.length + 1];
		for(int i = 0; i < src.length + 1; i++) {				// 무 -> 유 창조
			dp[i][0] = i;
		}
		
		for(int i = 0; i < snk.length + 1; i++) {
			dp[0][i] = i;
		}
		
		for(int i = 1; i < src.length + 1; i++) {
			for(int j = 1; j < snk.length + 1; j++) {
				if(src[i - 1] == snk[j - 1]) dp[i][j] = dp[i - 1][j - 1];								// LCS 구성 성분인 경우
				else dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;	// 그 외 수정, 추가, 삭제
			}
		}

		return dp[src.length][snk.length];
	}
}
