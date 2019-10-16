package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 *	백준 1347번: 미로 만들기
 *
 *	@see https://www.acmicpc.net/problem/1347/
 *
 */
public class Boj1347 {
	private static final String BLOCK = "#";
	private static final String WAY = ".";
	private static final String NEW_LINE = "\n";
	
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
		char[] cmd = br.readLine().toCharArray();
		
		System.out.println(drawMap(N, cmd));
	}
	
	private static String drawMap(int n, char[] arr) {
		boolean[][] map = new boolean[100][100];
		int[] min = {50, 50};
		int[] max = {50, 50};
		
		Point dir = new Point(1, 0);
		Point start = new Point(50, 50);
		
		map[50][50] = true;
		
		for(char c: arr) {
			if(c == 'F') {
				start.row += dir.row;
				start.col += dir.col;
				
				map[start.row][start.col] = true;
				min[0] = Math.min(min[0], start.row);			// map's min/max idx reset
				max[0] = Math.max(max[0], start.row);
				min[1] = Math.min(min[1], start.col);
				max[1] = Math.max(max[1], start.col);
				continue;
			}
			
			dir = getDirection(c, dir);							// rotate
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = min[0]; i <= max[0]; i++) {
			for(int j = min[1]; j <= max[1]; j++) {
				sb.append(map[i][j] ? WAY: BLOCK);
			}
			sb.append(NEW_LINE);
		}
		
		return sb.toString();
	}
	
	private static Point getDirection(char input, Point d) {
		if(d.row == 0) {
			if(d.col == 1) {
				if(input == 'R') d = new Point(1, 0);
				else d = new Point(-1, 0);
			}
			else{
				if(input == 'R') d = new Point(-1, 0);
				else d = new Point(1, 0);
			}
		}
		else {
			if(d.row == 1) {
				if(input == 'R') d = new Point(0, -1);
				else d = new Point(0, 1);
			}
			else {
				if(input == 'R') d = new Point(0, 1);
				else d = new Point(0, -1);
			}
		}
		
		return d;
	}
}
