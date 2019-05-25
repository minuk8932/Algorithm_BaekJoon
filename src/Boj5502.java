import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj5502 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		char[] word = br.readLine().toCharArray();
		System.out.println(makePalindrome(N, word));
	}
	
	private static int makePalindrome(int n, char[] arr) {
		int half = n / 2;
		char[] arr1 = new char[half];
		char[] arr2 = new char[half];
		
		for(int i = 0; i < half; i++) {
			arr1[i] = arr[i];
		}
		
		int idx = 0;
		half = n % 2 == 0 ? half - 1: half;
		
		for(int i = n - 1; i > half; i--) {
			arr2[idx++] = arr[i];
		}
		
		return arr1.length - lcs(arr1, arr2);
	}
	
	private static int lcs(char[] arr1, char[] arr2) {
		int[][] dp = new int[arr1.length][arr2.length];
		
		if(arr1[0] == arr2[0]) dp[0][0] = 1;
		for(int i = 1; i < arr2.length; i++) {
			dp[0][i] = arr1[0] == arr2[i] ? dp[0][i - 1] + 1: dp[0][i - 1];
		}
		
		for(int i = 1; i < arr1.length; i++) {
			dp[i][0] = arr1[i] == arr2[0] ? dp[i - 1][0] + 1: dp[i - 1][0];
		}
		
		for(int i = 1; i < arr1.length; i++) {
			for(int j = 1; j < arr2.length; j++) {
				dp[i][j] = arr1[i] == arr2[j] ?  dp[i - 1][j - 1] + 1: Math.max(dp[i - 1][j], dp[i][j - 1]);
			}
		}
		
		return dp[arr1.length - 1][arr2.length - 1];
	}
}
