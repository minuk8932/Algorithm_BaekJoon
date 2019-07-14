import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj2718 {
	private static long[] dp = new long[32];
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		recursion();
		
		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			sb.append(N % 2 == 0 ? dp[N] - 1: dp[N]).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
	
	private static void recursion() {		
		dp[0] = 1;
		dp[1] = 1;
		
		for(int i = 2; i < dp.length; i++) {
			dp[i] = dp[i - 1] + dp[i - 2] * 5;
		}
	}
}
