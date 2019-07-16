package line_sweeping;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 15593번: LifeGuards
 *
 *	@see https://www.acmicpc.net/problem/15593/
 *
 */
public class Boj15593 {	
	private static class Section implements Comparable<Section>{
		int from;
		int to;
		
		public Section(int from, int to) {
			this.from = from;
			this.to = to;
		}

		@Override
		public int compareTo(Section s) {
			if(this.from < s.from) return -1;
			else if(this.from > s.from) return 1;
			else return 0;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Section[] sect = new Section[N];		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			sect[i] = new Section(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(sect);
		System.out.println(maxCovered(N, sect));
	}
	
	private static int maxCovered(int n, Section[] arr) {
		int max = 0;
		
		for(int except = 0; except < n; except++) {
			PriorityQueue<Section> pq = new PriorityQueue<>();
			
			for(int line = 0; line < n; line++) {
				if(line == except) continue;			// 구간 1개 제외
				pq.offer(arr[line]);
			}
			
			int total = 0;
			int prev = pq.peek().from;
			int post = pq.poll().to;
			
			while(!pq.isEmpty()) {						// line sweeping
				Section current = pq.poll();
				
				if(post >= current.from) {
					post = current.to;
				}
				else {
					total += post - prev;
					prev = current.from;
					post = current.to;
				}
			}
			
			total += post -prev;
			if(total > max) max = total;
		}
		
		return max;
	}
}
