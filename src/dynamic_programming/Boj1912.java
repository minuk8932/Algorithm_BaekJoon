package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author minchoba 1912번
 */

public class Boj1912 {
	private static final String SPACE = " ";
	private static final int MAX = 100_001;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] num = new int[MAX];
		int res = 0;

		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		for (int i = 1; i < n + 1; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		int[] dp = new int[MAX];		// [현재까지 더했을 때 또는 현재의 값 중 최대] 
		res = dp[1] = num[1];
		
		for(int i = 2; i < n + 1; i++){
			dp[i] = Math.max(num[i], num[i] + dp[i - 1]);	// 더해가며 값들을 비교함, 즉 이전까지 합이랑 다음의 값을 비교해 더 큰 것을 max에 넣어둔다.
																			// 이렇게 했을 때, 몇개씩 더했을 때 최대가 아닌 몇개를 더했는지에 대한 문제가 아닌 임의의 갯수를 더함에 따른 최대 값.
		}
		
		for(int i = 1; i < n + 1; i++){
			System.out.println(dp[i]);
			if(dp[i] > res){				// output max
				res = dp[i];
			}
		}

		System.out.println(res);
	}
}
