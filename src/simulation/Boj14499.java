package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14499번: 주사위 굴리기
 *
 *	@see https://www.acmicpc.net/problem/14499/
 *
 */
public class Boj14499 {
	private static int[] dice = new int[6];
	private static int[] state = {0, 1, 2, 3, 4, 5};
	private static Point start = new Point(0, 0);
	
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
		}
		
		System.out.println(sb);
	}
	
	private static boolean rolling(int n, int m, int[][] arr, int query) {
		int nextRow = start.row + DIRECTIONS[query][ROW];
		int nextCol = start.col + DIRECTIONS[query][COL];

		if(nextRow < 1 || nextRow > n || nextCol < 1 || nextCol > m) return false;		// 범위 벗어나면 아무것도 하지 않음
		reset(query);					// 굴리기 메소드
		
		if(arr[nextRow][nextCol] == 0) {
			arr[nextRow][nextCol] = dice[state[5]];
		}
		else {
			dice[state[5]] = arr[nextRow][nextCol];
			arr[nextRow][nextCol] = 0;					// 주사위에 숫자가 복사된 경우
		}
		
		start = new Point(nextRow, nextCol);
		return true;
	}
	
	private static void reset(int q) {
		int[] tmp = new int[6];
		
		switch (q) {
		case 0:								// 동쪽 데굴
			tmp[0] = state[3];
			tmp[1] = state[1];
			tmp[2] = state[0];
			tmp[3] = state[5];
			tmp[4] = state[4];
			tmp[5] = state[2];
			
			for(int i = 0; i < 6; i++) {
				state[i] = tmp[i];
			}
			break;
			
		case 1:								// 서쪽 데굴
			tmp[0] = state[2];
			tmp[1] = state[1];
			tmp[2] = state[5];
			tmp[3] = state[0];
			tmp[4] = state[4];
			tmp[5] = state[3];
			
			for(int i = 0; i < 6; i++) {
				state[i] = tmp[i];
			}
			break;
			
		case 2:								// 북쪽 데굴
			tmp[0] = state[4];
			tmp[1] = state[0];
			tmp[2] = state[2];
			tmp[3] = state[3];
			tmp[4] = state[5];
			tmp[5] = state[1];
			
			for(int i = 0; i < 6; i++) {
				state[i] = tmp[i];
			}
			break;
			
		case 3:								// 남쪽 데굴
			tmp[0] = state[1];
			tmp[1] = state[5];
			tmp[2] = state[2];
			tmp[3] = state[3];
			tmp[4] = state[0];
			tmp[5] = state[4];

			for(int i = 0; i < 6; i++) {
				state[i] = tmp[i];
			}
			break;
		}
	}
}
