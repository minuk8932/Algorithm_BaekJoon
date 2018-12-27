package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2738번: 행렬 덧셈
 *
 *	@see https://www.acmicpc.net/problem/2738/
 *
 */
public class Boj2738 {
	private static final char SPACE = ' ';
	private static final char NEW_LINE = '\n';
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] res = new int[N][M];
		res = getArr(res, N, M, br);
		res = getArr(res, N, M, br);
		
		System.out.println(printArray(res));		// 결과 출력
	}
	
	private static int[][] getArr(int[][] arr, int n, int m, BufferedReader br) throws Exception{		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < m; j++) {
				arr[i][j] += Integer.parseInt(st.nextToken());
			}
		}
		
		return arr;
	}
	
	private static StringBuilder printArray(int[][] arr) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
				sb.append(arr[i][j]).append(SPACE);
			}
			sb.append(NEW_LINE);
		}
		
		return sb;
	}
}
