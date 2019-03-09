package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 16953번: A -> B
 *
 *	@see https://www.acmicpc.net/problem/16953/
 *
 */
public class Boj16953 {
	private static final int INF = 1_000_000_000;
	
	private static class Search{
		long value;
		int count;
		
		public Search(long value, int count) {
			this.value = value;
			this.count = count;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		System.out.println(bfs(A, B));
	}
	
	private static int bfs(long a, long b) {
		if(a == b) return 1;
		if(a > b) return -1;
		
		Queue<Search> q = new LinkedList<>();
		q.offer(new Search(a, 1));
		
		while(!q.isEmpty()) {
			Search current = q.poll();
			if(current.value > INF) continue;
			
			long[] next = {current.value * 2, current.value * 10 + 1};		// 다음 값
			int nextCount = current.count + 1;
			
			for(int i = 0; i < 2; i++) {	
				if(next[i] > INF) continue;
				
				if(next[i] == b) return nextCount;			// 값이 존재하면 연산 횟수 반환
				q.offer(new Search(next[i], nextCount));
			}
		}
		
		return -1;
	}
}
