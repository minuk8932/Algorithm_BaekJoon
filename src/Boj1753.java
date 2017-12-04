import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 		ArrayList로 구현해야함.
 * 		
 */

public class Boj1753 {
	private static final String SPACE = " ";
	private static final String NO_WAY = "INF";
	private static final String NEW_LINE = "\n";

	private static final int LIMIT = 20_001;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);

		int V = Integer.parseInt(st.nextToken()); // node
		int E = Integer.parseInt(st.nextToken()); // edge
		int K = Integer.parseInt(br.readLine()); // start

		int tmp = E;

		int[][] path = new int[E + 1][E + 1];
		int[][] cost = new int[E + 1][E + 1];

		for (int i = 1; i < E + 1; i++) {
			Arrays.fill(path[i], LIMIT);
			Arrays.fill(cost[i], LIMIT);
		}

		while (tmp-- > 0) {
			st = new StringTokenizer(br.readLine(), SPACE);

			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());

			path[start][end] = Math.min(val, path[start][end]);
		}
		br.close();

		for (int i = 1; i < E + 1; i++) {
			path[i][i] = 0;
		}

		PriorityQueue<Integer> pq = new PriorityQueue<>();

		pq.offer(K);

		while (!pq.isEmpty()) {
			int current = pq.poll();

			for (int next = 1; next < E + 1; next++) {
				if (cost[K][next] < path[current][next] + cost[K][next]) {
					cost[K][next] = path[current][next] + cost[K][next];

					pq.offer(next);

				}
			}
		}

		for (int i = 1; i < E + 1; i++) {
			for (int j = 1; j < E + 1; j++) {
				System.out.print(cost[i][j] + "\t");
			}
			System.out.println();
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 1; i < V + 1; i++) {
			if (path[K][i] == LIMIT) {
				sb.append(NO_WAY).append(NEW_LINE);
			}
			else {
				sb.append(path[K][i]).append(NEW_LINE);
			}
		}

		System.out.println(sb.toString());
	}

}
