package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * 
 * 	@author minchoba
 *	백준 1417번: 국회의원 선거
 */
public class Boj1417 {
	
	private static class Pair implements Comparable<Pair>{
		int cnt;
		
		public Pair(int cnt) {
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Pair p) {
			return this.cnt > p.cnt ? -1 : 1;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int target = Integer.parseInt(br.readLine());
		
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		for(int i = 0; i < N - 1; i++) {
			pq.offer(new Pair(Integer.parseInt(br.readLine())));
		}
		
		System.out.println(N == 1 ? 0 : purchase(N, target, pq));
	}
	
	private static int purchase(int n, int target, PriorityQueue<Pair> pq) {
		int count = 0;
		
		while(true) {
			Pair current = pq.poll();
			if(target > current.cnt) break;		// 매수 그만
			
			count++;
			target += 1;
			
			pq.offer(new Pair(current.cnt - 1));	// 값이 높은 순으로 저장
		}
		
		return count;
	}
}
