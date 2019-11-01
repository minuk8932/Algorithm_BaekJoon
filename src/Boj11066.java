import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj11066 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int[] files = new int[N + 1];
			int[] sum = new int[N + 1];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 1; i < N + 1; i++) {
				files[i] = Integer.parseInt(st.nextToken());
				sum[i] = sum[i - 1] + files[i];
			}
			
			sb.append(get(N, files, sum)).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
	
	private static long get(int n, int[] arr, int[] sum) {
		long[][] dp = new long[n + 1][n + 1];
		
		for(int i = 1; i < n; i++) {
			for(int x = 1; x + i <= n; x++) {
				int y = x + i;
				
				dp[x][y] = Long.MAX_VALUE;
				for(int z = x; z < y; z++) {
					dp[x][y] = Math.min(dp[x][y], dp[x][z] + dp[z + 1][y] + sum[y] - sum[x - 1]); 
				}
			}
		}
		
		return dp[1][n];
	}
}
