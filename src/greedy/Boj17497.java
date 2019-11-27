package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

/**
 * 
 * 	@author exponential-e
 *	백준 17497번: 계산기
 *
 *	@see https://www.acmicpc.net/problem/17497
 *
 */
public class Boj17497 {
	private static final String NO = "-1";
	private static final String NEW_LINE = "\n";
	private static final String[] OPS = {" ", "[*] ", "[+] ", "[/] ", "[-] "};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Long.parseLong(br.readLine());
		
		System.out.println(search(N));
	}
	
	private static String search(long n) {
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		
		while(n > 0) {
			if(n % 2 == 1) {				// div
				stack.push(3);
				n *= 2;
			}
			else if((n / 2) % 2 == 1) {		// plus
				stack.push(2);
				n -= 2;
			}
			else {							// mul
				stack.push(1);
				n /= 2;
			}
		}
		
		int size = stack.size();
		if(size > 99) return NO;
		
		StringBuilder sb = new StringBuilder();
		sb.append(size).append(NEW_LINE);
		
		while(!stack.isEmpty()) {
			sb.append(OPS[stack.pop()]);
		}
		
		return sb.toString();
	}
}
