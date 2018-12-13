package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 16509번: 장군
 *
 *	@see https://www.acmicpc.net/problem/16509/
 *
 */
public class Boj16509 {
	private static final char KING = 'k';
	private static final char PHASE = 'p';
	private static final char EMPTY = '.';
	
	private static final int[][] DIRECTIONS = {{-3, -2}, {-3, 2}, {-2, 3}, {2, 3}, {3, 2}, {3, -2}, {2, -3}, {-2, -3}};
	private static final int[][][] SITE_CHECK = {{{-1, 0}, {-1, 0}, {0, 1}, {0, 1}, {1, 0}, {1, 0}, {0, -1}, {0, -1}}, 
												{{-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st = new StringTokenizer(br.readLine());
		Point phase = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		
		st = new StringTokenizer(br.readLine());
		Point king = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		
		br.close();
		
		char[][] kChess = init(phase, king);
		System.out.println(bfs(kChess, phase, king));
	}
	
	private static class Point{
		int row;
		int col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	private static char[][] init(Point p, Point k){		// 장기판 채우기
		char[][] arr = new char[10][9];
		
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
				if(i == p.row && j == p.col) {
					arr[i][j] = PHASE;
				}
				
				else if(i == k.row && j == k.col) {
					arr[i][j] = KING;
				}
				
				else {
					arr[i][j] = EMPTY;
				}
			}
		}
		
		return arr;
	}
	
	private static int bfs(char[][] map, Point start, Point end) {
		int[][] isVisited = new int[10][9];
		
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(start.row, start.col));
		
		isVisited[start.row][start.col] = 1;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			for(int move = 0; move < DIRECTIONS.length; move++) {
				int nextRow = current.row + DIRECTIONS[move][ROW];
				int nextCol = current.col + DIRECTIONS[move][COL];
				
				if(!canMoving(map, move, current.row, current.col, 0) || 
						!canMoving(map, move, current.row, current.col, 1)) continue;
				
				if(nextRow >= 0 && nextRow < 10 && nextCol >= 0 && nextCol < 9) {
					if(isVisited[nextRow][nextCol] == 0 && (map[nextRow][nextCol] == EMPTY || map[nextRow][nextCol] == KING)) {
						isVisited[nextRow][nextCol] = isVisited[current.row][current.col] + 1;
						
						if(nextRow == end.row && nextCol == end.col) return isVisited[nextRow][nextCol] - 1;	// 장군인 경우
						q.offer(new Point(nextRow, nextCol));
					}
				}
			}
		}
		
		return -1;
	}
	
	private static boolean canMoving(char[][] arr, int idx, int row, int col, int order) {		// 상이 움직일 수 있는지 체크
		int nRow = row + SITE_CHECK[order][idx][ROW];
		int nCol = col + SITE_CHECK[order][idx][COL];
		
		if(nRow < 0 || nRow >= 10 || nCol < 0 || nCol >= 9) return false;
		
		if(arr[nRow][nCol] == EMPTY) return true;
		else return false;
	}
}
