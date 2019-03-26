package divide_and_conquer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1780번: 종이의 개수
 *
 *	@see https://www.acmicpc.net/problem/1780/
 *
 */
public class Boj1780 {
	private static int[] result = new int[3];
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] paper = new int[N][N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(paper, 0, 0, N);		// 최대 크기부터
		
		StringBuilder sb = new StringBuilder();
		sb.append(result[2]).append(NEW_LINE).append(result[0]).append(NEW_LINE).append(result[1]);
		
		System.out.println(sb);
	}
	
	private static void dfs(int[][] arr, int row, int col, int n) {
		if(isAllSame(arr, row, col, n)) return;		// 현 범위의 종이가 모두 같은 수 인가?

		for(int x = row; x < row + n; x += n / 3) {			// 행과 열은 크기에 맞게 증가
			for(int y = col; y < col + n; y += n / 3) {
				dfs(arr, x, y, n / 3);						// 아닌 경우 종이의 크기를 1/9로 나누어 재귀 호출
			}
		}
	}
	
	private static boolean isAllSame(int[][] arr, int row, int col, int n) {
		int value = arr[row][col];
		int index = arr[row][col] == -1 ? 2: arr[row][col];
		
		for(int i = row; i < row + n; i++) {
			for(int j = col; j < col + n; j++) {				
				if(value != arr[i][j]) return false;
			}
		}
		
		result[index]++;			// 모두 같은 수인 경우
		return true;
	}
}
