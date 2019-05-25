import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj17218 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(makePassword(br.readLine().toCharArray(), br.readLine().toCharArray()));
	}
	
	private static StringBuilder makePassword(char[] word1, char[] word2) {
		int[][] dp = new int[word1.length][word2.length];
		
		if(word1[0] == word2[0]) dp[0][0] = 1;
		
		for(int i = 1; i < word2.length; i++) {
			dp[0][i] = word1[i] == word2[i] ? dp[0][i - 1] + 1: dp[0][i - 1];
		}
		
		for(int i = 1; i < word1.length; i++) {
			dp[i][0] = word1[i] == word2[0] ? dp[i - 1][0] + 1: dp[i - 1][0];
		}
		
		for(int i = 1; i < word1.length; i++) {
			for(int j = 0; j < word2.length; j++) {
				dp[i][j] = word1[i] == word2[i] ?  dp[i - 1][j - 1] + 1: Math.max(dp[i - 1][j], dp[i][j]);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		int target = dp[word1.length - 1][word2.length - 1];
		int idx = word1.length - 1;
		
		for(int i = word2.length - 1; i >= 0; i--) {
			if(idx == 0) break;
			
			if(target == dp[idx][i]) continue;
			
			
			
			
		}
		
		return sb;
	}
}
