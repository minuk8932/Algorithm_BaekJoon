package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 10164번: 격자상의 경로
 *
 *	@see https://www.acmicpc.net/problem/10164/
 *
 */
public class Boj10164 {
	private static int[][] dp;
	
	private static class Point{
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][M];
		Point target = new Point(-1, -1);
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				int value = i * M + j + 1;
				arr[i][j] = value;
				
				if(value == K) {
					target = new Point(i, j);
				}
			}
		}
		
		System.out.println(N == 1 || M == 1 ? 1 : move(N, M, arr, target));		// N, M 둘 중 하나가 1이면 무조건 1
	}
	
	private static int move(int n, int m, int[][] arr, Point target) {
		dp = new int[n][m];
		
		if(target.x <= 0 && target.y <= 0) {			// 시작점이 목표경로 또는 목표 경로가 없는 경우
			init(1, n, true);
			init(1, m, false);
			fill(1, 1, n, m);
			
			for(int i = 1; i < n; i++) {
				for(int j = 1; j < m; j++) {
					dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
				}
			}
		}
		else {
			if(target.x == 0) {
				target.x++;
				init(target.y, m, false);		// 목표 경로가 가장 상단에 존재하는 경우
			}
			else if(target.y == 0) {			// 목표 경로가 가장 좌측에 존재하는 경우
				target.y++;
				init(target.x, n, true);
			}
			else {								// 그 외 목표 경로에서 가짓수가 1인 경우 값 입력
				init(1, target.x + 1, true);
				init(1, target.y + 1, false);
			}
			
			fill(1, 1, target.x + 1, target.y + 1);		// 목표 경로 기준으로 나누어서 가짓수 합
			fill(target.x, target.y, n, m);
		}
		
		return dp[n - 1][m - 1];
	}
	
	private static void init(int start, int end, boolean flag) {
		if(flag) {
			for(int i = start; i < end; i++) {
				dp[i][0] = 1;
			}
		}
		else {
			for(int i = start; i < end; i++) {
				dp[0][i] = 1;
			}
		}
	}
	
	private static void fill(int start1, int start2, int end1, int end2) {
		for(int i = start1; i < end1; i++) {
			for(int j = start2; j < end2; j++) {
				dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
			}
		}
	}
}
