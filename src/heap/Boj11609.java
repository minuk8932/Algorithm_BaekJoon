package heap;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11609번: Class Time
 *
 *	@see https://www.acmicpc.net/problem/11609/
 *
 */
public class Boj11609 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	private static class Pair implements Comparable<Pair>{
		String first;
		String last;
		
		public Pair(String first, String last) {
			this.first = first;
			this.last = last;
		}

		@Override
		public int compareTo(Pair p) {
			if(this.last.compareTo(p.last) != 0) {
				return this.last.compareTo(p.last);
			}
			else {
				return this.first.compareTo(p.first);
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		while(n-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String f = st.nextToken();
			String l = st.nextToken();
			
			pq.offer(new Pair(f, l));
		}
		
		System.out.println(getAttendance(pq));
	}
	
	private static StringBuilder getAttendance(PriorityQueue<Pair> pq) {
		StringBuilder sb = new StringBuilder();
		
		while(!pq.isEmpty()) {			// 우선 순위에 따라 출석부 구성
			Pair current = pq.poll();
			sb.append(current.first).append(SPACE).append(current.last).append(NEW_LINE);
		}
		
		return sb;
	}
}
