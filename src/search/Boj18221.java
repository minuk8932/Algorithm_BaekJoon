package search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 18221번: 교수님 저는 취업할래요
 *
 *	@see https://www.acmicpc.net.problem/18221/
 *
 */
public class Boj18221 {
	private static final int INTERVAL = 25;
	private static Point target = new Point(-1, -1);
	private static Point trap = new Point(-1, -1);
	
	private static ArrayList<Point> helper = new ArrayList<>();
	
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
		
		int[][] map = new int[N][N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == 1) helper.add(new Point(i, j));
				if(map[i][j] == 2) target = new Point(i, j);
				if(map[i][j] == 5) trap = new Point(i, j);
			}
		}
		
		System.out.println(result(map));
	}
	
	private static int result(int[][] arr) {
		if(!distance(target, trap)) return 0;
		int count = 0;
		
		Point min = new Point(Math.min(target.row, trap.row), Math.min(target.col, trap.col));
		Point max = new Point(Math.max(target.row, trap.row), Math.max(target.col, trap.col));
		
		for(Point p: helper) {
			if(p.row < min.row || p.col < min.col || p.row > max.row || p.col > max.col) continue;
			count++;
		}
		
		return count / 3 > 0 ? 1: 0;
	}
	
	private static boolean distance(Point p1, Point p2) {
		return Math.pow(p1.row - p2.row, 2) + Math.pow(p1.col - p2.col, 2) >= INTERVAL;
	}
}
