import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj17208 {
	private static int N, M, K;
	
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
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			or[i] = new Order(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		System.out.println(getMaxOrder(or));
	}
	
	private static int getMaxOrder(Order[] arr) {
		int[][][] dp = new int[N][M + 1][K + 1];
		int max = 0;
		
		for(int i = 0; i < N; i++) {
			int b = arr[i].burger <= M ? 1: 0;
			int f = arr[i].fry <= K ? 1: 0;
			
			if(b == 0 || f == 0) continue;
			dp[0][arr[i].burger][arr[i].fry] = 1;
		}
		
		for(int x = 0; x < N; x++) {
			for(int y = 0; y < M + 1; y++) {
				for(int z = 0; z < K + 1; z++) {
					
					int curB = arr[x].burger + y;
					int curF = arr[x].fry + z;
					int b = curB <= M ? 1: 0;
					int f = curF <= K ? 1: 0;
					
					if(b == 0 || f == 0) dp[x][y][z] = dp[x - 1][y][z];
					else dp[x][curB][curF] = dp[x - 1][y][z] + 1;
				}
			}
		}
		
		for(int x = 0; x < N + 1; x++) {
			for(int y = 0; y < M + 1; y++) {
				for(int z = 0; z < K + 1; z++) {
					System.out.print(dp[x][y][z] + " ");
					if(dp[x][y][z] > max) max = dp[x][y][z];
				}
				System.out.println();
			}
			System.out.println();
		}
		
		return max;
	}
}
