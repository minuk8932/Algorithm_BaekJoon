package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 12865번: 평범한 배낭
 *
 *	@see https://www.acmicpc.net/problem/12865/
 *
 */
public class Boj12865 {

	private static Item[] items;
	private static int[][] dp;
	private static int N;

	private static class Item {
		int weight;
		int value;

		public Item(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		items = new Item[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			items[i] = new Item(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		dp = new int[N][K + 1];
		for(int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i], -1);
		}

		System.out.println(recursion(N - 1, K));
	}

	/**
	 *
	 * Recursion
	 *
	 * line 66: next napsack not select
	 * line 68 ~ 70: next napsack select
	 *
	 * @param n
	 * @param k
	 * @return
	 */
	private static int recursion(int n, int k) {
		if(n < 0) return 0;

		if(dp[n][k] != -1) return dp[n][k];
		int result = recursion(n - 1, k);

		if(items[n].weight <= k) {
			result = Math.max(result, recursion(n - 1, k - items[n].weight) + items[n].value);
		}

		return dp[n][k] = result;
	}
}
