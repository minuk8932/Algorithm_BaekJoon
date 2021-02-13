import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Boj16707 {

	private static ArrayList<LandMark> input = new ArrayList<>();
	private static ArrayList<Integer>[] connection;

	private static int[][] capacity;
	private static int[][] flow;
	private static int[][] cost;

	private static final int INF = 1_000_000_000;

	private static class LandMark {
		int node1;
		int node2;
		int cost;

		public LandMark(int node1, int node2, int cost) {
			this.node1 = node1;
			this.node2 = node2;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int size = N * 4 + 3;
		int src = 0;
		int stop = N * 2 + 1;
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
			int node1 = Integer.parseInt(st.nextToken()) - 1;
			int node2 = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());

			input.add(new LandMark(node1, node2, cost));
		}

		for(int i = 1; i <= N; i++) {
			capacity[src][i] = 1;
			connection[src].add(i);
			connection[i].add(src);

			capacity[i + N][stop] = 1;
			connection[i + N].add(stop);
			connection[stop].add(i + N);

			capacity[stop][N * 2 + 1 + i] = 1;
			connection[N * 2 + 1 + i].add(stop);
			connection[stop].add(N * 2 + 1 + i);

			capacity[N * 3 + 1 + i][snk] = 1;
			connection[N * 3 + 1 + i].add(snk);
			connection[snk].add(N * 3 + 1 + i);
		}

		linker(input, 1, N + 1);
		linker(input, 2 * N + 2, 3 * N + 2);

		System.out.println(maximumFlowMinimumCost(src, stop, size)
				+ maximumFlowMinimumCost(stop, snk, size));
	}

	private static void linker(ArrayList<LandMark> input, int add1, int add2) {
		for(LandMark in: input) {
			int from = in.node1 + add1;
			int to = in.node2 + add2;

			connection[from].add(to);
			connection[to].add(from);
			capacity[from][to] = 1;

			cost[from][to] = in.cost;
			cost[to][from] = -in.cost;
		}
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
