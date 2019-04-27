import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Boj9376 {
	private static final String NEW_LINE = "\n";
	
	private static final char DOOR = '#';
	private static final char EMPTY = '.';
	private static final char BLOCK = '*';
	private static final char PRISONER = '$';
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0, COL = 1;
	private static final int INF = 1_000_000;
	
	private static char[][] map;
	
	private static class Point{
		int row;
		int col;
		int cost;
		
		public Point(int row, int col, int cost) {
			this.row = row;
			this.col = col;
			this.cost = cost;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			map = new char[h][w];
			ArrayList<Point> start = new ArrayList<>();
			start.add(new Point(0, 0, 0));
			
			for(int i = 0; i < h; i++) {
				String line = br.readLine();
				for(int j = 0; j < w; j++) {
					map[i][j] = line.charAt(j);
					if(map[i][j] == PRISONER) start.add(new Point(i, j, 0));
				}
			}
			
			int result = bfs(h, w, start);
			sb.append(result).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static int bfs(int H, int W, ArrayList<Point> s) {
		int[][][] visit = new int[3][H][W];
		
		for(int row = 0; row < H; row++) {
			Arrays.fill(visit[0][row], INF);
			Arrays.fill(visit[1][row], INF);
			Arrays.fill(visit[2][row], INF);
		}			
		
		for(int idx = 0; idx < 3; idx++) {
			boolean[][] pass = new boolean[H][W];
			Point start = s.get(idx);
			
			Deque<Point> deq = new LinkedList<>();
			deq.offer(start);
			
			while(!deq.isEmpty()) {
				Point current = deq.poll();
				
				if(current.row < 0 || current.col < 0 || current.row >= H || current.col >= W) continue;
				if(pass[current.row][current.col]) continue;
				pass[current.row][current.col] = true;
				visit[idx][current.row][current.col] = current.cost;
				
				if(idx == 0) System.out.print(current.cost + " " + current.row + " " + current.col);
				
				if(map[current.row][current.col] == BLOCK) continue;
					
				for(final int[] DIRECTION: DIRECTIONS) {
					int nextRow = current.row + DIRECTION[ROW];
					int nextCol = current.col + DIRECTION[COL];
					
					if(map[current.row][current.col] == DOOR) deq.offer(new Point(nextRow, nextCol, current.cost + 1));
					else deq.offerFirst(new Point(nextRow, nextCol, current.cost));
				}
			}
		}
	
		return getResult(H, W, visit);
	}
	
	private static int getResult(int H, int W, int[][][] visit) {
		int min = INF;
		
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				if(map[i][j] == BLOCK) continue;
				min = Math.min(min, visit[0][i][j] + visit[1][i][j] + visit[2][i][j] + (map[i][j] == DOOR ? 1: 0));
			}
		}
		
		return min;
	}
}
