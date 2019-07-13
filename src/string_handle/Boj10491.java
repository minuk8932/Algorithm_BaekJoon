package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 *	백준 10491번: Quite a Problem
 *
 *	@see https://www.acmicpc.net/problem/10491/
 *
 */
public class Boj10491 {
	private static final String[] TARGET = {"Pp", "Rr", "Oo", "Bb", "Ll", "Ee", "Mm"};
	private static final String Y = "yes\n";
	private static final String N = "no\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String input = "";
		while((input = br.readLine()) != null) {
			sb.append(hasProblem(input.toCharArray()) ? Y: N);		// is problem contains?
		}
		
		System.out.print(sb.toString());
	}
	
	private static boolean hasProblem(char[] c) {		
		if(c.length < TARGET.length) return false;
		
		for(int idx = 0; idx < c.length; idx++) {
			if(isSame(c[idx], 0)) {
				int tmp = idx, i = 0;
				
				for(; i < TARGET.length; i++) {					
					if(tmp >= c.length || !isSame(c[tmp], i)) break;		// problem이 없는 경우
					tmp++;
				}
				
				if(i == TARGET.length) return true;
			}
		}
		
		return false;
	}
	
	private static boolean isSame(char origin, int idx) {
		return origin == TARGET[idx].charAt(0) 
				|| origin == TARGET[idx].charAt(1) ? true: false;
	}
}
