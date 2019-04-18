package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 5623번: 수열의 합
 *
 *	@see https://www.acmicpc.net/problem/5623/
 *
 */
public class Boj5623 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[N][N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(getSequence(N, arr));
	}
	
	private static StringBuilder getSequence(int n, int[][] arr) {
		StringBuilder sb = new StringBuilder();
		int x = 0;
		
		if(n == 2) {										// 수열 구해서 뽑기
			x = 1;
			sb.append(x).append(SPACE).append(arr[0][1] - x);
		}
		else {
			x = (arr[0][1] - arr[1][2] + arr[0][2]) / 2;
			
			sb.append(x).append(SPACE);
			for(int i = 1; i < n; i++) {
				sb.append(arr[0][i] - x).append(SPACE);
			}
		}
		
		return sb;
	}
}
