package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 18188번: 다오의 데이트
 *
 *	@see https://www.acmicpc.net/problem/18188/
 *
 */
public class Boj18188 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0, COL = 1;
	private static final char UP = 'W', DOWN = 'S', RIGHT = 'D', LEFT = 'A';
	private static final char DAO = 'D', DIZ = 'Z', BLOCK = '@';
	private static final char EMP = ' ';
	
	private static StringBuilder sb = new StringBuilder();
	private static String NEW_LINE = "\n";
	
	private static ArrayList<Integer> dirs = new ArrayList<>();
	private static Point start = new Point(0, 0, 0, "");
	private static Point end = new Point(0, 0, 0, "");
	private static int[][] visit;
	
	private static class Point{
		int row;
		int col;
		int seq;
		String a;
		
		public Point(int row, int col, int seq, String a) {
			this.row = row;
			this.col = col;
			this.seq = seq;
			this.a = a;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[H][W];
		visit = new int[H][W];
		for(int i = 0; i < H; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < W; j++) {
				map[i][j] = line.charAt(j);
				
				if(map[i][j] == DAO) start = new Point(i, j, 0, "");
				if(map[i][j] == DIZ) end = new Point(i, j, 0, "");
			}
		}
		
		int N = Integer.parseInt(br.readLine());
		while(N-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = getIdx(st.nextToken().charAt(0));
			int b = getIdx(st.nextToken().charAt(0));
			
			dirs.add(a * 10 + b);
		}
		
		boolean result = bfs(H, W, map);
		System.out.println(!result ? "NO": sb.toString());
	}
	
	private static boolean bfs(int h, int w, char[][] map) {		
		Queue<Point> q = new LinkedList<>();
		q.offer(start);
		visit[start.row][start.col] = 1;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			if(current.seq == dirs.size()) continue;
			int d = dirs.get(current.seq);
			int[] ds = {d / 10, d % 10};
				
			for(int move: ds) {
				int nextRow = current.row + DIRECTIONS[move - 1][ROW];
				int nextCol = current.col + DIRECTIONS[move - 1][COL];
				
				if(nextRow < 0 || nextCol < 0 || nextRow >= h || nextCol >= w) continue;
				if(map[nextRow][nextCol] == BLOCK) continue;
				visit[nextRow][nextCol] = visit[current.row][current.col] + 1;
					
				char c = EMP;
				
				if(move == 1) c = DOWN;
				else if(move == 2) c = RIGHT;
				else if(move == 3) c = UP;
				else c = LEFT;
					
				if(nextRow == end.row && nextCol == end.col) {									// meet
					sb.append("YES").append(NEW_LINE).append(current.a).append(c);
					return true;
				}
				
				q.offer(new Point(nextRow, nextCol, current.seq + 1, current.a + c));			// save path
			}
		}
		
		return false;						// no way
	}
	
	private static int getIdx(char a) {		// set direction
		int idx = -1;
		
		switch(a) {
		case UP:
			idx = 3;
			break;
			
		case DOWN:
			idx = 1;
			break;
			
		case LEFT:
			idx = 4;
			break;
			
		case RIGHT:
			idx = 2;
			break;
		}
		
		return idx;
	}
}
