package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author minchoba
 * 백준 16174번: 점프왕 쪨리(Large)
 * 
 * @see https://www.acmicpc.net/problem/16174/
 *
 */
public class Boj16174 {
	private static final int ROW = 0, COL = 1;
	
	private static class Point{
		int row, col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(bfs(N, map) ? "HaruHaru" : "Hing");
	}
	
	private static boolean bfs(int n, int[][] arr) {		// 풀이는 16173번과 동일, 다만 메모리 초과 방지를 위해 방문 배열 사용
		boolean[][] isVisited = new boolean[n][n];
		
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(0, 0));
		isVisited[0][0] = true;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			int move = arr[current.row][current.col];
			if(move == 0) continue;
			
			int[][] d = {{1 * move, 0}, {0, 1 * move}};
			
			for(int[] dir: d) {
				int nextRow = current.row + dir[ROW];
				int nextCol = current.col + dir[COL];
				
				if(nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) continue;
				if(isVisited[nextRow][nextCol]) continue;
				isVisited[nextRow][nextCol] = true;
				
				if(arr[nextRow][nextCol] == -1) return true;
				
				q.offer(new Point(nextRow, nextCol));
			}
		}
		
		
		return false;
	}
}
