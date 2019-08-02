package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * 
 * 	@author exponential-e
 *	백준 7596번: MP3 Songs
 *
 *	@see https://www.acmicpc.net/problem/7596/
 *
 */
public class Boj7596 {
	private static final String NEW_LINE = "\n";
	
	private static class Title implements Comparable<Title>{
		String title;
		
		public Title(String title) {
			this.title = title;
		}

		@Override
		public int compareTo(Title t) {
			return this.title.compareToIgnoreCase(t.title);
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int idx = 0;
		
		while(true) {
			int N = Integer.parseInt(br.readLine());
			if(N == 0) break;
			
			PriorityQueue<Title> pq = new PriorityQueue<>();
			while(N-- > 0) {
				pq.offer(new Title(br.readLine()));
			}
			
			sb.append(++idx).append(NEW_LINE);
			while(!pq.isEmpty()) {
				sb.append(pq.poll().title).append(NEW_LINE);
			}
		}
		
		System.out.println(sb.toString());
	}
}
