package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj9507 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		int[] koong = new int[t];
		long[] dp = new long[68];
		
		
		for(int i = 0; i < t; i++){
			koong[i] = Integer.parseInt(br.readLine());
		}
		br.close();
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < t; i ++){
			
			if(koong[i] < 2){
				dp[0] = 1;
				dp[1] = 1;
			}
			
			if(koong[i] == 2){
				dp[2] = 2;
			}
			
			if(koong[i] == 3){
				dp[3] = 4;
			}
			
			if(koong[i] > 3){
				dp[0] = 1;
				dp[1] = 1;
				dp[2] = 2;
				dp[3] = 4;
				for(int j = 4; j <= koong[i]; j++){
					dp[j] = dp[j - 4] + dp[j - 1] + dp[j - 2] + dp[j - 3];
				}
			}
			sb.append(dp[koong[i]]).append(NEW_LINE);
		}
		System.out.println(sb.toString());
	}
}
