package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 4991번: 로봇 청소기
 *
 *	@see https://www.acmicpc.net/problem/4991/
 *
 */
public class Boj4991 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";

	private static final char START = 'o';
	private static final char DIRT = '*';
	private static final char BLOCK = 'x';

	private static final int[][] DIRECTIONS = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final int INF = 1_000_001;
	
	private static int res = 0;
	private static boolean[] pass;
	
	private static boolean arrived;

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());

			if (h == 0 && w == 0) {
				break;
			}

			int dustCnt = 1;
			char[][] map = new char[h][w];
			Point[] dust = new Point[10];

			for (int i = 0; i < h; i++) {
				String line = br.readLine();
				for (int j = 0; j < w; j++) {
					map[i][j] = line.charAt(j);

					if (map[i][j] == START) dust[0] = new Point(i, j);
					if (map[i][j] == DIRT) dust[dustCnt++] = new Point(i, j);
				}
			}
			
			arrived = true;
			pass = new boolean[dustCnt];
			res = INF;
			pass[0] = true;
			
			int[][] dist = bfs(map, h, w, dust, dustCnt, 0);
			
			if(arrived) backTracking(dustCnt, dist, 0, 0, 0);		// 청소기가 모두 도달 가능하면 백트래킹
			else res = 0;											// 도달 못하는 경우
			
			sb.append(res == 0 ? -1 : res).append(NEW_LINE);
		}

		System.out.println(sb);
	}

	private static class Point {
		int row;
		int col;

		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	private static void backTracking(int N, int[][] arr, int start, int cost, int count) {		
		if(count == N - 1) {			// N - 1개의 구역을 모두 지났을때의 거리 값 중 최소
			res = Math.min(cost, res);
			return;
		}
		
		for(int next = 0; next < N; next++) {
			if(start == next) continue;
			if(pass[next]) continue;
			pass[next] = true;
			
			backTracking(N, arr, next, cost + arr[start][next], count + 1);		// 다음 먼지를 재귀호출을 통해 접근
			pass[next] = false;			// 백 트래킹
		}
	}

	private static int[][] bfs(char[][] arr, int h, int w, Point[] dust, int dCount, int move) {
		int[][] dist = new int[dCount][dCount];			// 먼지 i ~ j 까지 거리
		
		for(int i = 0; i < dCount; i++) {
			for(int j = 0; j < dCount; j++) {
				if(i == j) continue;
				
				int[][] isVisited = new int[h][w];
				Queue<Point> q = new LinkedList<>();
				
				isVisited[dust[i].row][dust[i].col] = 1;
				
				q.offer(new Point(dust[i].row, dust[i].col));
				
				while(!q.isEmpty()) {
					Point current = q.poll();
					
					for(final int[] DIRECTION: DIRECTIONS) {
						int nextRow = current.row + DIRECTION[ROW];
						int nextCol = current.col + DIRECTION[COL];
						
						if(nextRow < 0 || nextRow >= h || nextCol < 0 || nextCol >= w) continue;
						if(isVisited[nextRow][nextCol] != 0 || arr[nextRow][nextCol] == BLOCK) continue;
						isVisited[nextRow][nextCol] = isVisited[current.row][current.col] + 1;
						
						if(nextRow == dust[j].row && nextCol == dust[j].col) {		// 먼지 또는 시작점 부터, 다른 먼지까지의 거리를 모두 저장
							dist[i][j] = isVisited[nextRow][nextCol] - 1;
						}
						
						q.offer(new Point(nextRow, nextCol));
					}
				}
			}
		}
		
		for(int i = 0; i < dCount; i++) {
			for(int j = 0; j < dCount; j++) {
				if(i == j) continue;
				
				if(dist[i][j] == 0) {			// 만약 청소기가 도달 못하는 곳이 한곳이라도 존재 할 경우
					arrived = false;
					break;
				}
			}
		}
		
		return dist;
	}
}
