package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 1149번: RGB 거리
 *
 *	@see https://www.acmicpc.net/problem/1149/
 *
 */
public class Boj1149 {
	private static int[][] dp;
	private static RGB[] rgb;

	private static class RGB{
		int R;
		int G;
		int B;

		public RGB(int R, int G, int B) {
			this.R = R;
			this.G = G;
			this.B = B;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		rgb = new RGB[N + 1];
		for(int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			rgb[i] = new RGB(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		dp = new int[N + 1][4];
		for (int i = 1; i <= N; i++) {
			dp[i][0] = dp[i][1] = dp[i][2] = dp[i][3] = -1;
		}

		System.out.println(painting(N, 3));
	}

	/**
	 *
	 * Painting
	 *
	 * line 68 ~ 78: check minimum cost, by current R/G/B cases
	 *
	 * @param home
	 * @param color
	 * @return
	 */
	private static int painting(int home, int color) {
		if(home == 0) return 0;
		if(dp[home][color] != -1) return dp[home][color];
		int result = Integer.MAX_VALUE;

		if(color == 3) {
			result = Math.min(painting(home, 0)
					, Math.min(painting(home, 1), painting(home, 2)));
		}
		else {
			switch (color) {
				case 0:
					result = Math.min(painting(home - 1, 1), painting(home - 1, 2)) + rgb[home].R;
					break;
				case 1:
					result = Math.min(painting(home - 1, 0), painting(home - 1, 2)) + rgb[home].G;
					break;
				case 2:
					result = Math.min(painting(home - 1, 1), painting(home - 1, 0)) + rgb[home].B;
					break;
			}
		}

		return dp[home][color] = result;
	}
}