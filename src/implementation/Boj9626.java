package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 * 	백준 9626번: 크로스워드 퍼즐
 * 
 * 	@see https://www.acmicpc.net/problem/9626/
 * 
 */
public class Boj9626 {
	private static final char[] FIRST = {'#', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.'};
	private static final char[] SECOND = {'.', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#'};
	
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int U = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		
		char[][] input = new char[N][M];
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < M; j++) {
				input[i][j] = line.charAt(j);
			}
		}
		
		System.out.println(getResult(N, M, U, L, R, D, input));
	}
	
	private static StringBuilder getResult(int n, int m, int u, int l, int r, int d, char[][] arr) {
		StringBuilder sb = new StringBuilder();
		
		char[][] init = new char[20][20];
		
		for(int i = 0; i < 20; i++) {					// 바탕화면
			for(int j = 0; j < 20; j++) {
				if(i % 2 == 0) init[i][j] = FIRST[j];
				else init[i][j] = SECOND[j];
			}
		}
		
		for(int i = 0; i < n + u + d; i++) {
			for(int j = 0; j < m + l + r; j++) {		// 범위에 들어설 땐 입력을 담는다
				if(i >= u && i < n + u) {
					if(j >= l && j < l + m) sb.append(arr[i - u][j - l]);
					else sb.append(init[i][j]);
				}
				else {
					sb.append(init[i][j]);
				}
			}
			
			sb.append(NEW_LINE);
		}
		
		return sb;
	}
}
