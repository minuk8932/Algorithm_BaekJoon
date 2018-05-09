import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj11779 {
	private static ArrayList<Node>[] path = null;
	private static StringTokenizer st = null;
	private static StringBuilder sb = null;

	private static int n = 0;
	private static int m = 0;
	private static int cnt = 1;
	private static int[] cost = null;

	private static final int INF = 1_000_001;
	private static final String SPACE = " ";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());

		path = new ArrayList[n + 1];
		for (int i = 0; i < n + 1; i++) {
			path[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			path[Integer.parseInt(st.nextToken())].add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		cost = new int[n + 1];
		Arrays.fill(cost, INF);
		
		sb = new StringBuilder();
		System.out.println(dijkstra(start, end));
		System.out.println(cnt);
		System.out.println(sb.toString());
		
		for(int i = 1; i < n + 1; i++){
			System.out.println(cost[i]);
		}
	}

	private static class Node implements Comparable<Node> {
		int node;
		int val;

		public Node(int node, int val) {
			this.node = node;
			this.val = val;
		}

		@Override
		public int compareTo(Node n) {
			return n.val > this.val ? -1 : 1;
		}
	}

	private static int dijkstra(int s, int e) {
		cost[s] = 0;
		int min = Integer.MAX_VALUE;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(s, cost[s]));

		while (!pq.isEmpty()) {
			Node current = pq.poll();

			for (Node next : path[current.node]) {
				int nextNode = next.node;
				int nextCost = next.val;
				
				if (cost[nextNode] > nextCost + cost[current.node]) {
					cost[nextNode] = nextCost + cost[current.node];
					
					if(nextNode == e){
						cnt++;
						min = Math.min(cost[nextNode], min);
					}
					
					pq.offer(new Node(nextNode, nextCost));
				}
			}
		}
		
		return min;
	}
	
	private static int count(){
		int cnt = 0;
		
		for(int i = 1; i < n + 1; i++){
			if(cost[i] != INF){
				cnt++;
			}
		}
		
		return cnt;
	}
	
	private static String getPath(){
		for(int i = 1; i < n + 1; i++){
			if(cost[i] != INF){
				sb.append(i).append(SPACE);
			}
		}		
		return sb.toString();
	}
}
