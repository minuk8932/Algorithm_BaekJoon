import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj15806 {
	private static int[][] room;
	private static final int[][] DIRECTIONS = {{-1, 1}, {-1, -1}, {1, -1}, {1, 1}};
	private static final int ROW = 0, COL = 1;
	
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
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());		// 방바닥 갯수
		
		Queue<Point> dust = new LinkedList<>();
		room = new int[N][N];
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			
			room[x][y] = -1;
			dust.offer(new Point(x, y));
		}
		
		spread(dust);
		System.out.println();
	}
	
	private static void spread(Queue<Point> q) {
		while(!q.isEmpty()) {
			Point current = q.poll();
				
			room[current.row][current.col] = 0;
			for(final int[] DIRECTION: DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
					
			}
		}
	}
}
