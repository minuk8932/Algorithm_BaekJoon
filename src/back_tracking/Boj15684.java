package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15684번: 사다리 조작
 *
 *	@see https://www.acmicpc.net/problem/15684/
 *
 */
public class Boj15684 {
	private static int result = 10;
	private static int[][] ladder;
	private static boolean[][] fix;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		ladder = new int[H][N];
		fix = new boolean[H][N];
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			ladder[from][to] = 1;								// 우측 이동
			ladder[from][to + 1] = -1;							// 좌측 이동
			
			fix[from][to] = fix[from][to + 1] = true;			// 고정 가로 선
		}
	
		backTracking(N, H, 0, 0, 0);
		System.out.println(result > 3 ? -1: result);			// 3보다 큰 값이 나오는 경우
	}
	
	private static void backTracking(int n, int h, int x, int y, int count) {
		if(count > 3) return;
		if(result <= count) return;		// 값 갱신이 안되는 경우

		if(arrived(n, h)) {
			result = count;
			return;
		}

		for(int row = x; row < h; row++) {
			for(int col = y; col < n; col++) {
				if(col + 1 >= n) continue;
				if(ladder[row][col] != 0 || ladder[row][col + 1] != 0 || fix[row][col]) continue;		// 놓으려는 자리에 이미 사다리가 놓여있는 경
				
				ladder[row][col] = 1;						// 우측 이동
				ladder[row][col + 1] = -1;					// 좌측 이동
				backTracking(n, h, row, col, count + 1);
				ladder[row][col] = ladder[row][col + 1] = 0;	// 백트래킹
			}
			
			y = 0;
		}
	}
	
	private static boolean arrived(int n, int h) {
		for(int i = 0; i < n; i++) {
			int idx = i;
			
			for(int j = 0; j < h; j++) {
				if(ladder[j][idx] == 0) continue;
				if(ladder[j][idx] == 1) idx++;
				else idx--;
			}
			
			if(idx != i) return false;			// 값에 따라 움직였으나 원래 위치로 돌아오지 못하는 경우
		}
		
		return true;
	}
}
