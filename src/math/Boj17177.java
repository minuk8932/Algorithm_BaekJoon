package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 17177번: 내접사각형 만들기
 *
 *	@see https://www.acmicpc.net/problem/17177/
 *
 */
public class Boj17177 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		System.out.println(isInnerSquare(a, b, c));
	}
	
	private static int isInnerSquare(int A, int B, int C) {
		int line = -1;
		int[] pow = {A * A, B * B, C * C};
		
		for(int x = 1; x < 101; x++) {			// 공식에 만족하는 선분이 존재하는가? 또한 A가 원의 지름이므로 이보다 길 수 없음
			if(pow[0] * x * x + 2 * A * B * C * x + pow[0] * pow[1] + pow[0] * pow[2] - pow[0] * pow[0] != 0) continue;
			line = x;
			break;
		}
		
		return line;
	}
}
