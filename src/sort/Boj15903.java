package sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15903번: 카드 합체 놀이
 *
 *	@see https://www.acmicpc.net/problem/15903/
 *
 */
public class Boj15903 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Number> pq = new PriorityQueue<>();
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < n; i++) {
			pq.offer(new Number(Long.parseLong(st.nextToken())));
		}
		
		while(m-- > 0) {
			long a = pq.poll().num;		// 가장 앞의 두 숫자를 뽑아낸 후
			long b = pq.poll().num;
			
			pq.offer(new Number(a + b));		// 더한 값을 우선순위 큐에 두번 저장
			pq.offer(new Number(a + b));
		}
		
		long res = 0;
		for(int i = 0; i < n; i++) {		// 결과로 남은 우선순위 큐 내의 숫자를 모두 더하고
			res += pq.poll().num;
		}
		
		System.out.println(res);		// 그 합을 출력
	}
	
	/**
	 * 숫자 이너 클래스, 팀 소트를 통한 우선순위 큐 내의 숫자들을 정렬
	 * @author minchoba
	 *
	 */
	private static class Number implements Comparable<Number>{
		long num;
		
		public Number(long num) {
			this.num = num;
		}

		@Override
		public int compareTo(Number n) {
			return this.num < n.num ? -1 : 1;
		}
	}
}
