package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 13459번: 구슬 탈출
 *
 *	@see https://www.acmicpc.net/problem/13459/
 *
 */
public class Boj13459 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static final char BLOCK = '#';
	private static final char HOLE = 'O';
	private static final char BLUE = 'B';
	private static final char RED = 'R';
	private static final char EMPTY = '.';
	
	private static class Point{
		int row;
		int col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	private static class BallPoint{
		Point red;
		Point blue;
		
		public BallPoint(Point red, Point blue) {
			this.red = red;
			this.blue = blue;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[N][M];
		Point[] ball = new Point[2];
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
				
				if(map[i][j] == RED) {
					ball[0] = new Point(i, j);
					map[i][j] = EMPTY;
				}
				
				if(map[i][j] == BLUE) {
					ball[1] = new Point(i, j);
					map[i][j] = EMPTY;
				}
			}
		}
		
		System.out.println(bfs(N, M, map, ball));
	}
	
	private static int bfs(int n, int m, char[][] arr, Point[] start) {
		boolean[][][][] isVisited = new boolean[n][m][n][m];
		
		int count = 0;
		
		Queue<BallPoint> q = new LinkedList<>();
		q.offer(new BallPoint(start[0], start[1]));
		
		isVisited[start[0].row][start[0].col][start[1].row][start[1].col] = true;
		
		while(!q.isEmpty()) {
			int size = q.size();
			
			while(size-- > 0) {
				BallPoint current = q.poll();
				Point red = current.red;
				Point blue = current.blue;
				
				if(arr[red.row][red.col] == HOLE && arr[blue.row][blue.col] != HOLE) return 1;
				
				for(final int[] DIRECTION: DIRECTIONS) {
					Point nextRed = new Point(red.row, red.col);
					Point nextBlue = new Point(blue.row, blue.col);
					
					nextRed = moveBall(arr, nextRed, DIRECTION);		// 구슬 움직임
					nextBlue = moveBall(arr, nextBlue, DIRECTION);
					
					if(nextRed.row == nextBlue.row && nextRed.col == nextBlue.col) {		// 겹치는 경우
						if(arr[nextRed.row][nextRed.col] == HOLE) continue;
						
						if(move(nextRed, red) < move(nextBlue, blue)) nextBlue = back(nextBlue, DIRECTION);	// 뒤로 밀어야 하는 구슬 결정
						else nextRed = back(nextRed, DIRECTION);
					}
					
					if(isVisited[nextRed.row][nextRed.col][nextBlue.row][nextBlue.col]) continue;
					isVisited[nextRed.row][nextRed.col][nextBlue.row][nextBlue.col] = true;
					
					q.offer(new BallPoint(new Point(nextRed.row, nextRed.col), new Point(nextBlue.row, nextBlue.col)));
				}
			}
			
			if(count > 9) return 0;		// 10번을 넘어갈 경우
			count++;
		}

		return 0;
	}
	
	private static Point moveBall(char[][] arr, Point next, int[] D) {
		while(arr[next.row + D[ROW]][next.col + D[COL]] != BLOCK && arr[next.row][next.col] != HOLE) {
			next.row += D[ROW];
			next.col += D[COL];
		}
		
		return new Point(next.row, next.col);
	}
	
	private static int move(Point next, Point current) {
		return Math.abs(next.row - current.row) + Math.abs(next.col - current.col);
	}
	
	private static Point back(Point next, int[] D) {
		return new Point(next.row - D[ROW], next.col - D[COL]);
	}
}
