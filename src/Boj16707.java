import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Boj16707 {

	private static ArrayList<Integer>[] connection;

	private static int[][] capacity;
	private static int[][] flow;
	private static int[][] cost;

	private static final int INF = 1_000_000_000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int size = N * 2 + 3;
		int src = 0;
		int stop = size - 2;
		int snk = size - 1;

		capacity = new int[size][size];
		flow = new int[size][size];
		cost = new int[size][size];

		connection = new ArrayList[size];
		for(int i = 0; i < size; i++) {
			connection[i] = new ArrayList<>();
		}

		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			linker(node1 + N, node2, c, 1);
		}

		for(int i = 1; i <= N; i++) {
			linker(i, i + N, 0, 1);
		}

		linker(src, 1, 0, 1);
		linker(2, stop, 0, 1);
		linker(stop, N + 2, 0, 1);
		linker(N, snk, 0, 1);

		System.out.println(maximumFlowMinimumCost(src, stop, size) + maximumFlowMinimumCost(stop, snk, size));
	}

	private static void linker(int from, int to, int value, int cap) {
		connection[from].add(to);
		connection[to].add(from);

		capacity[from][to] = cap;
		cost[from][to] = value;
		cost[to][from] = -value;
	}

	private static int maximumFlowMinimumCost(int S, int T, int N) {
		int[] prev = new int[N];
		int[] dist = new int[N];
		int result = 0;

		while(true){
			Queue<Integer> q = new LinkedList<>();

			boolean[] inQ = new boolean[N];
			Arrays.fill(prev, -1);
			Arrays.fill(dist, INF);

			q.offer(S);
			dist[S] = 0;
			inQ[S] = true;

			while(!q.isEmpty()){
				int current = q.poll();
				inQ[current] = false;

				for(int next: connection[current]){
					if(dist[next] <= dist[current] + cost[current][next]) continue;
					if(capacity[current][next] <= flow[current][next]) continue;
					dist[next] = dist[current] + cost[current][next];
					prev[next] = current;

					if(!inQ[next]){
						q.offer(next);
						inQ[next] = true;
					}
				}
			}

			if(prev[T] == -1) break;

			int maxFlow = INF;
			for(int i = T; i != S; i = prev[i]) {
				maxFlow = Math.min(maxFlow, capacity[prev[i]][i] - flow[prev[i]][i]);
			}

			for(int i = T; i != S; i = prev[i]){
				result += maxFlow * cost[prev[i]][i];
				flow[prev[i]][i] += maxFlow;
				flow[i][prev[i]] -= maxFlow;
			}
		}

		return result;
	}
}
