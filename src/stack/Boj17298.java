package stack;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 17298번: 오큰수
 *
 *	@see https://www.acmicpc.net/problem/17298/
 *
 */
public class Boj17298 {
	private static final String SPACE = " ";
	
	private static class Info{
		int idx;
		int val;
		
		public Info(int idx, int val) {
			this.idx = idx;
			this.val = val;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] A = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(NGE(N, A));
	}
	
	private static String NGE(int n, int[] arr) {
		StringBuilder sb = new StringBuilder();
		ArrayDeque<Info> stack = new ArrayDeque<>();
		int[] res = new int[n];
		
		for(int i = 0; i < n; i++) {			
			if(!stack.isEmpty()) {
				Info peek = stack.peek();
				
				if(peek.val < arr[i]) {
					boolean flag = false;
					
					while(!stack.isEmpty()) {				// find value that is bigger than peek val
						peek = stack.pop();
						
						if(peek.val >= arr[i]) {			// is stopped by smaller value
							flag = true;
							break;
						}
							
						res[peek.idx] = arr[i];
					}
					
					if(flag) stack.push(peek);				// re push
				}
				
				stack.push(new Info(i, arr[i]));
				continue;
			}
			
			stack.push(new Info(i, arr[i]));
		}
		
		while(!stack.isEmpty()) {
			Info pop = stack.pop();
			res[pop.idx] = -1;								// there is any bigger value right side
		}
		
		for(int i = 0; i < res.length; i++) {
			sb.append(res[i]).append(SPACE);
		}
		
		return sb.toString();
	}
}
