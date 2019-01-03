package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1018번: 체스판 다시 칠하기
 *
 *	@see https://www.acmicpc.net/problem/1018/
 *
 */
public class Boj1018 {
	private static final int SIZE = 8;
	private static final boolean[][] CHESS = {{true, false, true, false, true, false ,true, false}, 
											{false, true, false, true, false, true, false, true}, 
											{true, false, true, false, true, false ,true, false}, 
											{false, true, false, true, false, true, false, true},
											{true, false, true, false, true, false ,true, false},
											{false, true, false, true, false, true, false, true},
											{true, false, true, false, true, false ,true, false},
											{false, true, false, true, false, true, false, true},
											{true, false, true, false, true, false ,true, false}};											
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		boolean[][] map = new boolean[N][M];
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < M; j++) {
				boolean isTrue = false;
				if(line.charAt(j) == 'B') isTrue = true;
				
				map[i][j] = isTrue;
			}
		}
		
		int min = Integer.MAX_VALUE;
		
		for(int i = 0; i < N - SIZE + 1; i++) {
			for(int j = 0; j < M - SIZE + 1; j++) {
				
				int total = getSize(map, i, j, 0);				// 체스판마다 범위를 옮겨가며 확인
				total = Math.min(getSize(map, i, j, 1), total);		// W가 먼저 나오는 경우
				
				if(total < min) min = total;
			}
		}
		
		System.out.println(min);		// 최소 횟수 출력
	}
	
	private static int getSize(boolean[][] arr, int row, int col, int push) {
		int diff = 0;
		int x = push;
		
		for(int i = row; i < SIZE + row; i++) {
			int y = 0;
			
			for(int j = col; j < SIZE + col; j++) {
				if(CHESS[x][y++] != arr[i][j]) diff++;
			}
			
			x++;
		}
		
		return diff;
	}
}
