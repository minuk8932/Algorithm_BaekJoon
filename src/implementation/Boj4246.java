package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 4246번: To and Fro
 *
 *	@see https://www.acmicpc.net/problem/4246/
 *
 */
public class Boj4246 {
	private static final String NEW_LINE = "\n";
	
	private static StringBuilder sb = new StringBuilder();
	private static char[][] remake;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			int N = Integer.parseInt(br.readLine());
			if(N == 0) break;
			
			char[] words = br.readLine().toCharArray();
			getPlaintext(N, words);
		}
		
		System.out.println(sb);
	}
	
	private static void getPlaintext(int n, char[] arr) {
		int col = n;
		int row = arr.length / n;
		
		remake = new char[row][col];
		
		for(int i = 0; i < row; i++) {							// 행렬로 재구성
			if(i % 2 == 0) build(0, col - 1, arr, col, i, 1);
			else build(col - 1, 0, arr, col, i, -1);
		}
		
		for(int i = 0; i < col; i++) {
			for(int j = 0; j < row; j++) {						// 평문 출력
				sb.append(remake[j][i]);
			}
		}
		
		sb.append(NEW_LINE);
	}
	
	private static void build(int start, int end, char[] arr, int size, int row, int adder) {
		int index = 0;
		for(int i = start; i * adder <= end * adder; i += adder) {
			remake[row][i] = arr[row * size + index++];
		}
	}
}
