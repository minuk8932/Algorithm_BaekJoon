package stack;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 2841번: 외계인의 기타 연주
 *
 *	@see https://www.acmicpc.net/problem/2841/
 *
 */
public class Boj2841 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		ArrayDeque<Integer>[] stack = new ArrayDeque[6];
		for(int i = 0; i < stack.length; i++) {
			stack[i] = new ArrayDeque<>();
		}
		
		int count = 0;
		
		while(N-- > 0) {
			st = new StringTokenizer(br.readLine());
			int line = Integer.parseInt(st.nextToken()) - 1;
			int p = Integer.parseInt(st.nextToken());
			
			if(stack[line].isEmpty()) {			// first touch
				stack[line].push(p);
				count++;
				continue;
			}
			
			int peek = 0;
			
			while(!stack[line].isEmpty()) {		// to play higher
				peek = stack[line].peek();
				if(peek <= p) break;
				
				count++;
				stack[line].pop();
			}
			
			if(peek > p) {
				if(stack[line].isEmpty()) {		// next lower key, any touch
					count++;
					stack[line].push(p);
				}
				
				continue;
			}
			
			if(peek != p) {						// touch new one
					count++;
					stack[line].push(p);
			}
		}
		
		System.out.println(count);
	}
}
