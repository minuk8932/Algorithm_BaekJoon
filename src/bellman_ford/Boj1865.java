package bellman_ford;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author exponential-e
 * 백준 1865번 : 웜홀
 *
 * @see https://www.acmicpc.net/problem/1865
 *
 */
public class Boj1865 {
    private static final String NEW_LINE = "\n";
    private static final String WAY = "YES";
    private static final String NO_WAY = "NO";

    private static ArrayList<PathCost> whole;

    private static final int INF = 10_000_001;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

			whole = new ArrayList<>();

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken()) - 1;
                int e = Integer.parseInt(st.nextToken()) - 1;
                int c = Integer.parseInt(st.nextToken());

				whole.add(new PathCost(s, e, c));
				whole.add(new PathCost(e, s, c));
            }

			for (int i = 0; i < W; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken()) - 1;
				int e = Integer.parseInt(st.nextToken()) - 1;
				int c = Integer.parseInt(st.nextToken());

				whole.add(new PathCost(s, e, -c));						// wormhole
			}

            sb.append(bellmanF(whole, N, 0) ? WAY: NO_WAY).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static boolean bellmanF(ArrayList<PathCost> pc, int N, int index) {
        int[] dist = new int[N];

        Arrays.fill(dist, INF);
        dist[index] = 0;

        boolean flag = false;

        for(int i = 0; i < N; i++) {
			for (PathCost p: pc) {
				if (dist[p.e] > dist[p.s] + p.v) {				// cost update
					dist[p.e] = dist[p.s] + p.v;
					flag = true;
				}
			}

			if(!flag) return flag;
			flag = false;
		}

		for (PathCost p : pc) {
			if (dist[p.e] > dist[p.s] + p.v) return true;		// can back?
		}

        return false;
    }

    private static class PathCost {
        int s;
        int e;
        int v;

        public PathCost(int s, int e, int v) {
            this.s = s;
            this.e = e;
            this.v = v;
        }
    }
}