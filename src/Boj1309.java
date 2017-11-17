import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj1309 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] dp = new int[N + 1];
		
		if(N == 1){
			dp[N] = 3; //N + N/2 + 1;
		}
		
		if(N == 2){
			dp[N] = 7;
		}
		
		if(N == 3){
			dp[N] = 1 + 6 + 8;
		}
	}
}
