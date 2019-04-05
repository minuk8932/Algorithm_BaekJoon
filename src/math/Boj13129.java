package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 13129번: Disposable Cup
 *
 *	@see https://www.acmicpc.net/problem/13129/
 *
 */
public class Boj13129 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		System.out.println(height(A, B, N));
	}
	
	private static StringBuilder height(int a, int b, int n) {
		StringBuilder sb = new StringBuilder();
		a *= n;								// 고정 높이
		
		for(int i = 1; i < n + 1; i++) {
			long res = a + b * i;			// 상쇄 높이에 따라 계산
			sb.append(res).append(SPACE);
		}
		
		return sb;
	}
}
