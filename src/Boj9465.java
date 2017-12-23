import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj9465 {
	private static final int LINE = 2;

	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();

		while (T-- > 0) {
			int n = Integer.parseInt(br.readLine());
			int[][] stkr = new int[LINE + 1][n + 1];

			for (int i = 1; i < LINE + 1; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
				for (int j = 1; j < n + 1; j++) {
					stkr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int[][] dp = new int[LINE + 1][n + 1];
			
			dp[1][1] = stkr[1][1];
			dp[2][1] = stkr[2][1];

			if (n >= 2) {
				dp[1][2] = stkr[1][2] + stkr[2][1];
				dp[2][2] = stkr[2][2] + stkr[1][1];
			}
            
            if(n > 2){
			    for (int i = 3; i < n + 1; i++) {
				    dp[1][i] = Math.max(dp[2][i - 1], dp[2][i - 2]) + stkr[1][i];
				    dp[2][i] = Math.max(dp[1][i - 1], dp[1][i - 2]) + stkr[2][i];
			    }
            }
        
			int res = 0;
			for (int i = 1; i < n + 1; i++) {
				res = Math.max(dp[1][i], dp[2][i]);
			}
			
			sb.append(res).append(NEW_LINE);
		}

		System.out.println(sb.toString());
	}
}
