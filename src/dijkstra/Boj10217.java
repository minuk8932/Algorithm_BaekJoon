package dijkstra;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 10217번: KCM Travel
 *
 *	@see https://www.acmicpc.net/problem/10217/
 *
 */
public class Boj10217 {
	private static final String NEW_LINE = "\n";
	private static ArrayList<Path>[] list;
	
	private static class Path implements Comparable<Path>{
		int node;
		int time;
		int cost;

		public Path(int node, int time, int cost) {
			this.node = node;
			this.time = time;
			this.cost = cost;
		}

		@Override
		public int compareTo(Path p) {
			return this.time < p.time ? -1: 1;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			list = new ArrayList[N];
			for(int i = 0; i < N; i++) {
				list[i] = new ArrayList<>();
			}
			
			while(K-- > 0){
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken()) - 1;
				int v = Integer.parseInt(st.nextToken()) - 1;
				int c = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				
				list[u].add(new Path(v, d, c));
			}
			
			long result = dijkstra(N, M);
			sb.append(result == Integer.MAX_VALUE ? "Poor KCM": result).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
	
	private static int dijkstra(int n, int m) {
		int min = Integer.MAX_VALUE;
		
		int[][] timer = new int[n][m + 1];					// [node][cost] = time
		for(int i = 0; i < n; i++)
			Arrays.fill(timer[i], Integer.MAX_VALUE);
		
		PriorityQueue<Path> q = new PriorityQueue<>();
		q.offer(new Path(0, 0, m));
		timer[0][m] = 0;
		
		while(!q.isEmpty()) {
			Path current = q.poll();
			if(timer[current.node][current.cost] < current.time) continue; 
			
			for(Path next: list[current.node]) {				
				if(current.cost - next.cost >= 0 && timer[next.node][current.cost - next.cost] > timer[current.node][current.cost] + next.time) {	// find shortest
					timer[next.node][current.cost - next.cost] = timer[current.node][current.cost] + next.time;
					
					if(next.node == n - 1) min = Math.min(min, timer[next.node][current.cost - next.cost]);
					q.offer(new Path(next.node, timer[next.node][current.cost - next.cost], current.cost - next.cost));
				}
			}
		}
		
		return min;
	}
}