package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 10656번: 십자말풀이
 *
 *	@see https://www.acmicpc.net/problem/10656/
 *
 */
public class Boj10656 {
	private static final char BLOCK = '#';
	private static final char EMPTY = '.';
	
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		char[][] puzzle = new char[N][M];
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < M; j++) {
				puzzle[i][j] = line.charAt(j);
			}
		}
		
		System.out.println(getHintList(N, M, puzzle));
	}
	
	private static String getHintList(int n, int m, char[][] arr) {
		StringBuilder sb = new StringBuilder();
		int count = 0;
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(arr[i][j] == BLOCK) continue;
				
				if(isRowHint(n, arr, i, j) || isColHint(m, arr, i, j)) {			// 가로나 세로 힌트인 경우
					sb.append(i + 1).append(SPACE).append(j + 1).append(NEW_LINE);
					count++;
				}
			}
		}
		
		System.out.println(count);
		return sb.toString();
	}
	
	private static boolean isRowHint(int size, char[][] arr, int row, int col) {
		if(row != 0 && arr[row - 1][col] != BLOCK) return false;		// 위쪽이 막혀있지 않은 경우
		
		for(int i = row + 1; i < row + 3; i++) {
			if(i >= size || arr[i][col] != EMPTY) return false;			// 단어 길이가 3보다 작은경우
		}
		
		return true;
	}
	
	private static boolean isColHint(int size, char[][] arr, int row, int col) {
		if(col != 0 && arr[row][col - 1] != BLOCK) return false;		// 왼쪽이 막혀있지 않은 경우
		
		for(int i = col + 1; i < col + 3; i++) {
			if(i >= size || arr[row][i] != EMPTY) return false;
		}
		
		return true;
	}
}
