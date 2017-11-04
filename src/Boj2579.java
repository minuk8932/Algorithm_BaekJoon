import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj2579 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		int[] score = new int[t + 1];

		for (int i = 1; i < t + 1; i++) {
			score[i] = Integer.parseInt(br.readLine());
		}

		// 계단 오르는 방법의 수
		int[] way = new int[t + 1];
		for (int i = 1; i < t + 1; i++) {
			if (i == 1) {
				way[1] = 1;
			}

			if (i == 2) {
				way[2] = 2;
			}

			if (i == 3) {
				way[3] = 2;
			}

			if (i == 4) {
				way[4] = 3;
			}

			if (i > 4) {
				way[1] = 1;
				way[2] = 2;
				way[3] = 2;
				way[4] = 3;

				for (int j = 5; j < t + 1; j++) {
					way[j] = way[j - 2] + way[j - 3];
				}
			}
		}

		int[][] dp = new int[t + 1][way[t] + 1];

		for (int i = 1; i < t + 1; i++) {
			if (i == 1) {
				dp[i][1] = score[i]; // 1 score[1]
			}

			if (i == 2) {
				dp[i][1] = score[i - 1] + score[i]; // 1, 2 dp[1][1] + score[2]
				dp[i][2] = score[i]; // 2 score[2]
			}

			if (i == 3) {
				dp[i][1] = score[i] + score[i - 1]; // 1, 3 dp[1][1] + score[3]
				dp[i][2] = score[i] + score[i - 2]; // 2, 3 dp[2][2] + score[3]
			}

			if (i == 4) {
				dp[i][1] = score[i] + score[i - 1] + score[i - 3]; // 1, 2, 4	 dp[2][1] + score[4]
				dp[i][2] = score[i] + score[i - 2]; 						// 2, 4 	dp[2][2] + score[4]
				dp[i][3] = score[i] + score[i - 2] + score[i - 3]; // 1, 3, 4 	dp[3][1] +  score[4]
			}

			if (i > 4) {
				int loop = (int) (way[i] / 2);
				dp[1][1] = score[1];

				dp[2][1] = score[1] + score[2];
				dp[2][2] = score[2];

				dp[3][1] = dp[1][1] + score[3];
				dp[3][2] = dp[2][2] + score[3];

				dp[4][1] = dp[2][1] + score[4];
				dp[4][2] = dp[2][2] + score[4];
				dp[4][3] = dp[3][1] + score[4];

				for (int k = 1; k < loop + 2; k++) {
					if (way[i] % 2 == 0) {
						if (k <= (way[i] / 2)) {
							dp[i][k] = dp[way[i] - 1][k] + score[i];
						} 
						else {
							dp[i][k] = dp[way[i] - 2][k] + score[i];
						}
					}

					else {
						if (k <= ((way[i] + 1) / 2)) {
							dp[i][k] = dp[way[i] - 1][k] + score[i];
						} 
						else {
							if (k < loop + 1) {
								dp[i][k] = dp[way[i] - 2][k] + score[i];
							}
						}
					}
				}

			}
		}

		int max = 0;
		for (int i = 1; i < way[t] + 1; i++) {
			max = Math.max(dp[t][i], max);
		}
		System.out.println(max);
	}

}
