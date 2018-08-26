package heap;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * 
 * 	@author minchoba
 *	백준 11279번: 최대 힙
 *
 *	@see https://www.acmicpc.net/problem/11279/
 *
 */
public class Boj11279 {
	private static final char NEW_LINE = '\n';
	private static final int isEmpty = 0;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Number> heap = new PriorityQueue<>();
		
		while(N-- > 0) {
			int x = Integer.parseInt(br.readLine());
			
			if(x == 0) {				// 값이 0 일때, 힙이 비어있으면 0 아니면 힙의 최댓값을 버퍼에 담음
				sb.append(heap.isEmpty() ? isEmpty : heap.poll().num).append(NEW_LINE);
			}
			else {						// 0이 아닌경우 힙에 해당 숫자를 담음
				heap.offer(new Number(x));
			}
		}
		
		System.out.println(sb.toString());		// 결과 값 한번에 출력
	}
	
	/**
	 * 숫자 이너 클래스
	 * @author minchoba
	 *
	 */
	private static class Number implements Comparable<Number>{
		int num;
		
		public Number(int num) {
			this.num = num;
		}

		@Override
		public int compareTo(Number n) {
			return this.num > n.num ? -1 : 1;
		}
	}
}
