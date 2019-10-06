package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 2580번: 스도쿠
 *
 *	@see https://www.acmicpc.net/problem/2580/
 *
 */
public class Boj2580 {
	private static boolean[][][] filled = new boolean[3][10][10];
	
	private static StringBuilder result = new StringBuilder();
	private static final String SPACE = " ", NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[][] sudoku = new int[9][9];
		for(int i = 0; i < sudoku.length; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < sudoku[i].length; j++) {
				sudoku[i][j] = Integer.parseInt(st.nextToken());
				
				if(sudoku[i][j] != 0) {
					filled[0][i][sudoku[i][j]] = true;						// row check
					filled[1][j][sudoku[i][j]] = true;						// col check
					filled[2][redirect(i, j)][sudoku[i][j]] = true;			// sqr check
				}
			}
		}
		
		recursion(sudoku, 0);
		System.out.println(result.toString());
	}
	
	private static int redirect(int x, int y) {					// square's index
		return x / 3 * 3 + y / 3;
	}
	
	private static void recursion(int[][] arr, int count) {
        if(result.length() != 0) return;
        
		if(count == 81) {										// get answer
			
			for(int i = 0; i < arr.length; i++) {
				for(int j = 0; j < arr[i].length; j++) {
					result.append(arr[i][j]).append(SPACE);
				}
				result.append(NEW_LINE);
			}
			
			return;
		}
		
		int row = count / 9, col = count % 9;					// origin idx get
		if(arr[row][col] != 0) {								// already filled
			recursion(arr, count + 1);
			return;
		}
		
		for(int i = 1; i < 10; i++) {							// backtracking
			if(filled[0][row][i] || filled[1][col][i] || filled[2][redirect(row, col)][i]) continue;
			filled[0][row][i] = filled[1][col][i] = filled[2][redirect(row, col)][i] = true;
			arr[row][col] = i;
			
			recursion(arr, count + 1);
			arr[row][col] = 0;
			filled[0][row][i] = filled[1][col][i] = filled[2][redirect(row, col)][i] = false;
		}
	}
}
