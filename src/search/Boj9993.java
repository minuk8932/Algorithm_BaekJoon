package search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 9993번: Mirror Field
 *
 *	@see https://www.acmicpc.net/problem/9993/
 *
 */
public class Boj9993 {
	private static final char SLASH = '/';
	
	private static class Point{
		int row;
		int col;
		char dir;
		
		public Point(int row, int col, char dir) {
			this.row = row;
			this.col = col;
			this.dir = dir;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		char[][] mirror = new char[N][M];
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < M; j++) {
				mirror[i][j] = line.charAt(j);
			}
		}
		
		System.out.println(reflect(N, M, mirror));
	}
	
	private static int reflect (int n, int m, char[][] arr) {
		int max = -1;
		
		for(int i = 0; i < m; i++) {						// [0][i] u-d, [n - 1][i] d-u
			int r = razer(n, m, arr, new Point(0, i, 'N'));
			max = Math.max(r, max);
			
			r = razer(n, m, arr, new Point(n - 1, i, 'S'));
			max = Math.max(r, max);
		}
		
		for(int i = 0; i < n; i++) {						// [i][0] l-r, [i][m - 1] r-l
			int r = razer(n, m, arr, new Point(i, 0, 'W'));
			max = Math.max(r, max);
			
			r = razer(n, m, arr, new Point(i, m - 1, 'E'));
			max = Math.max(r, max);
		}
		
		return max;
	}
	
	private static int razer(int n, int m, char[][] mirror, Point start) {
		char current = mirror[start.row][start.col];
		int count = 0;

		while(true) {
			count++;
			
			Point reflect = path(current, start.dir);					// 레이저가 쏘아진 방향과 현 위치로 반사될 방향과 위치를 추적
			
			int nextRow = start.row + reflect.row;
			int nextCol = start.col + reflect.col;
			
			if(nextRow < 0 || nextCol < 0 || nextRow >= n || nextCol >= m) return count;		// 레이저 탈출
			start = new Point(nextRow, nextCol, reflect.dir);			// 다음으로 값 갱신
		
			current = mirror[nextRow][nextCol];
		}
	}
	
	private static Point path(char mirror, char dir) {
		Point tmp = new Point(-1, -1, ' ');
		
		switch(dir) {
		case 'N':
			if(mirror == SLASH) tmp = new Point(0, -1, 'E');
			else tmp = new Point(0, 1, 'W');
			break;
			
		case 'S':
			if(mirror == SLASH) tmp = new Point(0, 1, 'W');
			else tmp = new Point(0, -1, 'E');
			break;
			
		case 'E':
			if(mirror == SLASH) tmp = new Point(1, 0, 'N');
			else tmp = new Point(-1, 0, 'S');
			break;
			
		case 'W':
			if(mirror == SLASH) tmp = new Point(-1, 0, 'S');
			else tmp = new Point(1, 0, 'N');
			break;
		}
		
		return tmp;
	}
}
