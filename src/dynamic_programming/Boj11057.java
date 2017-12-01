package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj11057 {
	private static final long MOD = 10_007;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[][] dp = new long[N+1][10];		// [자릿수][앞자리 숫자]
		
		dp[0][9] = 1;
		
		for(int i = 0; i < 10; i++){
			if(i == 0){
				dp[1][i] = dp[0][9];
			}
			else{
				dp[1][i] = dp[1][i - 1] + 1;
			}
		}
		
		for(int i = 2; i < N+1; i++){
			for(int j = 0; j < 10; j++){
				if(j == 0){
					dp[i][j] = dp[i - 1][9] % MOD;
				}
				else{
					dp[i][j] = (dp[i][j - 1] + (dp[i][0] - dp[i - 1][j - 1]))% MOD;
					
					if(dp[i][j] <= MOD){
						dp[i][j] = MOD + dp[i][j];
					}
				}
			}
		}
		
		System.out.println(dp[N][9] % MOD);
	}
}
