import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14719 {
	
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
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[H][W];
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < W; i++) {
			int wall = Integer.parseInt(st.nextToken());
			
			for(int j = H - 1; j >= 0; j--) {
				if(wall == 0) break;
				
				wall--;
				map[j][i] = 1;
			}
		}

		
	}
}
