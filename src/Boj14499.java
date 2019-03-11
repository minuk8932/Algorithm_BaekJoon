import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14499 {
	private static int[] dice = new int[6];
	private static int[] state = {0, 1, 2, 3, 4, 5};			// up, back, right, left, front, down;
	private static Point start = new Point(0, 0);
	
	private static DiceState[][] ds = {{new DiceState(0, 0, 0, 0, 0, 0), new DiceState(0, 3, 1, 4, 2, 5), new DiceState(0, 1, 2, 3, 4, 5), new DiceState(0, 4, 3, 2, 1, 5), new DiceState(0, 2, 4, 1, 3, 5), new DiceState(0, 0, 0, 0, 0, 0)}, 
			{new DiceState(1, 2, 0, 5, 3, 4), new DiceState(0, 0, 0, 0, 0, 0), new DiceState(1, 5, 2, 3, 0, 4), new DiceState(1, 0, 3, 2, 5, 4), new DiceState(0, 0, 0, 0, 0, 0), new DiceState(1, 3, 5, 0, 2, 4)}, 
			{new DiceState(2, 4, 0, 5, 1, 3), new DiceState(2, 0, 1, 4, 5, 3), new DiceState(0, 0, 0, 0, 0, 0), new DiceState(0, 0, 0, 0, 0, 0), new DiceState(2, 5, 4, 1, 0, 3) ,new DiceState(2, 1, 5, 0, 4, 3)},
			{new DiceState(3, 4, 0, 5, 1, 2), new DiceState(3, 5, 1, 4, 0, 2), new DiceState(0, 0, 0, 0, 0, 0), new DiceState(0, 0, 0, 0, 0, 0), new DiceState(3, 0, 4, 1, 5, 2), new DiceState(3, 4, 5, 0, 1, 2)},
			{new DiceState(4, 3, 0, 5, 2, 1), new DiceState(0, 0, 0, 0, 0, 0), new DiceState(4, 0, 2, 3, 5, 1), new DiceState(4, 5, 3, 2, 0, 1), new DiceState(0, 0, 0, 0, 0, 0), new DiceState(4, 2, 5, 0, 3, 1)},
			{new DiceState(0, 0, 0, 0, 0, 0), new DiceState(5, 2, 1, 4, 3, 0), new DiceState(5, 1, 3, 2, 4, 0), new DiceState(5, 3, 4, 1, 2, 0), new DiceState(0, 0, 0, 0, 0, 0)}};
	
//	private static final Point[][] ROLL = {{new Point(3, 2), new Point(2, 3), new Point(4, 1), new Point(1, 4)}, 
//										{new Point(3, 2), new Point(2, 3), new Point(0, 5), new Point(5, 0)},
//										{new Point(0, 5), new Point(5, 0), new Point(4, 1), new Point(1, 4)},
//										{new Point(5, 0), new Point(0, 5), new Point(4, 1), new Point(1, 4)},
//										{new Point(3, 2), new Point(2, 3), new Point(5, 0), new Point(0, 5)},
//										{new Point(3, 2), new Point(2, 3), new Point(1, 4), new Point(4, 1)}};
	
	private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static final String NEW_LINE = "\n";
	
	private static class Point{
		int row;
		int col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	private static class DiceState{
		int up, bwd, right, left, fwd, down;
		
		public DiceState(int up, int bwd, int right, int left, int fwd, int down) {
			this.up = up;
			this.bwd = bwd;
			this.right = right;
			this.left = left;
			this.fwd = fwd;
			this.down = down;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		start = new Point(Integer.parseInt(st.nextToken()) + 1, Integer.parseInt(st.nextToken()) + 1);
		int K = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N + 1][M + 1];
		for(int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 1; j < M + 1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		
		while(K-- > 0) {
			int move = Integer.parseInt(st.nextToken()) - 1;
			boolean result = rolling(N, M, map, move);
			
			if(result) sb.append(dice[state[0]]).append(NEW_LINE);
			
			for(int i = 0; i < dice.length; i++) {
				System.out.print(dice[i] + " ");
			}
			System.out.println();
		}
		
		System.out.println(sb);
	}
	
	private static boolean rolling(int n, int m, int[][] arr, int query) {
		System.out.println(start.row + " " + start.col);
		if(arr[start.row][start.col] == 0) arr[start.row][start.col] = dice[state[5]];
		else dice[state[5]] = arr[start.row][start.col];
		
		int nextRow = start.row + DIRECTIONS[query][ROW];
		int nextCol = start.col + DIRECTIONS[query][COL];

		if(nextRow < 1 || nextRow > n || nextCol < 1 || nextCol > m) return false;
		
		start = new Point(nextRow, nextCol);
		reset(query);
		
		return true;
	}
	
	private static void reset(int q) {
		int[] tmp = new int[6];			// 스테이트값유지하자
		
		switch (q) {
		case 0:
			tmp[0] = ds[3][0].up;
			tmp[1] = ds[3][0].bwd;
			tmp[2] = ds[3][0].right;
			tmp[3] = ds[3][0].left;
			tmp[4] = ds[3][0].fwd;
			tmp[5] = ds[3][0].down;
			
			for(int i = 0; i < 6; i++) {
				state[i] = state[tmp[i]];
			}
			break;
			
		case 1:
			tmp[0] = ds[2][5].up;
			tmp[1] = ds[2][5].bwd;
			tmp[2] = ds[2][5].right;
			tmp[3] = ds[2][5].left;
			tmp[4] = ds[2][5].fwd;
			tmp[5] = ds[2][5].down;
			
			for(int i = 0; i < 6; i++) {
				state[i] = state[tmp[i]];
			}
			break;
			
		case 2:
			tmp[0] = ds[4][2].up;
			tmp[1] = ds[4][2].bwd;
			tmp[2] = ds[4][2].right;
			tmp[3] = ds[4][2].left;
			tmp[4] = ds[4][2].fwd;
			tmp[5] = ds[4][2].down;
			
			for(int i = 0; i < 6; i++) {
				state[i] = state[tmp[i]];
			}
			break;
			
		case 3:
			tmp[0] = ds[1][2].up;
			tmp[1] = ds[1][2].bwd;
			tmp[2] = ds[1][2].right;
			tmp[3] = ds[1][2].left;
			tmp[4] = ds[1][2].fwd;
			tmp[5] = ds[1][2].down;
			
			for(int i = 0; i < 6; i++) {
				state[i] = state[tmp[i]];
			}
			break;
		}
	}
}
