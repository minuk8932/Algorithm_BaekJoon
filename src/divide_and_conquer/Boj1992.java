package divide_and_conquer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 *	백준 1992번: 쿼드 트리
 *
 *	@see https://www.acmicpc.net/problem/1992/
 *
 */
public class Boj1992 {
	private static StringBuilder build = new StringBuilder();
	private static final String OPEN = "(";
	private static final String CLOSE = ")";
	
	private static final boolean[] FLAG = {false, true};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		boolean[][] image = new boolean[N][N];		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < N; j++) {
				image[i][j] = FLAG[line.charAt(j) - '0'];
			}
		}
		
		compress(N, image, 0, 0);
		System.out.println(build.toString());	
	}
	
	private static void compress(int n, boolean[][] img, int row, int col) {		
		for(int x = row; x < row + n; x++) {
			for(int y = col; y < col + n; y++) {	
				if(img[row][col] ^ img[x][y]) {					// 다른 값이 나오면 4분할
					int half = n / 2;
					build.append(OPEN);
					
					compress(half, img, row, col);						// 2사분면
					compress(half, img, row, col + half);				// 1사분면
					compress(half, img, row + half, col);				// 3사분면
					compress(half, img, row + half, col + half);		// 4사분면
					
					build.append(CLOSE);
					return;
				}
			}
		}
	
		build.append(img[row][col] ? 1: 0);								// 범위내 값이 모두 같은 경우 숫자 채우기
	}
}
