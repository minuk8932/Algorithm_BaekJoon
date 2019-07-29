package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 17363번: 우유가 넘어지면?
 *
 *	@see https://www.acmicpc.net/problem/17363/
 *
 */
public class Boj17363 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		char[][] img = new char[N][M];
		for(int i = 0; i < N; i++) {
			String input = br.readLine();
			
			for(int j = 0; j < M; j++) {
				img[i][j] = input.charAt(j);
				img[i][j] = switchChar(img[i][j]);
			}
		}
		
		System.out.println(rotate(N, M, img));
	}
	
	private static String rotate(int n, int m, char[][] arr) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = m - 1; i >= 0; i--) {		// 오른쪽으로 넘어트리기
			for(int j = 0; j < n; j++) {
				sb.append(arr[j][i]);
			}
			sb.append(NEW_LINE);
		}
		
		return sb.toString();
	}
	
	private static char switchChar(char c) {	// 미리 값 변경
		if(c == '-') return '|';
		else if(c == '|') return '-';
		else if(c == '/') return '\\';
		else if(c == '\\') return '/';
		else if(c == '^') return '<';
		else if(c == '<') return 'v';
		else if(c == 'v') return '>';
		else if(c == '>') return '^';
		else return c;
	}
}
