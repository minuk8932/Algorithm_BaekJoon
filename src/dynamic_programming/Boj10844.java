package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj10844 {
	private static final int DIV = 1_000_000_000;
	private static final int NUMS = 10;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		long[][] dp = new long[N + 1][NUMS];	//	[자릿 수][해당 자릿수의 변화 가능한 숫자 범위]
		
		for(int i = 1; i < NUMS; i++){
			dp[1][i] = 1;
		}
		
		for(int i = 2; i < N + 1; i++){
			for(int j = 0; j < NUMS; j++){
				long tmp = 0;
				
				if(j + 1 <= 9){
					tmp += dp[i - 1][j + 1];
				}
				
				if(j - 1 >= 0){
					tmp += dp[i - 1][j - 1];
				}
				
				dp[i][j] = tmp % DIV;
			}
		}
		
		long res = 0;
		
		for(int i = 0; i < NUMS; i++){
			res += dp[N][i] % DIV;
		}
		
		System.out.println(res % DIV);
	}
}
