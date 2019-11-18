import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj17494 {
	private static final String SORRY = "죄송합니다 한승엽 병장님";
	
	private static class Food{
		int full;
		int satisfy;
		
		public Food(int full, int satisfy) {
			this.full = full;
			this.satisfy = satisfy;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Food[] food = new Food[N + 1];
		int sum = 0;
		
		for(int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			int W = Integer.parseInt(st.nextToken());
			int H = Integer.parseInt(st.nextToken());
			
			food[i] = new Food(W, H);
			sum += W;
		}
		
		System.out.println(sum < M ? SORRY: filling(N, M, food));
	}
	
	private static int filling(int n, int m, Food[] arr) {
		int min = Integer.MAX_VALUE;
		
		int[][] dp = new int[n + 1][100_001];
		
		for(int i = 1; i < n + 1; i++) {
			int diff = dp.length - arr[i].full;
			dp[1][diff] = arr[i].satisfy;
			
			if(diff <= dp.length - m) min = Math.min(min, dp[1][diff]);
		}
		
		for(int i = 2; i < n + 1; i++) {
			for(int j = dp.length; j >= 0; j--) {
				int diff = j - arr[i].full;
				if(diff < 0) continue;
				
				dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][diff] + arr[i].satisfy);
				if(diff <= dp.length - m) min = Math.min(min, dp[i][j]);
			}
		}
		
		return min;
	}
}
