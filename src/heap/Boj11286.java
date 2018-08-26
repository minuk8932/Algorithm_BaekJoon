package heap;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * 
 * 	@author minchoba
 *	백준 11286번: 절댓값 힙
 *
 *	@see https://www.acmicpc.net/problem/11286/
 *
 */
public class Boj11286 {
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
			
			if(x == 0) {			// 숫자 값이 0 일때 힙이 비었으면 0을, 아니면 힙에서 최솟 값을 뽑아 버퍼에 담음
				sb.append(heap.isEmpty() ? isEmpty : heap.poll().num).append(NEW_LINE);
			}
			else {					// 0 이외의 정수가 들어온 경우 힙에 담아줌
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
			if(Math.abs(this.num) < Math.abs(n.num)) {
				return -1;
			}
			else if (Math.abs(this.num) > Math.abs(n.num)){
				return 1;
			}
			else{
				return this.num < n.num ? -1 : 1;
			}
		}
	}
}
