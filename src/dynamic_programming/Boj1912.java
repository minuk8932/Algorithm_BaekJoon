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
		int[] num = new int[MAX + 1];
		int res = Integer.MIN_VALUE;

		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		for (int i = 1; i < n + 1; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		int[] dp = new int[MAX + 1];		// [i] : i에서 n 까지 합중 가장 큰 것
		int[] tmp = new int[MAX+ 1];
		
		for(int i = 1; i < n+1; i++){
			dp[i] = num[i];
			tmp[i] = num [i];
		}

		for(int i = 2; i < n + 1; i++){
			dp[1] = dp[1] + num[i];
			
			if(tmp[1] < dp[1]){
				tmp[1] = dp[1];
			}
		}
		
		for(int i = 2; i < n + 1; i++){
			for(int j = i + 1; j < n + 1; j++){
				dp[i] = dp[i] + num[j];
				
				if(tmp[i] <= dp[i]){
					tmp[i] = Math.max(dp[i], num[i]);
				}
			}
		}
		
		for(int i = 1; i < n + 1; i++){
			res = Math.max(res, tmp[i]);
		}
		
		System.out.println(res);
	}
}
