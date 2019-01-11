package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 2445번: 별찍기 - 8
 *
 *	@see https://www.acmicpc.net/problem/2445/
 *
 */
public class Boj2445 {
	private static final char STAR = '*';
	private static final char SPACE = ' ';
	private static final char NEW_LINE = '\n';
	
	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		staring(N, 0, 1, 1, -1);
		staring(N, 1, -1, 0, 1);
		
		System.out.println(sb);		// 규칙에 따라 별을 찍어보자
	}
	
	private static void staring(int N, int a, int b, int c ,int d) {	// a, b, c, d는 반복 횟수 설정을 위한 상수
		for(int i = 1; i < N + 1; i++) {
			repeat(N * a + b * i, N, STAR, SPACE);
			repeat(N * c + d * i, N, SPACE ,STAR);
			
			sb.append(NEW_LINE);
		}
	}
	
	private static void repeat(int loop, int N, char print1, char print2) {
		for(int j = 0; j < N; j++) {
			if(loop-- > 0) sb.append(print1);
			else sb.append(print2);
		}
	}
}
