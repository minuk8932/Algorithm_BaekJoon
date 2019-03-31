package deque;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * 	@author minchoba
 *	백준 1835번: 카드
 *
 *	@see https://www.acmicpc.net/problem/1835/
 *
 */
public class Boj1835 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		System.out.println(process(N));
	}
	
	private static StringBuilder process(int n) {
		StringBuilder sb = new StringBuilder();
		Deque<Integer> deq = new LinkedList<>();
		deq.offer(n);
		
		for(int i = n - 1; i > 0; i--) {		// 문제 조건의 반대로 돌려줌
			int loop = i;
			deq.offerFirst(i);
			
			while(loop-- > 0) {
				int value = deq.pollLast();
				deq.offerFirst(value);
			}
		}
		
		while(!deq.isEmpty()) {
			sb.append(deq.poll()).append(SPACE);
		}
		
		return sb;
	}
}
