import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Boj11003 {
	private static final char SPACE = ' ';
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		Deque<Integer> deq = new LinkedList<>();
		st = new StringTokenizer(br.readLine());
		int[] A = new int[N + 1];
		
		for(int i = 1; i < N + 1; i++) {
			A[i] = Integer.parseInt(st.nextToken());
			
			if(deq.isEmpty()) {
				deq.offerFirst(A[i]);
			}
			else {
				while(!deq.isEmpty() && deq.peekLast() > A[i]) {
					deq.pollLast();
				}
				
				deq.offerLast(A[i]);
			}
			
			if(i > L) {
				if(deq.peekFirst() == A[i - L]) deq.pollFirst();
			}
			
			sb.append(deq.peekFirst()).append(SPACE);
		}
		
		System.out.println(sb.toString());
	}
}
