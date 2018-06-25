import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj13016 {
	private static final String NEW_LINE = "\n";
	
	private static StringBuilder sb = new StringBuilder();
	private static ArrayList<Node>[] map = null;
	private static int[] cost = null;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		int N = Integer.parseInt(br.readLine());
		
		map = new ArrayList[N + 1];
		for(int i = 0; i < N + 1; i++) {
			map[i] = new ArrayList<>();
		}
		
		for(int i = 1; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int length = Integer.parseInt(st.nextToken());
			
			map[from].add(new Node(to , length));
			map[to].add(new Node(from , length));
		}
		
		System.out.println(dijkstra(N));
	}
	
	private static class Node implements Comparable<Node>{
		int node;
		int cost;
		
		public Node(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node n) {
			return this.node > n.node ? -1 : 1;
		}
	}
	
	private static String dijkstra(int N) {
		for(int path = 1; path < N + 1; path++) {
			boolean[] isVisited = new boolean[N + 1];
			cost = new int[N + 1];
			isVisited[path] = true;
			
			int max = 0;
			
			PriorityQueue<Node> pq = new PriorityQueue<>();
			pq.offer(new Node(path, cost[path]));
			
			while(!pq.isEmpty()) {
				Node current = pq.poll();
				
				for(Node next : map[current.node]) {
					if(cost[next.node] < cost[current.node] + next.cost && !isVisited[next.node]) {
						isVisited[next.node] = true;
						cost[next.node] = cost[current.node] + next.cost;
						
						max = Math.max(max, cost[next.node]);
						
						pq.offer(new Node(next.node, cost[next.node]));
					}
				}
			}	
			
			sb.append(max).append(NEW_LINE);
		}
		
		return sb.toString();
	}
}
