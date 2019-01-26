import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj2325 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<Node>[] roads = new ArrayList[N + 1];
		int[][] cost = new int[N + 1][N + 1];
		for(int i = 1; i < N + 1; i++) {
			roads[i] = new ArrayList<>();
		}
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			
			roads[from].add(new Node(to, time));
			roads[to].add(new Node(from, time));
			cost[from][to] = cost[to][from] = time;
		}
		
		dijkstra(N, roads);
	}
	
	private static class Node implements Comparable<Node>{
		int edge;
		int cost;
		
		public Node(int edge, int cost) {
			this.edge = edge;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node n) {
			return this.cost < n.cost ? -1 : 1;
		}
	}
	
	private static void dijkstra(int n, ArrayList<Node>[] map) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] cost = new int[n + 1];
		Arrays.fill(cost, Integer.MAX_VALUE);
		
		for(Node start: map[1]) {
			pq.offer(new Node(start.edge, start.cost));
			cost[start.edge] = start.cost;
			
			while(!pq.isEmpty()) {
				Node current = pq.poll();
				
				for(Node next: map[current.edge]) {
					if(cost[next.edge] > cost[current.edge] + next.cost) {
						cost[next.edge] = cost[current.edge] + next.cost;
						
						pq.offer(new Node(next.edge, cost[next.edge]));
					}
				}
			}
		}
	}
}
