package stack;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 17299번: 오등큰수
 *
 *	@see https://www.acmicpc.net/problem/17299/
 *
 */
public class Boj17299 {
	private static final String SPACE = " ";
	
	private static class Pair{
		int idx;
		int val;
		
		public Pair(int idx, int val) {
			this.idx = idx;
			this.val = val;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] count = new int[1_000_001];
		Pair[] arr = new Pair[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = new Pair(i, Integer.parseInt(st.nextToken()));
			count[arr[i].val]++;
		}
		
		System.out.println(getSequence(N, count, arr));
	}
	
	private static String getSequence(int n, int[] cnt,  Pair[] p) {		
		ArrayDeque<Pair> stack = new ArrayDeque<>();
		int[] result = new int[n];
		
		for(int i = 0; i < n; i++) {			
			if(!stack.isEmpty()) {
				Pair peek = stack.peek();
				
				if(cnt[peek.val] < cnt[p[i].val]) {				// find over count
					boolean flag = false;
					
					while(!stack.isEmpty()) {
						peek = stack.pop();
						
						if(cnt[peek.val] >= cnt[p[i].val]) {	// if not stop
							flag = true;
							break;
						}
							
						result[peek.idx] = p[i].val;			// set value
					}
					
					if(flag) stack.push(peek);
				}
				
				stack.push(p[i]);
				continue;
			}
			
			stack.push(p[i]);
		}
		
		while(!stack.isEmpty()) {
			Pair pop = stack.pop();
			result[pop.idx] = -1;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++) {
			sb.append(result[i]).append(SPACE);
		}
		
		return sb.toString();
	}
}
