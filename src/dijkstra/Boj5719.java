package dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 5719번: 거의 최단 경로
 *
 * @see https://www.acmicpc.net/problem/5719
 *
 */
public class Boj5719 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	private static final int INF = 1_000_000_001;

	private static ArrayList<Node>[] map;
	private static int[][] removed;
	private static int[] cost = null;
	private static int N = 0;
	private static int M = 0;

	private static class Node implements Comparable<Node>{
		int dest;
		int cost;

		public Node(int dest, int cost){
			this.dest = dest;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node n) {
			return this.cost < n.cost ? -1 : 1;
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		while(true){
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			if(N == 0 && M == 0) break;

			st = new StringTokenizer(br.readLine(), SPACE);
			int S = Integer.parseInt(st.nextToken());
			int D = Integer.parseInt(st.nextToken());

			map = new ArrayList[N];
			cost = new int[N];
			removed = new int[N][N];

			for(int i = 0; i < N; i++){
				map[i] = new ArrayList<>();
				cost[i] = INF;
				Arrays.fill(removed[i], -1);
			}

			for(int i = 0; i < M; i++){
				st = new StringTokenizer(br.readLine());
				int U = Integer.parseInt(st.nextToken());
				int V = Integer.parseInt(st.nextToken());
				int P = Integer.parseInt(st.nextToken());

				map[U].add(new Node(V, P));
				removed[U][V] = P;		// save for reversed
			}

			dijkstra(S);				// find shortest path
			bfs(D);						// remove shortest path
			Arrays.fill(cost, INF);
			dijkstra(S);				// find almost shortest path

			sb.append(cost[D] == INF ? -1 : cost[D]).append(NEW_LINE);
		}

		System.out.println(sb.toString());
	}

	private static void bfs(int D) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(D);

		while(!q.isEmpty()) {
			int current = q.poll();

			for (int next = 0; next < N; next++) {
				if (cost[current] == cost[next] + removed[next][current] && removed[next][current] != -1) {
					removed[next][current] = -1;
					q.offer(next);
				}
			}
		}
	}

	private static void dijkstra(int S){
		cost[S] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(S, cost[S]));

		while(!pq.isEmpty()){
			Node current = pq.poll();
			if(cost[current.dest] < current.cost) continue;

			for(Node next : map[current.dest]){
				if(removed[current.dest][next.dest] == -1) continue;
				if(cost[next.dest] > cost[current.dest] + next.cost ){
					cost[next.dest] = cost[current.dest] + next.cost;

					pq.offer(new Node(next.dest, cost[next.dest]));
				}
			}
		}
	}
}
