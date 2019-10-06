import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj10942 {
	private static int[][] dp;
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] num = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		isPalin(N, num);
		
		int Q = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		while(Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			sb.append(dp[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1]).append(NEW_LINE);
		}
		
		System.out.print(sb.toString());
	}
	
	private static void isPalin(int n, int[] num) {
		dp = new int[n][n];
		for(int i = 0; i < n; i++) {
			dp[i][i] = 1;
		}
		
		for(int i = 0; i < n - 1; i++) {
			if(num[i] == num[i + 1]) dp[i][i + 1] = 1;
		}
	}
}
