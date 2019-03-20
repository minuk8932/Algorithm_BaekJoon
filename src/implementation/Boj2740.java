package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2740번: 행렬 곱셈
 *
 *	@see https://www.acmicpc.net/problem/2740/
 *
 */
public class Boj2740 {
	private static final String NEW_LINE = "\n", SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] matrix1 = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				matrix1[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		st.nextToken();
		int K = Integer.parseInt(st.nextToken());
		
		int[][] matrix2 = new int[M][K];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < K; j++) {
				matrix2[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(cartesian(N, M, K, matrix1, matrix2));
	}
	
	private static StringBuilder cartesian(int n, int m, int k, int[][] arr1, int[][] arr2) {
		StringBuilder sb = new StringBuilder();
		
		for(int x = 0; x < n; x++) {
			for(int y = 0; y < k; y++) {
				int tmp = 0;
				
				for(int i = 0; i < m; i++) {			// 행렬 곱 N x M, M x K
					tmp += arr1[x][i] * arr2[i][y];
				}
				
				sb.append(tmp).append(SPACE);
			}
			sb.append(NEW_LINE);
		}
		
		return sb;
	}
}
