package dijkstra;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1504번: 특정한 최단 경로
 *
 * @see https://www.acmicpc.net/problem/1504
 *
 */
public class Boj1504 {
	private static final String SPACE = " ";
	private static final long INF = 1_000_000_000_000_000L;

	private static class Node{
		int dest;
		long cost;

		public Node(int dest, long cost){
			this.dest = dest;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		int N = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		ArrayList<Node>[] map = new ArrayList[N];
		long[][] cost = new long[3][N];

		for(int i = 0; i < N; i++){
			cost[0][i] = INF;
			cost[1][i] = INF;
			cost[2][i] = INF;
			map[i] = new ArrayList<>();
		}

		while(E-- > 0){
			st = new StringTokenizer(br.readLine(), SPACE);
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			long c = Long.parseLong(st.nextToken());

			map[a].add(new Node(b, c));
			map[b].add(new Node(a, c));
		}

		st = new StringTokenizer(br.readLine(), SPACE);
		int must1 = Integer.parseInt(st.nextToken()) - 1;
		int must2 = Integer.parseInt(st.nextToken()) - 1;

		dijkstra(0, map, cost[0]);
		dijkstra(must1, map, cost[1]);
		dijkstra(must2, map, cost[2]);

		long min = Math.min(cost[0][must1] + cost[1][must2] + cost[2][N - 1]
				, cost[0][must2] + cost[2][must1] + cost[1][N - 1]);
		System.out.println(min >= INF ? -1 : min);
	}

	/**
	 *
	 * dijkstra
	 *
	 * @param start
	 * @param map
	 * @param cost
	 */
	private static void dijkstra(int start, ArrayList<Node>[] map, long[] cost){
		cost[start] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(x -> x.cost));
		pq.offer(new Node(start, cost[start]));

		while(!pq.isEmpty()){
			Node current = pq.poll();
			if(cost[current.dest] < current.cost) continue;

			for(Node next : map[current.dest]){
				if(cost[next.dest] <= cost[current.dest] + next.cost) continue;
				cost[next.dest] = cost[current.dest] + next.cost;

				pq.offer(new Node(next.dest, cost[next.dest]));
			}
		}
	}
}
