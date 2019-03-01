package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 3028번: 창영 마을
 *
 *	@see https://www.acmicpc.net/problem/3028/
 *
 */
public class Boj3028 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] method = br.readLine().toCharArray();
		
		System.out.println(getBall(method));
	}
	
	private static int getBall(char[] arr) {
		boolean[] ball = {true, false, false};
		
		for(char alpha: arr) {
			int current = alpha - 'A';
			
			boolean tmp = ball[current];		// 규칙에 따라 컵 섞기
			int next = (current + 1) % 3;
			
			ball[current] = ball[next];
			ball[next] = tmp;
		}
		
		return ball[0] ? 1 : ball[1] ? 2 : 3;	// 공이 든 위치
	}
}
