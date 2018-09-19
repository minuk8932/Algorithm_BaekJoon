package queue;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14469번: 소가 길을 건너간 이유 3
 *
 *	@see https://www.acmicpc.net/problem/14469/
 *
 */
public class Boj14469 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Cows> pq = new PriorityQueue<>();
		
		while(N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			pq.offer(new Cows(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));	// 우선 순위 큐를 통한 도착 순 정렬
		}
		
		Cows current = pq.poll();
		long times = current.arrive + current.inspect;		// 첫번째 소의 도착과 검사 시간 저장
		
		while(!pq.isEmpty()) {
			Cows next = pq.poll();
				
			if(times >= next.arrive) {			// 총 경과 시간보다 다음 도착 할 소의 시간이 작거나 같은 경우
				times += next.inspect;			// 시간이 경과 해야 소가 검사 받을 수 있으므로, 원래 값 + 검사 시간
			}
			else {								// 소가 도착하기 전에 이전 검사가 완료되는 경우
				times = next.arrive + next.inspect;		// 다음 소의 도착 시간과 검사 시간을 더한 시간으로 총 시간을 갱신
			}
			
			current = new Cows(next.arrive, next.inspect);		// 현재 소 정보를 다음 소의 정보로 초기화
		}
		
		System.out.println(times);			// 총 경과 시간 출력
	}
	
	/**
	 * 소 도착 정보 이너 클래스
	 * @author minchoba
	 *
	 */
	private static class Cows implements Comparable<Cows>{
		int arrive;
		int inspect;
		
		public Cows(int arrive, int inspect) {
			this.arrive = arrive;
			this.inspect = inspect;
		}

		@Override
		public int compareTo(Cows c) {
			if(c.arrive == this.arrive) {
				if(this.inspect < c.inspect) return -1;
				else if(this.inspect > c.inspect) return 1;
				else return 0;
			}
			else if(this.arrive < c.arrive){
				return -1;
			}
			else {
				return 1;
			}
		}
	}
}
