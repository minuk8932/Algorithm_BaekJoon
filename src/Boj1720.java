import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj1720 {
	private static int[] dp;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		dp = new int[N + 1];
		recursion(N);
		
		int res = N % 2 == 0 ? dp[N / 2] + 2 * dp[(N - 2) / 2]: dp[(N - 1) / 2];
		System.out.println((res + dp[N]) / 2);
	}
	
	private static int recursion(int n) {
		if(dp[n] > 0) return dp[n];
		if(n == 1 || n == 0) return dp[n] = 1;
		
		return dp[n] = recursion(n - 1) + recursion(n - 2) * 2;
	}
}
