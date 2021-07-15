package dijkstra;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 
 * 	@author minchoba
 *	백준 11779번: 최소비용 구하기 2
 *
 *	@see https://www.acmicpc.net/problem/11779/
 *
 */
public class Boj11779 {
	private static ArrayList<Node>[] map = null;
	private static StringTokenizer st = null;

	private static long[] cost = null;
	private static int[] trace = null;

	private static final long INF = 1_000_000_000_000L;
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";

	private static class Node implements Comparable<Node> {
		int node;
		long val;

		public Node(int node, long val) {
			this.node = node;
			this.val = val;
		}

		@Override
		public int compareTo(Node n) {
			if(this.val < n.val) return -1;
			else if(this.val > n.val) return 1;
			else return 0;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		map = new ArrayList[n];
		trace = new int[n];
		cost = new long[n];
		
		for (int i = 0; i < n; i++) {
			map[i] = new ArrayList<>();
			trace[i] = -1;
			cost[i] = INF;
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());
			
			map[from].add(new Node(to, cost));
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken()) - 1;
		int end = Integer.parseInt(st.nextToken()) - 1;

		dijkstra(start);
		System.out.print(printPath(start, end));
	}

	/**
	 *
	 * Print path
	 *
	 * line 91 ~ 94: tracking the shortest path
	 *
	 * @param s
	 * @param e
	 * @return
	 */
	private static String printPath(int s, int e) {
		StringBuilder sb = new StringBuilder();
		sb.append(cost[e]).append(NEW_LINE);

		Deque<Integer> path = new ArrayDeque<>();
		path.add(e);

		while(trace[e] != s) {
			e = trace[e];
			path.offer(e);
		}

		path.offer(s);
		sb.append(path.size()).append(NEW_LINE);

		while(!path.isEmpty()) {
			sb.append(path.pollLast() + 1).append(SPACE);
		}

		return sb.toString();
	}

	/**
	 *
	 * Dijkstra
	 *
	 * line 123: pre-cutting by DP
	 * line 130: save path
	 *
	 * @param s
	 */
	private static void dijkstra(int s) {
		cost[s] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(s, 0));

		while (!pq.isEmpty()) {
			Node current = pq.poll();
			if(current.val > cost[current.node]) continue;

			for (Node next : map[current.node]) {
				long nextCost = next.val + cost[current.node];

				if (cost[next.node] <= nextCost)  continue;
				cost[next.node] = nextCost;
				trace[next.node] = current.node;
					
				pq.offer(new Node(next.node, cost[next.node]));
			}
		}
	}
}
