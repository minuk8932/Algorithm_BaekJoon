package bellman_ford;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11657번 : 타임머신
 *
 *	@see https://www.acmicpc.net/problem/11657
 *
 */
public class Boj11657 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";

	private static final int NO_WAY = -1;
	private static final int INF = 100_000_001;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		ArrayList<PathCost>[] bus = new ArrayList[N + 1];
		for(int i = 0; i < N + 1; i++) bus[i] = new ArrayList<>();
		
		long[] dist = new long[N + 1];
		Arrays.fill(dist, INF);

		for (int i = 1; i < M + 1; i++) {
			st = new StringTokenizer(br.readLine(), SPACE);

			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());

			bus[A].add(new PathCost(B, C));
		}
		br.close();
		
		dist[1] = 0;
		StringBuilder sb = new StringBuilder();
		
		if(hasShortestWay(bus, dist, N)){
			for(int i = 2; i < N + 1; i++){
				sb.append(dist[i] == INF ? NO_WAY : dist[i]).append(NEW_LINE);
			}
		}
		else{
			sb.append(NO_WAY).append(NEW_LINE);
		}
		
		System.out.print(sb.toString());
	}

	static boolean hasShortestWay(ArrayList<PathCost>[] pc, long[] dist, int N) {
		for (int i = 1; i < N + 1; i++) {
			for (int start = 1; start < N + 1; start++) {
				for(PathCost p: pc[start]) {
					int end = p.e;
					int cost = p.v;
					
					if(dist[end] > dist[start] + cost) {
						if(dist[start] == INF && dist[end] == INF && cost < 0) continue;	// update not approved
						dist[end] = dist[start] + cost;

						if(i == N) return false;											// negative cycle
					}
				}
			}
		}

		return true;
	}

	private static class PathCost {
		int e;
		int v;
		
		public PathCost(int e, int v) {
			this.e = e;
			this.v = v;
		}
	}
}
