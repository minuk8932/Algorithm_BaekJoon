package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 9417번: 최대 GCD
 *
 *	@see https://www.acmicpc.net/problem/9417/
 *
 */
public class Boj9417 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		while(N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int[] values = new int[st.countTokens()];
			for(int i = 0; i < values.length; i++) {
				values[i] = Integer.parseInt(st.nextToken());
			}
			
			sb.append(process(values)).append(NEW_LINE);
		}
		
		System.out.print(sb.toString());
	}
	
	private static int process(int[] arr) {
		int max = 0;
		
		for(int i = 0; i < arr.length; i++) {
			for(int j = i + 1; j < arr.length; j++) {
				int g = gcd(arr[i], arr[j]);			// 최대 공약수 구하기
				
				if(g > max) max = g;					// 그중 최댓값
			}
		}
		
		return max;
	}
	
	private static int gcd(int a, int b) {
		if(a == 0) return b;
		return gcd(b % a, a);
	}
}
