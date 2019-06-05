package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 17265번: 나의 인생에는 수학과 함께
 *
 *	@see https://www.acmicpc.net/problem/17265/
 *
 */
public class Boj17265 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		char[][] map = new char[N][N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++) {
				map[i][j] = st.nextToken().charAt(0);
			}
		}
		
		getMinMax(N, map);
	}
	
	private static void getMinMax(int n, char[][] arr) {
		int[][][] dp = new int[n][n][2];
		dp[0][0][0] = dp[0][0][1] = arr[0][0] - '0';
		
		for(int i = 1; i < n; i ++) {
			if(i % 2 == 0) {
				dp[0][i][0] = dp[0][i][1] = getValue(arr[0][i] - '0', arr[0][i - 1], dp[0][i - 2][0]);
				dp[i][0][0] = dp[i][0][1] = getValue(arr[i][0] - '0', arr[i - 1][0], dp[i - 2][0][0]);
			}
			else {
				dp[0][i][0] = dp[0][i][1] = dp[0][i - 1][0];
				dp[i][0][0] = dp[i][0][1] = dp[i - 1][0][0];
			}
		}
		
		for(int i = 1; i < n; i++) {
			for(int j = 1; j < n; j++) {
				int sum = i + j;
				
				if(sum % 2 == 0) {					// 현 위치에 값이 존재하는 경우
					dp[i][j][0] = Math.min(getValue(arr[i][j] - '0', arr[i][j - 1], dp[i][j - 1][0]), 
							getValue(arr[i][j] - '0', arr[i - 1][j], dp[i - 1][j][0]));
	
					dp[i][j][1] = Math.max(getValue(arr[i][j] - '0', arr[i][j - 1], dp[i][j - 1][1]), 
							getValue(arr[i][j] - '0', arr[i - 1][j], dp[i - 1][j][1]));
				}
				else {								// 현 위치에 수식이 존재하는 경우
					dp[i][j][0] = Math.min(dp[i][j - 1][0], dp[i - 1][j][0]);
					dp[i][j][1] = Math.max(dp[i][j - 1][1], dp[i - 1][j][1]);
				}
			}
		}
		
		System.out.println(dp[n - 1][n - 1][1] + " " + dp[n - 1][n - 1][0]);		// 최대, 최소
	}
	
	private static int getValue(int value , char f, int prev) {			// 수식이 존재할때 값 계산
		switch (f) {
		case '+':
			prev += value;
			break;
			
		case '-':
			prev -= value;
			break;
			
		case '*':
			prev *= value;
			break;
		}
		
		return prev;
	}
}
