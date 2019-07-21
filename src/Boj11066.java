import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj11066 {
	private static long[][] dp;
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int[] files = new int[N];
			
			dp = new long[N][N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				files[i] = Integer.parseInt(st.nextToken());
			}
			
			sb.append(recursion(N, files, N - 1, N - 1)).append(NEW_LINE);			// 인접끼리만 합침
		}
		
		System.out.println(sb.toString());
	}
	
	private static long recursion(int n, int[] arr, int x, int y) {
		if(x == 0 || y == 0) return 0;
		
		if(dp[x][y] != 0) return dp[x][y];
		
		for(int i = x; i >= 0; i--) {
			for(int j = y; j >= 0; j--) {
				dp[x][y] += Math.min(recursion(n, arr, i, j), arr[x - 1] + arr[y - 1]);
			}
		}
		
		return dp[x][y];
	}
}
