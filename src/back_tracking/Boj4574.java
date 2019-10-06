package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 4574번: 스도미노쿠
 *
 *	@see https://www.acmicpc.net/problem/4574/
 *
 */
public class Boj4574 {
	private static final String P = "Puzzle";
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}};
	private static final int ROW = 0, COL = 1;
	
	private static StringBuilder result = new StringBuilder();
	
	private static boolean[][][] check;
	private static boolean[][] pair;
	private static boolean flag;
	
	private static int[][] arr;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int loop = Integer.parseInt(br.readLine());
		int t = 1;
		
		while(loop != 0) {
			arr = new int[9][9];
			pair = new boolean[10][10];
			check = new boolean[3][10][10];
			
			result.append(P).append(SPACE).append(t++).append(NEW_LINE);
			int input = loop;
			StringTokenizer st;

			while(input-- > 0) {
				st = new StringTokenizer(br.readLine());
				int[] tmp = {0, 0};
				int idx = 0;
				
				while(st.hasMoreTokens()) {
					int val = Integer.parseInt(st.nextToken());
					String point = st.nextToken();
					
					int row = point.charAt(0) - 'A';
					int col = point.charAt(1) - '1';
					
					arr[row][col] = val;
					check[0][row][val] = check[1][col][val] = check[2][redirect(row, col)][val] = true;		// row, col, square
					tmp[idx++] = val;
				}
				
				pair[tmp[0]][tmp[1]] = pair[tmp[1]][tmp[0]] = true;				// domino check
			}

			st = new StringTokenizer(br.readLine());
			int val = 1;

			while(st.hasMoreTokens()) {
				String point = st.nextToken();
				int row = point.charAt(0) - 'A';
				int col = point.charAt(1) - '1';
				
				arr[row][col] = val;
				check[0][row][val] = check[1][col][val] = check[2][redirect(row, col)][val++] = true;
			}
			
			flag = false;
			backTracking(0);
			loop = Integer.parseInt(br.readLine());
		}
		
		System.out.println(result.toString());
	}
	
	private static int redirect(int x, int y) {
		return x / 3 * 3 + y / 3;
	}
	
	private static void backTracking(int count) {
		if(flag) return;
		
		if(count == 81) {
			for(int i = 0; i < 9; i++) {
				for(int j = 0; j < 9; j++) {
					result.append(arr[i][j]);
				}
				result.append(NEW_LINE);
			}
			
			flag = true;
			return;
		}

		int row = count / 9, col = count % 9;
		if(arr[row][col] != 0) {
			backTracking(count + 1);
			return;
		}
		
		for(int idx = 0; idx < 2; idx++) {						// domino ways
			int nextRow = row + DIRECTIONS[idx][ROW];
			int nextCol = col + DIRECTIONS[idx][COL];
			if(nextRow > 8 || nextCol > 8 || arr[nextRow][nextCol] != 0) continue;
			
			for(int val = 1; val < 10; val++) {
				for(int val1 = 1; val1 < 10; val1++) {
					if(val1 == val || pair[val][val1]) continue;
					
					if(check[0][row][val] || check[1][col][val] || check[2][redirect(row, col)][val]) continue;
					if(check[0][nextRow][val1] || check[1][nextCol][val1] || check[2][redirect(nextRow, nextCol)][val1]) continue;
					check[0][row][val] = check[1][col][val] = check[2][redirect(row, col)][val] = true;
					check[0][nextRow][val1] = check[1][nextCol][val1] = check[2][redirect(nextRow, nextCol)][val1] = true;
					pair[val1][val] = pair[val][val1] = true;
					
					arr[row][col] = val;
					arr[nextRow][nextCol] = val1;
					
					backTracking(count + 1);
					arr[row][col] = arr[nextRow][nextCol] = 0;
					
					check[0][row][val] = check[1][col][val] = check[2][redirect(row, col)][val] = false;
					check[0][nextRow][val1] = check[1][nextCol][val1] = check[2][redirect(nextRow, nextCol)][val1] = false;
					pair[val1][val] = pair[val][val1] = false;
				}
			}
		}
	}
}
