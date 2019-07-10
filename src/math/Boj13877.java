package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 13877번: 이건 무슨 진법이지?
 *
 *	@see https://www.acmicpc.net/problem/13877/
 *
 */
public class Boj13877 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			
			sb.append(K).append(SPACE).append(getOctalDecimalHexa(st.nextToken().toCharArray())).append(NEW_LINE);
		}
		
		System.out.print(sb.toString());
	}
	
	private static String getOctalDecimalHexa(char[] nums) {
		StringBuilder sb = new StringBuilder();
		
		boolean flag = true;
		int[] nary = new int[3];
		
		for(int i = 0; i < nums.length; i++) {
			if(nums[i] > '7') {
				nary[0] = 0;
				flag = false;
			}
			
			if(flag) nary[0] += converter(nums[nums.length - 1 - i], 8, i);			// octal
			nary[1] += converter(nums[nums.length - 1 - i], 10, i);					// decimal
			nary[2] += converter(nums[nums.length - 1 - i], 16, i);					// hexa
		}
		
		return sb.append(nary[0]).append(SPACE).append(nary[1]).append(SPACE).append(nary[2]).toString();
	}
	
	private static int converter(char c, int power, int idx) {
		return (c - '0') * (int) Math.pow(power, idx);
	}
}
