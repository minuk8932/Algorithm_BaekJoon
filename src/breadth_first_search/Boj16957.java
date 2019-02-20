package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 16957번: 체스판 위의 공
 *
 *	@see https://www.acmicpc.net/problem/16957/
 *
 */
public class Boj16957 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final int INF = 300_001;
	
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	private static int[][] isVisited;
	
	private static class Point implements Comparable<Point>{
		int weight;
		int row;
		int col;
		
		public Point(int weight, int row, int col) {
			this.weight = weight;
			this.row = row;
			this.col = col;
		}

		@Override
		public int compareTo(Point p) {
			return this.weight > p.weight ? -1 : 1;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Point> balls = new PriorityQueue<>();
		int[][] chess = new int[R][C];
		isVisited = new int[R][C];
		
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < C; j++) {
				chess[i][j] = Integer.parseInt(st.nextToken());
				balls.offer(new Point(chess[i][j], i, j));
				isVisited[i][j] = 1;
			}
		}
		
		bfs(R, C, balls, chess);
		System.out.println(remake(R, C));
	}
	
	private static void bfs(int r, int c, PriorityQueue<Point> pq, int[][] map) {			// 숫자가 큰 판부터 탐색	
		while(!pq.isEmpty()) {
			Point current = pq.poll();
			Point edge = new Point(INF, -1, -1);
			
			for(int index = 0; index < 8; index++) {
				int nextRow = DIRECTIONS[index][ROW] + current.row;
				int nextCol = DIRECTIONS[index][COL] + current.col;
				
				if(nextRow >= r || nextCol >= c || nextRow < 0 || nextCol < 0 || map[nextRow][nextCol] > map[current.row][current.col]) continue;
				if(edge.weight > map[nextRow][nextCol]) edge = new Point(map[nextRow][nextCol], nextRow, nextCol);
			}
			
			if(edge.weight != INF) {			// 최소 크기 판이 존재하는 경우
				isVisited[edge.row][edge.col] += isVisited[current.row][current.col];
				isVisited[current.row][current.col] = 0;
			}
		}		
	}
	
	private static StringBuilder remake(int r, int c) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				sb.append(isVisited[i][j]).append(SPACE);
			}
			
			sb.append(NEW_LINE);
		}
		
		return sb;
	}
}
