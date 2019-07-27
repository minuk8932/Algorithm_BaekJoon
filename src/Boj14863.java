import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj14863 {
	private static long[][] dp;
	
	private static class Volunteer{
		int time;
		int cost;
		
		public Volunteer(int time, int cost) {
			this.time = time;
			this.cost = cost;
		}
	}
	
	private static class Method{
		Volunteer walk;
		Volunteer bike;
		
		public Method(Volunteer walk, Volunteer bike) {
			this.walk = walk;
			this.bike = bike;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Method[] tour = new Method[N + 1];
		for(int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			tour[i] = new Method(new Volunteer(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())),
					new Volunteer(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		dp = new long[N + 1][K + 1];
		System.out.println(getCost(N, K, tour));
	}
	
	private static long getCost(int n, int k, Method[] tour) {
		for(int i = 1; i < n + 1; i++) {
			Arrays.fill(dp[i], -1);
		}
		
		for(int city = 1; city < n + 1; city++) {
			for(int time = 0; time < k + 1; time++) {	
				if(time - tour[city].walk.time >= 0) {
					dp[city][time] = Math.max(dp[city][time], dp[city - 1][time - tour[city].walk.time] + tour[city].walk.cost);
		        }
				
				if(time - tour[city].bike.time >= 0) {
					dp[city][time] = Math.max(dp[city][time], dp[city - 1][time - tour[city].bike.time] + tour[city].bike.cost);
				}
	           
			}
		}
		
		long result = -1;
		for(int i = 1; i < k + 1; i++) {
			result = Math.max(result, dp[n][i]);
		}
		
		return result;
	}
}
