package heap;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * 
 * 	@author minchoba
 *	백준 1927번: 최소 힙
 *
 *	@see https://www.acmicpc.net/problem/1927/
 *
 */
public class Boj1927 {
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
			
			if(x == 0) {		// 0이 들어온 경우, 힙이 비어있다면 0을 그 외엔 힙 내의 최솟값을 뽑아내 버퍼에 담음
				sb.append(heap.isEmpty() ? isEmpty : heap.poll().num).append(NEW_LINE);
			}
			else {				// 0 이외의 값이 들어온 경우 힙에 값을 담아줌
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
			return this.num < n.num ? -1 : 1;
		}
	}
}
