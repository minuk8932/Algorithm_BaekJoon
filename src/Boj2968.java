import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2968 {
	private static final boolean WHITE = true;
	private static int K;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		
		boolean[][] board = new boolean[R][C];
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				int xor = i & j;
				board[i][j] = xor > 0 ? WHITE: !WHITE;
			}
		}
		
		System.out.println(count(R, C, board));
	}
	
	private static int count(int r, int c, boolean[][] arr) {
		int count = 0, depth = 0;
		boolean flag = true;
		
		while(K > 0) {
			int row = 0, col = 0;
			
			if(depth % 2 == 0) {
				count += move(r, c, depth, col, depth, arr, -1);
				
//				row = depth;				
				
//				while(row >= 0 && col >= 0 && row < r && col < c) {
//					if(arr[row][col] != WHITE) count++;
//					System.out.println(row + " " + col);
//					row--;
//					col++;
//					K--;
//				}
			}
			else {
				count += move(r, c, row, depth, depth, arr, 1);
				
//				col = depth;
				
//				while(row >= 0 && col >= 0 && row < r && col < c) {
//					if(arr[row][col] != WHITE) count++;
//					System.out.println(row + " " + col);
//					row++;
//					col--;
//					K--;
//				}
			}
			
			if(flag) depth++;
		}
		
		return count;
	}
	
	private static int move(int r, int c, int x, int y, int level, boolean[][] arr, int adder) {
		int sum = 0;
		
		while(x >= 0 && y >= 0 && x < r && y < c) {
			if(arr[x][y] != WHITE) sum++;
			System.out.println(x + " " + y);
			x += adder;
			y -= adder;
			K--;
		}
		
		return sum;
	}
}
