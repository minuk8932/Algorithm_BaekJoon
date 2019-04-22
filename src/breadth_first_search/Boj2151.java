package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * 	@author minchoba
 *	백준 2151번: 거울 설치
 *
 *	@see https://www.acmicpc.net/problem/2151/
 *
 */
public class Boj2151 {
	private static final char DOOR = '#';
	private static final char MIRROR = '!';
	private static final char BLOCK = '*';
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1 ,0}, {0, -1}};
	private static final int ROW = 0, COL = 1;
	
	private static Point[] target = new Point[2];
	
	private static class Point{
		int row;
		int col;
		int dir;
		
		public Point(int row, int col, int dir) {
			this.row = row;
			this.col = col;
			this.dir = dir;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		char[][] map = new char[N][N];
		int index = 0;
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < N; j++) {
				map[i][j] = line.charAt(j);
				if(map[i][j] == DOOR) target[index++] = new Point(i, j, -1);
			}
		}
		
		System.out.println(mirrorCount(N, map));
	}
	
	private static int mirrorCount(int n, char[][] arr) {
		int[][][] visit = new int[n][n][4];					// 임의의 거울에서 같은 방향으로 쐈는지의 여부도 체크해야함

		Queue<Point> q = new LinkedList<>();
		q.offer(target[0]);
		
		for(int i = 0; i < 4; i++) {
			visit[target[0].row][target[0].col][i] = 1;
		}
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int nextRow = current.row + DIRECTIONS[i][ROW];
				int nextCol = current.col + DIRECTIONS[i][COL];
				int nextDir = i;
				
				if(chDir(n, nextRow, nextCol, nextDir, arr, visit)) continue;
				
				int nextVisit = 0;
				
				if(current.dir == -1) {			// 초기 위치일 경우
					nextVisit = 1;
				}
				else {
					if(current.dir == nextDir) nextVisit = visit[current.row][current.col][current.dir];		// 방향이 다르면 +1
					else nextVisit = visit[current.row][current.col][current.dir] + 1;
				}
				
				while(!chDir(n, nextRow, nextCol, nextDir, arr, visit)) {			// 거울에 반사되면 일단 일직선으로 쏘아줌
					visit[nextRow][nextCol][nextDir] = nextVisit;
					
					if(arr[nextRow][nextCol] == MIRROR) q.offer(new Point(nextRow, nextCol, nextDir));		// 중간에 거울 존재시
					
					nextRow += DIRECTIONS[i][ROW];
					nextCol += DIRECTIONS[i][COL];
				}
			}
		}
		
		int result = Integer.MAX_VALUE;
		for(int i = 0; i < 4; i++) {				// 도달 가능한 경우 중 최솟값
			if(visit[target[1].row][target[1].col][i] != 0) result = Math.min(visit[target[1].row][target[1].col][i] - 1, result);
		}
		
		return result;
	}
	
	private static boolean chDir(int n, int row, int col, int dir, char[][] arr, int[][][] visit) {
		return row < 0 || col < 0 || row >= n || col >= n || 
				arr[row][col] == BLOCK || visit[row][col][dir] != 0 ? true: false;
	}
}
