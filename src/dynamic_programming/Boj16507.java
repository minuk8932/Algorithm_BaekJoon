package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 16507번: 어두운건 무서워
 *
 *	@see https://www.acmicpc.net/problem/16507/
 *
 */
public class Boj16507 {
	private static long[][] picture;
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		picture = new long[R + 1][C + 1];
		for(int i = 1; i < R + 1; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 1; j < C + 1; j++) {
				picture[i][j] = Long.parseLong(st.nextToken());
			}
		}
		
		makeSectionSum(R, C);
		StringBuilder sb = new StringBuilder();
		
		while(Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int row1 = Integer.parseInt(st.nextToken());
			int col1 = Integer.parseInt(st.nextToken());
			int row2 = Integer.parseInt(st.nextToken());
			int col2 = Integer.parseInt(st.nextToken());
			
			sb.append(getAvg(row1, col1, row2, col2)).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static void makeSectionSum(int N, int M) {
		for(int row = 1; row < N + 1; row++) {				// 1, 1 ~ row, col까지 직사각형 범위 부분합 저장	
			for(int col = 1; col < M + 1; col++) {
				picture[row][col] += picture[row - 1][col] + picture[row][col - 1] - picture[row - 1][col - 1];
				System.out.print(picture[row][col] + " ");
			}
			System.out.println();
		}
	}
	
	private static long getAvg(int startRow, int startCol, int endRow, int endCol) {
		long size = (endRow - startRow + 1) * (endCol - startCol + 1);				// 그림 크기, 해당 범위의 부분합
		return (picture[endRow][endCol] - picture[endRow][startCol - 1] - picture[startRow - 1][endCol] + picture[startRow - 1][startCol - 1]) / size;
	}
}
