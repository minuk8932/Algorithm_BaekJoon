import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj1922 {
	private static ArrayList<Node>[] map = null;
	private static int[][] res = null;
	
	private static final int INF = 50_000_000;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		map = new ArrayList[N + 1];
		res = new int[N + 1][N + 1];
		
		for(int i = 0; i < N + 1; i++) {
			map[i] = new ArrayList<>();
			Arrays.fill(res[i], INF);
		}
		
		for(int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			map[from].add(new Node(to, cost));
			map[to].add(new Node(from, cost));
		}
		
		dijkstra(N);
		
		int totalCost = 0;
		
		for(int i = 1; i < N + 1; i++) {
			int min = INF;
			
			for(int j = i + 1; j < N + 1; j++) {
				if(min > res[i][j]) min = res[i][j];
			}
			
			if(min != INF) totalCost += min;
		}
		
		System.out.println(totalCost);
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
	
	private static void dijkstra(int n) {		
		for(int i = 1; i < n + 1; i++) {
			for(Node start: map[i]) {				
				PriorityQueue<Node> pq = new PriorityQueue<>();
				
				pq.offer(new Node(start.edge, start.cost));
				res[i][start.edge] = start.cost;
				
				while(!pq.isEmpty()) {
					Node current = pq.poll();
					
					for(Node next: map[current.edge]) {
						if(res[current.edge][next.edge] > next.cost + current.cost) {
							res[current.edge][next.edge] = next.cost + current.cost;
							
							pq.offer(new Node(next.edge, res[current.edge][next.edge]));
						}
					}
				}
			}
		}
	}
}
