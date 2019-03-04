package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 9311번: Robot in a Maze
 *
 *	@see https://www.acmicpc.net/problem/9311/
 *
 */
public class Boj9311 {
	private static final String[] FORMAT = {"No Exit", "Shortest Path: "};
	private static final String NEW_LINE = "\n";
	
	private static final char EXIT = 'G';
	private static final char ROBOT = 'S';
	private static final char BLOCK = 'X';
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static class Point{
		int row;
		int col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	public static void main(String[] ags) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			ArrayList<Point> point = new ArrayList<>();
			Point end = new Point(-1, -1);
			
			char[][] map = new char[R][C];
			
			for(int i = 0; i < R; i++) {
				String line = br.readLine();
				
				for(int j = 0; j < C; j++) {
					map[i][j] = line.charAt(j);
					if(map[i][j] == EXIT) point.add(new Point(i, j));		// 출구 먼저 저장
					if(map[i][j] == ROBOT) end = new Point(i, j);
				}
			}
			
			int cost = loop(R, C, map, point, end);
			sb.append(cost == -1 ? FORMAT[0] : FORMAT[1] + cost).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static int loop(int r, int c, char[][] arr, ArrayList<Point> s, Point end) {
		int min = Integer.MAX_VALUE;
		
		for(Point start: s) {						// 출구에서 로봇의 위치를 찾아감
			int[][] isVisited = new int[r][c];
			
			Queue<Point> q = new LinkedList<>();
			q.offer(start);
			
			isVisited[start.row][start.col] = 1;
			
			while(!q.isEmpty()) {
				Point current = q.poll();
				
				for(final int[] DIRECTION: DIRECTIONS) {
					int nextRow = current.row + DIRECTION[ROW];
					int nextCol = current.col + DIRECTION[COL];
					
					if(nextRow < 0 || nextRow >= r || nextCol < 0 || nextCol >= c) continue;
					if(isVisited[nextRow][nextCol] != 0 || arr[nextRow][nextCol] == BLOCK) continue;
					isVisited[nextRow][nextCol] = isVisited[current.row][current.col] + 1;
					
					if(arr[nextRow][nextCol] == ROBOT) min = Math.min(isVisited[nextRow][nextCol], min);		// 로봇 위치 찾은 경우 최단 경로
					
					q.offer(new Point(nextRow, nextCol));
				}
			}
		}
		
		return min == Integer.MAX_VALUE ? -1 : min - 1;
	}
}
