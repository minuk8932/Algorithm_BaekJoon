package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj2156 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int[] grape = new int[n + 1];

		for (int i = 1; i < n + 1; i++) {
			grape[i] = Integer.parseInt(br.readLine());
		}

		int[][] dp = new int[n + 1][3];			// [][0] : 해당 포도주를 마시지 x, [][1] : 해당 포도주를 2번째로, [][2] : 해당 포도주가 마지막 선택일 경우

		if (n == 1) {
			dp[1][0] = 0;
			dp[1][1] = grape[1];
			dp[1][2] = grape[1];
		}

		if (n >= 2) {
			dp[1][0] = 0;
			dp[1][1] = dp[1][2] = grape[1];

			for (int i = 2; i < n + 1; i++) {
				int tmp = Math.max(dp[i - 1][1], dp[i - 1][2]);
				dp[i][0] = Math.max(tmp, dp[i - 1][2]);
				
				dp[i][1] = grape[i] + Math.max(dp[i - 1][2], dp[i - 1][0]);
				
				tmp = Math.max(dp[i - 2][0], dp[i - 2][1]);
				dp[i][2] = grape[i] + Math.max(tmp, dp[i-2][2]);
			}
		}
		
		int res = grape[1];
		for(int i = 1; i < n+1; i++){
			res = Math.max(dp[i][0], dp[i][1]);
			res = Math.max(res, dp[i][2]);
		}
		
		System.out.println(res);
	}
}
