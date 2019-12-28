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
 *	백준 18223번: 민준이와 마산 그리고 건우
 *
 *	@see https://www.acmicpc.net/problem/18223/
 *
 */
public class Boj18223 {
	private static ArrayList<Path>[] list;
	
	private static class Path implements Comparable<Path>{
		int node;
		int cost;
		
		public Path(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}

		@Override
		public int compareTo(Path p) {
			return this.cost < p.cost ? -1: 1;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken()) - 1;
		
		list = new ArrayList[V];
		for(int i = 0; i < V; i++) {
			list[i] = new ArrayList<>();
		}
		
		while(E-- > 0) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());
			
			list[from].add(new Path(to, cost));
			list[to].add(new Path(from, cost));
		}
		
		int help = dijkstra(0, P) + dijkstra(P, V - 1);					// go to Masan through KW
		int ignore = dijkstra(0, V - 1);								// just go to Masan
		
		System.out.println(help == ignore ? "SAVE HIM": "GOOD BYE");	// meet KW way is the shortest ?
	}
	
	private static int dijkstra(int start, int end){
		int[] dist = new int[list.length];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		PriorityQueue<Path> pq = new PriorityQueue<>();
		pq.offer(new Path(start, 0));
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Path current = pq.poll();

			for(Path next: list[current.node]) {
				if(dist[next.node] > dist[current.node] + next.cost) {
					dist[next.node] = dist[current.node] + next.cost;
					
					pq.offer(new Path(next.node, dist[next.node]));
				}
			}
		}
		
		return dist[end];
	}
}
