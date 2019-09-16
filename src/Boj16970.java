import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj16970 {
	private static ArrayList<Point> points = new ArrayList<>();
	
	private static class Point{
		int x;
		int y;
		
		public Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		init(N, M);
		System.out.println(getPoints(N, M, K));
	}
	
	private static void init(int n, int m) {
		for(int i = 0; i <= n; i++) {
			for(int j = 0; j <= m; j++) {
				points.add(new Point(i, j));
			}
		}
	}
	
	private static int getPoints(int n, int m, int k) {
		boolean[][][][] visit = new boolean[n + 1][m + 1][n + 1][m + 1];
		int count = 0;
		int size = points.size();
		
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(i == j) continue;
				
				Point from = points.get(i);
				Point to = points.get(j);
				
				int[] diff = {to.x - from.x, to.y - from.y};
				int x = from.x, y = from.y;
				int loop = k - 1;
				
				while(x <= n && y <= m) {
					x += diff[0];
					y += diff[1];

					loop--;
					if(loop == 0) {
						if(isOutOfBounds(from.x, from.y, x, y, n, m) || visit[from.x][from.y][x][y] || visit[x][y][from.x][from.y]) break;
						visit[from.x][from.y][x][y] = true;
						
						count++;
						break;
					}
				}
			}
		}
		
		return count;
	}
	
	private static boolean isOutOfBounds(int a, int b, int c, int d, int n, int m) {
		return a < 0 || a > n || b < 0 || b > m || c < 0 || c > n || d < 0 || d > m ? true: false;
	}
}
