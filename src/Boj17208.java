import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj17208 {
	private static int N, M, K;
	private static int[][][] dp;
	
	private static class Order{
		int burger;
		int fry;
		
		private Order(int burger, int fry) {
			this.burger = burger;
			this.fry = fry;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		Order[] or = new Order[N];
		dp = new int[N][M + 1][K + 1];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			or[i] = new Order(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		getMaxOrder(or, 0, 0, 0);
		
		int max = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M + 1; j++) {
				for(int k = 0; k < K + 1; k++) {
					System.out.print(dp[i][j][k] + " ");
					if(dp[i][j][k] > max) max = dp[i][j][k];
				}
				System.out.println();
			}
			System.out.println();
		}
		
		System.out.println(max);
	}
	
	private static int getMaxOrder(Order[] arr, int idx, int b, int f) {
		if(b > M || f > K) return 0;
		if(dp[idx][b][f] != 0) return 0;
		dp[idx][b][f] = 1;
		
		for(int x = idx; x < N; x++) {
			for(int y = b; y < M + 1; y++) {
				for(int z = f; z < K + 1; z++) {
					dp[x][y][z] = getMaxOrder(arr, x, y + arr[x].burger, z + arr[x].fry) + dp[idx][b][f];
				}
			}
		}
		
		return dp[idx][b][f];
	}
}
