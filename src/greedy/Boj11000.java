package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 11000번: 강의실 배정
 *
 *	@see https://www.acmicpc.net/problem/11000/	
 *
 */
public class Boj11000 {
	private static class Pair implements Comparable<Pair>{
		int s;
		int e;
		
		public Pair(int s, int e) {
			this.s = s;
			this.e = e;
		}

		@Override
		public int compareTo(Pair p) {
			if(this.s < p.s) {
				return -1;
			}
			else if(this.s > p.s) {
				return 1;
			}
			else {
				if(this.e < p.e) return -1;
				else if(this.e > p.e) return 1;
				else return 0;
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Pair[] lecture = new Pair[N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			lecture[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(lecture);
		System.out.println(assign(N, lecture));
	}
	
	private static int assign(int n, Pair[] p) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.offer(p[0].e);
		
		for(int i = 1; i < n; i++) {
			if(pq.peek() <= p[i].s) pq.poll();		//	current start >= before end ?
			pq.offer(p[i].e);
		}
		
		return pq.size();
	}
}
