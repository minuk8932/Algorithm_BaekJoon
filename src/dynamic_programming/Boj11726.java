package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj11726 {
	private static final int SPARE = 10_007;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] tile = new int[n + 1];
		int[] dp = new int[n + 1];
		
		if(n == 1){
			tile[1] = 1;
			dp[1] = 1;
		}
		
		if(n == 2){
			tile[2] = 2;
			dp[2] = 2;
		}
		
		
		
		if(n > 2){
			dp[1] = 1;
			dp[2] = 2;
			
			for(int i = 3; i < n+1; i++){
				dp[i] = (dp[i - 1] + dp[i - 2]) % SPARE;
			}
		}
		
		System.out.println(dp[n]);
	}
}
