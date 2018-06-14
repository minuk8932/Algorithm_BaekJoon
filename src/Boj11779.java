import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj11779 {
	private static ArrayList<Node>[] path = null;
	private static ArrayList<Node>[] revPath = null;
	private static StringTokenizer st = null;
	private static StringBuilder sb = null;

	private static int n = 0;
	private static int m = 0;
	private static int[] cost = null;
	private static boolean[] isVisited = null;

	private static final int INF = 100_001;
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		path = new ArrayList[n + 1];
		revPath = new ArrayList[n + 1];
		isVisited = new boolean[n + 1];
		
		for (int i = 0; i < n + 1; i++) {
			path[i] = new ArrayList<>();
			revPath[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			path[from].add(new Node(to, cost));
			revPath[to].add(new Node(from, cost));
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		cost = new int[n + 1];
		Arrays.fill(cost, INF);
		
		sb = new StringBuilder();
		sb.append(dijkstra(start, end)).append(NEW_LINE);
		
		String route = bfs(start, end);
		
		int cnt = 0;
		for(int i = 1; i < n + 1; i++){
			if(isVisited[i]) {
				cnt++;
			}
		}
		sb.append(cnt).append(NEW_LINE);
		
		System.out.print(sb.toString());
		System.out.println(route.trim());
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
		int min = INF;
		
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
						min = Math.min(cost[nextNode], min);
					}
					
					pq.offer(new Node(nextNode, nextCost));
				}
			}
		}
		
		return min;
	}
	
	private static String bfs(int s, int e) {
		StringBuilder pathSb = new StringBuilder();
		
		Queue<Integer> q = new LinkedList<>();
		q.offer(e);
		isVisited[e] = true;
		pathSb.append(SPACE).append(e);
		
		while(!q.isEmpty()) {
			int current = q.poll();
			
			if(current != s) {
				boolean chk = false;
				
				for(Node next : revPath[current]) {
					if(cost[current] == cost[next.node] + next.val && !chk) {
						isVisited[next.node] = true;
						
						pathSb.append(SPACE).append(next.node);
						
						chk = true;
						q.offer(next.node);
					}
				}
			}
		}
		
		return pathSb.reverse().toString();
	}
}
