import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Boj9252 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String stand = br.readLine();
		String comp = br.readLine();

		int lengS = stand.length();
		int lengC = comp.length();

		int[][] dp = new int[lengC + 1][lengS + 1];

		for (int i = 1; i < lengS + 1; i++) {
			if (comp.charAt(0) == stand.charAt(i - 1)) {
				dp[1][i] = dp[0][i - 1] + 1;
			} else {
				dp[1][i] = Math.max(dp[1][i - 1], dp[0][i]);
			}
		}

		for (int i = 2; i < lengC + 1; i++) {
			for (int j = 1; j < lengS + 1; j++) {
				if (comp.charAt(i - 1) == stand.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
				}
			}
		}

		int[] max = new int[lengC + 1];
		
		int[] idx = new int[lengS + 1];
		int cnt = 0;
		max[lengC] = dp[lengC][lengS];

		for (int i = lengC - 1; i > 0; i--) {
			for (int j = lengS; j > 0; j--) {
				if (max[i] < max[i + 1]) {
					if (max[i + 1] > dp[i][j]) {
						max[i] = Math.max(dp[i][j], max[i]);
					}
				}
			}
		}

		for (int i = 1; i < lengC + 1; i++) {
			LOOP: for (int j = 1; j < lengS + 1; j++) {
				if (max[i] == dp[i][j] && max[i] != 0) {
					idx[i] = j;
					cnt++;
					
					break LOOP;
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for(int i = 1; i < lengS + 1; i++){
			if(idx[i] != 0){
				sb.append(stand.charAt(idx[i] - 1));
			}
		}
		
		System.out.println(cnt);
		System.out.println(sb.toString());
	}
}
