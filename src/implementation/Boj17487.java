package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 *	백준 17487번: 타자 연습
 *
 *	@see https://www.acmicpc.net/problem/17487
 *
 */
public class Boj17487 {
	private static int left, right, wildcard;
	
	private static final String SPACE = " ";
	private static final boolean[] ALPHA = {true, true, true, true, true, true, true,
			false, false, false, false, false, false, false, 
			false, false, true, true, true, true, 
			false, true, true, true, true, true};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		
		System.out.println(getCount(line));
	}
	
	private static String getCount(String input) {		
		for(char c: input.toCharArray()) {
			if(c >= 'A' && c <= 'Z') {
				if(ALPHA[c - 'A']) left++;
				else right++;
				
				wildcard++;						// shift
			}
			else if(c >= 'a' && c <= 'z') {
				if(ALPHA[c - 'a']) left++;
				else right++;
			}
			else {
				wildcard++;						// space
			}
		}
		
		StringBuilder sb = new StringBuilder();
		int diff = 0;
		
		if(left > right) {						// value balancing
			diff = left - right;
			right = adder(diff, right);
		}
		else {
			diff = -left + right;
			left = adder(diff, left);
		}
		
		distribute();							// remained value balancing
		return sb.append(left).append(SPACE).append(right).toString();
	}
	
	private static void distribute() {		
		left += wildcard / 2;
		right += wildcard / 2;
		
		if(wildcard % 2 != 0) left++;
	}
	
	private static int adder(int d, int target) {
		if(d >= wildcard) {
			target += wildcard;
			wildcard = 0;
		}
		else {
			wildcard -= d;
			target += d;
		}
		
		return target;
	}
}
