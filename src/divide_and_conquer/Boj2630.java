package divide_and_conquer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 2630번: 색종이 만들기
 *
 *	@see https://www.acmicpc.net/problem/2630/
 *
 */
public class Boj2630 {
	private static int[][] paper;
	private static int[] res = new int[2];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		paper = new int[N][N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		recursion(0, 0, N);
		System.out.println(res[0] + "\n" + res[1]);
	}
	
	private static void recursion(int row, int col, int n) {
		int current = paper[row][col];
		
		for(int x = row; x < row + n; x++) {
			for(int y = col; y < col + n; y++) {
				if(current == paper[x][y]) continue;
				
				recursion(row + n / 2, col, n / 2);				// 분산
				recursion(row, col + n / 2, n / 2);
				recursion(row, col, n / 2);
				recursion(row + n / 2, col + n / 2, n / 2);
				return;
			}
		}
		
		res[current]++;
	}
}
