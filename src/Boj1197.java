import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj1197 {
	private static ArrayList<Node>[] tree = null;
	private static int[] cost = null;
	
	private static final int INF = 1_000_001;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		tree = new ArrayList[V + 1];
		for(int i = 0; i < V + 1; i++){
			tree[i] = new ArrayList<>();
		}
		
		cost = new int[V + 1];
		
		for(int i = 1; i < E + 1; i++){
			st = new StringTokenizer(br.readLine());
			tree[Integer.parseInt(st.nextToken())].add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		System.out.println(dijkstra(V, E));
	}
	
	private static class Node implements Comparable<Node>{
		int node;
		int cost;
		
		public Node(int node, int cost){
			this.node = node;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node n) {
			return this.cost < n.cost ? -1 : 1;
		}
	}
	
	private static int dijkstra(int node, int edge){
		int min = INF;
		
		for(int start = 1; start < node + 1; start++){
			Arrays.fill(cost, INF);
			cost[start] = 0;
			
			PriorityQueue<Node> pq = new PriorityQueue<>();
			pq.offer(new Node(start, cost[start]));
			
			while(!pq.isEmpty()){
				Node current = pq.poll();
				
				for(Node next : tree[current.node]){
					if(cost[next.node] > next.cost + cost[current.node]){
						cost[next.node] = next.cost + cost[current.node];
						
						System.out.println(next.node + " " + next.cost);
						
//						if(){
//							min = Math.min(min, cost[next.node]);
//						}
						
						pq.offer(new Node(next.node, cost[next.node]));
					}
				}
			}
		}
		
		return min;
	}
}
