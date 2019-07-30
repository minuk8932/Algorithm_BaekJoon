import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj17255 {
	private static int[] dp;
	private static char[] num;
	private static String str;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine();
		int n = str.length();
		
		num = new char[n + 1];
		for(int i = 1; i < n + 1; i++) {
			num[i] = str.charAt(i - 1);
		}
		
		dp = new int[Integer.parseInt(str) + 1];
		
		System.out.println(recursion(n, 0, ""));
	}
	
	private static int recursion(int n, int idx, String val) {
		if(n == 0) {
			if(val.equals(str)) return 1;
			else return 0;
		}
		
		if(!val.equals("") && dp[Integer.parseInt(val)] != 0) return dp[Integer.parseInt(val)];
		
		for(int i = 1; i < num.length; i++) {
			dp[n] += recursion(n - 1, 0, val + num[i]);
			dp[n] += recursion(n - 1, 1, num[i] + val);
		}
		
		return dp[Integer.parseInt(val)];
	}
}
