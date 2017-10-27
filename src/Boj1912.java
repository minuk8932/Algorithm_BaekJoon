import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author minchoba 1912번 : 연속 합 Runtime error!
 */

// [그들의 합 중 가장 큰 수]
public class Boj1912 {
	private static final String SPACE = " ";
	private static final int MAX = 100_000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] num = new int[MAX + 1];
		int res = Integer.MIN_VALUE;

		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		for (int i = 1; i < n + 1; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		int[] dp = new int[MAX + 1];
		Arrays.fill(dp, Integer.MIN_VALUE - 1);
		
		int k;
		for (int i = 1; i < n + 1; i++) {
			k = 2;
			for (int j = i; j < n + 1; j++) {
				dp[k] = dp[k] + num[j];
				res = Math.max(dp[j], res);
				
				k++;
			}
		}

		System.out.println(res);
	}
}
