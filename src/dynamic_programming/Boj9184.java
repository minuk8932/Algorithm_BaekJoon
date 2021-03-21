package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
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
	private static long[][][] dp = new long[21][21][21];

	private static final String FUNC_PREV = "w(";
	private static final String FUNC_POST = ") = ";
	private static final String COMMA = ", ";
	private static final String NEW_LINE = "\n";

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		init();

		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			if(a == -1 && b == -1 && c == -1) break;

			sb.append(FUNC_PREV).append(a).append(COMMA).append(b).append(COMMA).append(c).append(FUNC_POST)
					.append(underValue(a, b, c) ? 1: overValue(a, b, c) ? dp[20][20][20]: dp[a][b][c]).append(NEW_LINE);
		}

		System.out.println(sb.toString());
	}

	private static boolean overValue(int a, int b, int c) {
		return a > 20 || b > 20 || c > 20;
	}

	private static long recursion(int a, int b, int c) {
		if(a <= 0 || b <= 0 || c <= 0) return 1;

		if(dp[a][b][c] != -1) return dp[a][b][c];
		long result;

		if(a < b && b < c) result = recursion(a, b, c - 1) + recursion(a, b - 1, c - 1) - recursion(a, b - 1, c);
		else result = recursion(a - 1, b, c) + recursion(a - 1, b - 1, c) + recursion(a - 1, b, c - 1) - recursion(a - 1, b - 1, c - 1);

		return dp[a][b][c] = result;
	}

	private static boolean underValue(int a, int b, int c) {
		return a <= 0 || b <= 0 || c <= 0;
	}

	/**
	 *
	 * init
	 *
	 * line 78 ~ 84: find all cases
	 *
	 */
	private static void init() {
		for(int i = 0; i <= 20; i++) {
			for(int j = 0; j <= 20; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}

		for(int a = 20; a >= 0; a--) {
			for(int b = 20; b >= 0; b--) {
				for(int c = 20; c >= 0; c--) {
					recursion(a, b, c);
				}
			}
		}
	}
}
