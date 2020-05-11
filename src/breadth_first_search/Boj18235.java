package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 18235번: 지금 만나러 갑니다.
 *
 * @see https://www.acmicpc.net/problem/18235/
 *
 */
public class Boj18235 {
	private static int[][][] visit;
	private static int INF = 1_000_000;

	private static class Pair{
		int day;
		int pos;

		public Pair(int day, int pos) {
			this.day = day;
			this.pos = pos;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());

		visit = new int[2][20][N + 1];

		bfs(N, A, visit[0]);
		bfs(N, B, visit[1]);

		System.out.println(search(N));
	}
	
	private static void bfs(int n, int start, int[][] visit) {
		for(int i = 0; i < 20; i++) {
			Arrays.fill(visit[i], INF);
		}

		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair(0, start));

		visit[0][start] = 0;					// [day][position]

		while(!q.isEmpty()) {
			Pair current = q.poll();

			int dir = (int) Math.pow(2, current.day);
			int[] nexts = {current.pos + dir, current.pos - dir};

			for(int next: nexts) {
				if(next <= 0 || next > n) continue;
				if(visit[current.day + 1][next] <= visit[current.day][current.pos] + 1) continue;
				visit[current.day + 1][next] = visit[current.day][current.pos] + 1;

				q.offer(new Pair(current.day + 1, next));
			}
		}
	}

	private static int search(int n) {
		int result = INF;

		for(int i = 0; i < 20; i++) {			// find minimum
			for(int j = 0; j < n; j++) {
				if(visit[0][i][j] == visit[1][i][j]) result = Math.min(result, visit[0][i][j]);
			}
		}

		return result == INF ? -1: result;
	}
}
