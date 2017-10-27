package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1932 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[][] tri = new int[n+10][n+10];
		
		for(int i = 1; i < n+1; i++){
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			for(int j = 1; j <= i; j++){
				tri[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		long[][] dp = new long[n+10][n+10];
			
			for(int i = 1; i < n+1; i++){
				for(int j = 1; j < i + 1; j++){
					if(j == 1){
						dp[i][j] = dp[i - 1][1] + tri[i][j];
					}
					else if(j == i){
						dp[i][j] = dp[i - 1][j - 1] + tri[i][j];
					}
					else{
						dp[i][j] = tri[i][j] + Math.max(dp[i - 1][j], dp[i - 1][j - 1]);
					}
				}
			}
		
		long res = 0;
		
		for(int i = 1; i < n+1; i++){
			res = Math.max(res, dp[n][i]);
		}
		
		System.out.println(res);
	}

}
