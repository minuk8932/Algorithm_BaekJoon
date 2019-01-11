package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 2522번: 별찍기 12
 *
 *	@see https://www.acmicpc.net/problem/2522/
 *
 */
public class Boj2522 {
	private static final char STAR = '*';
	private static final char SPACE = ' ';
	private static final char NEW_LINE = '\n';
	
	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int count = 1, add = 1;
		
		for(int i = 0; i < N * 2 - 1; i++) {
			print(N - count, SPACE);
			print(count, STAR);
			
			sb.append(NEW_LINE);
			count += add;
			
			if(count == N) add = -1;	// 갯수 전환
		}
		
		System.out.print(sb);		// 규칙에 따라 별을 찍어보자
	}
	
	private static void print(int leng, char c) {
		for(int j = 0; j < leng; j++) {
			sb.append(c);
		}
	}
}
