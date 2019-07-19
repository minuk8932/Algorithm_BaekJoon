package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 13411번: 하늘에서 정의가 빗발친다.
 *
 *	@see https://www.acmicpc.net/problem/13411/
 *
 */
public class Boj13411 {
	private static final String NEW_LINE = "\n";
	
	private static class Coordinate{
		int x;
		int y;
		
		public Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private static class Pair implements Comparable<Pair>{
		Coordinate coor;
		int velocity;
		int idx;
		double time;
		
		public Pair(Coordinate coor, int velocity, int idx) {
			this.coor = coor;
			this.velocity = velocity;
			this.idx = idx;
		}
		
		public Pair(int idx, double time) {
			this.idx = idx;
			this.time = time;
		}

		@Override
		public int compareTo(Pair p) {
			if(this.time < p.time) {
				return -1;
			}
			else if(this.time > p.time) {
				return 1;
			}
			else {
				if(this.idx < p.idx) return -1;
				else if(this.idx > p.idx) return 1;
				else return 0;
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Pair[] robots = new Pair[N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			robots[i] = new Pair(new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())), Integer.parseInt(st.nextToken()), i);
		}
		
		System.out.println(getSequence(N, robots));
	}
	
	private static String getSequence(int n, Pair[] arr) {
		StringBuilder sb = new StringBuilder();
		
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		for(int i = 0; i < n; i++) {
			double xPow = arr[i].coor.x * arr[i].coor.x;
			double yPow = arr[i].coor.y * arr[i].coor.y;
			
			pq.offer(new Pair(arr[i].idx, Math.sqrt(xPow + yPow) / arr[i].velocity));		// 순번, 격추에 걸리는 시간
		}
		
		while(!pq.isEmpty()) {
			sb.append(pq.poll().idx + 1).append(NEW_LINE);
		}
		
		return sb.toString();
	}
}
