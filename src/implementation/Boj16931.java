package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 16931번: 겉넓이 구하기
 *
 *	@see https://www.acmicpc.net/problem/16931/
 *
 */
public class Boj16931 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] blocks = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < M; j++) {
				blocks[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(getArea(N, M, blocks));
	}
	
	private static int getArea(int n, int m, int[][] arr) {
		int area = n * m * 2;	
		area += getleftRightSide(n, m, arr) + getFrontBackSide(n, m, arr);		// 각 블럭마다 비어있는 면을 모두 더해줌
	
		return area;
	}
	
	private static int getleftRightSide(int n, int m, int[][] arr) {
		int value = 0;
		
		for(int i = 0; i < n; i++) {
			value += arr[i][0];
			
			for(int j = 1; j < m; j++) {
				value += Math.abs(arr[i][j - 1] - arr[i][j]);
			}
			
			value += arr[i][m - 1];
		}
		
		return value;
	}
	
	private static int getFrontBackSide(int n, int m, int[][] arr) {
		int value = 0;
		
		for(int i = 0; i < m; i++) {
			value += arr[0][i];
			
			for(int j = 1; j < n; j++) {
				value += Math.abs(arr[j - 1][i] - arr[j][i]);
			}
			
			value += arr[n - 1][i];
		}
		
		return value;
	}
}
