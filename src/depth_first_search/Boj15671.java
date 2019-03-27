package depth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15671번: 오델로
 *
 *	@see https://www.acmicpc.net/problem/15671/
 *
 */
public class Boj15671 {
	private static final String NEW_LINE = "\n";
	
	private static final char WHITE = 'W', BLACK = 'B', EMPTY = '.';
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
	private static final int ROW = 0, COL = 1;
	
	private static char[][] map = new char[6][6];
	private static ArrayList<Point> change;
	
	private static class Point{
		int row;
		int col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < 6; i++) {
			Arrays.fill(map[i], EMPTY);
		}
		
		map[2][2] = map[3][3] = WHITE;		// 초기 상태
		map[2][3] = map[3][2] = BLACK;
		
		boolean first = true;
		
		while(N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken()) - 1;
			int col = Integer.parseInt(st.nextToken()) - 1;
			
			puts(new Point(row, col), first);		// 돌 놓기 검은색부터 번갈아가며
			first = !first;
		}
		
		System.out.println(winner());
	}
	
	private static void puts(Point current, boolean black) {
		if(black) map[current.row][current.col] = BLACK;		// 현 위치에 돌 놓기
		else map[current.row][current.col] = WHITE;
		
		for(int[] DIRECTION: DIRECTIONS) {
			change = new ArrayList<>();
					
			int nextRow = current.row + DIRECTION[ROW];
			int nextCol = current.col + DIRECTION[COL];
			
			if(nextRow < 0 || nextRow >= 6 || nextCol < 0 || nextCol >= 6) continue;
			if(map[current.row][current.col] == map[nextRow][nextCol] || map[nextRow][nextCol] == EMPTY) continue;
			
			dfs(new Point(DIRECTION[ROW], DIRECTION[COL]), new Point(nextRow, nextCol), map[current.row][current.col], black);
			
			for(Point p: change) {						// 변경 가능하면 저장해둔 지점을 통해 맵 갱신
				if(black) map[p.row][p.col] = BLACK;
				else map[p.row][p.col] = WHITE;
			}
		}
	}
	
	private static void dfs(Point dir, Point p, char current, boolean color) {
		if(p.row < 0 || p.row >= 6 || p.col < 0 || p.col >= 6 || map[p.row][p.col] == EMPTY) {		// 둘러 싸지 못한 경우
			change = new ArrayList<>();
			return;
		}
		
		if(current == map[p.row][p.col]) return;	// 정상적으로 둘러 싼 경우
		
		change.add(new Point(p.row, p.col));		// 현재 놓은 돌과 다른색을 가지는 돌들의 위치
		
		int nextRow = p.row + dir.row;
		int nextCol = p.col + dir.col;
		
		dfs(new Point(dir.row, dir.col), new Point(nextRow, nextCol), current, color);
	}
	
	private static StringBuilder winner() {
		StringBuilder sb = new StringBuilder();
		int wCount = 0, bCount = 0;
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				if(map[i][j] == WHITE) wCount++;
				else if(map[i][j] == BLACK) bCount++;
				
				sb.append(map[i][j]);
			}
			sb.append(NEW_LINE);
		}

		sb.append(wCount > bCount ? "White": "Black").append(NEW_LINE);
		return sb;
	}
}
