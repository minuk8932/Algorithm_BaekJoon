import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj17270 {
	private static ArrayList<Node>[] path;
	
	private static boolean[] candidate;
	private static int[] dist;
	private static int INF = 10_000_001;
	
	private static class Node implements Comparable<Node>{
		int node;
		int cost;
		
		public Node(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node n) {
			return this.cost < n.cost ? -1: 1;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new	BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		path = new ArrayList[V];
		dist = new int[V];
		candidate = new boolean[V];
		for(int i = 0; i < V; i++) {
			path[i] = new ArrayList<>();
			dist[i] = INF;
		}
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int vertex1 = Integer.parseInt(st.nextToken()) - 1;
			int vertex2 = Integer.parseInt(st.nextToken()) - 1;
			int value = Integer.parseInt(st.nextToken());
			
			path[vertex1].add(new Node(vertex2, value));
			path[vertex2].add(new Node(vertex1, value));
		}
		
		st = new StringTokenizer(br.readLine());
		int J = Integer.parseInt(st.nextToken()) - 1;
		int S = Integer.parseInt(st.nextToken()) - 1;
		
		dijkstra(J, S);
		bfs(J, S);
	}
	
	private static void dijkstra(int start, int end) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			
			for(Node next: path[current.node]) {
				if(dist[next.node] > next.cost + dist[current.node]) {
					dist[next.node] = next.cost + dist[current.node];
					
					pq.offer(new Node(next.node, dist[next.node]));
				}
			}
		}
	}
	
	private static void bfs(int start, int end) {
		
	}
}
