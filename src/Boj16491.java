import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj16491 {
	private static boolean[][] crashPath;
	private static boolean[] occupy;
	private static int[] arrived;
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int X_ROW = 0;
	private static final int Y_COL = 1;
	private static final String NEW_LINE = "\n";
	
	private static class Point{
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		
		Point[] robot = new Point[N + 1];
		Point[] shelter = new Point[N + 1];
		
		for(int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			robot[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		for(int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			shelter[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		System.out.println(intersect(N, robot, shelter));
	}
	
	private static StringBuilder intersect(int n, Point[] robo, Point[] shel) {
		StringBuilder sb = new StringBuilder();
		
		
		
		return sb;
	}
}
