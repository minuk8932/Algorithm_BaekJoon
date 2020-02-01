import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2240 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		int[] get = new int[T];
		for(int i = 0; i < T; i++) {
			get[i] = Integer.parseInt(br.readLine()) - 1;
		}
		
		System.out.println(memo(T, W, get));
	}
	
	private static long memo(int t, int w, int[] get) {
		long[][] dp = new long[t][2];
		
		dp[t - 1][0] = (get[t - 1] == 0 ? 1: 0);
		dp[t - 1][1] = (get[t - 1] == 1 ? 1: 0);
		
		for(int i = t - 2; i >= 0; i--) {
			dp[i][0] = Math.max(dp[i + 1][0], dp[i + 1][1]) + (get[i] == 0 ? 1: 0);
			dp[i][1] = Math.max(dp[i + 1][0], dp[i + 1][1]) + (get[i] == 1 ? 1: 0);
		}
		
		return Math.max(dp[0][0], dp[0][1]);
	}
}
