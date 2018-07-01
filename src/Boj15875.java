import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj15875 {
	private static final String SPACE = " ";

	private static final int[][] DIRECTIONS = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	private static final int ROW = 0;
	private static final int COL = 1;

	private static int H = 0;
	private static int W = 0;
	
	private static int[][] map = null;
	private static int[][] isVisited = null;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());

		map = new int[H + 2][W + 2];
		for (int i = 1; i < H + 1; i++) {
			st = new StringTokenizer(br.readLine(), SPACE);
			
			for (int j = 1; j < W + 1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		br.close();

		int max = 0;

		for (int row = 2; row < H; row++) {
			for (int col = 2; col < W; col++) {
				isVisited = new int[H + 2][W + 2];
				
				int val = dfs(row, col);
                if(val > max){
					max = val;
				}
			}
		}

		System.out.println(max);
	}
	
	private static int dfs(int row, int col) {
		if(isVisited[row][col] != 0) return isVisited[row][col];

		isVisited[row][col] = 1;						

		for (final int[] DIRECTION : DIRECTIONS) {
			int nextRow = row + DIRECTION[ROW];
			int nextCol = col + DIRECTION[COL];

			if (nextRow < 2 || nextRow > H - 1 || nextCol < 2 || nextCol >  W - 1) continue;		
			if (map[nextRow][nextCol] > map[row][col]) continue;							

			isVisited[row][col] = Math.max(isVisited[row][col], dfs(nextRow, nextCol) + 1);	

		}

		return isVisited[row][col];
	}
}
