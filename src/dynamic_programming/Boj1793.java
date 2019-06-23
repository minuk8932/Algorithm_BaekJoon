package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 * 
 * 	@author minchoba
 *	백준 1793번: 타일링
 *
 *	@see https://www.acmicpc.net/problem/1793/
 *
 */
public class Boj1793 {
	private static final String NEW_LINE = "\n";
	private static BigInteger[] dp = new BigInteger[251];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		getValue();			// 미리 타일링
		
		String input = "";
		while((input = br.readLine()) != null) {
			int n = Integer.parseInt(input);
			sb.append(dp[n]).append(NEW_LINE);
		}
		
		System.out.print(sb.toString());
	}
	
	private static void getValue() {
		dp[0] = dp[1] = BigInteger.ONE;
		BigInteger two = BigInteger.ONE.add(BigInteger.ONE);
		
		for(int i = 2; i < 251; i++) {						// 타일 점화식 dp[i] = dp[i - 1] + 2 * dp[i - 2];
			dp[i] = dp[i - 1].add(dp[i - 2].multiply(two));
		}
	}
}
